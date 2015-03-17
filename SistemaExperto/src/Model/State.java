package Model;
import java.util.ArrayList;

public class State {
	
	public String name;
	public ArrayList<Attribute> attrs;
	
	public State(String name) {
		this.name	= name;
		attrs		= new ArrayList<Attribute>();
	}
	
	public void setAttribute(String key, String value, String connection) 
	{
		attrs.add(new Attribute(key, value, connection));
	}
	
	public boolean is(String key, String value, String connection)
	{
		boolean is=false;
		
		switch(connection)
		{
			case "!=":
				return !this.is(key, value, "=");
				
			case "<=":
			{
				if(this.is(key, value, "<"))
					return true;
				else
					return this.is(key, value, "=");
			}
				
			case ">=":
			{
				if(this.is(key, value, ">"))
					return true;
				else
					return this.is(key, value, "=");
			}
				
			case "<":
			{
				for(Attribute attr : attrs)
					//has the key
					if(attr.key.contentEquals(key))
					{
						if(attr.isMinor)
						{
							if(attr.value.contentEquals(value))
								return true;
							else
								return (Integer.parseInt(attr.value) <= Integer.parseInt(value));
						}
						else if(attr.equals)
							return (Integer.parseInt(attr.value) < Integer.parseInt(value));
					}
				
				return false;
			}
				
			case ">":
			{
				for(Attribute attr : attrs)
					//has the key
					if(attr.key.contentEquals(key))
					{
						if(attr.isMinor)
						{
							if(attr.value.contentEquals(value))
								return true;
							else
								return (Integer.parseInt(attr.value) >= Integer.parseInt(value));
						}
						else if(attr.equals)
							return (Integer.parseInt(attr.value) > Integer.parseInt(value));
					}
				
				return false;
			}
				
			case "=":
			{
				for(Attribute attr : attrs)
					//has the key
					if(attr.key.contentEquals(key))
						//has the value and the connection
						return (attr.equals && attr.value.contentEquals(value));
				
				return false;
			}
		}
		
		return is;
	}
	
	public String toString() {
		String s;
		
		s = "Name: " + name + "\nAttributes:\n";
		
		for(Attribute attr : attrs)
			s += "\t" + attr.key + " " + attr.connection + " " + attr.value + "\n";
		
		return s;
	}

}
