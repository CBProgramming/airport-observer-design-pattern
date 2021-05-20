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
 *LuggageDispatcherTest class
 */
public class LuggageDispatcherTest 
{
    private final int parkingBayNumber = 1;
    private ByteArrayOutputStream sysOut;
    private LuggageDispatcher luggage;
    
    /**
     * Set up variables for each test and set System out
     */   
    @Before
    public void setUp() 
    {
        luggage = new LuggageDispatcher();
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
     * Test of updateBayAssigned method, below digit boundary
     */
    @Test
    public void testUpdateBayAssignedIdBelowDigitBoundary() 
    {
        String aircraftId = "A/5698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Plane id indicates no luggage required. "
                + "Method call to investigate luggage requirements.\n" +
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updateBayAssigned method, lowest digit boundary
     */
    @Test
    public void testUpdateBayAssignedIdLowestDigitBoundary() 
    {
        String aircraftId = "A05698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch loose luggage to " +
                "parking bay 1 for plane A05698.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }  
    
    /**
     * Test of updateBayAssigned method, mid digit boundary
     */
    @Test
    public void testUpdateBayAssignedIdMidDigit() 
    {
        String aircraftId = "A55698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch loose luggage to " +
                "parking bay 1 for plane A55698.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }      
    
    /**
     * Test of updateBayAssigned method, highest digit boundary
     */
    @Test
    public void testUpdateBayAssignedIdHighestDigitBoundary() 
    {
        String aircraftId = "A95698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch loose luggage to " +
                "parking bay 1 for plane A95698.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }   
    
    /**
     * Test of updateBayAssigned method, above digit boundary
     */
    @Test
    public void testUpdateBayAssignedIdBeyondDigitBoundary() 
    {
        String aircraftId = "A:5698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Plane id indicates no luggage required. "
                + "Method call to investigate luggage requirements.\n" +
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }       
    
    /**
     * Test of updateBayAssigned method, below upper case boundary
     */
    @Test
    public void testUpdateBayAssignedIdBelowUpperCaseBoundary() 
    {
        String aircraftId = "A@5698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Plane id indicates no luggage required. "
                + "Method call to investigate luggage requirements.\n" +
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updateBayAssigned method, lowest upper case boundary
     */
    @Test
    public void testUpdateBayAssignedIdLowestUpperCaseBoundary() 
    {
        String aircraftId = "AA5698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch luggage container "
                + "to parking bay 1 for plane AA5698.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }  
    
    /**
     * Test of updateBayAssigned method, mid upper case boundary
     */
    @Test
    public void testUpdateBayAssignedIdMidUpperCase() 
    {
        String aircraftId = "AM5698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch luggage container "
                + "to parking bay 1 for plane AM5698.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }      
    
    /**
     * Test of updateBayAssigned method, highest upper case boundary
     */
    @Test
    public void testUpdateBayAssignedIdHighestUpperCaseBoundary() 
    {
        String aircraftId = "AZ5698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch luggage container "
                + "to parking bay 1 for plane AZ5698.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }   
    
    /**
     * Test of updateBayAssigned method, above upper case boundary
     */
    @Test
    public void testUpdateBayAssignedIdBeyondUpperCaseBoundary() 
    {
        String aircraftId = "A[5698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Plane id indicates no luggage required. "
                + "Method call to investigate luggage requirements.\n" +
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }      
 
    
    /**
     * Test of updateBayAssigned method, below lower case boundary
     */
    @Test
    public void testUpdateBayAssignedIdBelowLowerCaseBoundary() 
    {
        String aircraftId = "A`5698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Plane id indicates no luggage required. "
                + "Method call to investigate luggage requirements.\n" +
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updateBayAssigned method, lowest lower case boundary
     */
    @Test
    public void testUpdateBayAssignedIdLowestLowerCaseBoundary() 
    {
        String aircraftId = "Aa5698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch luggage container "
                + "to parking bay 1 for plane Aa5698.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }  
    
    /**
     * Test of updateBayAssigned method, mid lower case boundary
     */
    @Test
    public void testUpdateBayAssignedIdMidLowerCase() 
    {
        String aircraftId = "Am5698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch luggage container "
                + "to parking bay 1 for plane Am5698.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }      
    
    /**
     * Test of updateBayAssigned method, highest lower case boundary
     */
    @Test
    public void testUpdateBayAssignedIdHighestLowerCaseBoundary() 
    {
        String aircraftId = "Az5698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch luggage container "
                + "to parking bay 1 for plane Az5698.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }   
    
    /**
     * Test of updateBayAssigned method, beyond lower case boundary
     */
    @Test
    public void testUpdateBayAssignedIdBeyondLowerCaseBoundary() 
    {
        String aircraftId = "A{5698";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Plane id indicates no luggage required. "
                + "Method call to investigate luggage requirements.\n" +
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updateBayAssigned method, valid id with white space
     */
    @Test
    public void testUpdateBayAssignedValidIdSurroundingWhiteSpace() 
    {
        String aircraftId = "    AA456     ";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch luggage container "
                + "to parking bay 1 for plane AA456.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    } 
    
    /**
     * Test of updateBayAssigned method, null id
     */
    @Test
    public void testUpdateBayAssignedNullId() 
    {
        String aircraftId = null;
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Invalid aircraft id received for luggage " +
                    "processing. Method call to investigate luggage " +
                    "requirements.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }     
    
    /**
     * Test of updateBayAssigned method, empty id
     */
    @Test
    public void testUpdateBayAssignedEmptyId() 
    {
        String aircraftId = "";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Invalid aircraft id received for luggage " +
                    "processing. Method call to investigate luggage " +
                    "requirements.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }   

    /**
     * Test of updateBayAssigned method, whit space id
     */
    @Test
    public void testUpdateBayAssignedWhiteSpaceId() 
    {
        String aircraftId = " ";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Invalid aircraft id received for luggage " +
                    "processing. Method call to investigate luggage " +
                    "requirements.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }   

    /**
     * Test of updateBayAssigned method, id too short
     */
    @Test
    public void testUpdateBayAssignedTooShortId() 
    {
        String aircraftId = "A";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Invalid aircraft id received for luggage " +
                    "processing. Method call to investigate luggage " +
                    "requirements.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updateBayAssigned method, id too short with white space
     */
    @Test
    public void testUpdateBayAssignedTooShortIdSurroundedByWhiteSpace() 
    {
        String aircraftId = " A      ";
        luggage.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Invalid aircraft id received for luggage " +
                    "processing. Method call to investigate luggage " +
                    "requirements.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    } 
    

    /**
     * Test of dispatchLuggageContainer method, valid id
     */
    @Test
    public void testDispatchLuggageContainer() 
    {
        String aircraftId = "ABCDE";
        luggage.dispatchLuggageContainer(parkingBayNumber, aircraftId);
        String expectedResult = "Method call to dispatch luggage container "
                + "to parking bay 1 for plane ABCDE.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of dispatchLuggageContainer method, null id
     */
    @Test
    public void testDispatchLuggageContainerNullId() 
    {
        String aircraftId = null;
        luggage.dispatchLuggageContainer(parkingBayNumber, aircraftId);
        String expectedResult = "Method call to dispatch luggage container "
                + "to parking bay 1 for plane null.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of dispatchLooseLuggage method, valid id
     */
    @Test
    public void testDispatchLooseLuggage() 
    {
        String aircraftId = "A9CDE";
        luggage.dispatchLooseLuggage(parkingBayNumber, aircraftId);
        String expectedResult = "Method call to dispatch loose luggage "
                + "to parking bay 1 for plane A9CDE.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of dispatchLooseLuggage method, null id
     */
    @Test
    public void testDispatchLooseLuggageNullId() 
    {
        String aircraftId = null;
        luggage.dispatchLooseLuggage(parkingBayNumber, aircraftId);
        String expectedResult = "Method call to dispatch loose luggage "
                + "to parking bay 1 for plane null.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }    

    /**
     * Test of updatePlaneParked method
     */
    @Test
    public void testUpdatePlaneParked() 
    {
        luggage.updatePlaneParked(parkingBayNumber);
        String expectedResult = "Method call to begin loading luggage "
                + "at parking bay 1.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
}
