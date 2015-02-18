package Model;

public class Path<T> {

	public Path<T> 				previous;
	public Operator				op;
	public State<T>				state;
	
	public Path()
	{
		previous 	= null;
		op 			= null;
		state 		= null;
	}
	
	public Path(Path<T> origin , Operator op , State<T> state) 
	{
		this.previous	= origin;
		this.op			= op;
		this.state		= state;
	}
	
	@Override
	public String toString()
	{
		int n=0;
		String s= op + ":\n" + state.toString() + "\n";
		Path<T> p = this.previous;
		while(p!=null)
		{
			s= p.op +":\n" + p.state.toString() + "\n" + s;
			p=p.previous;
			n++;
		}
		
		
		return s + "n-trasitions:"+n;
	}
	
}
