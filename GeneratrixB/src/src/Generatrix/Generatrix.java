package Generatrix;

import java.util.HashMap;
import java.util.Stack;

import Model.Link;
import Model.State;
import Model.Operator;

public class Generatrix {
	
	public HashMap<Integer, State<int[][]>> statesMap;
	public int initialState;
	
	public Generatrix(int[][] initialState)
	{
		this.statesMap = new HashMap<Integer, State<int[][]>>();
		GenerateStates(initialState);
		this.initialState= hashCode(initialState);
	}
	
	/**
	 * Generate every combination of state.
	 */
	public void GenerateStates(int[][] initialState)
	{
		Stack<int[][]> stack= new Stack<int[][]>();
		stack.add(initialState);
		
		while(!stack.isEmpty())
		{
			int[][] currInfo= stack.pop();
			State<int[][]> state= new State<int[][]>(currInfo);
			this.statesMap.put(hashCode(currInfo), state);
			
			for(Operator op: Operator.values())
			{
				int[][] transition= createTransition(currInfo, op);
				//if the neighbor needs to be added to the list
				if(transition!=null)
				{
					int hash= hashCode(transition);
					if(!this.statesMap.containsKey(hash))
						stack.add(transition);
				}
				
				if(transition!=null)
					state.links.add(new Link(op, hashCode(transition)));
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
	public int[][] createTransition(int[][] stateInfo, Operator o)
	{
		int[][] newInfo = new int[stateInfo.length][stateInfo.length];
		
		//Copy and Find the zero
		int x=0,y=0;
		for(int i=0; i< stateInfo.length; i++)
		{
			for(int j=0; j< stateInfo[i].length; j++)
			{
				newInfo[i][j]= stateInfo[i][j];
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
				x2++;
				if(x2 >= newInfo[0].length) return null;
				break;
				
			case down:
				x2--;
				if(x2 < 0) return null;
				break;
				
			case left:
				y2++;
				if(y2 >= newInfo[0].length) return null;
				break;
				
			case right:
				y2--;
				if(y2 < 0) return null;
				break;
		}
		
		//Switch
		newInfo[x][y]= newInfo[x2][y2];
		newInfo[x2][y2]= stateInfo[x][y];
		
		return newInfo;
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
	
	public String toString(int hash)
	{
		State<int[][]> state = statesMap.get(hash);
		System.out.println(state);
		
		String 	s="";
		
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
			int[][] info= (int[][]) statesMap.get(l.targetId).info;
			
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
	
	public static int hashCode(int[][] stateInfo)
	{
		int n=0;
		
		for(int i=0; i< stateInfo.length; i++)
		{
			for(int ii=0; ii< stateInfo[i].length; ii++)
			{
				n*=10;
				n+=stateInfo[i][ii];
			}
		}
		
		return n;
	}
}
