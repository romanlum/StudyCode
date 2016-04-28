package swt6.orm.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class LogbookEntry implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final DateFormat fmt = DateFormat.getDateTimeInstance();

  @Id
  @GeneratedValue
  private Long id;

  private String activity;

  @Temporal(TemporalType.TIME)
  private Date startTime;

  @Temporal(TemporalType.TIME)
  private Date endTime;

  @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
		  fetch=FetchType.EAGER, 
		  optional=false //NOT NULL
		  )
  private Employee employee;

  @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE},
          fetch=FetchType.EAGER,
          optional=false //NOT NULL
  )
  private Module module;

  @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE},
          fetch=FetchType.EAGER,
          optional=false //NOT NULL
  )
  private Phase phase;

  public LogbookEntry() {
  }

  public LogbookEntry(String activity, Date start, Date end) {
    this.activity = activity;
    startTime = start;
    endTime = end;
  }

  public Long getId() {
    return id;
  }

  @SuppressWarnings("unused")
  private void setId(Long id) {
    this.id = id;
  }

  public String getActivity() {
    return activity;
  }

  public void setActivity(String activity) {
    this.activity = activity;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public void attachEmployee(Employee employee) {
    // If this entry is already linked to some employee,
    // remove this link.
    if (this.employee != null) {
      this.employee.getLogbookEntries().remove(this);
    }

    // Add a bidirection link between this entry and employee.
    if (employee != null) {
      employee.getLogbookEntries().add(this);
    }
    this.employee = employee;
  }

  public void detachEmployee() {
    if (employee != null) {
      employee.getLogbookEntries().remove(this);
    }

    employee = null;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date start) {
    startTime = start;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date end) {
    endTime = end;
  }

  public Module getModule() {
    return module;
  }

  public void setModule(Module module) {
    this.module = module;
  }

  public Phase getPhase() {
    return phase;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  public void attachModule(Module module) {
    if(module == null){
      throw new IllegalArgumentException("Module entry must not be null");
    }
    if(this.module != null) {
      this.getModule().getLogbookEntries().remove(this);
    }
    this.module = module;
    this.module.getLogbookEntries().add(this);
  }

  public void detachModule() {
    if(this.module != null) {
      this.getModule().getLogbookEntries().remove(this);
    }
    this.module = null;
  }

  public void attachPhase(Phase phase) {
    if(phase == null){
      throw new IllegalArgumentException("Phase entry must not be null");
    }
    if(this.phase != null){
      this.getPhase().getLogbookEntries().remove(this);
    }
    phase.getLogbookEntries().add(this);
    this.phase = phase;
  }

  public void detachPhase() {
    if(this.phase != null) {
      this.getPhase().getLogbookEntries().remove(this);
    }
    this.phase = null;
  }

  @Override
  public String toString() {
    return activity + ": " + fmt.format(startTime) + " - " + fmt.format(endTime) + " (" + getEmployee().getLastName() + ")";

  }
}
