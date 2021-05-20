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
import org.junit.BeforeClass;

/**
 *PlaneTest class
 */
public class PlaneTest
{
    private static int parkingBayNumber;
    private static InFlightMealDispatcher mealDispatcher;
    private static CateringTrollyDispatcher cateringDispatcher;
    private static FoodDispatcher allFood;
    private static FoodDispatcher catering;
    private static FoodDispatcher meals;
    private static LuggageDispatcher luggage;
    private static PassengerDispatcher passengers;
    private static FuelDispatcher fueler;
    private static ArrayList<PlaneObserver> observers;
    private static ByteArrayOutputStream sysOut;
    private static Plane plane;

    /**
     * Set up variables for all tests
     */       
    @BeforeClass
    public static void setUpClass()
    {
        parkingBayNumber = 1;
        mealDispatcher = new InFlightMealDispatcher();
        cateringDispatcher = new CateringTrollyDispatcher();
        allFood = new FoodDispatcher();
        allFood.registerObserver(mealDispatcher);
        allFood.registerObserver(cateringDispatcher);
        catering = new FoodDispatcher();
        catering.registerObserver(cateringDispatcher);
        meals = new FoodDispatcher();
        meals.registerObserver(mealDispatcher);
        fueler = new FuelDispatcher();
        luggage = new LuggageDispatcher();
        passengers = new PassengerDispatcher();
    }
    
