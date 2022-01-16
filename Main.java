
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import containers.BasicContainer;
import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import containers.RefrigeratedContainer;
import ports.Port;
import ships.Ship;
/**
 * This class provides us to run the program and has the main method.
 */
public class Main {
	/**
	 * This method is run first when the program starts and takes an input file and according to the inputs calls related parts from other classes and prints out the outputs to the output file.
	 * @param args the directions of input and output files	 
	 * @throws FileNotFoundException if there is an error to open the files in the paths given by the arguments.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		ArrayList<Container> myContainers = new ArrayList<Container>();
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ArrayList<Port> ports = new ArrayList<Port>();
		
		int cID=0;
		int sID=0;
		int pID=0;
		int N=in.nextInt();
		in.nextLine();
		for (int i=0;i<N;i++) {
			String line=in.nextLine();
			line = line.replaceAll("\\s+", " ");
			String[] lst = line.split(" ");
			int act=Integer.valueOf(lst[0]);
			if (act==1) {
				if (lst.length==4) {
					if (lst[3].charAt(0)=='R') {
						myContainers.add(new RefrigeratedContainer(cID,Integer.valueOf(lst[2])));
						ports.get(Integer.valueOf(lst[1])).getContainers().add(myContainers.get(cID));
						ports.get(Integer.valueOf(lst[1])).getRefrigeratedContainers().add(myContainers.get(cID));
					}if (lst[3].charAt(0)=='L') {
						myContainers.add(new LiquidContainer(cID,Integer.valueOf(lst[2])));
						ports.get(Integer.valueOf(lst[1])).getContainers().add(myContainers.get(cID));
						ports.get(Integer.valueOf(lst[1])).getLiquidContainers().add(myContainers.get(cID));
					}
				}else {
					if (Integer.valueOf(lst[2])<=3000) {
						myContainers.add(new BasicContainer(cID,Integer.valueOf(lst[2])));
						ports.get(Integer.valueOf(lst[1])).getContainers().add(myContainers.get(cID));
						ports.get(Integer.valueOf(lst[1])).getBasicContainers().add(myContainers.get(cID));
					}else {
						myContainers.add(new HeavyContainer(cID,Integer.valueOf(lst[2])));
						ports.get(Integer.valueOf(lst[1])).getContainers().add(myContainers.get(cID));
						ports.get(Integer.valueOf(lst[1])).getHeavyContainers().add(myContainers.get(cID));
					}
				}cID+=1;				
			}
			if (act==2) {
				Port pos=ports.get(Integer.valueOf(lst[1])); int mWeight=Integer.valueOf(lst[2]); int mNumberAll=Integer.valueOf(lst[3]); int mNumberHeavy=Integer.valueOf(lst[4]); int mNumberRefrigerated=Integer.valueOf(lst[5]); int mNumberLiquid=Integer.valueOf(lst[6]); double fuelConsumptionPerKm=Double.valueOf(lst[7]);
				ships.add(new Ship(sID, pos, mWeight, mNumberAll, mNumberHeavy, mNumberRefrigerated, mNumberLiquid, fuelConsumptionPerKm));
				sID+=1;
			}
			if (act==3) {
				double pX=Double.valueOf(lst[1]); double pY=Double.valueOf(lst[2]);
				ports.add(new Port(pID, pX, pY));
				pID+=1;
			}
			if (act==4) {
				Ship loadShip= ships.get(Integer.valueOf(lst[1])); Container loadCont=myContainers.get(Integer.valueOf(lst[2]));
				loadShip.load(loadCont);									
			}
			if (act==5) {
				Ship unloadShip= ships.get(Integer.valueOf(lst[1])); Container unloadCont=myContainers.get(Integer.valueOf(lst[2]));
				unloadShip.unLoad(unloadCont);
			}
			if (act==6) {
				Ship travelShip=ships.get(Integer.valueOf(lst[1])); Port destPort= ports.get(Integer.valueOf(lst[2]));
				travelShip.sailTo(destPort);				
			}
			if (act==7) {
				Ship fShip=ships.get(Integer.valueOf(lst[1])); double fuel=Double.valueOf(lst[2]);
				fShip.reFuel(fuel);
			}
			
		}			
		for (int i=0;i<pID;i++) {
			Port pp=ports.get(i);
			out.print("Port "+pp.getID()+": ("); out.printf("%.2f",pp.getX()); out.print(", "); out.printf("%.2f",pp.getY()); out.println(")");
			String Basic="BasicContainer:"; String Heavy="HeavyContainer:"; String Refrigerated="RefrigeratedContainer:"; String Liquid= "LiquidContainer:";
			if  (!pp.getBasicContainers().isEmpty()) {
				out.println("  "+Basic+Container.lsttostr(pp.getBasicContainers()));
			}
			if  (!pp.getHeavyContainers().isEmpty()) {
				out.println("  "+Heavy+Container.lsttostr(pp.getHeavyContainers()));
			}
			if  (!pp.getRefrigeratedContainers().isEmpty()) {
				out.println("  "+Refrigerated+Container.lsttostr(pp.getRefrigeratedContainers()));
			}
			if  (!pp.getLiquidContainers().isEmpty()) {
				out.println("  "+Liquid+Container.lsttostr(pp.getLiquidContainers()));
			}Collections.sort(pp.getCurrent());
			for (int j=0;j<pp.getCurrent().size();j++) {
				Ship ps=pp.getCurrent().get(j);
				out.print("  Ship "+ps.getID()+": ");out.printf("%.2f",ps.getFuel()); out.println();
				if (!ps.getBasicContainers().isEmpty()) {
					out.println("    "+Basic+Container.lsttostr(ps.getBasicContainers()));
				}
				if (!ps.getHeavyContainers().isEmpty()) {
					out.println("    "+Heavy+Container.lsttostr(ps.getHeavyContainers()));
				}
				if (!ps.getRefrigeratedContainers().isEmpty()) {
					out.println("    "+Refrigerated+Container.lsttostr(ps.getRefrigeratedContainers()));
				}
				if (!ps.getLiquidContainers().isEmpty()) {
					out.println("    "+Liquid+Container.lsttostr(ps.getLiquidContainers()));
				}
			}
		}		
		in.close();
		out.close();
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

