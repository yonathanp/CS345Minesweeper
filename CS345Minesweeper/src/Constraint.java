import java.util.ArrayList;


public class Constraint {
	public final Integer WILDCARD = -1;
	int IntervalComponentIndex;
	IntPair Interval;
	ArrayList<Integer> Values;
	
	public Constraint() {
		Values = new ArrayList<Integer>();
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
