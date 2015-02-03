package Generatrix;

import java.util.HashMap;

import Model.MatrixState;
import Model.Path;
import Model.State;

public class Main {
	public static void main(String args[])
	{
		//System.out.println("Terminated in: "+combinationsTest()+" ms");
		System.out.println("Terminated in: "+dfsTest()+" ms");
	}
	
	public static long combinationsTest()
	{
		int[][] initialInfo = new int[][]{
				{ 0, 1, 2 },
				{ 3, 4, 5 },
				{ 6, 7, 8 }
		};
		
		MatrixState initialState = new MatrixState(initialInfo);
		
		//test
		long startTime= System.currentTimeMillis();
		long endTime;
		HashMap<Integer, State<?>> statesMap = Generatrix.StateMap(initialState);
		endTime= System.currentTimeMillis();
		
		//print results
		System.out.println("N States: "+statesMap.size());
		
		return endTime-startTime;
	}
	
	public static long dfsTest()
	{
		
		int[][] initialInfo = new int[][]{
				{ 0, 1, 2 },
				{ 3, 4, 5 },
				{ 6, 7, 8 }
		};
		
		int[][] finalInfo = new int[][]{
				{ 3, 1, 2 },
				{ 6, 4, 5 },
				{ 7, 0, 8 }
		};
		
		MatrixState initialState = new MatrixState(initialInfo);
		MatrixState finalState = new MatrixState(finalInfo);
		
		//test
		long startTime= System.currentTimeMillis();
		long endTime;
		Path path = Generatrix.dfs(initialState, finalState);
		endTime= System.currentTimeMillis();
		
		//print results
		System.out.println(path);
		
		return endTime-startTime;
	}
}
