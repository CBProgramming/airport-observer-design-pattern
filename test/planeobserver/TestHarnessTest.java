/**
 * @author Christopher Burrell
 * Student number 7145969
 * ICA 1 - Part 2
 * Airport routing controller
 * Observer design pattern
 */
package planeobserver;


import org.junit.Test;

/**
 * TestHarnessTest class
 */
public class TestHarnessTest 
{

    static TestHarness harness;
    
    /**
     * Test of TestHarness instantiation
     */
    @Test
    public void testHarness()
    {
        harness = new TestHarness();        
    }

    /**
     * Test of main method, of class TestHarness.
     * @throws java.lang.Exception
     */
    @Test
    public void testMain() throws Exception 
    {        
        String[] args = null;
        TestHarness.sleepTime = 0;
        TestHarness.main(args);
    }  
}
