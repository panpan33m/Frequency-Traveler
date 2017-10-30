import static org.junit.Assert.*;

import org.junit.Test;

public class TravelerTest {


	TravelProgram deltaAirlines = new FlierProgram("Delta Airlines", 500);
	TravelPackage delta1 = new FlierPackage("Domestic", 25000);
	TravelPackage delta2 = new FlierPackage("Domestic first", 40000);
	TravelPackage delta3 = new FlierPackage("International", 65000);

	TravelProgram AA = new FlierProgram("American Airlines", 300);
	TravelPackage aa1 = new FlierPackage("Domestic", 20000);
	TravelPackage aa2 = new FlierPackage("International", 60000);

	TravelProgram priorityClub = new GuestProgram("Priority Club");
	TravelPackage pc1 = new GuestPackage("Budget", 500, 60);
	TravelPackage pc2 = new GuestPackage("Moderate", 1000, 100);
	TravelPackage pc3 = new GuestPackage("Deluxe", 2000, 200);

	TravelProgram starwood = new GuestProgram("Starwood");
	TravelPackage starwood1 = new GuestPackage("Business", 1500, 125);
	TravelPackage starwood2 = new GuestPackage("Deluxe", 2500, 250);


	@Test
	public void pointsCreditFlierTest() {
		//test a traveler with delta airline
		Traveler A = new Traveler("Adelta");
		deltaAirlines.addPackage(delta1);
		deltaAirlines.addPackage(delta2);
		deltaAirlines.addPackage(delta3);
		A.addMembership(deltaAirlines);
		A.pointsCreditFlier(deltaAirlines, 1, 499, 500);
		assertEquals("499 miles, 1 traveler", 0, A.getPoints(deltaAirlines));
		A.pointsCreditFlier(deltaAirlines, 1, 500, 500);
		assertEquals("500 miles per traveler, 1 traveler, 500 miles in total", 500, A.getPoints(deltaAirlines));
		A.pointsCreditFlier(deltaAirlines, 2, 1000, 500);
		assertEquals("+1000 miles per traveler, 2 travelers, 2500 miles in total", 2500, A.getPoints(deltaAirlines));

		//test a traveler with AA airline
		A = new Traveler("Aaa");
		AA.addPackage(aa1);
		AA.addPackage(aa2);
		A.addMembership(AA);
		A.pointsCreditFlier(AA, 1, 299, 300);
		assertEquals("299 miles, 1 traveler", 0, A.getPoints(AA));
		A.pointsCreditFlier(AA, 1, 300, 300);
		assertEquals("+300 miles per traveler, 1 traveler, 300 miles in total", 300, A.getPoints(AA));
		A.pointsCreditFlier(AA, 2, 1000, 300);
		assertEquals("+1000 miles per traveler, 2 travelers, 2300 miles in total", 2300, A.getPoints(AA));
	}

	@Test
	public void pointsCreditHotelTest(){
		//test a traveler with priority Club
		Traveler A = new Traveler("Apc");
		priorityClub.addPackage(pc1);
		priorityClub.addPackage(pc2);
		priorityClub.addPackage(pc3);
		A.addMembership(priorityClub);
		A.pointsCreditHotel(priorityClub, 1, ((GuestPackage) priorityClub.getPackage(0)).getAwardPerNight());
		assertEquals("Budget, 60 award per night, 1 night", 60, A.getPoints(priorityClub));
		A.pointsCreditHotel(priorityClub, 2, ((GuestPackage) priorityClub.getPackage(1)).getAwardPerNight());
		assertEquals("Moderate, 100 award per night, 2 nights", 260, A.getPoints(priorityClub));
		A.pointsCreditHotel(priorityClub, 1, ((GuestPackage) priorityClub.getPackage(2)).getAwardPerNight());
		assertEquals("Deluxe, 200 award per night, 1 night", 460, A.getPoints(priorityClub));

		//test a traveler with Starwood
		A = new Traveler("Astarwood");
		starwood.addPackage(starwood1);
		starwood.addPackage(starwood2);
		A.addMembership(starwood);
		A.pointsCreditHotel(starwood, 1, ((GuestPackage) starwood.getPackage(0)).getAwardPerNight());
		assertEquals("Business, 125 award per night, 1 night", 125, A.getPoints(starwood));
		A.pointsCreditHotel(starwood, 2, ((GuestPackage) starwood.getPackage(1)).getAwardPerNight());
		assertEquals("Deluxe, 250 award per night, 2 nights", 625, A.getPoints(starwood));
	}

