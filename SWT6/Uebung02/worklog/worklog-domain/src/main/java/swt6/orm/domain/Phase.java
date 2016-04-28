package swt6.orm.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Phase {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "phase", cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<LogbookEntry> logbookEntries = new HashSet<>();

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
