package MatrixImpl;

import java.util.ArrayList;
import java.util.Collection;

import Model.Heuristic;
import Model.Link;
import Model.State;

public class MatrixHeuristic{
	
	/**
	 * @return and-based heuristic
	 */
	public static Heuristic<int[][]> matches()
	{
		return new Heuristic<int[][]>()
		{
			public Collection<Link<int[][]>> heuristic(State<int[][]> source, State<int[][]> target) {
				ArrayList<Link<int[][]>> possibleTargets = source.links();
				
				int size= possibleTargets.size();
				for(int i=0; i<(size-1); i++)
					for(int j=i+1; j<size; j++)
					{
						int keyI= assertions(possibleTargets.get(i).target.info, target.info);
						int keyJ= assertions(possibleTargets.get(j).target.info, target.info);
						
						//switch
						if(keyI<keyJ)
						{							
							possibleTargets.add(i, possibleTargets.remove(j));
							possibleTargets.add(j, possibleTargets.remove(i+1));
						}
					}
				
				return possibleTargets;
			}
		};
	}
	
	private static int assertions(int[][]a , int[][]b)
	{
		int w= a.length;
		int h= a[0].length;
		int count=0;
		
		for(int i=0; i<w; i++)
			for(int j=0; j<h; j++)
			{
				if (a[i][j] == b[i][j])
					count++;
			}
		
		return count;
	}
	
	

}
