import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.util.DateTimeUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import swt6.orm.domain.*;
import swt6.orm.hibernate.HibernateUtil;
import swt6.orm.hibernate.HibernateWorkLogManagerImpl;
import swt6.orm.service.WorkLogManager;
import swt6.util.DateUtil;

import java.io.File;
import java.util.Date;
import java.util.List;

public class WorkLogManagerTest {

    WorkLogManager manager;
    IDatabaseTester databaseTester;

    private IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File(getClass().getResource("testdata.xml").toURI()));
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
        manager = new HibernateWorkLogManagerImpl();
        HibernateUtil.getSessionFactory();
        IDataSet dataSet = readDataSet();
        cleanlyInsertDataset(dataSet);
    }

    @After
    public void tearDown() throws Exception {
        HibernateUtil.closeSession();
        HibernateUtil.closeSessionFactory();
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
        LogbookEntry entry = new LogbookEntry("Testing",DateUtil.getTime(23,00), DateUtil.getTime(23,50));
        entry.attachModule(new Module("NEW MODULE"));
        entry.attachPhase(new Phase("NEW PHASE"));
        manager.addLogbookEntry(manager.getEmployee(11),entry);
        Assert.assertEquals(7,manager.getEmployee(11).getLogbookEntries().size());

    }

}
