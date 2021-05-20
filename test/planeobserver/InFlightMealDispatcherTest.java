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
 *InFlightMealDispatcherTest class
 */
public class InFlightMealDispatcherTest 
{
    private ByteArrayOutputStream sysOut;
    private InFlightMealDispatcher meals;

    /**
     * Set up variables for each test and set System out
     */   
    @Before
    public void setUp() 
    {
        meals = new InFlightMealDispatcher();
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
        int parkingBayNumber = 3, foodQuantity = 100;
        meals.updateBayAssigned(parkingBayNumber, foodQuantity);
        String expectedResult = "Method call to dispatch 100 units of in "
                + "flight meals to parking bay 3.\n" 
                + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updatePlaneParked method
     */
    @Test
    public void testUpdatePlaneParked() 
    {
        int parkingBayNumber = 4;
        meals.updatePlaneParked(parkingBayNumber);
        String expectedResult = "Method call to begin loading in flight meals "
                + "onto plane at parking bay 4.\n"
                + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
}
