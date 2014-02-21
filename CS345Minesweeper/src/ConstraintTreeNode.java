
public class ConstraintTreeNode {
	private SortedList Equalities;
	private IntervalList Intervals;
	
	public ConstraintTreeNode() {
		Equalities = new SortedList();
		Intervals = new IntervalList();
	}

	public void Dump(){
		System.out.println("Node: {Intervals:[" + Intervals.toString() + "], Equalities:[" + Equalities.GetKeySet() + "]} \t");
		System.out.println("Children:");
		ConstraintTreeNode[] Children = Equalities.GetVals();
		for(ConstraintTreeNode Child : Children){
			Child.Dump();
		}
		System.out.println("End Children:");
	}
	
	public boolean Covers(int v){
		return Intervals.Covers(v);
	}
	
	public boolean Find(int v){
		return Equalities.Find(v);
	}
	
	public void Insert(int equality, ConstraintTreeNode node){
		Equalities.Insert(equality, node);
	}
	
	public ConstraintTreeNode GetChild(int equality){
		return Equalities.GetVal(equality);
	}
	
	public void InsertInterval(IntPair Interval){
		Intervals.Insert(Interval.GetVal1(), Interval.GetVal2());
	}
	
	public void DeleteEqualitiesInterval(IntPair Interval){
		Equalities.DeleteInterval(Interval.GetVal1(), Interval.GetVal2());
	}
}
