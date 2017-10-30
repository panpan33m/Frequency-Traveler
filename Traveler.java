import java.util.ArrayList;
/**
 * Traveler class stores information related to the traveler
 * @author Celine
 *
 */
public class Traveler {
	private String name;
	private ArrayList<TravelProgram> memberOf;
	private ArrayList<Integer> points;
	private ArrayList<Integer> membershipNum;

	/**
	 * constructor of Traveler
	 * @param name
	 */
	public Traveler(String name) {
		this.name = name;
		memberOf = new ArrayList<TravelProgram>();
		points = new ArrayList<Integer>();
		membershipNum = new ArrayList<Integer>();
	}

	/**
	 * add a TravelProgram to the traveler
	 * At the same location of ArrayList memberOf, ArrayList points and ArrayList membershipNum, 
	 * add the regarding information about this program related to the traveler
	 * @param program
	 */
	public void addMembership(TravelProgram program){
		memberOf.add(program);
		points.add(0);
		membershipNum.add(program.generateID());
	}

	/**
	 * check if this traveler belongs to a certain program
	 * @param program
	 * @return
	 */
	public boolean belongTo(TravelProgram program){
		for(int i=0; i<memberOf.size(); i++){
			//if an element of memberOf matches the program's name
			if (memberOf.get(i).getProgramName().equals(program.getProgramName())){
				return true;
			}
		}
		return false;
	}

	/**
	 * getter of the size of the ArrayList of programs the traveler is member of
	 * @return
	 */
	public int size(){
		return memberOf.size();
	}

	/**
	 * getter of a travel program at certain position
	 * @param index
	 * @return
	 */
	public TravelProgram getTravelProgram(int index){
		return memberOf.get(index);
	}

	/**
	 * get the points the travel has of a certain program
	 * If the traveler is not a member of that program, return -1 to show error
	 * @param programSel
	 * @return
	 */
	public int getPoints(TravelProgram programSel) {
		for(int i = 0; i<memberOf.size(); i++)
		{
			if (memberOf.get(i).getProgramName().equals(programSel.getProgramName())){
				return points.get(i);
			}
		}
		return -1;
	}



	/**
	 * add points by purchasing a plane ticket 
	 * with the appropriate number of miles to the user’s frequent flyer account
	 * @param programSel
	 * @param travelerNum
	 * @param miles
	 */
	public void pointsCreditFlier(TravelProgram programSel, int travelerNum, int miles, int minMiles) {
		int index = -1;
		for(int i = 0 ; i<memberOf.size(); i++)
		{
			if (memberOf.get(i).getProgramName().equals(programSel.getProgramName())){
				index = i;
			}
		}
		
		if (travelerNum*miles < minMiles)
			points.set(index, 0);
		else{
			points.set(index, points.get(index) + travelerNum*miles);
		}
	}

	/**
	 * deduce points when using points to purchase a plane ticket 
	 * with the appropriate number of miles to the user’s frequent flyer account
	 * @param programSel
	 * @param nightsNum
	 * @param awardPerNight
	 */
	public void usingPointsFlier(TravelProgram programSel, int travelerNum, int miles) {
		int index = -1;
		for(int i = 0 ; i<memberOf.size(); i++)
		{
			if (memberOf.get(i).getProgramName().equals(programSel.getProgramName())){
				index = i;
			}
		}
		points.set(index, points.get(index) - travelerNum*miles);
	}

	/**
	 * deduce points when using points to book a hotel 
	 * with the appropriate number of points to the user’s frequent guest account 
	 * considering the level of hotel and number of nights 
	 * to the user’s frequent guest account
	 * @param programSel
	 * @param nightsNum
	 * @param awardPerNight
	 */
	public void pointsCreditHotel(TravelProgram programSel, int nightsNum, int awardPerNight) {
		int index = -1;
		for(int i = 0 ; i<memberOf.size(); i++)
		{
			if (memberOf.get(i).getProgramName().equals(programSel.getProgramName())){
				index = i;
			}
		}
		points.set(index, points.get(index) + nightsNum*awardPerNight);
	}

	/**
	 * add points by purchasing a hotel room 
	 * with the appropriate number of points to the user’s frequent guest account 
	 * considering the level of hotel and number of nights
	 * @param programSel
	 * @param nightsNum
	 * @param purchasePrice
	 */
	public void usingPointsHotel(TravelProgram programSel, int nightsNum, int purchasePrice) {
		int index = -1;
		for(int i = 0 ; i<memberOf.size(); i++)
		{
			if (memberOf.get(i).getProgramName().equals(programSel.getProgramName())){
				index = i;
			}
		}
		points.set(index, points.get(index) - nightsNum*purchasePrice);
	}

	/**
	 * print out the traveler
	 * with its name, memberID, enrolled program, miles/points
	 */
	public String toString(){
		String str = "";
		str += "Name: "+name+"\n";
		str += "Memberships: \n";
		for (int i=0; i<memberOf.size(); i++){
			if (memberOf.get(i) instanceof FlierProgram){
				str+= memberOf.get(i).getProgramName()+" Frequent Flier Program, Miles = "+points.get(i)+", Membership Number = "+membershipNum.get(i)+"\n";
			}
			else if(memberOf.get(i) instanceof GuestProgram){
				str+= memberOf.get(i).getProgramName()+" Frequent Guest Program, Points = "+points.get(i)+", Membership Number = "+membershipNum.get(i)+"\n";
			}

		}
		return str;
	}

	/**
	 * getter of traveler's name
	 * @return
	 */
	public String getName() {
		return name;
	}
}
