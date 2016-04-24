package swt6.orm.domain;

import java.util.Set;

public class Phase {

    private Long id;
    private String name;
    private Set<LogbookEntry> logbookEntries;

    public Phase(String name) {
        this.name = name;
    }

    public Phase() {
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

    public Set<LogbookEntry> getLogbookEntries() {
        return logbookEntries;
    }

    public void setLogbookEntries(Set<LogbookEntry> logbookEntries) {
        this.logbookEntries = logbookEntries;
    }

    public void addLogbookEntry(LogbookEntry entry){
        if (entry == null) {
            throw new IllegalArgumentException("Logbook entry must not be null");
        }
        if (entry.getPhase() != null) {
            entry.getPhase().getLogbookEntries().remove(entry);
        }
        this.getLogbookEntries().add(entry);
        entry.setPhase(this);
    }

    public void removeLogbookEntry(LogbookEntry entry){
        if (entry == null) {
            throw new IllegalArgumentException("Logbook entry must not be null");
        }
        if (entry.getPhase() != null) {
            entry.getPhase().getLogbookEntries().remove(entry);
        }
        this.getLogbookEntries().add(entry);
        entry.setPhase(this);
    }
}
