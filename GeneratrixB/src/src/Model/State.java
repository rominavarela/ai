package Model;

import java.util.ArrayList;

public abstract class State <T> {
	
	public int					hash;
	public T					info;
	private ArrayList<Link<T>>	links;

	public State(T info)
	{
		this.hash		= hashCode(info);
		this.info		= info;
		
	}
	
	public 		abstract 	int 				hashCode(T info);
	public 		abstract	Link <T>			transition(Operator o);
	public 		abstract 	String 				toString();
	
	public 		ArrayList<Link<T>>	links()
	{
		if(links==null)
		{
			links		= new ArrayList<Link<T>>();
			
			//for every neighbor
			for(Operator op: Operator.values())
			{
				Link<T> transition= transition(op);
				
				if(transition!=null)
					links.add(transition);
			}
		}
			
		return links;
	}
}