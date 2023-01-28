// Emilio Morales, COP 4520, Spring 2023

import java.util.*;
import java.io.*;

public class PrimeThreads 
{
	public static void main(String[] args) throws IOException 
	{
		long sumPrimes = 0;
		long numPrimes = 0;
		
		// Stores all our threads to make it easier to iterate.
		ArrayList<FindPrimes> myThreads = new ArrayList<FindPrimes>();
		
		long timeStart = System.currentTimeMillis();
		
		for (int i = 1; i <= 8; i++)
		{
			// Initializing a thread to find primes from where previous thread ended up till new range.
			myThreads.add(new FindPrimes((int)Math.pow(10, i - 1), (int)Math.pow(10, i)));
			myThreads.get(i - 1).start();
		}
		
		// Making sure we dont get info until are threads are finished
		while (!areThreadsDead(myThreads))
		{
			continue;
		}
		
		long timeEnd = System.currentTimeMillis();
		
		double duration = (timeEnd - timeStart) * 0.001;
		
		
		for (FindPrimes i : myThreads)
		{
			sumPrimes += i.getSumOfPrimes();
			numPrimes += i.getNumPrimes();
		}
		
		String executionTime = "Time: " + duration + "s";
		
		File result = new File("primes.txt");
		result.createNewFile();
		
		FileWriter info = new FileWriter(result);
		info.write("<" + executionTime + ">" + " <primes found: " + numPrimes + ">" + " <sum: " + sumPrimes + ">");
		info.close();
	}

	static boolean areThreadsDead(ArrayList<FindPrimes> list)
	{
		for (FindPrimes i: list)
		{
			if (i.isAlive()) return false;
		}

		return true;
	}
	
}// End of Main

class FindPrimes extends Thread
{
	private int myStartNum;
	private int myEndNum;
	
	private long sumOfPrimes = 0;
	private long numPrimes = 0;
	
	//private ArrayList<Integer> MaxPrimes;// figure out later	
	
	FindPrimes(int start, int end)
	{
		myStartNum = start;
		myEndNum = end;
	}
	
	@Override
	public void run()
	{
		for (int i = myStartNum; i < myEndNum; i++)
		{
			if (isPrime(i))
			{
				sumOfPrimes += i;
				numPrimes++;
				
				// Do something here with regards to max Primes
			}
		}
	}
	
	long getNumPrimes()
	{
		return numPrimes;
	}
	
	long getSumOfPrimes()
	{
		return sumOfPrimes;
	}
	
	boolean isPrime(int n) 
	{
		if ( n == 2) return true;

		if ( n % 2 == 0) return false;

		for (int i = 3;  i < n / 2; i += 2) 
			if (n % i == 0 ) return false;
		
		return true;	
	}
}// End of FindPrimes
