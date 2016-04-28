package swt6.orm.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import swt6.orm.domain.*;
import swt6.orm.service.WorkLogManager;

import java.util.List;
import java.util.stream.Collectors;

/***
 * Hibernate WorkLogManager implementation
 */
public class HibernateWorkLogManager implements WorkLogManager {

    @Override
    public Long createEmployee(Employee employee) {

        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(employee);
        tx.commit();
        return employee.getId();

    }

    @Override
    public Employee getEmployee(long employeeId) {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Employee empl = (Employee) session.get(Employee.class,employeeId);
        tx.commit();
        return empl;

    }

    @Override
    public List<Employee> getAllEmployees() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Employee> employees = session.createQuery("select e from Employee e").list();
        tx.commit();
        return employees;
    }

    @Override
    public List<Project> getLeadingProjectsForEmployee(long employeeId) {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("select p from Project p where p.leader.id=:employeeId");
        query.setLong("employeeId",employeeId);
        List<Project> projects = query.list();

        tx.commit();
        return projects;

    }

    @Override
    public List<LogbookEntry> getLogbookEntriesForEmployee(long employeeId) {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select le from LogbookEntry le where employee.id=:employeeId");
        query.setLong("employeeId", employeeId);
        List<LogbookEntry> entries = query.list();
        tx.commit();
        return entries;
    }

    @Override
    public void addLogbookEntry(Employee employee, LogbookEntry... entries) {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        //attach
        employee = (Employee) session.merge(employee);
        for (LogbookEntry entry:entries) {
            entry.setEmployee(employee);
            session.saveOrUpdate(entry);
        }

        tx.commit();

    }

    @Override
    public Project createProject(String name, Employee leader, List<Employee> members, List<Module> modules) {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Project project = new Project(name,leader);
        session.saveOrUpdate(project);
        if(members != null) {
            members.stream().forEach(x -> project.addMember(x));
        }
        if(modules != null) {
            modules.stream().forEach(x -> project.addModule(x));
        }
        tx.commit();
        return project;

    }

    @Override
    public Employee assignProjectsToEmployee(Employee employee, Project... projects) {
        Session session = HibernateUtil.getCurrentSession();

        Transaction tx = session.beginTransaction();
        Employee mergedEmployee = (Employee) session.merge(employee);

        for (Project project : projects) {
            mergedEmployee.addProject((Project) session.merge(project));
        }
        tx.commit();
        return mergedEmployee;
    }

    @Override
    public Phase createPhase(String name) {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Phase phase = new Phase(name);
        session.saveOrUpdate(phase);
        tx.commit();
        return phase;
    }

    @Override
    public List<LogbookEntry> getLogbookEntriesForPhase(String phase) {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select le from LogbookEntry le where le.phase.name = :phase");
        query.setString("phase", phase);
        List<LogbookEntry> entries = query.list();
        tx.commit();
        return entries;
    }


    @Override
    public Project getProjectById(long id) {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Project project = (Project) session.get(Project.class,id);

        tx.commit();
        return project;
    }


}
