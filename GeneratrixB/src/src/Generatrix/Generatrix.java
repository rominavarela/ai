package Generatrix;

import java.util.HashMap;
import java.util.Stack;

import Model.Link;
import Model.Path;
import Model.State;

public class Generatrix {
	
	/**
	 * Generate every combination of state
	 * @param initialInfo
	 * @return
	 */
	public static HashMap<Integer, State<?>> StateMap (State<?> initialState)
	{
		HashMap<Integer, State<?>> statesMap = new HashMap<Integer, State<?>>();
		statesMap.put(initialState.hash, initialState);
		
		Stack<Integer> 	stack		= new Stack<Integer>();
		stack.add(initialState.hash);
		
		//for every state
		while(!stack.isEmpty())
		{
			int 		currHash	= stack.pop();
			State<?> 	currState	= (State<?>) statesMap.get(currHash);
			
			//for every neighbor
			for(Link<?> l: currState.links())
			{
				//if it has not been checked
				if(! statesMap.containsKey(l.target.hash))
				{
					statesMap.put(l.target.hash, l.target);
					stack.add(l.target.hash);
				}
			}
		}
		
		return statesMap;
	}
	
	
	public static String toString(State<?> state)
	{
		String 	s= state.toString();
		
		s+="\ntransitions:\n";
		
		for(Link<?> l: state.links())
		{
			s+= l.target.toString();
			s+="\n";
		}
		
		return s;
	}
	
	/**
	 * Depth First Search
	 * @param initialState
	 * @param targetState
	 * @return
	 */
	public static Path dfs(State<?> initialState, State<?> targetState)
	{
		Path			caminito					= new Path(null, null, initialState);
		HashMap<Integer, Boolean> 	visitedMap 		= new HashMap<Integer, Boolean>();
		
		// for each not-visited state
		while(caminito.state.hash != targetState.hash )
		{
			boolean avanced= false;
			
			for(Link<?> l: caminito.state.links())
			{
				if(visitedMap.containsKey(l.target.hash))
					continue;
				
				caminito= new Path(caminito, l.operator, l.target);
				visitedMap.put(l.target.hash, true);
				avanced=true;
				break;
			}
			
			//si no avanzo, retrocede
			if(!avanced)
				caminito= caminito.origin;
		}
		
		
		return caminito;
	}
}
