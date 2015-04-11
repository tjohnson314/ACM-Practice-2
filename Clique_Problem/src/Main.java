
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		//First we read all of our points. Each can be treated as an open interval centered
		//at the given location, with radius equal to the weight, and our goal is to find
		//the largest possible set of disjoint intervals
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Pair> points = new ArrayList<Pair>();
		
		int maxLoc = -1;
		for(int i = 0; i < n; i++)
		{
			int loc = sc.nextInt();
			int weight = sc.nextInt();
			points.add(new Pair(loc, weight));
			if(loc > maxLoc)
				maxLoc = loc;
		}
		sc.close();
		
		//Next we sort our intervals from left to right by their right endpoints (location + weight)
		Collections.sort(points);
		
		//We want to do dynamic programming on an array. But we can't make an array of size 10^9.
		//So we store a compressed version of our array. We only need to store the values at the
		//right endpoints of intervals.
		int[] cliqueSizes = new int[n];
		for(int i = 0; i < n; i++)
		{
			//System.out.println("Point: " + Integer.toString(points.get(i).elem1) + ", " + Integer.toString(points.get(i).elem2));
			int left = points.get(i).left();
			
			int newSize = 1;
			if(left >= points.get(0).elem1 + points.get(0).elem2) //Check if we can include previous points
			{
				int prevIndex = prevSize(points, left, 0, n - 1);
				//System.out.println("Prev index: " + Integer.toString(prevIndex));
				newSize += cliqueSizes[prevIndex];
			}
			
			//Either choose the current point and use the best set for everything to the left of it...
			if(i == 0 || newSize > cliqueSizes[i - 1])
				cliqueSizes[i] = newSize;
			//Or decide not to use the current point.
			else
				cliqueSizes[i] = cliqueSizes[i - 1];
			
			//System.out.println("Clique size: " + Integer.toString(cliqueSizes[i]) + "\n");
		}
		
		System.out.println(cliqueSizes[n - 1]);
	}
	
	//To look up a previously computed value, we have to do a binary search on our list of points
	//to get the largest index that is smaller than or equal to the given value. Then we look up that index
	//in cliqueSizes.
	public static int prevSize(ArrayList<Pair> points, int left, int start, int end)
	{
		//Invariant: Right endpoint of point at start index is smaller than the given value,
		//           Right endpoint of point at end index is at least equal to the given value
		//System.out.println(Integer.toString(start) + ", " + Integer.toString(end));
		if(start == end)
			return start;
		else if(start + 1 == end)
		{
			if(points.get(end).right() <= left)
				return end;
			else
				return start;
		}
		else
		{
			int currLoc = (start + end)/2;
			if(points.get(currLoc).right() <= left)
				return prevSize(points, left, currLoc, end);
			else
				return prevSize(points, left, start, currLoc);
		}
	}
	
	static class Pair implements Comparable<Pair>
	{
		int elem1;
		int elem2;
		
		public Pair(int e1, int e2)
		{
			elem1 = e1;
			elem2 = e2;
		}
		
		public int compareTo(Pair other)
		{
			return ((Integer)(elem1 + elem2)).compareTo(other.elem1 + other.elem2);
		}
		
		public int left()
		{
			return elem1 - elem2;
		}
		
		public int right()
		{
			return elem1 + elem2;
		}
	}
}
