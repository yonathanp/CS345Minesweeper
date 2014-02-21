
public class ConstraintTree implements CDS {
	ConstraintTreeNode Root;
	
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
		for (int i = 0; i<C.IntervalComponentIndex; i++) {
			Integer equality = C.Values.get(i);

			if ( equality != C.WILDCARD && v.Intervals.Covers(equality)){
				return;		
			}	

			if ( !v.Equalities.Find(equality) ){
				ConstraintTreeNode new_node = new ConstraintTreeNode();
				v.Equalities.Insert(equality, new_node);
			}
			v = v.Equalities.Data.get(equality);
		}
		Integer l = C.Interval.GetVal1();
		Integer r = C.Interval.GetVal2();
		v.Intervals.Insert(l, r);
		v.Equalities.DeleteInterval(l, r);
	}
	

	int NextChainVal(){return 0;}
	
}
