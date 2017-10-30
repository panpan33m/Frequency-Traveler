/**
 * TravelPackage stores the data of award levels
 * they can later be added to each travel program
 * @author Celine
 *
 */
public class TravelPackage {
	private String name;
	private int purchasePrice;
	
	/**
	 * constructor of TravelPackage
	 * @param name
	 * @param purchasePrice
	 */
	public TravelPackage(String name, int purchasePrice){
		this.name = name;
		this.purchasePrice = purchasePrice;
	}
	

	/**
	 * getter of the award level's name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter of the purchase price
	 * @return
	 */
	public int getPurchase() {
		return purchasePrice;
	}
	
	public String toString(){
		return name;
	}
}
