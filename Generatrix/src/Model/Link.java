package Model;

@SuppressWarnings("rawtypes")
public class Link{
	
	public Operator		operator;
	public State		target;
	
	public Link(Operator op, State target)
	{
		this.operator= op;
		this.target= target;
	}
}