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
public interface PlaneObserver 
{

    /**
     * Initiate dispatch of resources to the relevant parking bay
     * @param parkingBayNumber bay number plane will park at
     * @param aircraftId id of plane
     */
    public void updateBayAssigned(int parkingBayNumber, String aircraftId);
    
    /**
     * Initiate resupply of resources to parked plane
     * @param parkingBayNumber bay number plane is parked at
     */
    public void updatePlaneParked(int parkingBayNumber);  
}