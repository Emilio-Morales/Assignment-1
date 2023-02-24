import java.util.ArrayList;

public class Problem2 
{

	public static void main(String[] args) 
	{
		int numGuests = 50;
		ArrayList<Guest> guests = new ArrayList<Guest>(numGuests);
		ArrayList<Boolean> hasSeen = new ArrayList<Boolean>(numGuests);
		ArrayList<Boolean> guestInterested = new ArrayList<Boolean>();
		boolean stillInterested = true;
		// This keeps track of whether a guest is viewing vase or not;
		boolean vaseAvailable = true; 
		
		// Initializing all threads.
		for (int i = 0; i < numGuests; i++)
		{
			guests.add(new Guest());
			guests.get(i).start();
			boolean temp = guests.get(i).interestInVase;
			guestInterested.add(temp);
		}

		// This keeps track of the number of guests that have seen the vase.
		int counter = 0;
		
		// Guests will be able to view the vase as much as they want so long as they are still interested in doing so.
		// Guests will check to see if the vase is available, if not they will not even bother seeing it.
		// They are chosen at random to simulate the problems conditions, in this case it being that seeing the vase is
		// first come first serve, and only if guests are interested. 
		while (stillInterested);
		{
			int guestIdx = (int) Math.floor(Math.random() * (numGuests));// generates from 0 to numGuests;
			Guest currGuest = guests.get(guestIdx);
			if (vaseAvailable) 
			{
				currGuest.seeingVase = true;
				vaseAvailable = false;
				// views vase for a little then leaves
				for (int i = 0; i < 10; i++ );
				vaseAvailable = true;
			}
			if (currGuest.seenVase == true && !hasSeen.get(guestIdx) )
				{
					counter++;
					hasSeen.set(guestIdx, true);
				}
			
			if (!checkInterest(guestInterested)) stillInterested = false;
		}
		
		System.out.println("Number of guests to view vase:  "  + counter);
	}
	
	// Checks to see if guests are still interested in vase
	static boolean checkInterest(ArrayList<Boolean> input)
	{	
		for (int i = 0; i < input.size(); i++)
		{
			if (input.get(i)) return true;
		}
		
		return false;
	}

}// END OF Problem2


class Guest extends Thread
{
	public boolean seenVase;	
	public boolean seeingVase;
	public boolean interestInVase;
	
	Guest()
	{
		seenVase = false;
		seeingVase = false;
		// randomly determine whether a guest wants to view the vase
		if (Math.random() < 50)	interestInVase = false;
		else interestInVase = true;
	}
	
	@Override
	public void run()
	{
		// If a guest is seeing the vase, flag it to know they have seen it.
		// Then, it randomly determines whether they still want to view it or not.
		if (seeingVase) 
		{
			seenVase = true;
			if (Math.random() < 50)	interestInVase = false;
			else interestInVase = true;
			seeingVase = false;
		}	
	}
}// End of Guest

