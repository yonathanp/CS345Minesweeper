import java.util.ArrayList;


public class ConstraintTreeNode {
	private SortedList Equalities;
	private IntervalList Intervals;
	private Pattern NodePattern;
	
	public ConstraintTreeNode() {
		Equalities = new SortedList();
		Intervals = new IntervalList();
		NodePattern = new Pattern();
	}
	
	public ConstraintTreeNode(ArrayList<Integer> P) {
		Equalities = new SortedList();
		Intervals = new IntervalList();
		NodePattern = new Pattern(P);
	}
	
	public void Dump(){
		System.out.println("Node: {\nIntervals:[" + Intervals.toString() + "],\nEqualities:[" + Equalities.GetKeySet() + "],");
		NodePattern.Dump();
		System.out.println("\n} \t");
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
	
	public boolean IsEmpty(){
		return Intervals.IsEmpty();
	}
	
	public int GetPatternElement(int i){
		return NodePattern.GetValue(i);
	}
	
	public ArrayList<Integer> GetPatternCopy(int first, int last){
		return NodePattern.GetValuesCopy(first, last);
	}
	
	public int GetPatternLength(){
		return NodePattern.Length();
	}
	
	public ArrayList<Integer> GetFullPatternCopy(){
		return NodePattern.GetValuesCopy(0, GetPatternLength()-1);
	}
	
	public int GetIntervalsNext(int v){
		return Intervals.Next(v);
	}
}
