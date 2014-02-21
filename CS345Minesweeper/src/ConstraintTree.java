
public class ConstraintTree implements CDS {
	private ConstraintTreeNode Root;
	
	public ConstraintTree() {
		Root = new ConstraintTreeNode();
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
		for (int i = 0; i < C.GetIntervalComponentIndex(); i++) {
			Integer equality = C.GetPrefixElement(i);
			if ( equality != Constraint.WILDCARD && v.Covers(equality)){ return;}	
			if ( !v.Find(equality) ){
				ConstraintTreeNode new_node = new ConstraintTreeNode();
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
