package Generatrix;

import java.util.HashMap;

import Model.MatrixState;
import Model.Path;
import Model.State;

public class Main {
	public static void main(String args[])
	{
		System.out.println("Terminated in: "+combinationsTest()+" ms");
		//System.out.println("Terminated in: "+bfsTest()+" ms");
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
		for(State<?> s: statesMap.values())
			System.out.println(s+" "+s.hash);
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
				{ 1, 0, 2 },
				{ 3, 4, 5 },
				{ 6, 7, 8 }
		};
		
		MatrixState initialState = new MatrixState(initialInfo);
		MatrixState finalState = new MatrixState(finalInfo);
		
		//test
		long startTime= System.currentTimeMillis();
		long endTime;
		Path path = Generatrix.dfs(initialState, finalState);
		endTime= System.currentTimeMillis();
		
		//print results
		System.out.println(path.printBackward());
		
		return endTime-startTime;
	}
	
	public static long bfsTest()
	{
		
		int[][] initialInfo = new int[][]{
				{ 0, 1, 2 },
				{ 3, 4, 5 },
				{ 6, 7, 8 }
		};
		
		int[][] finalInfo = new int[][]{
				{ 3, 1, 2 },
				{ 6, 4, 5 },
				{ 0, 7, 8 }
		};
		
		MatrixState initialState = new MatrixState(initialInfo);
		MatrixState finalState = new MatrixState(finalInfo);
		
		//test
		long startTime= System.currentTimeMillis();
		long endTime;
		Generatrix.bfs(initialState, finalState);
		endTime= System.currentTimeMillis();
		
		//print results
		//System.out.println(path.printForward());
		
		return endTime-startTime;
	}
}
