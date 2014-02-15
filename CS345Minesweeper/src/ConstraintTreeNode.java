import java.util.ArrayList;


public class ConstraintTreeNode {
	SortedList Equalities;
	IntervalList Intervals;
	ArrayList<ConstraintTreeNode> Children;
}
