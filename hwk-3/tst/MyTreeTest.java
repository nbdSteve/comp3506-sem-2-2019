import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyTreeTest {
    RoleTree roles;

    @Before
    public void setup() {
        roles = new RoleTree(0);
    }

    @Test(timeout=1000)
    public void constructorTest() {
        Assert.assertEquals(0, roles.getFranchiseeId());
        Assert.assertNotNull(roles.getRoleTree());
    }

    @Test(timeout=1000)
    public void addEmployeeTest() {
        try {
            roles.addEmployee(0, 1);
            roles.addEmployee(0, 2);
        } catch (RoleTree.SupervisorDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertTrue(roles.getRoleTree().contains(1));
        Assert.assertTrue(roles.getRoleTree().contains(2));
        try {
            roles.addEmployee(1, 3);
            roles.addEmployee(1, 4);
        } catch (RoleTree.SupervisorDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertTrue(roles.getRoleTree().contains(3));
        Assert.assertTrue(roles.getRoleTree().contains(4));
    }

    @Test(timeout=1000)
    public void addEmployeeWithInvalidSupervisorTest() {
        Assert.assertFalse(roles.getRoleTree().contains(1));
        try {
            roles.addEmployee(1, 2);
            Assert.fail();
        } catch (RoleTree.SupervisorDoesNotExistException e) {
            // pass test
        }
        Assert.assertFalse(roles.getRoleTree().contains(1));
        Assert.assertFalse(roles.getRoleTree().contains(2));
    }

    @Test
    public void removeEmployeeTest() {
        try {
            roles.addEmployee(0, 1);
        } catch (RoleTree.SupervisorDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertTrue(roles.getRoleTree().contains(1));
        try {
            roles.removeEmployee(0, 1);
        } catch (RoleTree.SupervisorDoesNotExistException | RoleTree.EmployeeDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertFalse(roles.getRoleTree().contains(1));
    }

    @Test(timeout=1000)
    public void removeEmployeeWithInvalidSupervisorTest() {
        try {
            roles.addEmployee(0, 1);
        } catch (RoleTree.SupervisorDoesNotExistException e) {
            Assert.fail();
        }
        try {
            roles.removeEmployee(3, 1);
        } catch (RoleTree.SupervisorDoesNotExistException e) {
            // pass test
        } catch (RoleTree.EmployeeDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertTrue(roles.getRoleTree().contains(1));
    }

    @Test(timeout=1000)
    public void removeInvalidEmployeeTest() {
        Assert.assertFalse(roles.getRoleTree().contains(2));
        try {
            roles.removeEmployee(0, 2);
        } catch (RoleTree.EmployeeDoesNotExistException e) {
            // pass test
        } catch (RoleTree.SupervisorDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertFalse(roles.getRoleTree().contains(2));
    }
}