package Model;

import java.util.ArrayList;

public class State <T> {
	
	public T				info;
	public ArrayList<Link>	links;
	public boolean			visited; // todo averiguar como hacer múltiples busquedas 

	public State(T info)
	{
		this.info		= info;
		this.links		= new ArrayList<Link>();
		this.visited	= false;
	}
}