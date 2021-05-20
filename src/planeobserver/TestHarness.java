/**
 * @author Christopher Burrell
 * Student number 7145969
 * ICA 1 - Part 2
 * Airport routing controller
 * Observer design pattern
 */
package planeobserver;

import java.util.ArrayList;

/**
 * Test harness
 */
public class TestHarness
{
    static int sleepTime = 15000;

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException throws exception
     */
    public static void main(String[] args) throws InterruptedException 
    {
        //initialising three different combinations of food dispatchers
        InFlightMealDispatcher inFlightD = new InFlightMealDispatcher();
        CateringTrollyDispatcher cateringD = new CateringTrollyDispatcher();
        ArrayList<FoodObserver> bothFood = new ArrayList<>();
        bothFood.add(inFlightD);
        bothFood.add(cateringD);
        FoodDispatcher allFoodD = new FoodDispatcher(bothFood);
        FoodDispatcher cateringFoodD = new FoodDispatcher(cateringD);
        FoodDispatcher inFlightFoodD = new FoodDispatcher();
        inFlightFoodD.registerObserver(inFlightD);

        //initialising other dispatcher objects
        LuggageDispatcher luggageD = new LuggageDispatcher();
        PassengerDispatcher passengerD = new PassengerDispatcher();
        FuelDispatcher fuelD = new FuelDispatcher();
   
        //initialising planes using various combinations, methods and constructors
        //fuel, luggage container, passengers, 50 units in flight meals and catering
        Plane p1 = new Plane("AA125");
        p1.registerObserver(fuelD);
        p1.registerObserver(luggageD);
        p1.registerObserver(passengerD);
        p1.registerObserver(allFoodD); 
        
        //loose luggage (added twice), 100 units in flight meals and catering
        Plane p2 = new Plane("P9125");
        p2.registerObserver(luggageD);
        p2.registerObserver(luggageD);
        p2.registerObserver(allFoodD);  

        //passengers, ,loose luggage, 0 units food (investigated)
        Plane p3 = new Plane("77548");
        p3.registerObserver(fuelD);
        p3.registerObserver(passengerD);
        p3.registerObserver(luggageD);
        p3.registerObserver(cateringFoodD);
        
        //0 units food (investigated), invalid luggage code (investigated)
        Plane p4 = new Plane("??D896");
        p4.registerObserver(inFlightFoodD);
        p4.registerObserver(luggageD);  
        
        //null aircraft id, non reliant supplies dispatched (fuel, passengers)
        //id reliant supplies investgate
        Plane p5 = new Plane(null);
        p5.registerObserver(fuelD);
        p5.registerObserver(luggageD);
        p5.registerObserver(passengerD);
        p5.registerObserver(allFoodD);          
                 
        // assign bays to first three planes
        p1.assignBay(1);
        Thread.sleep(sleepTime);
        p2.assignBay(2);
        Thread.sleep(sleepTime);
        p3.assignBay(3);
        Thread.sleep(sleepTime);
        
        // park first three planes
        p1.park();
        Thread.sleep(sleepTime);
        p2.park();
        Thread.sleep(sleepTime);
        p3.park();
        Thread.sleep(sleepTime);

        // assign bay and park fourth plane, changing observer registrations
        p4.assignBay(4);
        System.out.println("\nUnregistering in fligh food dispatcher and "
                + "registering catering trolly dispatcher on further "
                + "investion.\n");
        p4.unregisterObserver(inFlightFoodD);
        p4.registerObserver(cateringFoodD);
        Thread.sleep(sleepTime);
        p4.park();
        Thread.sleep(sleepTime);
        
        // assign bay and park fifth plane        
        p5.assignBay(5);
        System.out.println("\nUnregistering luggage dispatcher on further "
                + "investion.\n");   
        p5.unregisterObserver(luggageD);   
        Thread.sleep(sleepTime);
        p5.park();              
    }
}