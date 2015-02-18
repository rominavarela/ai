package MatrixImpl;

import Model.Link;
import Model.Operator;
import Model.State;

public class MatrixState extends State<int[][]>{

	public MatrixState(int[][] info) {
		super(info);
	}

	@Override
	public int hashCode(int[][] info) {
		int n=0;
		
		for(int i=0; i< info.length; i++)
		{
			for(int ii=0; ii< info[i].length; ii++)
			{
				n*=10;
				n+=info[i][ii];
			}
		}
		
		return n;
	}
	
	@Override
	public Link <int[][]> transition(Operator op) {
		int[][] transition = new int[info.length][info.length];
		
		//Copy and Find the zero
		int x=0,y=0;
		for(int i=0; i< info.length; i++)
		{
			for(int j=0; j< info[i].length; j++)
			{
				transition[i][j]= info[i][j];
				if(transition[i][j]==0)
				{
					x=i;
					y=j;
				}
			}
		}
		
		//Calculate new positions
		int x2=x, y2=y;
		switch(op)
		{
			case up:
				x2++;
				if(x2 >= transition[0].length) return null;
				break;
				
			case down:
				x2--;
				if(x2 < 0) return null;
				break;
				
			case left:
				y2++;
				if(y2 >= transition[0].length) return null;
				break;
				
			case right:
				y2--;
				if(y2 < 0) return null;
				break;
		}
		
		//Switch
		transition[x][y]= transition[x2][y2];
		transition[x2][y2]= info[x][y];
		
		return new Link<int[][]>( op , new MatrixState(transition));
	}

	@Override
	public String toString() {
		String 	s="";
		
		for(int i=0; i< info.length; i++)
		{
			for(int ii=0; ii< info[i].length; ii++)
				s+=(info[i][ii]+" ");
			s+="\n";
		}
		return s;
	}

}
