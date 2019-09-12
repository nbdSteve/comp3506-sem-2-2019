/**
 *
 */
public class RoleTree {
    // store an instance of the role tree
    private StandardTree<Integer> roleTree;

    /**
     * Initialize the role tree, and set the franchisee
     *
     * @param franchiseeId int, the employee ID of the franchisee
     */
    public RoleTree(int franchiseeId) {
        roleTree = new StandardTree<>(franchiseeId);
    }

    /**
     * Returns the franchisee's employee id
     *
     * @return int
     */
    public int getFranchiseeId() {
        return roleTree.getRoot();
    }

    /**
     * Returns the StandardTree instance for the role tree
     *
     * @return StandardTree<Integer>
     */
    public StandardTree<Integer> getRoleTree() {
        return roleTree;
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
        if (!roleTree.contains(supervisorId)) {
            throw new SupervisorDoesNotExistException();
        }
        StandardTree<Integer> supervisorTree = getEmpTree(supervisorId, roleTree);
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
        if (!roleTree.contains(empId)) {
            throw new EmployeeDoesNotExistException();
        }
        if (!roleTree.contains(supervisorId)) {
            throw new SupervisorDoesNotExistException();
        }
        StandardTree<Integer> supervisorTree = getEmpTree(supervisorId, roleTree);
        supervisorTree.removeChild(getEmpTree(empId, roleTree));
    }

    /**
     *
     * @param empId
     * @param roleTree
     * @return
     */
    public StandardTree<Integer> getEmpTree(int empId, StandardTree<Integer> roleTree) {
        if (empId == roleTree.getRoot()) {
            return roleTree;
        }
        for (int i = 0; i < roleTree.getChildren().size(); i++) {
            if (roleTree.getChildren().get(i).getRoot() == empId) {
                return (StandardTree) roleTree.getChildren().get(i);
            }
            getEmpTree(empId, roleTree);
        }
        return null;
    }

    /**
     * Exception thrown when the employee does not exist in the tree
     */
    public class EmployeeDoesNotExistException extends Exception {
    }

    /**
     * Exception thrown when the supervisor does not exist in the tree
     */
    public class SupervisorDoesNotExistException extends Exception {
    }
}