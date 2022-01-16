
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * Represents a liquid container.
 * This class is a child class of HeavyContainer class.
 * This class has 1 constructor
 */
public class LiquidContainer extends HeavyContainer {
	/**
	 * Constructor for liquid containers. Assigns the container's ID, weight and fuel consumption per unit weight to carry it for 1 km.
	 * @param ID the ID of the container	
	 * @param weight the weight of the container
	 */
	public LiquidContainer(int ID, int weight){
		super(ID,weight);
		setFuelConsumptionPerUnitWeight(4.00);
	}

}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

