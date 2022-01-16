
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;

import interfaces.IPort;
import ships.Ship;

import java.util.ArrayList;

import containers.Container;
/**
 * Represents a port.
 * A port has 10 fields.
 * This class has 1 constructor.
 * This class has some methods about ports.
 */
public class Port implements IPort {
	/**
	 * the ID of the port
	 */
	private final int ID;
	/**
	 *the X coordinate of the port
	 */
	private final double X;
	/**
	 * the Y coordinate of the port
	 */
	private final double Y;
	/**
	 * The list of the containers which are currently in the port.
	 */
	private final ArrayList<Container> containers;
	/**
	 * The list of the basic containers which are currently in the port.
	 */
	private final ArrayList<Container> basicContainers;
	/**
	 * The list of the heavy (but not refrigerated nor liquid) containers which are currently in the port.
	 */
	private final ArrayList<Container> heavyContainers;
	/**
	 * The list of the refrigerated containers which are currently in the port.
	 */
	private final ArrayList<Container> refrigeratedContainers;
	/**
	 * The list of the liquid containers which are currently in the port.
	 */
	private final ArrayList<Container> liquidContainers;
	/**
	 * The list of the ships which have visited and left the port at least 1 time.
	 */
	private final ArrayList<Ship> history;
	/**
	 * The list of ships which are currently in the port.
	 */
	private final ArrayList<Ship> current;
	/**
	 * Constructor for port objects. Assigns the port's ID , X coordinate and Y coordinate. Also creates empty lists for containers, basicContainers, heavyContainers, refrigeratedContainers, liquidContainers, history and current fields.
	 * @param ID the ID of the created port.
	 * @param X the X coordinate of the created port.
	 * @param Y the Y coordinate of the created port.
	 */
	public Port(int ID,double X,double Y){
		this.ID=ID;
		this.X=X;
		this.Y=Y;
		containers= new ArrayList<Container>();
		history= new ArrayList<Ship>();
		current= new ArrayList<Ship>();
		basicContainers= new ArrayList<Container>();
		heavyContainers = new ArrayList<Container>();
		refrigeratedContainers= new ArrayList<Container>();
		liquidContainers= new ArrayList<Container>();
		
	}
	/**
	 * Calculates the distance between this port and the other port.
	 * @param other the other port
	 * @return Returns the distance between the port and the other port.
	 */
	public final double getDistance(Port other) {
		return Math.pow(Math.pow(X-other.X, 2)+Math.pow(Y-other.Y, 2),0.5);
	}
	/**
	 * Adds the incoming ship to the list of the ships currently in the port if the list does not contain that ship.Also changes the current port of the ship s to this port.
	 * @param s the ship which comes to the port (incoming ship)
	 */
	public final void incomingShip(Ship s) {
		s.setCurrentPort(this);
		if (!current.contains(s)) {
			current.add(s);
		}
	}	
	/**
	 * Adds the outgoing ship to the list of the ships which have visited and left the port at least 1 time if the list does not contain that ship.
	 * @param s the ship which leaves the port (outgoing ship)
	 */
	public final void outgoingShip(Ship s) {
		current.remove(s);
		if (!history.contains(s)) {
			history.add(s); 
		}
	}
	/**
	 * Getter method for the list of the containers which are currently in the port.
	 * @return Returns the list of the containers which are currently in the port
	 */
	public final ArrayList<Container> getContainers() {
		return containers;
	}
	/**
	 * Getter method for the list of the basic containers which are currently in the port.
	 * @return Returns the list of the basic containers which are currently in the port
	 */
	public final ArrayList<Container> getBasicContainers() {
		return basicContainers;
	}
	/**
	 * Getter method for the list of the refrigerated containers which are currently in the port.
	 * @return Returns the list of the refrigerated containers which are currently in the port
	 */
	public final ArrayList<Container> getRefrigeratedContainers() {
		return refrigeratedContainers;
	}
	/**
	 * Getter method for the list of the liquid containers which are currently in the port.
	 * @return Returns the list of the liquid containers which are currently in the port
	 */
	public final ArrayList<Container> getLiquidContainers() {
		return liquidContainers;
	}
	/**
	 * Getter method for the list of the heavy (but not refrigerated nor liquid) containers which are currently in the port.
	 * @return Returns the list of the heavy (but not refrigerated nor liquid) containers which are currently in the port
	 */
	public final ArrayList<Container> getHeavyContainers() {
		return heavyContainers;
	}
	/**
	 * Getter method for the list of the ships which are currently in the port.
	 * @return Returns the list of the ships which are currently in the port
	 */	
	public final ArrayList<Ship> getCurrent() {
		return current;
	}
	/**
	 * Getter method for the X coordinate of the port.
	 * @return Returns the X coordinate of the port.
	 */
	public final double getX() {
		return X;
	}
	/**
	 * Getter method for the Y coordinate of the port.
	 * @return Returns the Y coordinate of the port.
	 */
	public final double getY() {
		return Y;
	}
	/**
	 * Getter method for the ID of the port.
	 * @return Returns the ID of the port.
	 */
	public final int getID() {
		return ID;
	}	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

