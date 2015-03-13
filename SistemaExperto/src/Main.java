import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * http://www.developerfusion.com/code/2064/a-simple-way-to-read-an-xml-file-in-java/
 * */

public class Main
{
	public static ArrayList <State> stateList = new ArrayList<State>();
	
	public static void main(String[] args)
	{
		init();
		
		System.out.println("Welcome, escribe tus atributos en formato \"attr_name=attr_val\".\"exit\" para terminar.");
		
		try{
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    String s;
		    while (true) {
		    	
			    s = bufferRead.readLine().toLowerCase();
		    	if (s.contentEquals("exit"))
		    		break;
			    
			    System.out.println(s + " - OK!");
			    
			    filterStatesByAttribute(s.split("=")[0], s.split("=")[1]);
			    
		    }
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Para tus inputs, encontramos lo siguiente: ");
		for (State s : stateList)
		{
			System.out.println(s);
		}
		if (stateList.isEmpty())
			System.out.println("NADA!!!");
		
	}
	
	public static void filterStatesByAttribute(String key, String value)
	{
		for(int i=stateList.size()-1; i>=0; i--)
		{
			State s= stateList.get(i);
			
			String attrVal = (String) s.attributes.get(key); // AQUI ALGO FUMADO PASA TODO
			System.out.println("shalala " +attrVal);
			if (attrVal == null || !attrVal.contentEquals(value))
			{
				System.out.println("quitando " + i);
				stateList.remove(i);
			}
		}
		
		/*
		for (State s : stateList)
		{
			String attrVal = s.attributes.get(key); 
			if (attrVal == null || !attrVal.contentEquals(value))//if no match
				matches.add(s);
		}*/
		
		System.out.println("Matches found for " + key + " - " + value);
		for (State s : stateList)
			System.out.println(s);
		
	}
	
	public static void init()
	{
		
		try
		{
			BufferedReader in= new BufferedReader(new java.io.FileReader("input2.txt"));
			while(in.ready())
			{
				String line=in.readLine();
				String[] firstSplit= line.split(":");
				String[] secondSplit= firstSplit[1].split(",");
				
				State currState = new State();
            	currState.name = firstSplit[0];
            	
            	for(String token : secondSplit)
            	{
            		currState.setAttribute(token.split("=")[0], token.split("=")[1]);
            	}
            	
            	stateList.add(currState);
			}
			in.close();
		}catch(Exception ex){}
		
		for (State s : stateList)
			System.out.println(s);
	}

}
