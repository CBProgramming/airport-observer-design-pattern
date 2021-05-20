/**
 * @author Christopher Burrell
 * Student number 7145969
 * ICA 1 - Part 2
 * Airport routing controller
 * Observer design pattern
 */
package planeobserver;

/**
 * LuggageDispatcher class
 */
public class LuggageDispatcher implements PlaneObserver
{
    /**
     * Empty constructor for LuggageDispatcher
     */   
    LuggageDispatcher()
    {

    }
    
    /**
     * Initiate dispatch of luggage to the relevant parking bay
     * @param parkingBayNumber bay number plane will park at
     * @param aircraftId id of plane (second char denotes luggage type)
     */    
    @Override
    public void updateBayAssigned(int parkingBayNumber, String aircraftId) 
    {
        if (aircraftId == null || aircraftId.trim().length() < 2)
        {
            System.out.println("Invalid aircraft id received for luggage " +
                    "processing. Method call to investigate luggage "
                    + "requirements.\n");
        }
        else
        {
            aircraftId = aircraftId.trim();
            if (Character.isLetter(aircraftId.charAt(1)))
                dispatchLuggageContainer(parkingBayNumber,aircraftId);
            else if (Character.isDigit(aircraftId.charAt(1)))
                dispatchLooseLuggage(parkingBayNumber,aircraftId);
            else
                System.out.println("Plane id indicates no luggage required. "+
                       "Method call to investigate luggage requirements.\n");            
        }
    }
    
    /**
     * Dispatch luggage container to parking bay
     * @param parkingBayNumber bay number plane will park at
     * @param aircraftId id of plane
     */
    public void dispatchLuggageContainer(int parkingBayNumber, String aircraftId)
    {
        System.out.println("Method call to dispatch luggage container to " +
                "parking bay " + parkingBayNumber + " for plane " + aircraftId + 
                ".\n");
    }
    
    /**
     * Dispatch loose luggage to parking bay
     * @param parkingBayNumber bay number plane will park at
     * @param aircraftId id of plane
     */
    public void dispatchLooseLuggage (int parkingBayNumber, String aircraftId)
    {
        System.out.println("Method call to dispatch loose luggage to " +
                "parking bay " + parkingBayNumber + " for plane " + aircraftId + 
                ".\n");
    }

    /**
     * Initiate loading luggage onto plane
     * @param parkingBayNumber bay number plane is parked at
     */       
    @Override
    public void updatePlaneParked(int parkingBayNumber) 
    {
        System.out.println("Method call to begin loading luggage at " + 
                "parking bay " + parkingBayNumber + ".\n"); 
    }
}
