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
 * CateringTrollyDispatcherTest class
 */
public class CateringTrollyDispatcherTest 
{
    private ByteArrayOutputStream sysOut;
    private CateringTrollyDispatcher trolly;

    /**
     * Set up variables for each test and set System out
     */    
    @Before
    public void setUp() 
    {
        trolly = new CateringTrollyDispatcher();
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
        int parkingBayNumber = 1, foodQuantity = 50;
        trolly.updateBayAssigned(parkingBayNumber, foodQuantity);
        String expectedResult = "Method call to dispatch 50 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updatePlaneParked method
     */
    @Test
    public void testUpdatePlaneParked() 
    {
        int parkingBayNumber = 2;
        trolly.updatePlaneParked(parkingBayNumber);
        String expectedResult = "Method call to begin loading catering trolly "
                + "onto plane at parking bay 2.\n"
                + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
}
