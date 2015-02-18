package Model;

import java.util.Collection;

public interface Heuristic <T>{
	public abstract Collection<Link<T>> heuristic( State<T> source , State<T> target );
}
