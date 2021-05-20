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
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *FoodDispatcherTest class
 */
public class FoodDispatcherTest 
{    
    private final int parkingBayNumber = 1;
    private final InFlightMealDispatcher mealDispatcher = new InFlightMealDispatcher();
    private final CateringTrollyDispatcher cateringDispatcher = new CateringTrollyDispatcher();
    private FoodDispatcher foodDispatcher;
    private ArrayList<FoodObserver> observers;
    private ByteArrayOutputStream sysOut;
  
    /**
     * Set up variables for each test and set System out
     */  
    @Before
    public void setUp() 
    {
        foodDispatcher = new FoodDispatcher();
        observers = new ArrayList<>();
        sysOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sysOut));
    }
    
    /**
     * Reset System out after tests complete
     */     
    @AfterClass
    public static void tearDownClass() 
    {
        System.setOut(System.out);
    }  

    /**
     * Test of registerObserver method
     */
    @Test
    public void testRegisterObserver() 
    {
        FoodObserver[] expectedArray = {cateringDispatcher};
        foodDispatcher.registerObserver(cateringDispatcher);
        Object[] testArray = foodDispatcher.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);
    }
    
    /**
     * Test of registerObserver method attempting to add duplicate
     */
    @Test
    public void testRegisterDuplicateObserver() 
    {
        FoodObserver[] expectedArray = {mealDispatcher};  
        foodDispatcher.registerObserver(mealDispatcher);
        foodDispatcher.registerObserver(mealDispatcher);
        Object[] testArray = foodDispatcher.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);
    }
    
    /**
     * Test of registerObserver method attempting to add null
     */
    @Test
    public void testRegisterNullObserver() 
    {
        FoodObserver[] expectedArray = {cateringDispatcher};
        foodDispatcher.registerObserver(cateringDispatcher);
        foodDispatcher.registerObserver(null);
        Object[] testArray = foodDispatcher.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);
    }  
    
    /**
     * Test of registerObserver method attempting to add null on empty list
     */
    @Test
    public void testRegisterNullObserverFirstItem() 
    {
        FoodObserver[] expectedArray = {};
        foodDispatcher.registerObserver(null);
        Object[] testArray = foodDispatcher.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);
    }      

    /**
     * Test of unregisterObserver method as only observer
     */
    @Test
    public void testUnregisterOnlyObserver() 
    {
        FoodObserver[] expectedArray = {};
        observers.add(mealDispatcher);
        foodDispatcher.setObservers(observers);
        foodDispatcher.unregisterObserver(mealDispatcher);
        Object[] testArray = foodDispatcher.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);        
    }

    /**
     * Test of unregisterObserver method with additional observers
     */
    @Test
    public void testUnregisterSecondObserver() 
    {
        FoodObserver[] expectedArray = {cateringDispatcher};
        observers.add(mealDispatcher);
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        foodDispatcher.unregisterObserver(mealDispatcher);
        Object[] testArray = foodDispatcher.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);        
    }
    
    /**
     * Test of unregisterObserver method when not in list
     */
    @Test
    public void testUnregisterObserverNotInList() 
    {
        FoodObserver[] expectedArray = {cateringDispatcher};
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        foodDispatcher.unregisterObserver(mealDispatcher);
        Object[] testArray = foodDispatcher.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);        
    }
    
    /**
     * Test of unregisterObserver method on empty list
     */
    @Test
    public void testUnregisterObserverEmptyList() 
    {
        FoodObserver[] expectedArray = {};
        foodDispatcher.setObservers(observers);
        foodDispatcher.unregisterObserver(mealDispatcher);
        Object[] testArray = foodDispatcher.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);        
    }
    
    /**
     * Test of unregisterObserver method with null observer
     */
    @Test
    public void testUnregisterNullObserver() 
    {
        FoodObserver[] expectedArray = {cateringDispatcher};
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        foodDispatcher.unregisterObserver(null);
        Object[] testArray = foodDispatcher.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);        
    }
    
    /**
     * Test of updateBayAssigned method, aircraft id below lower case boundary
     */
    @Test
    public void testUpdateBayAssignedOneObserverBelowLowerCaseBoundary() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "`25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Aircraft id indicates no food required. " +
                     "Method call to investigate food requirements.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updateBayAssigned method, aircraft id on first lower case low 
     * boundary
     */
    @Test
    public void testUpdateBayAssignedOneObserverLowerCaseFirstLowBoundary() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "a25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch 50 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updateBayAssigned method, aircraft id on first lower case high 
     * boundary
     */
    @Test
    public void testUpdateBayAssignedOneObserverLowerCaseFirstHighBoundary() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "m25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch 50 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updateBayAssigned method, aircraft id on second lower case low 
     * boundary
     */
    @Test
    public void testUpdateBayAssignedOneObserverLowerCaseSecondLowBoundary() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "n25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch 100 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
 
    /**
     * Test of updateBayAssigned method, aircraft id on second lower case high 
     * boundary
     */
    @Test
    public void testUpdateBayAssignedOneObserverLowerCaseSecondHighBoundary() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "z25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch 100 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updateBayAssigned method, aircraft id above lower case boundary
     */
    @Test
    public void testUpdateBayAssignedOneObserverAboveLowerCaseBoundary() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "{25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Aircraft id indicates no food required. " +
                     "Method call to investigate food requirements.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }    
    
    /**
     * Test of updateBayAssigned method, aircraft id below upper case boundary
     */
    @Test
    public void testUpdateBayAssignedOneObserverBelowUpperCaseBoundary() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "@25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Aircraft id indicates no food required. " +
                     "Method call to investigate food requirements.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updateBayAssigned method, aircraft id on first upper case low
     * boundary
     */
    @Test
    public void testUpdateBayAssignedOneObserverUpperCaseFirstLowBoundary() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "A25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch 50 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    } 
    
    /**
     * Test of updateBayAssigned method, aircraft id on first upper case high
     * boundary
     */
    @Test
    public void testUpdateBayAssignedOneObserverUpperCaseFirstHighBoundary() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "M25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch 50 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }     
        
    /**
     * Test of updateBayAssigned method, aircraft id on second upper case low
     * boundary
     */
    @Test
    public void testUpdateBayAssignedOneObserverUpperCaseSecondLowBoundary() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "N25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch 100 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }  
        
    /**
     * Test of updateBayAssigned method, aircraft id on second upper case high
     * boundary
     */
    @Test
    public void testUpdateBayAssignedOneObserverUpperCaseSecondHighBoundary() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "Z25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch 100 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    } 
    
    /**
     * Test of updateBayAssigned method, aircraft id above upper case boundary
     */
    @Test
    public void testUpdateBayAssignedOneObserverAboveUpperCaseBoundary() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "[25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Aircraft id indicates no food required. " +
                     "Method call to investigate food requirements.\n" + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updateBayAssigned method, valid id with white space
     */
    @Test
    public void testUpdateBayAssignedOneObserverValidIdSurroundedByWhiteSpace() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "      Z25698      ";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch 100 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    } 
    
    /**
     * Test of updateBayAssigned method, two observers
     */
    @Test
    public void testUpdateBayAssignedTwoObservers() 
    {
        observers.add(cateringDispatcher);
        observers.add(mealDispatcher);
        foodDispatcher.setObservers(observers);
        String aircraftId = "a25698";
        foodDispatcher.updateBayAssigned(parkingBayNumber,aircraftId);
        String expectedResult = "Method call to dispatch 50 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator") +
                "Method call to dispatch 50 units of in flight meals to "
                + "parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updateBayAssigned method, no observers
     */
    @Test
    public void testUpdateBayAssignedNoObserversValidId() 
    {
        foodDispatcher.setObservers(observers);
        foodDispatcher.updateBayAssigned(parkingBayNumber,"X78569");
    } 
    
    /**
     * Test of updateBayAssigned method, id indicates no food needed
     */
    @Test
    public void testUpdateBayAssignedNoObserversIdForNoFood() 
    {
        foodDispatcher.setObservers(observers);
        foodDispatcher.updateBayAssigned(parkingBayNumber,"778569");
    }  
    
    /**
     * Test of updateBayAssigned method, null id and no observers
     */
    @Test
    public void testUpdateBayAssignedNoObserversNullId() 
    {
        foodDispatcher.setObservers(observers);
        String test = null;
        foodDispatcher.updateBayAssigned(parkingBayNumber,test);
    }   
    
    /**
     * Test of updateBayAssigned method, empty id and no observers
     */
    @Test
    public void testUpdateBayAssignedNoObserversEmptyId() 
    {
        foodDispatcher.setObservers(observers);
        String test = "";
        foodDispatcher.updateBayAssigned(parkingBayNumber,test);
    } 
    
    /**
     * Test of updateBayAssigned method, white space id, no observers
     */
    @Test
    public void testUpdateBayAssignedNoObserversWhiteSpaceId() 
    {
        foodDispatcher.setObservers(observers);
        String test = " ";
        foodDispatcher.updateBayAssigned(parkingBayNumber,test);
    }     
 
    /**
     * Test of notifyObserversAssignedBay method, one observer
     */
    @Test
    public void testNotifyObserversAssignedBayOneObserver() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        foodDispatcher.notifyObserversAssignedBay(parkingBayNumber, 50);
        String expectedResult = "Method call to dispatch 50 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }

    /**
     * Test of notifyObserversAssignedBay method, two observers
     */
    @Test
    public void testNotifyObserversAssignedBayTwoObservers() 
    {
        observers.add(cateringDispatcher);
        observers.add(mealDispatcher);
        foodDispatcher.setObservers(observers);
        foodDispatcher.notifyObserversAssignedBay(parkingBayNumber,100);
        String expectedResult = "Method call to dispatch 100 units of catering "
                + "to parking bay 1.\n" + System.getProperty("line.separator") +
                "Method call to dispatch 100 units of in flight meals to "
                + "parking bay 1.\n" + System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }    
    
    /**
     * Test of notifyObserversAssignedBay method, no observers
     */
    @Test
    public void testNotifyObserversAssignedBayNoObservers() 
    {
        foodDispatcher.setObservers(observers);
        foodDispatcher.notifyObserversAssignedBay(parkingBayNumber,50);
    }
    
    /**
     * Test of updatePlaneParked method, one observer
     */
    @Test
    public void testUpdatePlaneParkedOneObserver() 
    {
        observers.add(cateringDispatcher);
        foodDispatcher.setObservers(observers);
        foodDispatcher.updatePlaneParked(parkingBayNumber);
        String expectedResult = ("Method call to begin loading catering trolly "
                + "onto plane at parking bay 1.\n") + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updatePlaneParked method, two observers
     */
    @Test
    public void testUpdatePlaneParkedTwoObservers() 
    {
        observers.add(cateringDispatcher);
        observers.add(mealDispatcher);
        foodDispatcher.setObservers(observers);
        foodDispatcher.updatePlaneParked(parkingBayNumber);
        String expectedResult = ("Method call to begin loading catering trolly "
                + "onto plane at parking bay 1.\n") + 
                System.getProperty("line.separator") + ("Method call to begin "
                + "loading in flight meals onto plane at parking bay 1.\n") + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of updatePlaneParked method, no observers
     */
    @Test
    public void testUpdatePlaneParkedNoObservers() 
    {
        foodDispatcher.setObservers(observers);
        foodDispatcher.updatePlaneParked(parkingBayNumber);
    }    
    
    /**
     * Test of notifyObserversPlaneParked method, one observer
     */
    @Test
    public void testNotifyObserversPlaneParkedOneObserver() 
    {
        observers.add(mealDispatcher);
        foodDispatcher.setObservers(observers);
        foodDispatcher.notifyObserversPlaneParked(parkingBayNumber);
        String expectedResult = ("Method call to begin loading in flight meals "
                + "onto plane at parking bay 1.\n") + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of notifyObserversPlaneParked method, two observers
     */
    @Test
    public void testNotifyObserversPlaneParkedTwoObservers() 
    {
        observers.add(cateringDispatcher);
        observers.add(mealDispatcher);
        foodDispatcher.setObservers(observers);
        foodDispatcher.notifyObserversPlaneParked(parkingBayNumber);
        String expectedResult = ("Method call to begin loading catering trolly "
                + "onto plane at parking bay 1.\n") + 
                System.getProperty("line.separator") + ("Method call to begin "
                + "loading in flight meals onto plane at parking bay 1.\n") + 
                System.getProperty("line.separator");
        assertEquals(expectedResult,sysOut.toString());
    }
    
    /**
     * Test of notifyObserversPlaneParked method, no observers
     */
    @Test
    public void testNotifyObserversPlaneParkedNoObservers() 
    {
        foodDispatcher.setObservers(observers);
        foodDispatcher.notifyObserversPlaneParked(parkingBayNumber);
    }
    
    /**
     * Test of getObservers method, two observers
     */
    @Test
    public void testGetObserversTwoObservers() 
    {
        observers.add(cateringDispatcher);
        observers.add(mealDispatcher);
        foodDispatcher.setObservers(observers);
        FoodDispatcher fd = new FoodDispatcher();
        fd.registerObserver(cateringDispatcher);
        fd.registerObserver(mealDispatcher);
        assertEquals(observers,fd.getObservers());
    }
    
    /**
     * Test of getObservers method, no observers
     */
    @Test
    public void testGetObserversEmptyList() 
    {
        FoodDispatcher fd = new FoodDispatcher();
        assertEquals(observers,fd.getObservers());
    }    
    
    /**
     * Test of getObservers method, null list
     */
    @Test
    public void testGetObserversNullList() 
    {
        observers = null;
        FoodDispatcher fd = new FoodDispatcher();
        fd.setObservers(null);
        assertEquals(observers,fd.getObservers());
    } 
    
    /**
     * Test of setObservers method, two observers
     */
    @Test
    public void testSetObserversTwoObservers() 
    {
        observers.add(cateringDispatcher);
        observers.add(mealDispatcher);
        foodDispatcher.setObservers(observers);
        FoodDispatcher fd = new FoodDispatcher();
        fd.setObservers(observers);
        assertEquals(observers,fd.getObservers());
    }
    
    /**
     * Test of setObservers method, no observers
     */
    @Test
    public void testSetObserversEmptyList() 
    {
        FoodDispatcher fd = new FoodDispatcher();
        fd.setObservers(observers);
        assertEquals(observers,fd.getObservers());
    }    
    
    /**
     * Test of setObservers method, null list
     */
    @Test
    public void testSetObserversNullList() 
    {
        observers = null;
        FoodDispatcher fd = new FoodDispatcher();
        fd.setObservers(null);
        assertEquals(observers,fd.getObservers());
    }     
    
    /**
     * Test of getQuantity method, below lower case boundary
     */
    @Test
    public void testGetQuantityBelowLowerCaseBoundary() 
    {
        String aircraftId = "`25698";        
        int expectedQuantity = 0;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    }
    
    /**
     * Test of getQuantity method, at first lower case low boundary
     */
    @Test
    public void testGetQuantityLowerCaseFirstLowBoundary() 
    {
        String aircraftId = "a25698";
        int expectedQuantity = 50;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    }
    
    /**
     * Test of getQuantity method, at first lower case high boundary
     */
    @Test
    public void testGetQuantityLowerCaseFirstHighBoundary() 
    {
        String aircraftId = "m25698";
        int expectedQuantity = 50;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    }
    
    /**
     * Test of getQuantity method, at second lower case low boundary
     */
    @Test
    public void testGetQuantityLowerCaseSecondLowBoundary() 
    {
        String aircraftId = "n25698";
        int expectedQuantity = 100;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    }
 
    /**
     * Test of getQuantity method, at second lower case high boundary
     */
    @Test
    public void testGetQuantityLowerCaseSecondHighBoundary() 
    {
        String aircraftId = "z25698";
        int expectedQuantity = 100;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    }
    
    /**
     * Test of getQuantity method, above lower case boundary
     */
    @Test
    public void testGetQuantityAboveLowerCaseBoundary() 
    {
        String aircraftId = "{25698";
        int expectedQuantity = 0;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    }    
    
    /**
     * Test of getQuantity method, below upper case boundary
     */
    @Test
    public void testGetQuantityBelowUpperCaseBoundary() 
    {
        String aircraftId = "@25698";
        int expectedQuantity = 0;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    }
    
    /**
     * Test of getQuantity method, at first upper case low boundary
     */
    @Test
    public void testGetQuantityUpperCaseFirstLowBoundary() 
    {
        String aircraftId = "A25698";
        int expectedQuantity = 50;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    } 
    
    /**
     * Test of getQuantity method, at first upper case high boundary
     */
    @Test
    public void testGetQuantityUpperCaseFirstHighBoundary() 
    {
        String aircraftId = "M25698";
        int expectedQuantity = 50;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    }     
        
    /**
     * Test of getQuantity method, at second upper case low boundary
     */
    @Test
    public void testGetQuantityUpperCaseSecondLowBoundary() 
    {
        String aircraftId = "N25698";
        int expectedQuantity = 100;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    }  
        
    /**
     * Test of getQuantity method, at second upper case high boundary
     */
    @Test
    public void testGetQuantityUpperCaseSecondHighBoundary() 
    {
        String aircraftId = "Z25698";
        int expectedQuantity = 100;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    } 
    
    /**
     * Test of getQuantity method, above upper case boundary
     */
    @Test
    public void testGetQuantityAboveUpperCaseBoundary() 
    {
        String aircraftId = "[25698";
        int expectedQuantity = 0;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    }

    /**
     * Test of getQuantity method, id surrounded by white space
     */
    @Test
    public void testValidIdSurroundedByWhiteSpace() 
    {
        String aircraftId = "     Z25698     ";
        int expectedQuantity = 100;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    } 
    
    /**
     * Test of getQuantity method, null id
     */
    @Test
    public void testGetQuantityNullId() 
    {
        String aircraftId = null;
        int expectedQuantity = -1;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    }   
    
    /**
     * Test of getQuantity method, empty id
     */
    @Test
    public void testGetQuantityEmptyId() 
    {
        String aircraftId = "";
        int expectedQuantity = -1;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    } 
    
    /**
     * Test of getQuantity method, white space id
     */
    @Test
    public void testGetQuantityWhiteSpaceId() 
    {
        String aircraftId = " ";
        int expectedQuantity = -1;
        int actualQuantity = foodDispatcher.getQuantity(aircraftId);
        assertEquals(expectedQuantity,actualQuantity);
    }      
}