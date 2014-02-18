import java.util.ArrayList;


public class Tuple {
	public ArrayList<Integer> Value;
	public int GetAttrVal(int idx){ return Value.get(idx);}
	public int GetArity(){ return Value.size();}
}
