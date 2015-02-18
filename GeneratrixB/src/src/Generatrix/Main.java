package Generatrix;

import java.util.HashMap;

import Defaults.Defaults;
import MatrixImpl.MatrixHeuristic;
import MatrixImpl.MatrixState;
import Model.Heuristic;
import Model.Path;
import Model.State;

public class Main {
	
	/**
	 ///////////////////////////////////////////////////////////////////////////////////////
	 * CONFIGURATION
	 */
	static MatrixState 		initialState = new MatrixState(
	new int[][]{
			{ 0, 1, 2 },
			{ 3, 4, 5 },
			{ 6, 7, 8 }
	});
	
	static MatrixState 		finalState 	 = new MatrixState(
	new int[][]{
			{ 1, 2, 5 },
			{ 4, 6, 8 },
			{ 3, 7, 0 }
	});
	
	//static Heuristic<int[][]> 	heuristic	= Defaults.no_heuristic();
	static Heuristic<int[][]> 	heuristic	= MatrixHeuristic.matches();
	
	/**
	 ///////////////////////////////////////////////////////////////////////////////////////
	 * MAIN
	 */
	public static void main(String args[])
	{
		//System.out.println("Terminated in: "+combinationsTest()+" ms");
		System.out.println("Terminated in: "+dfsTest()+" ms");
	}
	
	/**
	 ///////////////////////////////////////////////////////////////////////////////////////
	 * TESTS
	 */
	
	/**
	 * Creates and counts all possible states.
	 * @return time in milliseconds
	 */
	public static long combinationsTest()
	{
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
	
	/**
	 * Deph First Search Test
	 * @return time in milliseconds
	 */
	public static long dfsTest()
	{
		long startTime= System.currentTimeMillis();
		long endTime;
		
		Path<int[][]> path = Generatrix.dfs(initialState, finalState, heuristic);
		
		endTime= System.currentTimeMillis();
		
		//print results
		System.out.println(path);
		return endTime-startTime;
	}
	
	/**
	 * Breath First Search Test
	 * @return time in milliseconds
	 */
	public static long bfsTest()
	{
		long startTime= System.currentTimeMillis();
		long endTime;
		
		Path<int[][]> path = Generatrix.bfs(initialState, finalState, heuristic);
		
		endTime= System.currentTimeMillis();
		
		//print results
		System.out.println(path);
		return endTime-startTime;
	}
}
