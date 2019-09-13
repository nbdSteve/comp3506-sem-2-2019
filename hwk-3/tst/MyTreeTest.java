import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test methods for the EmployeeTree.java class
 */
public class MyTreeTest {
    // store a employee tree instance, this is used for all of the tests
    private EmployeeTree employeeTree;

    @Before
    public void setup() {
        employeeTree = new EmployeeTree(0);
    }

    @Test(timeout = 1000)
    public void constructorTest() {
        Assert.assertEquals(0, employeeTree.getFranchiseeId());
        Assert.assertNotNull(employeeTree.getEmployeeTree());
    }

    @Test(timeout = 1000)
    public void addEmployeeTest() {
        try {
            employeeTree.addEmployee(0, 1);
            employeeTree.addEmployee(0, 2);
        } catch (EmployeeTree.SupervisorDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertTrue(employeeTree.getEmployeeTree().contains(1));
        Assert.assertTrue(employeeTree.getEmployeeTree().contains(2));
        try {
            employeeTree.addEmployee(1, 3);
            employeeTree.addEmployee(1, 4);
        } catch (EmployeeTree.SupervisorDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertTrue(employeeTree.getEmployeeTree().contains(3));
        Assert.assertTrue(employeeTree.getEmployeeTree().contains(4));
    }

    @Test(timeout = 1000)
    public void addEmployeeWithInvalidSupervisorTest() {
        Assert.assertFalse(employeeTree.getEmployeeTree().contains(1));
        try {
            employeeTree.addEmployee(1, 2);
            Assert.fail();
        } catch (EmployeeTree.SupervisorDoesNotExistException e) {
            // pass test
        }
        Assert.assertFalse(employeeTree.getEmployeeTree().contains(1));
        Assert.assertFalse(employeeTree.getEmployeeTree().contains(2));
    }

    @Test
    public void removeEmployeeTest() {
        try {
            employeeTree.addEmployee(0, 1);
        } catch (EmployeeTree.SupervisorDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertTrue(employeeTree.getEmployeeTree().contains(1));
        try {
            employeeTree.removeEmployee(0, 1);
        } catch (EmployeeTree.SupervisorDoesNotExistException | EmployeeTree.EmployeeDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertFalse(employeeTree.getEmployeeTree().contains(1));
    }

    @Test(timeout = 1000)
    public void removeEmployeeWithInvalidSupervisorTest() {
        try {
            employeeTree.addEmployee(0, 1);
        } catch (EmployeeTree.SupervisorDoesNotExistException e) {
            Assert.fail();
        }
        try {
            employeeTree.removeEmployee(3, 1);
        } catch (EmployeeTree.SupervisorDoesNotExistException e) {
            // pass test
        } catch (EmployeeTree.EmployeeDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertTrue(employeeTree.getEmployeeTree().contains(1));
    }

    @Test(timeout = 1000)
    public void removeInvalidEmployeeTest() {
        Assert.assertFalse(employeeTree.getEmployeeTree().contains(2));
        try {
            employeeTree.removeEmployee(0, 2);
        } catch (EmployeeTree.EmployeeDoesNotExistException e) {
            // pass test
        } catch (EmployeeTree.SupervisorDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertFalse(employeeTree.getEmployeeTree().contains(2));
    }

    @Test(timeout = 1000)
    public void listEmployeeTest() {
        List<Integer> expectedList = new ArrayList<>();
        try {
            Assert.assertEquals(expectedList, employeeTree.getEmployeeList(0));
        } catch (EmployeeTree.SupervisorDoesNotExistException e) {
            Assert.fail();
        }
        expectedList.add(1);
        expectedList.add(2);
        try {
            employeeTree.addEmployee(0, 1);
            employeeTree.addEmployee(0, 2);
            Assert.assertEquals(expectedList, employeeTree.getEmployeeList(0));
        } catch (EmployeeTree.SupervisorDoesNotExistException e) {
            Assert.fail();
        }
        try {
            employeeTree.getEmployeeList(5);
            Assert.fail();
        } catch (EmployeeTree.SupervisorDoesNotExistException e) {
            // pass test
        }
    }
}