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
 * Plane class
 */
public class Plane implements PlaneSubject
{
    private String aircraftId;
    private int parkingBayNumber;
    private boolean parked;
    private ArrayList<PlaneObserver> observers;

    /**
     * Constructor for Plane object
     * @param aircraftId unique identifier for Plane object
     */       
    Plane(String aircraftId)
    {
        this.aircraftId = aircraftId;
        parkingBayNumber = 0;
        observers = new ArrayList<>();
        parked = false;
    }
    
    /**
     * updates parking bay number for plane then notifies plane observers
     * @param parkingBayNumber parking bay number plane has been assigned to park at
     */
    public void assignBay(int parkingBayNumber)
    {
        this.parkingBayNumber = parkingBayNumber;
        System.out.println("\n\n\n\nParking bay " + parkingBayNumber +
                 " assigned to aircraft " + aircraftId + "\n");
        notifyObserversAssignedBay();
    }
    
    /**
     * Notify plane observers of an assigned parking bay for a plane
     */
    @Override
    public void notifyObserversAssignedBay() 
    {
        for (PlaneObserver o : observers)
        {
            System.out.println("Notifying " + o.getClass().getSimpleName() + 
                    " observer of plane " + aircraftId + " assignment to "
                            + "parking bay " + parkingBayNumber + ".");
            o.updateBayAssigned(parkingBayNumber,aircraftId);
        }        
    }
    
    /**
     * Parks plane then notifies plane observers
     */
    public void park()
    {
        if (!parked)
        {
            parked = true;
            System.out.println("\n\n\n\nPlane " + aircraftId + " parked at "
                    + "parking bay " + this.parkingBayNumber + ".\n");
            notifyObserversPlaneParked();            
        }
        else
        {
            System.out.println("Plane already parked.  Method call to "
                    + "investigate");
        }

    }    
    
    /**
     * Notify plane observers that a plane is ready to be resupplied
     */
    @Override
    public void notifyObserversPlaneParked() 
    {
        for (PlaneObserver o : observers)
        {
            System.out.println("Notifying " + o.getClass().getSimpleName() + 
                    "observer that plane " + aircraftId + " has parked.");
            o.updatePlaneParked(parkingBayNumber);
        }   
    }    
    
    /**
     * Register a plane observer
     * @param o Observer to be registered
     */
    @Override
    public void registerObserver(PlaneObserver o) 
    {
        if(!observers.contains(o) && o != null)
        {
        this.observers.add(o);
        }
    }

    /**
     * Unregister a plane observer
     * @param o Observer to be unregistered
     */
    @Override
    public void unregisterObserver(PlaneObserver o) 
    {
        this.observers.remove(o);        
    }
 
    /**
     * Get aircraftId
     * @return aircraftId
     */    
    public String getAircraftId()
    {
        return aircraftId;
    }
    
    /**
     * Get parkingBayNumber
     * @return parkingBayNumber
     */ 
    public int getParkingBayNumber() 
    {
        return parkingBayNumber;
    }

    /**
     * Get parked
     * @return parked
     */     
    public boolean isParked() 
    {
        return parked;
    }
    
    /**
     * Get observers
     * @return observers
     */ 
    public ArrayList<PlaneObserver> getObservers() 
    {
        return observers;
    }

    /**
     * Set aircraftId
     * @param aircraftId id of aircraft
     */  
    public void setAircraftId(String aircraftId) 
    {
        this.aircraftId = aircraftId;
    }

    /**
     * Set parkingBayNumber
     * @param parkingBayNumber parking bay number
     */      
    public void setParkingBayNumber(int parkingBayNumber) 
    {
        this.parkingBayNumber = parkingBayNumber;
    }
    
    /**
     * Set parked
     * @param parked true if plane is parked
     */  
    public void setParked(boolean parked) 
    {
        this.parked = parked;
    }
    
    /**
     * Set observers
     * @param observers list of observers
     */      
    public void setObservers(ArrayList<PlaneObserver> observers) 
    {
        this.observers = observers;
    }    
}
