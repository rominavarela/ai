package Generatrix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
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
			boolean advanced= false;
			
			// para cada enlace del estado cabeza
			for(Link<?> l: caminito.state.heuristic(targetState.hash))
			{
				// si ya visistaste, saltalo
				if(visitedMap.containsKey(l.target.hash))
					continue;
				
				caminito= new Path(caminito, l.operator, l.target);
				visitedMap.put(l.target.hash, true);
				advanced=true;
				break;
			}
			
			//si no avanzo, retrocede
			if(!advanced)
				caminito= caminito.previous;
		}
		
		
		return caminito;
	}
	
	public static void bfs(State<?> initialState, State<?> targetState)
	{
		HashMap<Integer, State<?>> statesMap = StateMap(initialState);
		
		// calcula el hash del objetivo para saber si se encontró
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
			// añadir el info del estado objetivo a la cola
			for (Link<?> currLink : currState.heuristic(targetState.hash))
			{
				// obten el estado objetivo
				State<?> currTargetState = statesMap.get(currLink.target.hash);
				// y si no ha sido visitado, añádelo a la cola
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

	/**
	 * Depth First Search
	 * @param initialState
	 * @param targetState
	 * @return
	 */
	public static Path bfs1(State<?> initialState, State<?> targetState)
	{	
		// indica el orden de procesamiento, en este caso de amplitud
		Queue<State<?>> statesToProcess = new LinkedList<State<?>>();
		// inserta el estado inicial
		statesToProcess.add(initialState);
		
		// registra cuales ya fueron visitados
		HashMap<Integer, Boolean> 	visitedMap 		= new HashMap<Integer, Boolean>();
		
		// mientras haya estados disponibles en donde buscar
		while(!statesToProcess.isEmpty())
		{
			// obten el siguiente a analizar
			State<?> currState = statesToProcess.poll();
			
			// IMPORTANTE! marcar el estado actual como visitado
			visitedMap.put(currState.hash, true);

			// si se encuentra, termina
			if (currState.hash == targetState.hash)
				break;
			
			// para cada link del estado actual
			// añadir el estado objetivo a la cola
			for (Link<?> currLink : currState.links())
			{
				// obten el estado objetivo
				State<?> currTargetState = currLink.target;
				
				// y si no ha sido visitado, añádelo a la cola
				if (!visitedMap.containsKey(currTargetState.hash))
				{
					statesToProcess.add(currTargetState);
					// y marca el nodo actual como padre del nodo objetivo
					currTargetState.bfsParent = currState;
					currTargetState.bfsParentOp = currLink.operator;
				}
			}
		}

		initialState.bfsParent = null;
		// encuentra el nodo objetivo
		State<?> curr = targetState;
		Path caminito = new Path();
		caminito.state = targetState;
		caminito.op = targetState.bfsParentOp;
		// mientras no te pases del origen
		while (curr.bfsParent != null)
		{
			System.out.println(curr);
			curr = curr.bfsParent;
			Path newPath = new Path();
			newPath.next = caminito;
			newPath.state = curr;
			newPath.op = curr.bfsParentOp;
			caminito = newPath;
		}
		
		return caminito;
	}
}
