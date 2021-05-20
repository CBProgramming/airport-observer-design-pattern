/**
 * @author Christopher Burrell
 * Student number 7145969
 * ICA 1 - Part 2
 * Airport routing controller
 * Observer design pattern
 */
package planeobserver;

/**
 * InFlightMealDispatcher class
 */
public class InFlightMealDispatcher implements FoodObserver
{
    /**
     * Empty constructor for InFlightMealDispatcher
     */   
    InFlightMealDispatcher()
    {

    }
    
    /**
     * Initiate dispatch of in flight meals to the relevant parking bay
     * @param parkingBayNumber bay number plane will park at
     * @param foodQuantity units of food to be dispatched
     */
    @Override
    public void updateBayAssigned(int parkingBayNumber, int foodQuantity) 
    {
        System.out.println("Method call to dispatch " + foodQuantity + " units " + 
                "of in flight meals to parking bay " + parkingBayNumber + ".\n");   
    }
    
    /**
     * Initiate loading of food resources to parked plane
     * @param parkingBayNumber bay number plane will park at
     */
    @Override
    public void updatePlaneParked(int parkingBayNumber) 
    {
        System.out.println("Method call to begin loading in flight meals "
                + "onto plane at parking bay " + parkingBayNumber + ".\n");   
    }  
}
