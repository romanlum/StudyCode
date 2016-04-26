package swt6.orm.domain;

import java.text.DateFormat;
import java.util.Date;

public class TemporaryEmployee extends Employee {
  private static final long serialVersionUID = 1L;
  private static final DateFormat fmt = DateFormat.getDateInstance();
  
  private String renter;
  private Double hourlyRate;
  private Date   startDate;
  private Date   endDate;
   
  public TemporaryEmployee() {
  }
  
  public TemporaryEmployee(String firstName, String lastName, Date dateOfBirth) {
    super(firstName, lastName, dateOfBirth);
  }
  
  public TemporaryEmployee(String firstName, String lastName, Date dateOfBirth, Address address) {
    super(firstName, lastName, dateOfBirth, address);
  }

  public Date getEndDate() {
    return endDate;
  }
  
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  
  public Double getHourlyRate() {
    return hourlyRate;
  }
  
  public void setHourlyRate(Double hourlyRate) {
    this.hourlyRate = hourlyRate;
  }
  
  public String getRenter() {
    return renter;
  }
  
  public void setRenter(String renter) {
    this.renter = renter;
  }
  
  public Date getStartDate() {
    return startDate;
  }
  
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer(super.toString());
    sb.append(", hourlyRate=" + hourlyRate);
    sb.append(", renter=" + renter);
    sb.append(", startDate=" + fmt.format(startDate));
    sb.append(", endDate=" + fmt.format(endDate));
    
    return sb.toString();
  }
}
