import java.util.ArrayList;


public class ConstraintTree implements CDS {
	private ConstraintTreeNode Root;
	
	public ConstraintTree() {
		Root = new ConstraintTreeNode();
	}
	

	private ArrayList<ConstraintTreeNode> PrincipalFilter(Pattern p){
		// Return oldred list of all constraint tree nodes u such that the pattern for node u is a generalization of p
		return null;
	}
	
	
	@Override
	public Tuple GetProbepoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void InsertConstraint(Constraint C) {
		// Algorithm 5 in paper
		ConstraintTreeNode v = Root;
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		for (int i = 0; i < C.GetIntervalComponentIndex(); i++) {
			Integer equality = C.GetPrefixElement(i);
			pattern.add(equality);
			if ( equality != Constraint.WILDCARD && v.Covers(equality)){ return;}	
			if ( !v.Find(equality) ){
				ConstraintTreeNode new_node = new ConstraintTreeNode(pattern);
				v.Insert(equality, new_node);
			}
			v = v.GetChild(equality);
		}
		IntPair Interval = C.GetInterval();
		v.InsertInterval(Interval);
		v.DeleteEqualitiesInterval(Interval);
	}
	
	
	public int NextChainVal(){return 0;}
	
	public void Dump(){
		Root.Dump();
	}
	
}
