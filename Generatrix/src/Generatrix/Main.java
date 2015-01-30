package Generatrix;

public class Main {
	public static void main(String args[])
	{		
		int[][] initialState = new int[][]{
				{ 0, 1, 2 },
				{ 3, 4, 5 },
				{ 6, 7, 8 }
		};
		
		Generatrix theMatrix= new Generatrix(initialState);
		System.out.println("N States: "+theMatrix.states.size());
		System.out.println(theMatrix.toString(0));
		
	}
}
