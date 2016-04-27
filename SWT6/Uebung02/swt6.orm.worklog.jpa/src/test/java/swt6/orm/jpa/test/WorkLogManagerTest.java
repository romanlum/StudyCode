package swt6.orm.jpa.test;

import org.apache.poi.POIDocument;
import org.apache.xmlbeans.impl.piccolo.xml.EntityManager;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import swt6.orm.domain.annotated.*;
import swt6.orm.jpa.JPAUtil;
import swt6.orm.jpa.JPAWorkLogManager;
import swt6.orm.service.WorkLogManager;
import swt6.util.DateUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WorkLogManagerTest {

    WorkLogManager manager;
    IDatabaseTester databaseTester;

    private IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File("data.xml"));
    }

    private void cleanlyInsertDataset(IDataSet dataSet) throws Exception {
        databaseTester=new JdbcDatabaseTester(
                "org.h2.Driver", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Before
    public void setUp() throws Exception {
        manager = new JPAWorkLogManager();
        JPAUtil.getEntityManagerFactory();
        IDataSet dataSet = readDataSet();
        cleanlyInsertDataset(dataSet);
    }

    @After
    public void tearDown() throws Exception {
        JPAUtil.closeEntityManager();
        JPAUtil.closeEntityManagerFactory();
    }

    /**
     * Creates a new employee and validates its properties
     */
    @Test
    public void createEmployeeTest() {

        Long id =  manager.createEmployee(new PermanentEmployee("Roman", "Lumetsberger",10000,  DateUtil.getDate(1988,01,11),
                new Address("4030","Linz","Hillerstrasse")));
        Assert.assertNotNull(id);

        List<Employee> empls= manager.getAllEmployees();
        Assert.assertEquals(21,empls.size());

        PermanentEmployee last = (PermanentEmployee) empls.get(empls.size()-1);
        Assert.assertEquals("Roman",last.getFirstName());
        Assert.assertEquals("Lumetsberger",last.getLastName());
        Assert.assertEquals(10000,last.getSalary(),1);
    }

    /**
     * Fetches a already existing employee and validates the properties
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
        Assert.assertEquals(6,e.getLogbookEntries().size());
    }

    /***
     * Checks the leading projects for a given employee
     */
    @Test
    public void getLeadingProjectsTest() {
        List<Project> projects = manager.getLeadingProjectsForEmployee(11);
        Assert.assertEquals(2,projects.size());
    }

    /***
     * Fetches the logbook entries for a given employee
     * Validates eager fetched properties
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
     */
    @Test
    public void addLogbookEntryTest(){
        Project project = manager.getProjectById(1);

        LogbookEntry entry = new LogbookEntry("Testing",DateUtil.getTime(23,00), DateUtil.getTime(23,50));
        Module mod = new Module("NEW MODULE");
        mod.attachProject(project);

        entry.attachModule(mod);
        entry.attachPhase(new Phase("NEW PHASE"));
        manager.addLogbookEntry(manager.getEmployee(11),entry);
        Assert.assertEquals(7,manager.getEmployee(11).getLogbookEntries().size());
    }

    /***
     * Creates a new project with some modules and checks that
     * it is persisted correctly
     */
    @Test
    public void createProjectTest() {
        Employee empl = manager.getEmployee(99);
        List<Module> modules = new ArrayList<>();
        modules.add(new Module("MOD1"));
        modules.add(new Module("MOD2"));
        modules.add(new Module("MOD3"));

        manager.createProject("New Project",empl,null,modules);
        Project newProject =  manager.getProjectById(5);
        Assert.assertNotNull(newProject);
        Assert.assertEquals("New Project",newProject.getName());
        //Check modules
        Assert.assertArrayEquals(modules.stream().map(x -> x.getName()).sorted().toArray(),
                newProject.getModules().stream().map(x -> x.getName()).sorted().toArray());
    }


    /***
     * Assigns projects to an employee and checks
     * it afterwards
     */
    @Test
    public void assignProjectsToEmployeeTest() {

        Employee empl = manager.getEmployee(20);
        Assert.assertNotNull(empl);
        Assert.assertEquals(0,empl.getProjects().size());

        Project proj1= manager.getProjectById(2);
        Project proj2= manager.getProjectById(3);

        empl = manager.assignProjectsToEmployee(empl,proj1,proj2);

        Assert.assertEquals(2,empl.getProjects().size());
        Assert.assertArrayEquals(empl.getProjects().stream().map(x -> x.getName()).sorted().toArray(),
                new String[]{proj2.getName(),proj1.getName()});

        empl = manager.getEmployee(20);
        for (Project p : empl.getProjects()){
            System.out.println(p);
        }
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
        IDataSet actual = databaseTester.getConnection().createDataSet(new String[]{"phase"});
        ITable table = actual.getTable("phase");
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
     * Fetches a given project
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
