package swt6.orm.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/***
 * Employee entity
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Address address;

    private Set<LogbookEntry> logbookEntries = new HashSet<>();
    private Set<Project> projects = new HashSet<>();
    private Set<Project> leadingProjects = new HashSet<>();

    public Employee() {
    }

    public Employee(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(String firstName, String lastName, Date dateOfBirth, Address address) {
        this(firstName, lastName, dateOfBirth);
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<LogbookEntry> getLogbookEntries() {
        return logbookEntries;
    }

    private void setLogbookEntries(Set<LogbookEntry> logbookEntries) {
        this.logbookEntries = logbookEntries;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    private void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Project> getLeadingProjects() {
        return leadingProjects;
    }

    private void setLeadingProjects(Set<Project> leadingProjects) {
        this.leadingProjects = leadingProjects;
    }

    public void addLeadingProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project must not be null");
        }

        leadingProjects.add(project);
        project.attachLeader(this);
    }

    public void removeProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project must not be null");
        }

        if (!projects.contains(project)) {
            throw new IllegalArgumentException("Project does not belong to employee");
        }

        projects.remove(project);
        project.getMembers().remove(this);
    }

    public void addLogbookEntry(LogbookEntry entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Logbook enry must not be null");
        }
        if (entry.getEmployee() != null) {
            entry.getEmployee().logbookEntries.remove(entry);
        }
        this.getLogbookEntries().add(entry);
        entry.setEmployee(this);
    }

    public void removeLogbookEntry(LogbookEntry entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Logbook enry must not be null");
        }

        if (entry.getEmployee() != this) {
            throw new IllegalArgumentException("Can't remove logbook entry of another employee");
        }

        this.getLogbookEntries().remove(entry);
        entry.setEmployee(null);
    }

    public void addProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project must not be null");
        }

        projects.add(project);
        project.getMembers().add(this);
    }

    public void detach() {

    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("%d: %s, %s (%4$td.%4$tm.%4$tY)", id, lastName, firstName, dateOfBirth));
        if (address != null)
            sb.append(", " + address);

        return sb.toString();
    }
}