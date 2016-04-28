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
import swt6.orm.domain.*;
import swt6.orm.domain.test.AbstractWorkLogManagerTest;
import swt6.orm.hibernate.HibernateUtil;
import swt6.orm.hibernate.HibernateWorkLogManager;
import swt6.orm.service.WorkLogManager;
import swt6.util.DateUtil;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class WorkLogManagerTest extends AbstractWorkLogManagerTest {


    @Before
    public void setUp() throws Exception {
        manager = new HibernateWorkLogManager();
        HibernateUtil.getSessionFactory();
        IDataSet dataSet = readDataSet();
        cleanlyInsertDataset(dataSet);
    }

    @After
    public void tearDown() throws Exception {
        HibernateUtil.closeSession();
        HibernateUtil.closeSessionFactory();
    }






}
