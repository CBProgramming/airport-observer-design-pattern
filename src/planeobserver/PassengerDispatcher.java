/**
 * @author Christopher Burrell
 * Student number 7145969
 * ICA 1 - Part 2
 * Airport routing controller
 * Observer design pattern
 */
package planeobserver;

/**
 * PassengerDispatcher class
 */
public class PassengerDispatcher implements PlaneObserver
{
    /**
     * Empty constructor for PassengerDispatcher
     */    
    PassengerDispatcher()
    {

    }
    
    /**
     * Initiate dispatch of passengers to the relevant parking bay
     * @param parkingBayNumber bay number plane will park at
     * @param aircraftId id of plane
     */
    @Override
    public void updateBayAssigned(int parkingBayNumber, String aircraftId) 
    {
        System.out.println("Method call to dispatch passengers to aircraft " + 
                aircraftId + " at parking bay " + parkingBayNumber + ".\n"); 
    }

    /**
     * Initiate passenger boarding
     * @param parkingBayNumber bay plane is parked at
     */    
    @Override
    public void updatePlaneParked(int parkingBayNumber) 
    {
        System.out.println("Method call to begin boarding passengers at " + 
                "parking bay " + parkingBayNumber + ".\n"); 
    }
}
