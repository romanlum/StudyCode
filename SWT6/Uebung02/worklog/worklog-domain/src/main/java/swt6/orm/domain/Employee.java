package swt6.orm.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "employee_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("E")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "zipCode", column = @Column(name = "zipCode")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "street", column = @Column(name = "street"))})
    private Address address;

    /***
     * Logbook entries are lazy fetched, because there will be a lot of them
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<LogbookEntry> logbookEntries = new HashSet<>();

    @ManyToMany(mappedBy = "members", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "leader", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Project> leadingProjects = new HashSet<>();

    // classes persisted by Hibernate must have a default constructor
    // (newInstance of reflection API)
    public Employee() {
    }

    public Employee(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(String firstName, String lastName, Date dateOfBirth, Address address) {
        this(firstName, lastName, dateOfBirth);
        setAddress(address);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Project> getLeadingProjects() {
        return leadingProjects;
    }

    public void setLeadingProjects(Set<Project> leadingProjects) {
        this.leadingProjects = leadingProjects;
    }

    @SuppressWarnings("unused")
    private void setLogbookEntries(Set<LogbookEntry> logbookEntries) {
        this.logbookEntries = logbookEntries;
    }

    public void addLogbookEntry(LogbookEntry entry) {
        if (entry.getEmployee() != null) {
            entry.getEmployee().logbookEntries.remove(entry);
        }

        logbookEntries.add(entry);
        entry.setEmployee(this);
    }

    public void removeLogbookEntry(LogbookEntry entry) {
        logbookEntries.remove(entry);
    }

    public Set<Project> getProjects() {
        return projects;
    }

    @SuppressWarnings("unused")
    private void setProjects(Set<Project> projects) {
        this.projects = projects;
    }


    public void addProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project must not be null");
        }

        projects.add(project);
        project.getMembers().add(this);
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

    public void addLeadingProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project must not be null");
        }

        if (project.getLeader() != null) {
            project.getLeader().getLeadingProjects().remove(project);
        }

        this.getLeadingProjects().add(project);
        project.setLeader(this);
    }

    public void removeLeadingProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project must not be null");
        }

        if (project.getLeader() != this) {
            throw new IllegalArgumentException("Can't remove leading project entry of another employee");
        }

        this.getLeadingProjects().remove(project);
        project.setLeader(null);
    }

    public String toString() {
        DateFormat fmt = DateFormat.getDateInstance();
        StringBuffer sb = new StringBuffer();
        sb.append(id + ": " + lastName + ", " + firstName + " (" + fmt.format(dateOfBirth.getTime()) + ")");
        if (address != null) {
            sb.append(", " + address);
        }

        return sb.toString();
    }
}