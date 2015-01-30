package Generatrix;

import java.util.ArrayList;

import Model.Link;
import Model.State;

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
		// for each state in states,
		//		for each operator
		//			generate a new state with the operator
		//			if new state is valid, add to state list
	}
	
	/**
	 * generarEstado(operador, estado):estado 
	 * Genera un estado que es el resultado de aplicar el operador al estado que mandamos
	 * 
	 * @param newState
	 * @param operatorToApply
	 * @return
	 */
	public State<int[][]> createState(State<int[][]> newState, String operatorToApply)
	{

		int[][] newInfo = new int[][]{
				{ 0, 1, 2 },
				{ 3, 4, 5 },
				{ 6, 7, 8 }
		};
		
		return new State<int[][]>(newInfo); // TODO
	}
	
	/**
	 * estadoValido(estado):boolean 
	 * Regresa verdadero o falso si el estado es valido o inv�lido
	 * 
	 * @return
	 */
	public boolean isValidState(State<int[][]> toVerify)
	{
		return true; // TODO
	}
	
	/**
	 * estadoRepetido(estado):estado 
	 * Regresa un estado; el m�smo si es un estado nuevo y el estado original si no es nuevo.
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
