package Generatrix;

import java.util.ArrayList;

public class Main {
	public static void main(String args[])
	{
		ArrayList<String> operators = new ArrayList<String>();
		
		operators.add("up");
		operators.add("down");
		operators.add("rigth");
		operators.add("left");
		
		int[][] initialState = new int[][]{
		{ 0, 1, 2 },
		{ 3, 4, 5 },
		{ 6, 7, 8 }
		};
		
		Generatrix theMatrix= new Generatrix(operators, initialState);
		printArray((int[][]) theMatrix.state.info);
		
		}
	
	public static void printArray(int[][] a)
	{
		for(int i=0; i< a.length; i++)
		{
			for(int ii=0; ii< a[i].length; ii++)
				System.out.print(a[i][ii]+" ");
			System.out.println();
		}
	}
}
