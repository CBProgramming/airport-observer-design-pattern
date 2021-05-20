/**
 * @author Christopher Burrell
 * Student number 7145969
 * ICA 1 - Part 2
 * Airport routing controller
 * Observer design pattern
 */
package planeobserver;

/**
 * PlaneSubject interface
 */
public interface PlaneSubject 
{

    /**
     * Register a plane observer
     * @param o Observer to be registered
     */
    public void registerObserver(PlaneObserver o);
    
    /**
     * Unregister a plane observer
     * @param o Observer to be unregistered
     */
    public void unregisterObserver(PlaneObserver o);
    
    /**
     * Notify plane observers of an assigned parking bay for a plane
     */
    public void notifyObserversAssignedBay();    
    
    /**
     * Notify plane observers that a plane is ready to be resupplied
     */
    public void notifyObserversPlaneParked();       
}
