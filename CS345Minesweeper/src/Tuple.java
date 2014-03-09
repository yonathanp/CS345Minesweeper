import java.util.ArrayList;


public class Tuple {
	public ArrayList<Integer> Value;
	public int GetAttrVal(int idx){ return Value.get(idx);}
	public int GetArity(){ return Value.size();}
	
	public Tuple(){
		Value = new ArrayList<Integer>();
	}
	
	public Tuple(int Size){
		Value = new ArrayList<Integer>(Size);
	}
	
	public Tuple(Tuple T){
		Value = new ArrayList<Integer>(T.Value);
	}
	
	public Tuple(ArrayList<Integer> V){
		Value = new ArrayList<Integer>(V);
	}
	
	public void AddVal(int v){
		Value.add(v);
	}
	
	public void AddVal(int idx, int v){
		Value.add(idx, v);
	}
	
	public void Truncate(int L){
		while(Value.size() > L){
			Value.remove(Value.size()-1);
		}
	}
	
	public void Dump(){
		System.out.print("(");
		for ( int i =0; i< Value.size(); i++){
			System.out.print(Value.get(i) + ",");
		}
		System.out.print(")\n");
	}
}
