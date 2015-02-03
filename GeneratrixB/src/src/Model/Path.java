package Model;

public class Path {
	
	public Path 				origin;
	public Operator				op;
	public State<?>				state;
	
	public Path( Path origin , Operator op , State<?> state) 
	{
		this.origin		= origin;
		this.op			= op;
		this.state		= state;
	}
	
	public String toString()
	{
		if(this.origin==null)
			return "origin:\n"+this.state.toString();
		else
			return this.origin.toString()
					+"\n" + op
					+":\n" + this.state.toString();
			
		
		
	}
}
