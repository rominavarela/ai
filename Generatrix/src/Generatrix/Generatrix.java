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
		GenerateLinks();
	}
	
	/**
	 * Generate every combination of state.
	 */
	public void GenerateStates()
	{
		//TODO
	}
	
	/**
	 * Generate every state's links (it's neighbors and their operator).
	 */
	public void GenerateLinks()
	{
		for (State<int[][]> currState : states)
		{
			for(Operator op: Operator.values())
			{
				State<int[][]> transition= createState(currState, op);
				if(transition!=null)
					currState.links.add(new Link(op, transition));
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
	public State<int[][]> createState(State<int[][]> state, Operator o)
	{
		int[][] newInfo = new int[state.info.length][state.info.length];
		
		//Copy and Find the zero
		int x=0,y=0;
		for(int i=0; i< state.info.length; i++)
		{
			for(int j=0; j< state.info[i].length; j++)
			{
				newInfo[i][j]= state.info[i][j];
				if(newInfo[i][j]==0)
				{
					x=i;
					y=j;
				}
			}
		}
		
		//Calculate new positions
		int x2=x, y2=y;
		switch(o)
		{
			case up:
				x2--;
				if(x2 < 0) return null;
				break;
				
			case down:
				x2++;
				if(x2 >= newInfo[0].length) return null;
				
				break;
				
			case left:
				y2--;
				if(y2 < 0) return null;
				
				break;
				
			case right:
				y2++;
				if(y2 >= newInfo[0].length) return null;
				
				break;
		}
		
		//Switch
		newInfo[x][y]= newInfo[x2][y2];
		newInfo[x2][y2]= state.info[x][y];
		
		return new State<int[][]>(newInfo); // TODO
	}
	
	/**
	 * Informs whether a transition is valid for a determined state.
	 * 
	 * @return
	 */
	public boolean isOperationValid(State<int[][]> toVerify, Operator operatorToApply)
	{
		switch(operatorToApply)
		{
		case up:
			// 0 cannot be on the bottom row
			for (int col = 0; col < toVerify.info[toVerify.info.length-1].length; col++)
				if (toVerify.info[toVerify.info.length-1][col] == 0)
					return false;
			break;
		case down:
			// 0 cannot be on the top row
			for (int col = 0; col < toVerify.info[0].length; col++)
				if (toVerify.info[0][col] == 0)
					return false;
			break;
		case left:
			// 0 cannot be on the right column
			for (int row = 0; row < toVerify.info.length; row++)
				if (toVerify.info[row][toVerify.info[row].length-1] == 0)
					return false;
			break;
		case right:
			// 0 cannot be on the left column
			for (int row = 0; row < toVerify.info.length; row++)
				if (toVerify.info[row][0] == 0)
					return false;
			break;
		}
		
		return true;
	}
	
	/**
	 * Searches for the received state in the state list; 
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
	
	public String toString(int nState)
	{
		State<int[][]> state = states.get(nState);
		String 	s="State "+nState+"\n";
		
		for(int i=0; i< state.info.length; i++)
		{
			for(int ii=0; ii< state.info[i].length; ii++)
				s+=(state.info[i][ii]+" ");
			s+="\n";
		}
		
		s+="\ntransitions:\n";
		
		for(Link l: state.links)
		{
			s+=l.operator+"\n";
			int[][] info= (int[][]) l.target.info;
			
			for(int i=0; i< info.length; i++)
			{
				for(int ii=0; ii< info[i].length; ii++)
					s+=(info[i][ii]+" ");
				s+="\n";
			}
			
			s+="\n";
		}
		
		return s;
	}
}