	@Test
	public void usingPointsFlierTest(){
		//test a traveler with delta airline
		Traveler A = new Traveler("A");
		deltaAirlines.addPackage(delta1);
		deltaAirlines.addPackage(delta2);
		deltaAirlines.addPackage(delta3);
		A.addMembership(deltaAirlines);
		//add 25000 miles to account, miles in total: 25000
		A.pointsCreditFlier(deltaAirlines, 1, 25000, 500);
		A.usingPointsFlier(deltaAirlines, 1, deltaAirlines.getPackage(0).getPurchase());
		assertEquals("redeem a domestic ticket for 1 traveler(in total 25000 miles)", 0, A.getPoints(deltaAirlines));
		//add 42000 miles to account, miles in total: 42000
		A.pointsCreditFlier(deltaAirlines, 2, 21000, 500);
		A.usingPointsFlier(deltaAirlines, 1, deltaAirlines.getPackage(1).getPurchase());
		assertEquals("redeem a domestic first ticket for 1 traveler(in total 40000 miles)", 2000, A.getPoints(deltaAirlines));
		//add 210000 miles to account, miles in total: 212000
		A.pointsCreditFlier(deltaAirlines, 3, 70000, 500);
		A.usingPointsFlier(deltaAirlines, 2, deltaAirlines.getPackage(2).getPurchase());
		assertEquals("redeem an international ticket for 2 travelers(in total 130000 miles)", 82000, A.getPoints(deltaAirlines));

		//test a traveler with AA airline
		A = new Traveler("Aaa");
		AA.addPackage(aa1);
		AA.addPackage(aa2);
		A.addMembership(AA);
		//add 20000 miles to account, miles in total: 20000
		A.pointsCreditFlier(AA, 1, 20000, 300);
		A.usingPointsFlier(AA, 1, AA.getPackage(0).getPurchase());
		assertEquals("redeem a domestic ticket for 1 traveler(in total 20000 miles)", 0, A.getPoints(AA));
		//add 300000 miles to account, miles in total: 300000
		A.pointsCreditFlier(AA, 10, 30000, 300);
		A.usingPointsFlier(AA, 2, AA.getPackage(1).getPurchase());
		assertEquals("redeem a interntional ticket for 2 travelers(in total 120000 miles)", 180000, A.getPoints(AA));
	}

	@Test
	public void usingPointstHotelTest(){
		//test a traveler with priority Club
		Traveler A = new Traveler("Apc");
		priorityClub.addPackage(pc1);
		priorityClub.addPackage(pc2);
		priorityClub.addPackage(pc3);
		A.addMembership(priorityClub);
		//add 10*60(Budget)=600 points to account, points in total: 600
		A.pointsCreditHotel(priorityClub, 10, ((GuestPackage) priorityClub.getPackage(0)).getAwardPerNight());
		A.usingPointsHotel(priorityClub, 1, priorityClub.getPackage(0).getPurchase());
		assertEquals("redeem a budget hotel for 1 night(in total 500 points)", 100, A.getPoints(priorityClub));
		//add 20*100(Moderate)=2000 points to account, points in total: 2100
		A.pointsCreditHotel(priorityClub, 20, ((GuestPackage) priorityClub.getPackage(1)).getAwardPerNight());
		A.usingPointsHotel(priorityClub, 2, priorityClub.getPackage(1).getPurchase());
		assertEquals("redeem a moderate hotel for 2 nights(in total 2000 points)", 100, A.getPoints(priorityClub));
		//add 10*200(Deluxe)=2000 points to account, points in total: 2100
		A.pointsCreditHotel(priorityClub, 10, ((GuestPackage) priorityClub.getPackage(2)).getAwardPerNight());
		A.usingPointsHotel(priorityClub, 1, priorityClub.getPackage(2).getPurchase());
		assertEquals("redeem a deluxe hotel for 1 night(in total 2000 points)", 100, A.getPoints(priorityClub));

		//test a traveler with Starwood
		A = new Traveler("Astarwood");
		starwood.addPackage(starwood1);
		starwood.addPackage(starwood2);
		A.addMembership(starwood);
		//add 20*125(Business)=2500 points to account, points in total: 2500
		A.pointsCreditHotel(starwood, 20, ((GuestPackage) starwood.getPackage(0)).getAwardPerNight());
		A.usingPointsHotel(starwood, 1, starwood.getPackage(0).getPurchase());
		assertEquals("redeem a business hotel for 1 night(in total 1500 points)", 1000, A.getPoints(starwood));
		//add 40*250(Deluxe)=10000 points to account, points in total: 11000
		A.pointsCreditHotel(starwood, 40, ((GuestPackage) starwood.getPackage(1)).getAwardPerNight());
		A.usingPointsHotel(starwood, 2, starwood.getPackage(1).getPurchase());
		assertEquals("redeem a deluxe hotel for 2 nights(in total 5000 points)", 6000, A.getPoints(starwood));
	}

}
