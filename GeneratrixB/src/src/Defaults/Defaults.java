package Defaults;

import java.util.ArrayList;
import java.util.Collection;

import Model.Heuristic;
import Model.Link;
import Model.State;

public class Defaults {
	public static <T> Heuristic<T> no_heuristic()
	{
		return new Heuristic<T>()
		{
			public Collection<Link<T>> heuristic(State<T> source, State<T> target) 
			{
				return source.links();
			}
		};
	}
	
	/**
	 * @return and-based heuristic
	 */
	public static <T> Heuristic<T> xor_heuristic()
	{
		return new Heuristic<T>()
		{
			public Collection<Link<T>> heuristic(State<T> source, State<T> target) {
				ArrayList<Link<T>> possibleTargets = source.links();
				int targetHash = target.hash;
				
				int size= possibleTargets.size();
				for(int i=0; i<(size-1); i++)
					for(int j=i+1; j<size; j++)
					{
						int keyI= possibleTargets.get(i).hashCode() ^ targetHash;
						int keyJ= possibleTargets.get(j).hashCode() ^ targetHash;
						
						//switch
						if(keyI>keyJ)
						{							
							possibleTargets.add(i, possibleTargets.remove(j));
							possibleTargets.add(j, possibleTargets.remove(i+1));
						}
					}
				
				return possibleTargets;
			}
		};
	}
}
