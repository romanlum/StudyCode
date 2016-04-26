package swt6.orm.domain;

import java.util.HashSet;
import java.util.Set;

/***
 * Module entity
 */
public class Module {
    private Long id;
    private String name;
    private Project project;
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




}
