import java.util.ArrayList;

public class Pattern {
	public final static Integer WILDCARD = -1;
	private ArrayList<Integer> Values;
	
	public Pattern() {
		Values = new ArrayList<Integer>();
	}
	
	public Pattern(ArrayList<Integer> V){
		Values = new ArrayList<Integer>(V);
	}
	
	public boolean Specializes(Pattern p){
		if ( p.Length() != this.Length()){
			return false;
		}
		for (int i = 0; i< this.Length(); i++){
			if ( p.GetValue(i) != WILDCARD && p.GetValue(i) != this.GetValue(i)){
				return false;
			}
		}
		return true;	
	}
	
	public int Length(){
		return Values.size();
	}
	
	public Integer GetValue(Integer i){
		return this.Values.get(i);
	}
	
	public void AddValue(Integer i){
		this.Values.add(i);
	}

	public void Dump() {
		System.out.print("Pattern: [");

		for ( int i = 0; i < this.Length(); i++ ){
			if ( Values.get(i) == WILDCARD){
				System.out.print("*,");
			}
			else {
				System.out.print( Values.get(i) + ",");
			}
		}
		System.out.print("]");
	}
}
