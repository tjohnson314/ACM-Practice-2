
import java.util.Scanner;
import java.util.ArrayList;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[n];
		
		for(int i = 0; i < n; i++)
			nums[i] = sc.nextInt();
		sc.close();
		
		long sum = 0;
		for(int i = 0; i < n; i++)
			sum += nums[i];
		
		//System.out.println("Sum: " + Long.toString(sum));
		
		if(sum%3 != 0)
			System.out.println(0);
		else
		{
			long sumEach = sum/3;
			ArrayList<Integer> startArr = new ArrayList<Integer>();
			ArrayList<Integer> endArr = new ArrayList<Integer>();
			
			long sumStart = nums[0];
			for(int i = 1; i < n; i++)
			{
				if(sumStart == sumEach)
				{
					startArr.add(i);
					//System.out.println("Start: " + Integer.toString(i));
				}
				sumStart += nums[i];
			}
			
			long sumEnd = sum - nums[0];
			for(int i = 1; i < n; i++)
			{
				if(sumEnd == sumEach)
				{
					endArr.add(i);
					//System.out.println("End: " + Integer.toString(i));
				}
				sumEnd -= nums[i];
			}
			
			int ind1 = 0;
			int ind2 = 0;
			long ways = 0;
			long inc = 0;
			while(ind1 < startArr.size() && ind2 < endArr.size())
			{
				if(startArr.get(ind1) < endArr.get(ind2))
				{
					//System.out.println("Split: " + Integer.toString(startArr.get(ind1)));
					inc++;
					ind1++;
				}
				else
				{
					//System.out.println("Split: " + Integer.toString(endArr.get(ind2)));
					ways += inc;
					ind2++;
				}
			}
			
			ways += (endArr.size() - ind2)*inc;
			System.out.println(ways);
		}
	}

}
