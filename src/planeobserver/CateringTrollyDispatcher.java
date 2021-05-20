/**
 * @author Christopher Burrell
 * Student number 7145969
 * ICA 1 - Part 2
 * Airport routing controller
 * Observer design pattern
 */
package planeobserver;

/**
 * CateringTrollyDispatcher class
 */
public class CateringTrollyDispatcher implements FoodObserver
{
    /**
     * Empty constructor for CateringTrollyDispatcher
     * 
     */   
    CateringTrollyDispatcher()
    {

    }
    
    /**
     * Initiate dispatch of catering trolly to the relevant parking bay
     * @param parkingBayNumber bay number plane will park at
     * @param foodQuantity units of food to dispatch
     */
    @Override
    public void updateBayAssigned(int parkingBayNumber, int foodQuantity) 
    {
        System.out.println("Method call to dispatch " + foodQuantity + " units " + 
                "of catering to parking bay " + parkingBayNumber + ".\n");   
    }
    
    /**
     * Initiate loading of food resources to parked plane
     * @param parkingBayNumber bay number plane will park at
     */
    @Override
    public void updatePlaneParked(int parkingBayNumber) 
    {
        System.out.println("Method call to begin loading catering trolly "
                + "onto plane at parking bay " + parkingBayNumber + ".\n");   
    }    
}
