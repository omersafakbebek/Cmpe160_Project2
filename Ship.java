
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;

import java.util.ArrayList;
import java.util.Collections;

import containers.BasicContainer;
import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
import interfaces.IShip;
import ports.Port;
/**
 * Represents a ship.
 * A ship has 14 fields.
 * This class has 1 constructor.
 * This class has some methods about ships. 
 */
public class Ship implements IShip,Comparable<Ship> {
	/**
	 * ID of the ship.
	 */
	private final int ID;
	/**
	 * the current amount of fuel of the ship.
	 */
	private double fuel;
	/**
	 * The port which includes the ship currently.
	 */
	private Port currentPort;
	/**
	 * The list of the containers which are currently on the ship.
	 */
	private final ArrayList<Container> currentContainers;
	/**
	 * The list of the basic containers which are currently on the ship.
	 */
	private final ArrayList<Container> basicContainers;
	/**
	 * The list of the heavy (but not liquid nor refrigerated) containers which are currently on the ship.
	 */
	private final ArrayList<Container> heavyContainers;
	/**
	 * The list of the refrigerated containers which are currently on the ship.
	 */
	private final ArrayList<Container> refrigeratedContainers;
	/**
	 * The list of the liquid containers which are currently on the ship.
	 */
	private final ArrayList<Container> liquidContainers;
	/**
	 * The maximum weight that the ship can have.
	 */
	private final int maxWeight;
	/**
	 * The maximumum number of all containers that the ship can carry.
	 */
	private final int maxNumberOfAllContainers;
	/**
	 * The maximumum number of heavy (includes liquid and refrigerated) containers that the ship can carry.
	 */
	private final int maxNumberOfHeavyContainers;
	/**
	 * The maximumum number of refrigerated containers that the ship can carry.
	 */
	private final int maxNumberOfRefrigeratedContainers;
	/**
	 * The maximumum number of liquid containers that the ship can carry.
	 */
	private final int maxNumberOfLiquidContainers;
	/**
	 * The amount of fuel consumed per kilometer when the ship is empty.
	 */
	private final double fuelConsumptionPerKM;
	/**
	 * Constructor for ship objects. Assigns the ship's ID, port, and fuel , maxWeight, maxNumberOfAllContainers, maxNumberOfHeavyContainers, maxNumberOfRefrigeratedContainers,maxNumberOfLiquidContainers, fuelConsumptionPerKM and adds the ship to the list of the ships currently in the current port of the ship.  Also creates empty lists for currentContainers, basicContainers, heavyContainers, refrigeratedContainers, liquidContainers.
	 * @param ID the ID number of the created ship.
	 * @param p represents the port in which the ship is created.
	 * @param totalWeightCapacity The maximum weight that the created ship can have.
	 * @param maxNumberOfAllContainers The maximumum number of all containers that the created ship can carry.
	 * @param maxNumberOfHeavyContainers The maximumum number of heavy (includes liquid and refrigerated) containers that the created ship can carry.
	 * @param maxNumberOfRefrigeratedContainers The maximumum number of refrigerated containers that the created ship can carry.
	 * @param maxNumberOfLiquidContainers The maximumum number of liquid containers that the created ship can carry.
	 * @param fuelConsumptionPerKM The amount of fuel consumed per kilometer when the created ship is empty.
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM) {
		this.ID=ID;
		currentPort=p;
		fuel=0;
		p.getCurrent().add(this);		
		maxWeight=totalWeightCapacity;
		this.maxNumberOfAllContainers=maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers=maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers=maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers=maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM=fuelConsumptionPerKM;
		currentContainers= new ArrayList<Container>();
		basicContainers= new ArrayList<Container>();
		heavyContainers = new ArrayList<Container>();
		refrigeratedContainers= new ArrayList<Container>();
		liquidContainers= new ArrayList<Container>();
	}	
	/**
	 * Sorts the list of the containers currently on the ship.
	 * @return Returns the sorted list of the containers currently on the ship.
	 */
	public final ArrayList<Container> getCurrentContainers() {
		Collections.sort(currentContainers);
		return currentContainers;
	}
	/**
	 * Calculates the amount of fuel required for the ship to travel 1 kilometer.
	 * @return Returns the amount of fuel required for the ship to travel 1 kilometer.
	 */
	public final double calculateFuelConsumptionPerKM() {
		double total=fuelConsumptionPerKM;
		for (int i=0; i<currentContainers.size();i++) {
			total+=currentContainers.get(i).consumption();
		}
		return total;
	}
	/**
	 * Calculates the total weight of the containers which are currently on the ship.
	 * @return Returns the total weight of the containers which are currently on the ship
	 */
	public final int calculateWeight() {
		int totalW=0;
		for (int i=0; i<currentContainers.size();i++) {
			totalW+=currentContainers.get(i).getWeight();
		}
		return totalW;
	}
	/**
	 * Checks if the ship can travel to the destination port according to its current amount of fuel and required amount fuel to travel to the destination port.
	 * Calls travel method if it can travel to the destinatation port and does the related sail operations.
	 * @see travel
	 * @return Returns true if the ship has enough amount of fuel to travel to the target port. Returns false if the ship has not enough amount of fuel to travel to the target port.
	 * @param p the target port.
	 */
	public final boolean sailTo(Port p) {
		if (currentPort.getDistance(p)*calculateFuelConsumptionPerKM()<=getFuel()) {
			travel(p);
			return true;
		}
		else {			
			return false;
		}
	}
	/**
	 * Adds the given amount of fuel into the ship.
	 * @param newFuel the amount of fuel added into the ship
	 */
	public final void reFuel(double newFuel){
		fuel = getFuel() + newFuel;		
	}
	/**
	* Checks whether the container can be loaded into the ship according to existence of the container in the current port, limitation of maximum number of containers on the ship, limitation of maximum number of the containers which are on the ship and which have the same type as the container and maximum weight of the containers on the ship that the ship can carry.
	* If container can be loaded, calls loadOperation method and does the related load operations.
	* @return Returns true if the container can be loaded. Otherwise returns false.	
	* @param cont the container wanted to be loaded into the ship.
	*/
	public final boolean load(Container cont) {
		if (currentPort.getContainers().contains(cont)) {
			if(cont.getWeight()+calculateWeight()<=maxWeight && currentContainers.size()+1<=maxNumberOfAllContainers){
				if(cont instanceof BasicContainer) {
					loadOperation(cont);
					return true;
				}else if (cont instanceof RefrigeratedContainer) {
					if(getHeavyContainers().size()+getRefrigeratedContainers().size()+getLiquidContainers().size()+1<=maxNumberOfHeavyContainers && getRefrigeratedContainers().size()+1<=maxNumberOfRefrigeratedContainers) {
						loadOperation(cont);
						return true;
					}else {
						return false;
					}					
				}else if (cont instanceof LiquidContainer) {
					if(getHeavyContainers().size()+getRefrigeratedContainers().size()+getLiquidContainers().size()+1<=maxNumberOfHeavyContainers && getLiquidContainers().size()+1<=maxNumberOfLiquidContainers) {
						loadOperation(cont);
						return true;
					}else {
						return false;
					}
				}else if(cont instanceof HeavyContainer) {
					if (getHeavyContainers().size()+getRefrigeratedContainers().size()+getLiquidContainers().size()+1<=maxNumberOfHeavyContainers) {
						loadOperation(cont);
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			}else {
				return false;
			}
		}else {
			return false;
		}		
	}
	/**
	 * Does the necessary load operations.	 
	 * Adds the container to the list of the containers currently on the ship and the list of the containers which are currently on the ship and which have the same container type  as the container if the container can be loaded.
	 * Removes the container from the list of the containers currently in the port and the list of the containers which are currently in the port and which have the same container type as the container if the container can be loaded.
	 * @param cont the container wanted to be loaded into the ship.
	 */
	public final void loadOperation(Container cont) {		
				
			currentContainers.add(cont);
			currentPort.getContainers().remove(cont);
			if (cont instanceof BasicContainer) {
				getBasicContainers().add(cont);
				currentPort.getBasicContainers().remove(cont);
			}else if(cont instanceof RefrigeratedContainer) {
				getRefrigeratedContainers().add(cont); 
				currentPort.getRefrigeratedContainers().remove(cont);
			}else if (cont instanceof LiquidContainer) {
				getLiquidContainers().add(cont);
				currentPort.getLiquidContainers().remove(cont);
			}else if (cont instanceof HeavyContainer) {
				getHeavyContainers().add(cont);
				currentPort.getHeavyContainers().remove(cont);
			}
		
	}
	/**
	 * Checks if the container can be unloaded from the ship according to existence of the container on the ship.
	 * Calls the unLoadOperation method if the container can be unloaded from the ship and does the related unload operations.
	 * @return Returns true if the container can be unloaded from the ship.Otherwise returns false.	
	 * @param cont the container wanted to be unloaded from the ship.
	 */
	public final boolean unLoad(Container cont) {
		if (currentContainers.contains(cont)) {
			unLoadOperation(cont);
			return true;
		}else {
			return false;
		}
		
	}
	/**
	 * Does the necessary unload operations.	 
	 * Removes the container from the list of the containers currently on the ship and the list of the containers which are currently on the ship and which have the same container type as the container if the container can be unloaded.
	 * Adds the container to the list of the containers currently in the port and the list of the containers which are currently in the port and which have the same container type  as the container if the container can be unloaded.
	 * @param cont the container wanted to be unloaded from the ship.
	 */
	public final void unLoadOperation(Container cont) {
		
			currentContainers.remove(cont);
			currentPort.getContainers().add(cont);
			if (cont instanceof BasicContainer) {
				getBasicContainers().remove(cont);
				currentPort.getBasicContainers().add(cont);
			}else if(cont instanceof RefrigeratedContainer) {
				getRefrigeratedContainers().remove(cont);
				currentPort.getRefrigeratedContainers().add(cont);
			}else if (cont instanceof LiquidContainer) {
				getLiquidContainers().remove(cont); 
				currentPort.getLiquidContainers().add(cont);
			}else if (cont instanceof HeavyContainer) {
				getHeavyContainers().remove(cont);
				currentPort.getHeavyContainers().add(cont);
			}
		
	}
	/**
	 * Does the related travel operations.  
	 * Calls outgoingShip method with parameter the ship for the current port of the ship and incomingShip method with parameter the ship for the target port.
	 * Removes used amount of fuel from current amount of fuel of the ship .
	 * @param p the target port.
	 */
	public final void travel(Port p) {		
		fuel = getFuel() - calculateFuelConsumptionPerKM()*currentPort.getDistance(p);
		currentPort.outgoingShip(this); p.incomingShip(this);							
	}
	/**
	 * Compares two ship's ID's.
	 * @return Returns 0 if they have the same ID. Returns 1 if the ID of this ship is bigger than the ID of the other ship.Returns -1 if the ID of this ship is smaller than the ID of the other ship.
	 * @param s the other ship
	 */
	public final int compareTo(Ship s) {
		if(this.ID==s.ID) {
			return 0;
		}
		if(this.ID > s.ID) {
			return 1;
		}
		if(this.ID < s.ID) {
			return -1;
		}
		else {
			return 1;
		}
	}
	/**
	 * Getter method for the current amount of fuel of the ship.
	 * @return Returns the current amount of fuel of the ship.
	 */
	public final double getFuel() {
		return fuel;
	}
	/**
	 * Getter method for ID of the ship.
	 * @return Returns the ID of the ship.
	 */
	public final int getID() {
		return ID;
	}
	/**
	 * Getter method for the list of the basic containers currently on the ship.
	 * @return Returns the list of the basic containers currently on the ship
	 */
	public final ArrayList<Container> getBasicContainers() {
		return basicContainers;
	}
	/**
	 * Getter method for the list of the heavy(but not refrigerated nor liquid) containers currently on the ship.
	 * @return Returns the list of the heavy(but not refrigerated nor liquid) containers currently on the ship
	 */
	public final ArrayList<Container> getHeavyContainers() {
		return heavyContainers;
	}
	/**
	 * Getter method for the list of the refrigerated containers currently on the ship.
	 * @return Returns the list of the refrigerated containers currently on the ship
	 */
	public final ArrayList<Container> getRefrigeratedContainers() {
		return refrigeratedContainers;
	}
	/**
	 * Getter method for the list of the liquid containers currently on the ship.
	 * @return Returns the list of the liquid containers currently on the ship
	 */
	public final ArrayList<Container> getLiquidContainers() {
		return liquidContainers;
	}
	/**
	 * Setter method for the current port of the ship. 
	 * @param currentPort The new current port.
	 */
	public void setCurrentPort(Port currentPort) {
		this.currentPort = currentPort;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

