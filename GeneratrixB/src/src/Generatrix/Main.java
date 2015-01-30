package Generatrix;

public class Main {
	public static void main(String args[])
	{
		long startTime= System.currentTimeMillis();
		long endTime;
		
		int[][] initialState = new int[][]{
				{ 0, 1, 2 },
				{ 3, 4, 5 },
				{ 6, 7, 8 }
		};
		
		Generatrix theMatrix= new Generatrix(initialState);
		
		endTime= System.currentTimeMillis();
		System.out.println("N States: "+theMatrix.states.size());
		System.out.println("Terminated in: "+(endTime-startTime));
		
		System.out.println(theMatrix.toString(0));
	}
}
