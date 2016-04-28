package swt6.orm.jpa.test;

import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import swt6.orm.domain.test.AbstractWorkLogManagerTest;
import swt6.orm.jpa.JPAUtil;
import swt6.orm.jpa.JPAWorkLogManager;

public class WorkLogManagerTest extends AbstractWorkLogManagerTest {


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

}
