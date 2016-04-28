package swt6.orm.jpa;

import swt6.orm.domain.*;
import swt6.orm.service.WorkLogManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 27.04.2016.
 */
public class JPAWorkLogManager implements WorkLogManager {
    @Override
    public Long createEmployee(Employee employee) {
        EntityManager em = JPAUtil.getTransactedEntityManager();
        em.persist(employee);
        JPAUtil.commit();
        return employee.getId();
    }

    @Override
    public Employee getEmployee(long employeeId) {
        EntityManager em = JPAUtil.getTransactedEntityManager();
        Employee employee = em.find(Employee.class,employeeId);
        JPAUtil.commit();
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        EntityManager em = JPAUtil.getTransactedEntityManager();
        List<Employee> empList = em.createQuery("select e from Employee e",Employee.class).getResultList();
        JPAUtil.commit();
        return empList;
    }

    @Override
    public List<Project> getLeadingProjectsForEmployee(long employeeId) {
        EntityManager em = JPAUtil.getTransactedEntityManager();
        TypedQuery<Project> projQuery = em.createQuery("select p from Project p where p.leader.id=:id",Project.class);
        projQuery.setParameter("id",employeeId);
        List<Project> result = projQuery.getResultList();
        JPAUtil.commit();
        return result;
    }

    @Override
    public List<LogbookEntry> getLogbookEntriesForEmployee(long employeeId) {
        EntityManager em = JPAUtil.getTransactedEntityManager();
        TypedQuery<LogbookEntry> projQuery = em.createQuery("select l from LogbookEntry l where l.employee.id=:id",LogbookEntry.class);
        projQuery.setParameter("id",employeeId);
        List<LogbookEntry> result = projQuery.getResultList();
        JPAUtil.commit();
        return result;
    }

    @Override
    public void addLogbookEntry(Employee employee, LogbookEntry... entries) {
        EntityManager em = JPAUtil.getTransactedEntityManager();
        //attach
        employee = em.merge(employee);
        for (LogbookEntry entry:entries) {
            entry.setEmployee(employee);
            em.merge(entry);
        }
        JPAUtil.commit();
    }

    @Override
    public Project createProject(String name, Employee leader, List<Employee> members, List<Module> modules) {
        EntityManager em = JPAUtil.getTransactedEntityManager();
        //attach
        Project project = new Project(name);
        leader = em.merge(leader);
        project.attachLeader(leader);
        if(members != null) {
            for (Employee empl:members) {
                project.addMember(empl);
            }
        }

        if(modules != null) {
            for (Module mod:modules) {
                project.addModule(mod);
            }
        }

        em.persist(project);
        JPAUtil.commit();
        return project;
    }

    @Override
    public Employee assignProjectsToEmployee(Employee employee, Project... projects) {
        EntityManager em = JPAUtil.getTransactedEntityManager();
        employee = em.merge(employee);
        List<Project> mergedProjects = new ArrayList<>();
        for (Project p : projects) {
            mergedProjects.add(em.merge(p));
        }

        for(Project p : mergedProjects) {
            p.addMember(employee);
        }
        JPAUtil.commit();
        return employee;
    }

    @Override
    public Phase createPhase(String name) {
        EntityManager em = JPAUtil.getTransactedEntityManager();
        Phase phase = new Phase(name);
        em.persist(phase);
        JPAUtil.commit();
        return phase;
    }

    @Override
    public List<LogbookEntry> getLogbookEntriesForPhase(String phase) {
        EntityManager em = JPAUtil.getTransactedEntityManager();
        TypedQuery<LogbookEntry> projQuery = em.createQuery("select l from LogbookEntry l where l.phase.name=:name",LogbookEntry.class);
        projQuery.setParameter("name",phase);
        List<LogbookEntry> result = projQuery.getResultList();
        JPAUtil.commit();
        return result;
    }

    @Override
    public Project getProjectById(long id) {
        EntityManager em = JPAUtil.getTransactedEntityManager();
        TypedQuery<Project> projectQuery = em.createQuery("select p from Project p where p.id=:id",Project.class);
        projectQuery.setParameter("id",id);
        Project project = projectQuery.getSingleResult();
        JPAUtil.commit();
        return project;
    }
}
