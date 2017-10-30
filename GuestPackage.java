
public class GuestPackage extends TravelPackage {
	private int awardPerNight;
	
	/**
	 * constructor of GuestPackage
	 * @param name
	 * @param price
	 * @param awardPerNight
	 */
	public GuestPackage(String name, int price, int awardPerNight) {
		super(name, price);
		this.awardPerNight = awardPerNight;
	}
	
	/**
	 * getter of the award points per night
	 * @return
	 */
	public int getAwardPerNight() {
		return awardPerNight;
	}
	
	
	

}
