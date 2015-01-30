package Generatrix;

import Model.Link;
import Model.State;

public class Main {
	public static void main(String args[])
	{		
		int[][] initialState = new int[][]{
				{ 0, 1, 2 },
				{ 3, 4, 5 },
				{ 6, 7, 8 }
		};
		
		Generatrix theMatrix= new Generatrix(initialState);
		System.out.println(toString(theMatrix.states.get(0)));
		
	}
	
	public static String toString(State<int[][]> state)
	{
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
