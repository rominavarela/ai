package Generatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import Model.Heuristic;
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
	@SuppressWarnings("unchecked")
	public static <T> Path<T> dfs(State<T> initialState, State<T> targetState, Heuristic<T> heuristic)
	{
		Path<T>					caminito	= new Path<T>(null, null, initialState);
		ArrayList<Integer>		visitedMap	= new ArrayList<Integer>();
		//Hashtable<Integer, Boolean> 	visitedMap 		= new Hashtable<Integer, Boolean>();
		
		// for each not-visited state
		while(caminito.state.hash != targetState.hash )
		{
			boolean advanced= false;
			
			// para cada enlace del estado cabeza
			for(Link<?> l: heuristic.heuristic(caminito.state, targetState ) /*caminito.state.heuristic(targetState.hash)*/)
			{
				// si ya visistaste, saltalo
				if(visitedMap.contains(l.target.hash))
					continue;
				
				caminito= new Path<T>(caminito, l.operator, (State<T>) l.target);
				visitedMap.add(l.target.hash);
				advanced=true;
				break;
			}
			
			//si no avanzo, retrocede
			if(!advanced)
				caminito= caminito.previous;
		}
		
		
		return caminito;
	}
	
	/**
	 * Breath First Search
	 * @param initialState
	 * @param targetState
	 * @return
	 */
	public static <T> Path<T> bfs(State<T> initialState, State<T> targetState, Heuristic<T> heuristic)
	{
		Queue<Path<T>>	 				caminitos			= new LinkedList<Path<T>>();
		caminitos.add(new Path<T>(null, null, initialState));
		
		Hashtable<Integer, Boolean> 	visitedMap 			= new Hashtable<Integer, Boolean>();
		
		//while there is a path
		while(!caminitos.isEmpty())
		{
			Path<T> caminito= caminitos.poll();
			
			//for every new branch
			for(Link<T> l: heuristic.heuristic(caminito.state, targetState ))
			{
				//if we arrived to our destiny
				if(l.target.hash == targetState.hash)
					return new Path<>(caminito, l.operator, l.target);
				
				// if target is repeated, ignore it
				if(visitedMap.containsKey(l.target.hash))
					continue;
				
				visitedMap.put(l.target.hash, true);
				caminitos.add(new Path<>(caminito, l.operator, l.target));
			}
		}
		
		return null;
	}
	
	public static void bfs(State<?> initialState, State<?> targetState)
	{
		HashMap<Integer, State<?>> statesMap = StateMap(initialState);
		
		// calcula el hash del objetivo para saber si se encontr�
		Integer targetHash = targetState.hash;
		
		Queue<State<?>> statesToProcess= new LinkedList<State<?>>();
		statesToProcess.add(initialState);
		
		// registra cuales ya fueron visitados
		HashMap<Integer, Boolean> 	visitedMap 		= new HashMap<Integer, Boolean>();
		
		while(!statesToProcess.isEmpty())
		{
			// obten el siguiente a analizar
			State<?> currState = statesMap.get(statesToProcess.poll().hash);
			
			// IMPORTANTE! marcar el estado actual como visitado
			visitedMap.put(currState.hash, true);
			
			// si se encuentra, sal del ciclo
			if (currState.hash == targetHash)
				break;
			
			// para cada link del estado actual
			// a�adir el info del estado objetivo a la cola
			for (Link<?> currLink : currState.links())
			{
				// obten el estado objetivo
				State<?> currTargetState = statesMap.get(currLink.target.hash);
				// y si no ha sido visitado, a��delo a la cola
				if (!visitedMap.containsKey(currTargetState.hash))
				{
					statesToProcess.add(currTargetState);
					currTargetState.bfsParent = currState;
					currTargetState.bfsParentOp = currLink.operator;
				}
			}
		}
		
		// encuentra el nodo objetivo
		State<?> curr = statesMap.get(targetState.hash);
		
		while (curr != null)
		{
			if (curr.bfsParentOp == null)
				System.out.println("origin:");
			else
				System.out.println(curr.bfsParentOp + ":");
			System.out.println(curr);
			curr = curr.bfsParent;
		}
	}
}
