package Model;

public class Path {

	public Path 				previous;
	public Path 				next;
	public Operator				op;
	public State<?>				state;
	
	public Path()
	{
		previous 	= null;
		next 		= null;
		op 			= null;
		state 		= null;
	}
	
	public Path(Path origin , Operator op , State<?> state) 
	{
		this.previous	= origin;
		this.op			= op;
		this.state		= state;
	}
	
	public String printBackward()
	{
		if(this.previous==null)
			return "origin:\n"+this.state.toString();
		else
			return this.previous.printBackward()
					+"\n" + op
					+":\n" + this.state.toString();
	}
	
	public String printForward()
	{
		String s = "";
		if (this.op == null)
		{
			s += "origin:\n"+this.state.toString();
			s += this.next.printForward();
		}
		else
		{
			s += "\n" + op +":\n" + this.state.toString();
			s += this.next.printForward();
		}
		
		return s;
	}
}
