
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * Represents a heavy container.
 * This class is a child class of Container class.
 * This class has 2 child classes called LiquidContainer and RefrigeratedContainer.
 * This class has 1 constructor
 */
public class HeavyContainer extends Container {
	/**
	 * Constructor for heavy containers. Assigns the container's ID, weight and fuel consumption per unit weight to carry it for 1 km.
	 * @param ID the ID of the container	
	 * @param weight the weight of the container
	 */
	public HeavyContainer(int ID, int weight){
		super(ID,weight);
		setFuelConsumptionPerUnitWeight(3.00);		
	}
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

