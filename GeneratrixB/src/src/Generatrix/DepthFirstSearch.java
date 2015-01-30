package Generatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Model.State;
import Model.Path;

public class DepthFirstSearch {
	
	public Path dfs(HashMap<Integer, State<int[][]>> map, State<int[][]> initialState, State<int[][]> targetState)
	{
		ArrayList <Path> caminitos = new ArrayList<Path>();
		
		Integer targetHash = Generatrix.hashCode(targetState.info);
		
		Stack<int[][]> stack= new Stack<int[][]>();
		stack.push(initialState.info);
		
		while(!stack.isEmpty())
		{
			int[][] currInfo= stack.pop();
			Integer currHash= Generatrix.hashCode(currInfo);
			State<int[][]> currState= map.get(currHash);
			
			// por cada path que tenemos
			// ver cual puede ligarse a currState
			// marcarse como visitado
			
			if (currHash == targetHash)
				break;
		}
		
		// todo ver cual fue el mas corto
		return caminitos.get(0);
	}
	
	

}
