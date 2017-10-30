/**
 * GuestProgram extends TravelProgram, storing each guest program
 * @author Celine
 *
 */
public class GuestProgram extends TravelProgram{

	/**
	 * constructor of GuestProgram
	 * @param programName
	 */
	public GuestProgram(String programName) {
		super(programName);
	}

	public String toString(){
		String str = "Frequent Guest Program \n";
		str+="Name: "+this.getProgramName()+"\n";
		for(int i = 0 ; i< this.numOfPackages(); i++){
			GuestPackage gpack = (GuestPackage) this.getPackage(i);
			str+="  "+gpack.getName()+": "+gpack.getPurchase()+" to redeem; "
					+ gpack.getAwardPerNight()+" awarded\n";
		}
		return str;
	}
}
