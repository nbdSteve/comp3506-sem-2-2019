import java.util.ArrayList;
import java.util.List;

/**
 * This class represent how a Tree data structure can be used to represent
 * a business's employees. This data structure is appropriate and useful
 * because supervisors can have the employees that they supervise added
 * as children to their tree - this makes it easy to see who is supervising
 * who at any given time.
 *
 * Furthermore, adding an employee into a certain level is easy since each
 * employee has a supervisor (expect for the franchisee). This tree has been
 * modelled off of where I work (Domino's).
 */
public class EmployeeTree {
    // store an instance of the employee tree
    private StandardTree<Integer> employeeTree;

    /**
     * Initialize the employee tree, and set the franchisee
     *
     * @param franchiseeId int, the employee ID of the franchisee
     */
    public EmployeeTree(int franchiseeId) {
        employeeTree = new StandardTree<>(franchiseeId);
    }

    /**
     * Returns the franchisee's employee id
     *
     * @return int
     */
    public int getFranchiseeId() {
        return employeeTree.getRoot();
    }

    /**
     * Returns the StandardTree instance for the employee tree
     *
     * @return StandardTree<Integer>
     */
    public StandardTree<Integer> getEmployeeTree() {
        return employeeTree;
    }

    /**
     * Adds the new employee tree as a child of the specified supervisor
     *
     * @param supervisorId int, the employee ID of the supervisor
     * @param empId        int, the employee ID of the new employee
     * @throws SupervisorDoesNotExistException
     */
    public void addEmployee(int supervisorId, int empId) throws
            SupervisorDoesNotExistException {
        if (!employeeTree.contains(supervisorId)) {
            throw new SupervisorDoesNotExistException();
        }
        StandardTree<Integer> supervisorTree = getEmpTree(supervisorId, employeeTree);
        supervisorTree.addChild(new StandardTree<>(empId));
    }

    /**
     * Removes the specified employee tree from the supervisor node,
     * this will cascade delete any trees attached to the employee being deleted
     *
     * @param supervisorId int, the employee ID of the supervisor
     * @param empId        int, the employee ID of the employee to remove
     * @throws SupervisorDoesNotExistException
     * @throws EmployeeDoesNotExistException
     */
    public void removeEmployee(int supervisorId, int empId) throws
            SupervisorDoesNotExistException,
            EmployeeDoesNotExistException {
        if (!employeeTree.contains(empId)) {
            throw new EmployeeDoesNotExistException();
        }
        if (!employeeTree.contains(supervisorId)) {
            throw new SupervisorDoesNotExistException();
        }
        StandardTree<Integer> supervisorTree = getEmpTree(supervisorId, employeeTree);
        supervisorTree.removeChild(getEmpTree(empId, employeeTree));
    }

    /**
     * Returns the list of employee ID's that the specified supervisor supervises
     *
     * @param supervisorId int, the supervisor's employee ID
     * @return List<Integer>
     * @throws SupervisorDoesNotExistException
     */
    public List<Integer> getEmployeeList(int supervisorId) throws
            SupervisorDoesNotExistException {
        if (employeeTree.contains(supervisorId)) {
            List<Integer> employeeIds = new ArrayList<>();
            StandardTree<Integer> supervisorTree = getEmpTree(supervisorId, employeeTree);
            for (Tree<Integer> employeeTree : supervisorTree.getChildren()) {
                employeeIds.add(employeeTree.getRoot());
            }
            return employeeIds;
        } else {
            throw new SupervisorDoesNotExistException();
        }
    }

    /**
     * Returns the StandardTree instance for the specified employee, this methods calls
     * itself recursively to find the employee's tree
     *
     * @param empId    int, the id of the employee
     * @param employeeTree StandardTree<Integer>, the main instance of the employee tree
     * @return StandardTree<Integer>
     */
    public StandardTree<Integer> getEmpTree(int empId, StandardTree<Integer> employeeTree) {
        if (empId == employeeTree.getRoot()) {
            return employeeTree;
        }
        for (int i = 0; i < employeeTree.getChildren().size(); i++) {
            if (employeeTree.getChildren().get(i).getRoot() == empId) {
                return (StandardTree) employeeTree.getChildren().get(i);
            }
            getEmpTree(empId, employeeTree);
        }
        return null;
    }

    /**
     * Exception thrown when the employee does not exist in the tree
     */
    static class EmployeeDoesNotExistException extends Exception {
    }

    /**
     * Exception thrown when the supervisor does not exist in the tree
     */
    static class SupervisorDoesNotExistException extends Exception {
    }
}