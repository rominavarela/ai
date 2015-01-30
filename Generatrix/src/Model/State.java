package Model;
import java.util.ArrayList;

public class State <T> {
	public T info;
	public ArrayList<Link> links;

	public State(T info)
	{
		this.info=info;
	}
}