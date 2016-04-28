package swt6.orm.domain.test;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;
import swt6.orm.domain.*;
import swt6.orm.service.WorkLogManager;
import swt6.util.DateUtil;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorkLogManagerTest {
    protected WorkLogManager manager;
    protected IDatabaseTester databaseTester;

    protected IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File(getClass().getResource("/testdata.xml").toURI()));
    }

    protected void cleanlyInsertDataset(IDataSet dataSet) throws Exception {
        databaseTester=new JdbcDatabaseTester(
                "org.h2.Driver", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    /***
     * Fetches a database table through DbUnit
     * @param tableName
     * @return
     */
    protected ITable getDatabaseTable(String tableName) throws Exception {
        return databaseTester.getConnection().createDataSet(new String[]{tableName})
                .getTable(tableName);
    }

    /**
     * Creates a new employee and validates its properties
     * e.g. new employee
     */
    @Test
    public void createEmployeeTest() throws Exception {

        Long id =  manager.createEmployee(new PermanentEmployee("Roman", "Lumetsberger",10000,  DateUtil.getDate(1988,01,11),
                new Address("4030","Linz","Hillerstrasse")));
        Assert.assertNotNull(id);

        List<Employee> empls= manager.getAllEmployees();
        Assert.assertEquals(21,empls.size());

        //Check through api
        PermanentEmployee last = (PermanentEmployee) empls.get(empls.size()-1);
        Assert.assertEquals(Long.valueOf(21),last.getId());
        Assert.assertEquals("Roman",last.getFirstName());
        Assert.assertEquals("Lumetsberger",last.getLastName());
        Assert.assertEquals(10000,last.getSalary(),1);

        //Check through DBUnit
        //ID 1 is not used in test data, therefore it is used on insert
        ITable table = getDatabaseTable("employee");
        Assert.assertEquals(21,table.getRowCount());
        Assert.assertEquals("Roman",table.getValue(20,"firstname"));
        Assert.assertEquals("Lumetsberger",table.getValue(20,"lastname"));


    }

    /**
     * Fetches a already existing employee and validates the properties
     * including the work logs
     * Useful for generating a reports for the given employee
     */
    @Test
    public void getEmployeeTest() {
        Employee e = manager.getEmployee(11);
        Assert.assertNotNull(e);
        Assert.assertEquals("Elizabeth",e.getFirstName());
        Assert.assertEquals("Bell",e.getLastName());
        Assert.assertEquals(DateUtil.getDate(1992,10,14),e.getDateOfBirth());
        Assert.assertEquals("Lincuo",e.getAddress().getCity());
        Assert.assertEquals("8411 Victoria Parkway",e.getAddress().getStreet());
        Assert.assertEquals("4030",e.getAddress().getZipCode());
        Assert.assertEquals(1611.76,((PermanentEmployee)e).getSalary(),0.001);
    }

    /***
     * Checks the leading projects for a given employee
     */
    @Test
    public void getLeadingProjectsTest() {
        List<Project> projects = manager.getLeadingProjectsForEmployee(11);
        Assert.assertEquals(2,projects.size());
        Assert.assertEquals("Worklog Manager",projects.get(0).getName());
        Assert.assertEquals("TV-App",projects.get(1).getName());

    }

    /***
     * Fetches all logbook entries for a given employee
     * Also validates eager fetched properties module and phase
     * Useful for generating a work-log-report
     */
    @Test
    public void getLogbookEntriesForEmployeeTest() {
        List<LogbookEntry> entries = manager.getLogbookEntriesForEmployee(11);
        Assert.assertEquals(6,entries.size());
        Assert.assertEquals("GUI",entries.stream().findFirst().get().getModule().getName());
        Assert.assertEquals("Analyse",entries.stream().findFirst().get().getPhase().getName());
    }

    /***
     * Tests that a new logbook entry can be added to an employee
     * This test includes creating a new module and phase
     */
    @Test
    public void addLogbookEntryTest() throws Exception {
        Project project = manager.getProjectById(1);

        LogbookEntry entry = new LogbookEntry("Testing",DateUtil.getTime(23,00), DateUtil.getTime(23,50));
        Module mod = new Module("NEW MODULE");
        mod.attachProject(project);

        entry.attachModule(mod);
        entry.attachPhase(new Phase("NEW PHASE"));
        manager.addLogbookEntry(manager.getEmployee(11),entry);
        Assert.assertEquals(7,manager.getLogbookEntriesForEmployee(11).size());

        //Check through DBUnit
        ITable table = getDatabaseTable("logbookentry");
        Assert.assertEquals(101,table.getRowCount());
        Assert.assertEquals("Testing",table.getValue(100,"activity"));


        //Phase
        table = getDatabaseTable("phase");
        Assert.assertEquals(4,table.getRowCount());
        Assert.assertEquals("NEW PHASE",table.getValue(3,"name"));

        //module
        table = getDatabaseTable("module");
        Assert.assertEquals(6,table.getRowCount());
        Assert.assertEquals("NEW MODULE",table.getValue(5,"name"));



    }

    /***
     * Creates a new project with some modules and checks that
     * it is persisted correctly
     */
    @Test
    public void createProjectTest() throws Exception {
        Employee empl = manager.getEmployee(1);
        List<Module> modules = new ArrayList<>();
        modules.add(new Module("MOD1"));
        modules.add(new Module("MOD2"));
        modules.add(new Module("MOD3"));

        manager.createProject("New Project",empl,null,modules);
        //check through api
        Project newProject =  manager.getProjectById(5);
        Assert.assertNotNull(newProject);
        Assert.assertEquals("New Project",newProject.getName());

        Assert.assertArrayEquals(modules.stream().map(x -> x.getName()).sorted().toArray(),
                newProject.getModules().stream().map(x -> x.getName()).sorted().toArray());

        //Check through DBUnit
        ITable table = getDatabaseTable("project");
        Assert.assertEquals(5,table.getRowCount());
        Assert.assertEquals("New Project",table.getValue(4,"name"));
        Assert.assertEquals(BigInteger.valueOf(1),table.getValue(4,"leader_id"));

        table = getDatabaseTable("module");
        Assert.assertEquals(8,table.getRowCount());
        Assert.assertEquals("MOD",table.getValue(7,"name")
                .toString().substring(0,3)); //only check MOD because ordering not deterministic
        Assert.assertEquals(BigInteger.valueOf(5),table.getValue(7,"project_id"));
        Assert.assertEquals("MOD",table.getValue(6,"name")
                .toString().substring(0,3)); //only check MOD because ordering not deterministic
        Assert.assertEquals(BigInteger.valueOf(5),table.getValue(6,"project_id"));
        Assert.assertEquals("MOD",table.getValue(5,"name")
                .toString().substring(0,3)); //only check MOD because ordering not deterministic
        Assert.assertEquals(BigInteger.valueOf(5),table.getValue(5,"project_id"));

    }


    /***
     * Assigns projects to an employee
     * Useful when a new employee starts working on projects
     */
    @Test
    public void assignProjectsToEmployeeTest() throws Exception {

        Employee empl = manager.getEmployee(20);
        Assert.assertNotNull(empl);
        Assert.assertEquals(0,empl.getProjects().size());

        Project proj1= manager.getProjectById(2);
        Project proj2= manager.getProjectById(3);

        empl = manager.assignProjectsToEmployee(empl,proj1,proj2);

        Assert.assertEquals(2,empl.getProjects().size());
        Assert.assertArrayEquals(empl.getProjects().stream().map(x -> x.getName()).sorted().toArray(),
                new String[]{proj2.getName(),proj1.getName()});

        //Check insert with DBUnit
        ITable actualTable = databaseTester.getConnection()
                .createQueryTable("ProjectEmployee","select * from ProjectEmployee where employee_id=20");
        Assert.assertEquals(2,actualTable.getRowCount());
        Assert.assertEquals(BigInteger.valueOf(2),actualTable.getValue(0,"project_id"));
        Assert.assertEquals(BigInteger.valueOf(20),actualTable.getValue(0,"employee_id"));
        Assert.assertEquals(BigInteger.valueOf(3),actualTable.getValue(1,"project_id"));
        Assert.assertEquals(BigInteger.valueOf(20),actualTable.getValue(1,"employee_id"));

    }

    /***
     * Creates a new project phase
     * Does not assign it to a project
     */
    @Test
    public void createPhaseTest() throws Exception {
        Phase phase = manager.createPhase("new phase");
        Assert.assertEquals(4,(long)phase.getId());

        //Check through dbunit
        ITable table = getDatabaseTable("phase");
        Assert.assertEquals(4,table.getRowCount());
        Assert.assertEquals("new phase",table.getValue(3,"name"));
    }

    /**
     * Fetches all logbook entries for a given phase
     * useful for reports
     */
    @Test
    public void getLogbookEntriesForPhaseTest() {
        List<LogbookEntry> entries= manager.getLogbookEntriesForPhase("Testen");
        Assert.assertNotNull(entries);
        Assert.assertEquals(37,entries.size());

        //check one employee
        Assert.assertTrue(entries.stream().anyMatch(x -> x.getEmployee().getId()==19));

    }

    /**
     * Fetches a given project including members and modules
     * Useful for project overview
     */
    @Test
    public void getProjectTest() {
        Project project =  manager.getProjectById(1);
        Assert.assertNotNull(project);
        Assert.assertEquals("Worklog Manager",project.getName());
        Assert.assertEquals("Elizabeth",project.getLeader().getFirstName());
        Assert.assertEquals(4,project.getMembers().size());
        Assert.assertEquals(2,project.getModules().size());
    }
}
