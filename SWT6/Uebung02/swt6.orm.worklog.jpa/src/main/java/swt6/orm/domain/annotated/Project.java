package swt6.orm.domain.annotated;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE},
            fetch=FetchType.EAGER,
            optional=false //NOT NULL
    )
    private Employee leader;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(name = "ProjectEmployee",
            joinColumns = {
                    @JoinColumn(name = "project_id")
            }
            , inverseJoinColumns = {
            @JoinColumn(name = "employee_id")
    })
    private Set<Employee> members = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Module> modules = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getMembers() {
        return members;
    }

    public void setMembers(Set<Employee> members) {
        this.members = members;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public void addModule(Module entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Module entry must not be null");
        }
        if (entry.getProject() != null) {
            entry.getProject().getModules().remove(entry);
        }
        this.getModules().add(entry);
        entry.setProject(this);
    }

    public void removeModule(Module entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Module entry must not be null");
        }

        if (entry.getProject() != this) {
            throw new IllegalArgumentException("Can't remove project entry of another module");
        }

        this.getModules().remove(entry);
        entry.setProject(null);
    }

    public void addMember(Employee entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Employee entry must not be null");
        }
        this.getMembers().add(entry);
        entry.getProjects().add(this);
    }

    public void removeMember(Employee entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Employee entry must not be null");
        }

        this.getMembers().remove(entry);
        entry.getProjects().remove(this);
    }

    public Employee getLeader() {
        return leader;
    }

    public void setLeader(Employee leader) {
        this.leader = leader;
    }

    public void attachLeader(Employee employee) {
        if (employee == null) {
            throw new NullPointerException("Employee must not be null");
        }

        if (this.leader != null && this.getLeader().getLeadingProjects().contains(this)) {
            this.getLeader().getLeadingProjects().remove(this);
        }
        this.leader = employee;
        this.leader.getLeadingProjects().add(this);
    }

    public void detachLeader() {
        if (this.leader != null && this.getLeader().getLeadingProjects().contains(this)) {
            this.getLeader().getLeadingProjects().remove(this);
        }
        this.leader = null;
    }

    public String toString() {
        return name;
    }
}
