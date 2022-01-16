
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Represents a container.
 * This is an abstract class and has 2 child classes called BasicContainers and HeavyContainers.
 * A container has 3 fields.
 * This class has 1 constructor.
 * This class has some methods about containers.
 */
public abstract class Container implements Comparable<Container>{
	/**
	 * the ID of the container
	 */
	private final int ID;
	/**
	 * the weight of the container
	 */
	private final int weight;
	/**
	 * fuel consumption per unit weight of the container to carry the container for 1 km
	 */
	private double fuelConsumptionPerUnitWeight;
	/**
	 * Constructor for Containers. Assigns the container's ID and weight.
	 * @param ID the ID of the created container	
	 * @param weight the weight of the created container
	 */
	 public Container(int ID, int weight){
		this.ID=ID;
		this.weight=weight;
		
	}
	 /**
	  * Calculates required amount of fuel to carry the container for 1 km.
	  * @return Returns the required amount of fuel to carry the container for 1 km
	  */
	final public double consumption() {
		return weight*fuelConsumptionPerUnitWeight;
	}
	/**
	 * Checks whether two containers are equal or not according to their types, IDs and weights.
	 * @param other other container
	 * @return Returns true if the two containers' IDs and weights are the same and the two containers have the same type.Returns false if the two containers' IDs are not same or their weights are not same or they have not the same type.
	 * */
	public final boolean equals(Container other) {		
		if (this.getClass()==other.getClass() && ID==other.ID && weight==other.weight) {
			return true;
		}else {
			return false;
		}		
	}
	/**
	 * Compares two containers ID.
	 * @return Returns 0 if their IDs are the same.Returns 1 if this container's ID is bigger than the other container's ID. Returns -1 if this container's ID is smaller than the other container's ID.
	 * @param container the other container
	 */
	public final int compareTo(Container container) {
		if(this.ID==container.ID) {
			return 0;
		}
		if(this.ID > container.ID) {
			return 1;
		}
		if(this.ID < container.ID) {
			return -1;
		}
		else {
			return 1;
		}
	}
	/**
	 * Setter method for fuel consumption per unit weight of the container to carry the container for 1 km.
	 * @param fuelConsumptionPerUnitWeight The value which will be assigned for fuel consumption per unit weight of the container to carry the container for 1 km.
	 */
	public final void setFuelConsumptionPerUnitWeight(double fuelConsumptionPerUnitWeight) {
		this.fuelConsumptionPerUnitWeight = fuelConsumptionPerUnitWeight;
	}
	/**
	 * Getter method for the weight of the container
	 * @return the weight of the container
	 */
	public final int getWeight() {
		return weight;
	}
	/**
	 * Getter method for the ID of the container
	 * @return the ID of the container
	 */
	public final int getID() {
		return ID;
	}
	/**
	 * Sorts the given list according to its items' IDs and creates a string by concatenating its items IDs by a whitespace.
	 * @param lst the list which includes containers and which will be sorted and used to create a string
	 * @return string which includes sorted IDs of the containers in the given list and a whitespace between IDs
	 */
	public final static String lsttostr(ArrayList<Container> lst) {
		String mystr="";
		Collections.sort(lst);
		for (int i=0;i<lst.size();i++) {
			mystr+=" "+lst.get(i).ID;
		}
		return mystr;		
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

