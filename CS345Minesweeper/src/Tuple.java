import java.util.ArrayList;


public class Tuple {
	public ArrayList<Integer> Value;
	public int GetAttrVal(int idx){ return Value.get(idx);}
	public int GetArity(){ return Value.size();}
	
	public Tuple(){
		Value = new ArrayList<Integer>();
	}
	
	public Tuple(Tuple T){
		Value = new ArrayList<Integer>(T.Value);
	}
	
	public Tuple(ArrayList<Integer> V){
		Value = new ArrayList<Integer>(V);
	}
}
