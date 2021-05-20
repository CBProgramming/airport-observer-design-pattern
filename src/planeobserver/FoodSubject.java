/**
 * @author Christopher Burrell
 * Student number 7145969
 * ICA 1 - Part 2
 * Airport routing controller
 * Observer design pattern
 */
package planeobserver;

/**
 * FoodSubject interface
 */
public interface FoodSubject 
{
    /**
     * Register a food observer
     * @param o Observer to be registered
     */
    public void registerObserver(FoodObserver o);
    
    /**
     * Unregister a food observer
     * @param o Observer to be unregistered
     */
    public void unregisterObserver(FoodObserver o);
    
    /**
     * Notify food observers of bay a plane is parking at
     * @param parkingBayNumber bay plane is parking at
     * @param quantity units of food to be supplied
     */
    public void notifyObserversAssignedBay(int parkingBayNumber, int quantity); 
    
    /**
     * Notify food observers that a plane is ready to be resupplied
     * @param parkingBayNumber bay plane is parked at
     */
    public void notifyObserversPlaneParked(int parkingBayNumber);    
}
