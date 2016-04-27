package swt6.orm.domain.annotated;

import swt6.orm.domain.annotated.Project;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/***
 * Module entity
 */
@Entity
public class Module {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(cascade={CascadeType.PERSIST},
            fetch=FetchType.EAGER,
            optional=false //NOT NULL
    )
    private Project project;


    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<LogbookEntry> logbookEntries = new HashSet<>();

    public Module(String name) {
        this.name = name;
    }

    public Module() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<LogbookEntry> getLogbookEntries() {
        return logbookEntries;
    }

    private void setLogbookEntries(Set<LogbookEntry> logbookEntries) {
        this.logbookEntries = logbookEntries;
    }

    public void addLogbookEntry(LogbookEntry entry){
        if (entry == null) {
            throw new IllegalArgumentException("Logbook entry must not be null");
        }
        if (entry.getModule() != null) {
            entry.getModule().getLogbookEntries().remove(entry);
        }
        this.getLogbookEntries().add(entry);
        entry.setModule(this);
    }

    public void removeLogbookEntry(LogbookEntry entry){
        if (entry == null) {
            throw new IllegalArgumentException("Logbook entry must not be null");
        }
        if (entry.getModule() != null) {
            entry.getModule().getLogbookEntries().remove(entry);
        }
        this.getLogbookEntries().add(entry);
        entry.setModule(this);
    }

    public void attachProject(Project proj) {
        if(proj == null){
            throw new IllegalArgumentException("project entry must not be null");
        }

        if(this.project != null) {
            this.project.getModules().remove(this);
        }

        this.project = proj;
        this.project.getModules().add(this);
    }




}
