package StateGraph;

@SuppressWarnings("rawtypes")
public class Link <T>{
	
	public T		operator;
	public State	target;
	
	public Link(T op, State target)
	{
		this.operator= op;
		this.target= target;
	}
}
