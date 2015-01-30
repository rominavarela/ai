package Generatrix;

import java.util.ArrayList;

import Model.Link;
import Model.State;
import Model.Operator;

public class Generatrix {
	
	public ArrayList<State<int[][]>>	states;
	
	public Generatrix(int[][] initialState)
	{		
		this.states = new ArrayList<State<int[][]>>();
		
		this.states.add(new State<int[][]>(initialState));
		
		GenerateStates();
	}
	
	/**
	 * Generate every state's links (it's neighbors and their operator).
	 */
	public void GenerateStates()
	{
		for (State<int[][]> currState : states)
		{
			for (Operator currOperator : Operator.values())
			{
				if (isOperationValid(currState, currOperator))
				{
					// create the next state
					State<int[][]> s = createState(currState, currOperator);
					
					// ensure the next state is not duplicated
					s = repeatedState(s);
					
					// create the link to the new state 
					// and add it to the original state
					Link l = new Link(currOperator, s);
					currState.links.add(l);
				}
			}
		}
	}
	
	/**
	 * Generates a new state product of applying a transition to a received state.
	 * 
	 * @param newState
	 * @param operatorToApply
	 * @return
	 */
	public State<int[][]> createState(State<int[][]> newState, Operator operatorToApply)
	{

		int[][] newInfo = new int[][]{
				{ 0, 1, 2 },
				{ 3, 4, 5 },
				{ 6, 7, 8 }
		};
		
		return new State<int[][]>(newInfo); // TODO
	}
	
	/**
	 * Informs whether a transition is valid for a determined state.
	 * 
	 * @return
	 */
	public boolean isOperationValid(State<int[][]> toVerify, Operator operatorToApply)
	{
		return true; // TODO
	}
	
	/**
	 * Searchs for the received state in the state list; 
	 * reports it if found,
	 * adds it to the list if it is not found.
	 * 
	 * @param newState
	 * 
	 * @return A reference to a state 
	 */
	public State<int[][]> repeatedState(State<int[][]> newState)
	{
		// begin search presuming new state
		boolean exists = false;
		State<int[][]> resultState = newState;
		
		// iterate through the state list;
		// if it exists, report the existing and mark existence
		for (State<int[][]> currState : states)
			if (sameState(newState, currState)) 
			{
				resultState = currState;
				exists = true;
			}
		
		// if the new state was not found, add it to the state list
		if (!exists)
			states.add(newState);
		
		return resultState;
	}
	
	/**
	 * Compares two states according to their info.
	 * 
	 * @param state1 
	 * 			First state to compare.
	 * @param state2 
	 * 			Second state to compare.
	 * 
	 * @return true if their info match, false otherwise.
	 */
	public boolean sameState(State<int[][]> state1, State<int[][]> state2)
	{
		for (int i = 0; i < state1.info.length; i++)
			for (int j = 0; j < state1.info[i].length; j++)
				if (state1.info[i][j] != state2.info[i][j])
					return false;
		return true;
	}
}
