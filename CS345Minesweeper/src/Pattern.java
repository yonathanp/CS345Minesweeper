import java.util.ArrayList;

public class Pattern {
	private ArrayList<Integer> Values;
	
	public Pattern() {
		Values = new ArrayList<Integer>();
	}
	
	public Pattern(Pattern p){
		Values = new ArrayList<Integer>(p.Values);
		
	}
	
	public Pattern(ArrayList<Integer> V){
		Values = new ArrayList<Integer>(V);
	}
	
	// returns true iff current pattern is a specialization of argument pattern p
	public boolean Specializes(Pattern p){
		if ( p.Length() != this.Length()){
			return false;
		}
		for (int i = 0; i< this.Length(); i++){
			if ( p.GetValue(i) != Constraint.WILDCARD && p.GetValue(i) != this.GetValue(i)){
				return false;
			}
		}
		return true;	
	}
	
	public int Length(){
		return Values.size();
	}
	
	public Integer GetValue(int i){
		return this.Values.get(i);
	}
	
	public void AddValue(int v){
		this.Values.add(v);
	}
	
	public void SetValue(int i, int v){
		Values.set(i,v);
	}
	
	// return a copy of the pattern's values array, as indicated by the inclusive indices first & last
	public ArrayList<Integer> GetValuesCopy(int first, int last){
		ArrayList<Integer> V = new ArrayList<Integer>(last - first + 1);
		for(int i = first; i < last; i++){
			V.add(Values.get(first + i));
		}
		return V;
	}

	public void Dump() {
		System.out.print("Pattern: [");

		for ( int i = 0; i < this.Length(); i++ ){
			if ( Values.get(i) == Constraint.WILDCARD){
				System.out.print("*,");
			}
			else {
				System.out.print( Values.get(i) + ",");
			}
		}
		System.out.print("]\n");
	}
}
