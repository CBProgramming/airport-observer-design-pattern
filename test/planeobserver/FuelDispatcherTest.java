/**
 * @author Christopher Burrell
 * Student number 7145969
 * ICA 1 - Part 2
 * Airport routing controller
 * Observer design pattern
 */
package planeobserver;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *FuelDispatcherTest class
 */
public class FuelDispatcherTest 
{    
    private ByteArrayOutputStream sysOut;
    private FuelDispatcher fueler;
    
    /**
     * Set up variables for each test and set System out
     */        
    @Before
    public void setUp() 
    {
        fueler = new FuelDispatcher();
        sysOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sysOut));
    }

    /**
     * Reset System out after tests complete
     */    
    @After
    public void tearDown() 
    {
        System.setOut(System.out);
    }

    /**
     * Test of updateBayAssigned method
     */
    @Test
    public void testUpdateBayAssigned() 
    {
        int parkingBayNumber = 1;
        String aircraftId = "A4DT7";
        fueler.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch fuel to aircraft " +
            "A4DT7 at parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }

    /**
     * Test of updatePlaneParked method
     */
    @Test
    public void testUpdatePlaneParked() 
    {
        int parkingBayNumber = 1;
        fueler.updatePlaneParked(parkingBayNumber);
        String expectedResult = "Method call to refuel aircraft at parking bay"
                + " 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
}
