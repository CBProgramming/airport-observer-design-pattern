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
 * FoodDispatcher class
 */
public class FoodDispatcher implements PlaneObserver, FoodSubject
{
    private ArrayList<FoodObserver> observers;
    
    /**
     * Constructor for FoodDispatcher
     */   
    FoodDispatcher()
    {
        observers = new ArrayList<>();
    }
    
    /**
     * Constructor for FoodDispatcher
     * @param observers list of observers
     */   
    FoodDispatcher(ArrayList<FoodObserver> observers)
    {
        this.observers = new ArrayList<>();
        for (FoodObserver o : observers)
        {
            this.observers.add(o);
        }        
    }   
    
    /**
     * Constructor for FoodDispatcher
     * @param observer FoodObserver to add to observers array list
     */   
    FoodDispatcher(FoodObserver observer)
    {
        observers = new ArrayList<>();
        observers.add(observer);
    }      
    
    /**
     * Initiate dispatch of food to the relevant parking bay
     * @param parkingBayNumber bay number plane will park at
     * @param aircraftId aircraft Id
     */    
    @Override
    public void updateBayAssigned(int parkingBayNumber, String aircraftId) 
    {
        int quantity = getQuantity(aircraftId);
        switch (quantity) 
        {
            case -1:
                System.out.println("Invalid aircraft id for food dispatch. " +
                        "Method call to investigate food requirements.\n");
                break;
            case 0:
                System.out.println("Aircraft id indicates no food required. " +
                        "Method call to investigate food requirements.\n");
                break;
            default:
                notifyObserversAssignedBay(parkingBayNumber,quantity);
                break;             
        }
    }
    
    /**
     * Notify food observers of parking bay number and quantity of food needed
     * @param parkingBayNumber bay number to dispatch to
     * @param quantity units of food to dispatch
     */
    @Override
    public void notifyObserversAssignedBay(int parkingBayNumber,int quantity) 
    {
        for (FoodObserver o : observers)
        {
            o.updateBayAssigned(parkingBayNumber,quantity);
        } 
    }
    
    /**
     * Initiate loading of food to parked plane
     * @param parkingBayNumber bay number plane will park at
     */    
    @Override
    public void updatePlaneParked(int parkingBayNumber) 
    {    
        notifyObserversPlaneParked(parkingBayNumber);
    }

    /**
     * Notify food observers that plane is ready to be loaded
     * @param parkingBayNumber bay number to dispatch to
     */
    @Override
    public void notifyObserversPlaneParked(int parkingBayNumber) 
    {
        for (FoodObserver o : observers)
        {
            o.updatePlaneParked(parkingBayNumber);
        } 
    }
    
    /**
     * Register a food observer
     * @param o Observer to be registered
     */
    @Override
    public void registerObserver(FoodObserver o) 
    {
        if(!observers.contains(o) && o != null)
        {
            observers.add(o);
        }
    }

     /**
     * Unregister a food observer
     * @param o Observer to be unregistered
     */
    @Override
    public void unregisterObserver(FoodObserver o) 
    {
        observers.remove(o);  
    }
    
    /**
     * Get list of observers
     * @return array list of observers
     */
    public ArrayList<FoodObserver> getObservers() 
    {
        return observers;
    }
    
    /**
     * Set list of observers
     * @param observers array list of observers
     */
    public void setObservers(ArrayList<FoodObserver> observers) 
    {
        this.observers = observers;
    }

    /**
     * Get quantity of food needed for plane
     * @param aircraftId id code assigned based on quantity of food needed
     * @return quantity of food to be dispatch
     */
    public int getQuantity(String aircraftId) 
    {        
        if(aircraftId == null)
        {
            return -1;
        }
        aircraftId = aircraftId.trim();
        if (aircraftId.isEmpty())
        {
            return -1;
        }
        else if ((aircraftId.charAt(0) >= 'a' && aircraftId.charAt(0) <= 'm')
                || (aircraftId.charAt(0) >= 'A' && aircraftId.charAt(0) <= 'M'))
        {
            return 50;
        }
        else if((aircraftId.charAt(0) >= 'n' && aircraftId.charAt(0) <= 'z')
                || (aircraftId.charAt(0) >= 'N' && aircraftId.charAt(0) <= 'Z'))
        {
            return 100;
        }
        else
        {
            return 0;  
        }
    }
}