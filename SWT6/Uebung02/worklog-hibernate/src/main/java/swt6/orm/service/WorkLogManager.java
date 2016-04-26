package swt6.orm.service;

import swt6.orm.domain.*;

import java.util.List;

/***
 * WorkLog manager service interface
 */
public interface WorkLogManager {


    /***
     * Saves the given employee
     * @param employee
     * @return
     */
    Long createEmployee(Employee employee);

    /***
     * Fetches the employee with the given id
     * @param employeeId
     * @return
     */

    Employee getEmployee(long employeeId);
    /***
     * Fetches all employees
     * @return
     */
    List<Employee> getAllEmployees();

    /***
     * Fetches all projects of the given employee where
     * he is the project leader
     * @param employeeId
     * @return
     */
    List<Project> getLeadingProjectsForEmployee(long employeeId);

    /***
     * Fetches all logbook entries for the given employee
     * @param employeeId
     * @return
     */
    List<LogbookEntry> getLogbookEntriesForEmployee(long employeeId);

    /***
     * Adds a logbook entry to the given employee
     * @param employee
     * @param entries
     */
    void addLogbookEntry(Employee employee, LogbookEntry... entries);

    /***
     * Creates a new project
     * @param name name of the project
     * @param leader project leader
     * @param members members
     * @param modules project modules
     * @return
     */
    Project createProject(String name, Employee leader, List<Employee> members, List<Module> modules);

    /**
     * Assigns the project to the given employee
     * @param employee
     * @param projects
     * @return
     */
    Employee assignProjectsToEmployee(Employee employee, Project... projects);

    /***
     * Creates a new phase
     * @param name
     * @return
     */
    Phase createPhase(String name);

    /***
     * Returns all logbook entries for a given phase
     * @param phase
     * @return
     */
    List<LogbookEntry> getLogbookEntriesForPhase(Phase phase);


    /***
     * Fetches the project with the given id
     * @param id
     * @return
     */
    Project getProjectById(long id);
}
