import java.util.ArrayList;


public class Constraint {
	public final static Integer WILDCARD = -2;
	private int IntervalComponentIndex;
	private IntPair Interval;
	private ArrayList<Integer> Values;
	
	public Constraint() {
		Values = new ArrayList<Integer>();
	}
	
	public Constraint(ArrayList<Integer> V, IntPair I){
		Values = new ArrayList<Integer>(V);
		 Interval = new IntPair(I);
		 IntervalComponentIndex = V.size();
	}
	
	public int GetIntervalComponentIndex(){
		return IntervalComponentIndex;
	}
	
	public int GetPrefixElement(int i){
		return Values.get(i);
	}
	
	public IntPair GetInterval(){
		return new IntPair(Interval);
	}
	
	public void Dump() {
		System.out.print("Constraint: [");

		for ( int i = 0; i < IntervalComponentIndex; i++ ){
			if ( Values.get(i) == WILDCARD){
				System.out.print("*,");
			}
			else {
				System.out.print( Values.get(i) + ",");
			}
		}
		System.out.print("(" + Interval.GetVal1() + "," + Interval.GetVal2() + ")]\n");

	}
}
