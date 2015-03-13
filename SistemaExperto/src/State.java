import java.util.Hashtable;
import java.util.Map;


public class State {
	
	public String name;
	public Hashtable<String, String> attributes;
	
	public State() {
		attributes = new Hashtable<String, String>();
	}
	
	public void setAttribute(String name, String value) {
		if (attributes.containsKey(name))
			attributes.replace(name, value);
		else
			attributes.put(name, value);
	}
	
	public String toString() {
		String s;
		
		s = "Name: " + name + "\nAttributes:\n";
		
		for (Map.Entry<String, String> entry : attributes.entrySet())
			s += "\t" + entry.getKey() + " - " + entry.getValue() + "\n";
		
		return s;
	}

}