    /**
     * Set up variables for each test and set System out
     */    
    @Before
    public void setUp() 
    {
        plane = new Plane("ABCDE");
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
     * Register all observers to plane object
     */  
    public void registerAllObservers()
    {
        plane.registerObserver(fueler);
        plane.registerObserver(luggage);
        plane.registerObserver(passengers);
        plane.registerObserver(allFood);
    }
    
    /**
     * Test of assignBay method, all observers registered
     */
    @Test
    public void testAssignBayOutputValidId() 
    {
        registerAllObservers();
        plane.assignBay(parkingBayNumber);
        String expected = "\n\n\n\nParking bay 1 assigned to aircraft ABCDE\n"
                + System.getProperty("line.separator") + "Notifying "
                + "FuelDispatcher observer of plane ABCDE assignment to parking "
                + "bay 1." + System.getProperty("line.separator") + "Method "
                + "call to dispatch fuel to aircraft ABCDE at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "LuggageDispatcher observer of plane ABCDE assignment to "
                + "parking bay 1." + System.getProperty("line.separator")
                + "Method call to dispatch luggage container to parking bay 1 "
                + "for plane ABCDE.\n" + System.getProperty("line.separator")
                + "Notifying PassengerDispatcher observer of plane ABCDE "
                + "assignment to parking bay 1." 
                + System.getProperty("line.separator") + "Method call to "
                + "dispatch passengers to aircraft ABCDE at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "FoodDispatcher observer of plane ABCDE assignment to "
                + "parking bay 1." + System.getProperty("line.separator") 
                + "Method call to dispatch 50 units of in flight meals to "
                + "parking bay 1.\n" + System.getProperty("line.separator")
                + "Method call to dispatch 50 units of catering to parking bay "
                + "1.\n"+ System.getProperty("line.separator");
        assertEquals(expected,sysOut.toString());
    }

    /**
     * Test of assignBay method, no observers registered
     */
    @Test
    public void testAssignBayOutputValidIdNoObservers() 
    {
        plane.assignBay(parkingBayNumber);
        String expected = "\n\n\n\nParking bay 1 assigned to aircraft ABCDE\n"
                + System.getProperty("line.separator");
        assertEquals(expected,sysOut.toString());
    }
    
    /**
     * Test of assignBay method, all observers registered, null id
     */
    @Test
    public void testAssignBayOutputNullId() 
    {
        registerAllObservers();
        plane.setAircraftId(null);
        plane.assignBay(parkingBayNumber);
        String expected = "\n\n\n\nParking bay 1 assigned to aircraft null\n"
                + System.getProperty("line.separator") + "Notifying "
                + "FuelDispatcher observer of plane null assignment to parking "
                + "bay 1." + System.getProperty("line.separator") + "Method "
                + "call to dispatch fuel to aircraft null at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "LuggageDispatcher observer of plane null assignment to "
                + "parking bay 1." + System.getProperty("line.separator")
                + "Invalid aircraft id received for luggage processing. Method "
                + "call to investigate luggage requirements.\n" 
                + System.getProperty("line.separator")
                + "Notifying PassengerDispatcher observer of plane null "
                + "assignment to parking bay 1." 
                + System.getProperty("line.separator") + "Method call to "
                + "dispatch passengers to aircraft null at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "FoodDispatcher observer of plane null assignment to "
                + "parking bay 1." + System.getProperty("line.separator") 
                + "Invalid aircraft id for food dispatch. Method call to "
                + "investigate food requirements.\n" 
                + System.getProperty("line.separator");
        assertEquals(expected,sysOut.toString());
    }

    /**
     * Test of notifyObserversAssignedBay method, all observers registered
     */
    @Test
    public void testNotifyObserversAssignedBay() 
    {
        registerAllObservers();
        plane.setParkingBayNumber(parkingBayNumber);
        plane.notifyObserversAssignedBay();
        String expected = "Notifying FuelDispatcher observer of plane "
                + "ABCDE assignment to parking bay 1." 
                + System.getProperty("line.separator") + "Method "
                + "call to dispatch fuel to aircraft ABCDE at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "LuggageDispatcher observer of plane ABCDE assignment to "
                + "parking bay 1." + System.getProperty("line.separator")
                + "Method call to dispatch luggage container to parking bay 1 "
                + "for plane ABCDE.\n" + System.getProperty("line.separator")
                + "Notifying PassengerDispatcher observer of plane ABCDE "
                + "assignment to parking bay 1." 
                + System.getProperty("line.separator") + "Method call to "
                + "dispatch passengers to aircraft ABCDE at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "FoodDispatcher observer of plane ABCDE assignment to "
                + "parking bay 1." + System.getProperty("line.separator") 
                + "Method call to dispatch 50 units of in flight meals to "
                + "parking bay 1.\n" + System.getProperty("line.separator")
                + "Method call to dispatch 50 units of catering to parking bay "
                + "1.\n"+ System.getProperty("line.separator");
        assertEquals(expected,sysOut.toString());
    }

    /**
     * Test of notifyObserversAssignedBay method, all observers registered, 
     * null id
     */
    @Test
    public void testNotifyObserversAssignedBayNullId() 
    {
        registerAllObservers();
        plane.setAircraftId(null);
        plane.setParkingBayNumber(parkingBayNumber);
        plane.notifyObserversAssignedBay();
        String expected = "Notifying FuelDispatcher observer of plane "
                + "null assignment to parking bay 1." 
                + System.getProperty("line.separator") + "Method "
                + "call to dispatch fuel to aircraft null at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "LuggageDispatcher observer of plane null assignment to "
                + "parking bay 1." + System.getProperty("line.separator")
                + "Invalid aircraft id received for luggage processing. Method "
                + "call to investigate luggage requirements.\n" 
                + System.getProperty("line.separator")
                + "Notifying PassengerDispatcher observer of plane null "
                + "assignment to parking bay 1." 
                + System.getProperty("line.separator") + "Method call to "
                + "dispatch passengers to aircraft null at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "FoodDispatcher observer of plane null assignment to "
                + "parking bay 1." + System.getProperty("line.separator") 
                + "Invalid aircraft id for food dispatch. Method call to "
                + "investigate food requirements.\n" 
                + System.getProperty("line.separator");
        assertEquals(expected,sysOut.toString());
    }

    /**
     * Test of notifyObserversAssignedBay method, no observers registered
     */
    @Test
    public void testNotifyObserversAssignedBayNoObservers() 
    {
        plane.setParkingBayNumber(parkingBayNumber);
        plane.notifyObserversAssignedBay();
        String expected = "";
        assertEquals(expected,sysOut.toString());
    }
    
    /**
     * Test of park method, all observers registered
     */
    @Test
    public void testPark() 
    {
        registerAllObservers();
        plane.setParkingBayNumber(parkingBayNumber);
        plane.park();
        String expected = "\n\n\n\nPlane ABCDE parked at parking bay 1.\n"
                + System.getProperty("line.separator") + "Notifying "
                + "FuelDispatcherobserver that plane ABCDE has parked." 
                + System.getProperty("line.separator") + "Method call to "
                + "refuel aircraft at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "LuggageDispatcherobserver that plane ABCDE has parked." 
                + System.getProperty("line.separator") + "Method call to begin "
                + "loading luggage at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "PassengerDispatcherobserver that plane ABCDE has parked." 
                + System.getProperty("line.separator") + "Method call to begin "
                + "boarding passengers at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "FoodDispatcherobserver that plane ABCDE has parked." 
                + System.getProperty("line.separator") + "Method call to begin "
                + "loading in flight meals onto plane at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Method call to begin "
                + "loading catering trolly onto plane at parking bay 1.\n" 
                + System.getProperty("line.separator");       
        assertEquals(expected,sysOut.toString());        
    }

    /**
     * Test of park method, no observers registered
     */
    @Test
    public void testParkNoObservers() 
    {
        plane.setParkingBayNumber(parkingBayNumber);
        plane.park();
        String expected = "\n\n\n\nPlane ABCDE parked at parking bay 1.\n"
                + System.getProperty("line.separator");       
        assertEquals(expected,sysOut.toString());        
    }
    
    /**
     * Test of park method when plane already parked
     */
    @Test
    public void testParkAlreadyParked() 
    {
        registerAllObservers();
        plane.setParkingBayNumber(parkingBayNumber);
        plane.setParked(true);
        plane.park();
        String expected = "Plane already parked.  Method call to investigate"
                + System.getProperty("line.separator");       
        assertEquals(expected,sysOut.toString());        
    }    
    
    /**
     * Test of notifyObserversPlaneParked method, all observers registered
     */
    @Test
    public void testNotifyObserversPlaneParked() 
    {
        registerAllObservers();
        plane.setParkingBayNumber(parkingBayNumber);
        plane.notifyObserversPlaneParked();
        String expected = "Notifying FuelDispatcherobserver that plane "
                + "ABCDE has parked." + System.getProperty("line.separator") 
                + "Method call to refuel aircraft at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "LuggageDispatcherobserver that plane ABCDE has parked." 
                + System.getProperty("line.separator") + "Method call to begin "
                + "loading luggage at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "PassengerDispatcherobserver that plane ABCDE has parked." 
                + System.getProperty("line.separator") + "Method call to begin "
                + "boarding passengers at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Notifying "
                + "FoodDispatcherobserver that plane ABCDE has parked." 
                + System.getProperty("line.separator") + "Method call to begin "
                + "loading in flight meals onto plane at parking bay 1.\n" 
                + System.getProperty("line.separator") + "Method call to begin "
                + "loading catering trolly onto plane at parking bay 1.\n" 
                + System.getProperty("line.separator");       
        assertEquals(expected,sysOut.toString());
    }

    /**
     * Test of notifyObserversPlaneParked method, no observers registered
     */
    @Test
    public void testNotifyObserversPlaneParkedNoObservers() 
    {
        plane.setParkingBayNumber(parkingBayNumber);
        plane.notifyObserversPlaneParked();
        String expected = "";       
        assertEquals(expected,sysOut.toString());
    }    
    
    /**
     * Test of registerObserver method, basic test
     */
    @Test
    public void testRegisterObserver() 
    {
        PlaneObserver[] expectedArray = {allFood};
        plane.registerObserver(allFood);
        Object[] testArray = plane.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);
    }
    
