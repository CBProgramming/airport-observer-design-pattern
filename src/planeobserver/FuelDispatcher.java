/**
 * @author Christopher Burrell
 * Student number 7145969
 * ICA 1 - Part 2
 * Airport routing controller
 * Observer design pattern
 */
package planeobserver;

/**
 * FuelDispatcher class
 */
public class FuelDispatcher implements PlaneObserver
{
    /**
     * Empty constructor for FuelDispatcher
     */   
    FuelDispatcher()
    {

    }
    
    /**
     * Initiate dispatch of fuel to the relevant parking bay
     * @param parkingBayNumber bay number plane will park at
     * @param aircraftId aircraft Id
     */
    @Override
    public void updateBayAssigned(int parkingBayNumber, String aircraftId) 
    {
        System.out.println("Method call to dispatch fuel to aircraft " +
                aircraftId + " at parking bay " + parkingBayNumber + ".\n"); 
    }

    /**
     * Initiate resupply of fuel to the relevant parking bay
     * @param parkingBayNumber bay number plane will park at
     */    
    @Override
    public void updatePlaneParked(int parkingBayNumber) 
    {
        System.out.println("Method call to refuel aircraft at parking bay " + 
                parkingBayNumber + ".\n"); 
    }
}
