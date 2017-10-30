import java.util.ArrayList;
/**
 * TravelProgram is the parent class of all travel programs
 * It stores the data of each program's name and the packages the program has
 * It can later be referred to compare package data with user miles/points
 * @author Celine
 *
 */
public class TravelProgram {
	private String programName;
	private ArrayList<TravelPackage> packages;
	private int ID = 0;

	/**
	 * constructor of TravelProgram
	 * create an ArrayList of TravelPackage
	 * @param programName
	 */
	public TravelProgram(String programName) {
		this.programName = programName;
		packages = new ArrayList<TravelPackage>();
	}
	
	/**
	 * method to generate ID for traveler
	 * each travel program has its own ID system
	 * @return
	 */
	public int generateID(){
		ID++;
		return ID;
	}
	
	/**
	 * add a TravelPackage to the program
	 * @param pack
	 */
	public void addPackage(TravelPackage pack){
		packages.add(pack);
	}

	/**
	 * getter of package
	 * @param index
	 * @return
	 */
	public TravelPackage getPackage(int index){
		return packages.get(index);
	}
	
	/**
	 * getter of program name
	 * @return
	 */
	public String getProgramName() {
		return programName;
	}

	/**
	 * getter of the number of packages in the program
	 * @return
	 */
	public int numOfPackages(){
		return packages.size();
	}
}