    /**
     * Test of registerObserver method, duplicate observer
     */
    @Test
    public void testRegisterDuplicateObserver() {
        PlaneObserver[] expectedArray = {allFood};
        plane.registerObserver(allFood);
        plane.registerObserver(allFood);
        Object[] testArray = plane.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);
    }    

    /**
     * Test of registerObserver method attempting to add null
     */
    @Test
    public void testRegisterNullObserver() 
    {
        PlaneObserver[] expectedArray = {allFood};
        plane.registerObserver(allFood);
        plane.registerObserver(null);
        Object[] testArray = plane.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);
    }  
    
    /**
     * Test of registerObserver method attempting to add null on empty list
     */
    @Test
    public void testRegisterNullObserverFirstItem() 
    {
        PlaneObserver[] expectedArray = {};
        plane.registerObserver(null);
        Object[] testArray = plane.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);
    } 
    
    /**
     * Test of unregisterObserver method, one registered
     */
    @Test
    public void testUnregisterOnlyObserver() 
    {
        PlaneObserver[] expectedArray = {};
        observers.add(allFood);
        plane.setObservers(observers);
        plane.unregisterObserver(allFood);
        Object[] testArray = plane.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);        
    }

    /**
     * Test of unregisterObserver method, two registered
     */
    @Test
    public void testUnregisterSecondObserver() 
    {
        PlaneObserver[] expectedArray = {allFood};
        observers.add(allFood);
        observers.add(luggage);
        plane.setObservers(observers);
        plane.unregisterObserver(luggage);
        Object[] testArray = plane.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);        
    }
    
    /**
     * Test of unregisterObserver method, not in list
     */
    @Test
    public void testUnregisterObserverNotInList() 
    {
        PlaneObserver[] expectedArray = {allFood};
        observers.add(allFood);
        plane.setObservers(observers);
        plane.unregisterObserver(luggage);
        Object[] testArray = plane.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);        
    }
    
    /**
     * Test of unregisterObserver method, empty list
     */
    @Test
    public void testUnregisterObserverEmptyList() 
    {
        PlaneObserver[] expectedArray = {};
        plane.setObservers(observers);
        plane.unregisterObserver(luggage);
        Object[] testArray = plane.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);        
    }
    
    /**
     * Test of unregisterObserver method, null reference
     */
    @Test
    public void testUnregisterNullObserver() 
    {
        PlaneObserver[] expectedArray = {allFood};
        observers.add(allFood);
        plane.setObservers(observers);
        plane.unregisterObserver(null);
        Object[] testArray = plane.getObservers().toArray();
        assertArrayEquals(expectedArray,testArray);        
    }

    /**
     * Test of getObservers method, basic test
     */
    @Test
    public void testGetObserversTwoObservers() 
    {
        observers.add(luggage);
        observers.add(passengers);
        plane.setObservers(observers);
        Plane testPlane = new Plane("ABCDE");
        testPlane.registerObserver(luggage);
        testPlane.registerObserver(passengers);
        assertEquals(observers,testPlane.getObservers());
    }
    
    /**
     * Test of getObservers method, empty list
     */
    @Test
    public void testGetObserversEmptyList() 
    {
        Plane testPlane = new Plane("ABCDE");
        assertEquals(observers,testPlane.getObservers());
    }    
    
    /**
     * Test of getObservers method, null list
     */
    @Test
    public void testGetObserversNullList() 
    {
        observers = null;
        Plane testPlane = new Plane("ABCDE");
        testPlane.setObservers(null);
        assertEquals(observers,testPlane.getObservers());
    } 
    
    
    /**
     * Test of getAircraftId method, valid id
     */
    @Test
    public void testGetAircraftId() 
    {
        String expected = "ABCDE";
        assertEquals(expected,plane.getAircraftId());
    }
    
    /**
     * Test of getAircraftId method, null id
     */
    @Test
    public void testGetNullAircraftId() 
    {
        String expected = null;
        plane.setAircraftId(null);
        assertEquals(expected,plane.getAircraftId());
    }    

    /**
     * Test of getParkingBayNumber method
     */
    @Test
    public void testGetParkingBayNumber() 
    {
        int expected = 1;
        plane.setParkingBayNumber(parkingBayNumber);
        assertEquals(expected,plane.getParkingBayNumber());
    }

    /**
     * Test of isParked method
     */
    @Test
    public void testIsParked() 
    {
        boolean expected = true;
        plane.setParked(true);
        assertEquals(expected,plane.isParked());        
    }

    /**
     * Test of setObservers method, basic test
     */
    @Test
    public void testSetObserversTwoObservers() 
    {
        observers.add(luggage);
        observers.add(fueler);
        Plane testPlane = new Plane("ABCDE");
        testPlane.setObservers(observers);
        assertEquals(observers,testPlane.getObservers());
    }
    
    /**
     * Test of setObservers method, empty list
     */
    @Test
    public void testSetObserversEmptyList() 
    {
        Plane testPlane = new Plane("ABCDE");
        testPlane.setObservers(observers);
        assertEquals(observers,testPlane.getObservers());
    }    
    
    /**
     * Test of setObservers method, null list
     */
    @Test
    public void testSetObserversNullList() 
    {
        observers = null;
        Plane testPlane = new Plane("ABCDE");
        testPlane.setObservers(null);
        assertEquals(observers,testPlane.getObservers());
    }  
    
    /**
     * Test of setAircraftId method, valid id
     */
    @Test
    public void testSetAircraftId() 
    {
        String expected = "EFGHI";
        plane.setAircraftId("EFGHI");
        assertEquals(expected,plane.getAircraftId()); 
    }      

    /**
     * Test of setAircraftId method, null id
     */
    @Test
    public void testSetNullAircraftId() 
    {
        String expected = null;
        plane.setAircraftId(null);
        assertEquals(expected,plane.getAircraftId()); 
    } 
    
    /**
     * Test of parkingBayNumber method
     */
    @Test
    public void testSetParkingBayNumber() 
    {        
        plane.setParkingBayNumber(parkingBayNumber);
        assertEquals(parkingBayNumber,plane.getParkingBayNumber()); 
    }
    
    /**
     * Test of setParked method
     */
    @Test
    public void testSetParked() 
    {        
        plane.setParked(true);
        assertEquals(true,plane.isParked()); 
    }    
}
