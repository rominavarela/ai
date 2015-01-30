package Model;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class State <T> {
	
	public T				info;
	public ArrayList<Link>	links;
	public boolean			visited;

	public State(T info)
	{
		this.info=info;
	}
}