import java.io.BufferedReader;
import java.util.ArrayList;

/*
 * http://www.developerfusion.com/code/2064/a-simple-way-to-read-an-xml-file-in-java/
 * */

public class Main
{
	public static void main(String[] args)
	{
		ArrayList <State> stateList = new ArrayList<State>();
		
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
