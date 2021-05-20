/**
 * @author Christopher Burrell
 * Student number 7145969
 * ICA 1 - Part 2
 * Airport routing controller
 * Observer design pattern
 */
package planeobserver;

/**
 * PlaneObserver interface
 */
public interface FoodObserver 
{

    /**
     * Initiate dispatch of food resources to the relevant parking bay
     * @param parkingBayNumber bay number plane will park at
     * @param foodQuantity units of food to dispatch
     */
    public void updateBayAssigned(int parkingBayNumber, int foodQuantity);
    
    /**
     * Initiate loading of food resources to parked plane
     * @param parkingBayNumber bay number plane will park at
     */
    public void updatePlaneParked(int parkingBayNumber);    
}