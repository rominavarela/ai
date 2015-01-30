package Model;

public class Link{
	
	public Operator		operator;
	public int			targetId;
	
	public Link(Operator op, int targetId)
	{
		this.operator= op;
		this.targetId= targetId;
	}
}