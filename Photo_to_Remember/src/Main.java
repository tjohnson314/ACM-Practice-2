
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] widths = new int[n];
		int[] heights = new int[n];
		int sumWidths = 0;
		int hloc1 = -1; //Largest height
		int hloc2 = -1; //Second-largest height
		for(int i = 0; i < n; i++)
		{
			widths[i] = sc.nextInt();
			sumWidths += widths[i];
			heights[i] = sc.nextInt();
			if(hloc1 < 0 || heights[i] > heights[hloc1])
			{
				hloc2 = hloc1;
				hloc1 = i;
			}
			else if(hloc2 < 0 || heights[i] > heights[hloc2])
				hloc2 = i;
		}
		sc.close();
		
		for(int i = 0; i < n; i++)
		{
			int totalWidth = sumWidths - widths[i];
			int height;
			if(i != hloc1)
				height = heights[hloc1];
			else
				height = heights[hloc2];
			
			System.out.print(totalWidth*height);
			if(i != n - 1)
				System.out.print(' ');
		}
	}
}
