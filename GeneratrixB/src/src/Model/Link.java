package Model;

public class Link<T>{
	
	public Operator		operator;
	public State<T>		target;
	
	public Link(Operator op, State<T> target )
	{
		this.operator	= op;
		this.target		= target;
	}
}