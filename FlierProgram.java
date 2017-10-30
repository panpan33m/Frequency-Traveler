/**
 * FlierProgram extends TravelProgram, storing each flier program
 * @author Celine
 *
 */
public class FlierProgram extends TravelProgram {
	private int miles;
	/**
	 * constructor of FlierProgram
	 * @param programName
	 * @param miles
	 */
	public FlierProgram(String programName, int miles) {
		super(programName);
		this.miles = miles;
	}
	
	/**
	 * getter of miles
	 * @return
	 */
	public int getMiles() {
		return miles;
	}
	
	public String toString(){
		String str = "Frequent Flier Program \n";
		str+="Name: "+this.getProgramName()+"\n";
		for(int i = 0 ; i< this.numOfPackages(); i++)
			str+="  "+this.getPackage(i).getName()+": "+this.getPackage(i).getPurchase()+" to redeem"+"\n";
		str+="  Minimum miles allowed = "+miles;
		return str;
	}
	

	

}
