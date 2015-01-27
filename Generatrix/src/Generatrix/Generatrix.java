package Generatrix;

import java.util.ArrayList;

import StateGraph.State;

public class Generatrix {
	
	public State<int[][]>			 	state;
	public ArrayList<String> 			operators;
	
	public Generatrix(ArrayList<String> operators, int[][] initialState)
	{		
		this.state		= new State<int[][]>(initialState);
		this.operators	= operators;
		
		magic();
	}
	
	/**
	 * Generate the current state's links (it's neighbors and their operator).
	 * @param state
	 */
	public void magic()
	{
		//TODO
	}
	
}
