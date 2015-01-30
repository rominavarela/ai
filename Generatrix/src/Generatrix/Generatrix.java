package Generatrix;

import java.util.ArrayList;

import Model.State;

public class Generatrix {
	
	public ArrayList<State<int[][]>>	states;
	public ArrayList<String>			operators;
	
	public Generatrix(ArrayList<String> operators, int[][] initialState)
	{		
		this.states = new ArrayList<State<int[][]>>();
		
		this.states.add(new State<int[][]>(initialState));
		this.operators	= operators;
		
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
	 * Regresa verdadero o falso si el estado es valido o inválido
	 * 
	 * @return
	 */
	public boolean isValidState(State<int[][]> toVerify)
	{
		return false; // TODO
	}
	
	/**
	 * estadoRepetido(estado):estado 
	 * Regresa un estado; el mísmo si es un estado nuevo y el estado original si no es nuevo.
	 * 
	 * @param newState
	 * @return
	 */
	public State<int[][]> isNewState(State<int[][]> newState)
	{
		// iterar a través de la lista de estados; si está reportar el encontrado, 
		// si no está, añadirlo a la lista y reportar el mismo
		return states.get(0); // TODO
	}
	
}
