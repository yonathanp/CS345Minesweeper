import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;


public class ConstraintTreeNode {
	SortedList Equalities;
	IntervalList Intervals;
	
	public ConstraintTreeNode() {
		Equalities = new SortedList();
		Intervals = new IntervalList();
	}

	public void Dump(){
		System.out.println("Node: {Intervals:[" + Intervals.Data.toString() + "], Equalities:[" + Equalities.Data.keySet() + "]} \t");
		System.out.println("Children:");
		Object[] children = Equalities.Data.values().toArray();
		ConstraintTreeNode child;
		for(int i =0; i< children.length; i++ ){
			child = (ConstraintTreeNode)children[i];
			child.Dump();
		}
		System.out.println("End Children:");
	}
}
