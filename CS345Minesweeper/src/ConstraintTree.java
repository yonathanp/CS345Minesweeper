import java.util.ArrayList;

// Assumption: Domain of all relations is N (i.e. the natural numbers)
// Any interval in ConstraintTree that contains -1 is of the form (-Inf,r) for some r >= 0
public class ConstraintTree implements CDS {
	int GAOLength;	// At any given point the height of the ConstraintTree is at most GAOLength nodes
	private ConstraintTreeNode Root;
	
	public ConstraintTree(int n) {
		Root = new ConstraintTreeNode();
		GAOLength = n;
	}
	
	// for beta-acyclic queries only
	private ArrayList<ConstraintTreeNode> PrincipalFilter(Pattern p){
		// Return ORDERED list of all constraint tree nodes u such that the pattern for node u is a generalization of p
		// and u.Intervals is not empty. Ordered means: Result[i] is a specialization of Result[i+1].
		// Note: We are assuming that the query is beta-acyclic and that the GAO is such that for each (t1,...,ti)
		// the principle filter G(t1,...,ti) is a CHAIN - see appendix F for how to set the GAO.
		
		//p.Dump();
		ArrayList<ConstraintTreeNode> G = new ArrayList<ConstraintTreeNode>();
		if(p.Length() == 0){
			G.add(Root);
			return G;
		}
		// generate pattern chain
		ArrayList<Pattern> GeneralizedPatterns = new ArrayList<Pattern>();
		Pattern P0 = new Pattern();
		P0.AddValue(p.GetValue(0));
		Pattern P1 = new Pattern();
		P1.AddValue(Constraint.WILDCARD);
		GeneralizedPatterns.add(P0);
		GeneralizedPatterns.add(P1);
		for(int i = 1; i < p.Length(); i++){
			int j = GeneralizedPatterns.size();
			for(int k = 0; k < j; k++){
				Pattern r = new Pattern(GeneralizedPatterns.get(k));
				r.AddValue(Constraint.WILDCARD);
				GeneralizedPatterns.add(r);
				GeneralizedPatterns.get(k).AddValue(p.GetValue(i));
			}
		}
		// create the ordered list of nodes corresponding to patterns in Chain
		for(Pattern q : GeneralizedPatterns){
			// follow the path encoded by q down the ConstraintTree
			ConstraintTreeNode v = Root;
			for(int i = 0; i < q.Length(); i++){
				if(v == null) { break;}
				v = v.GetChild(q.GetValue(i)); 
			}
			if(v != null && !v.IsEmpty()){ G.add(v);}
		}
		return G;
	}
	
	// Algorithm 4 in paper - for beta-acyclic queries only; G is a chain
	private int NextChainVal(int startValue, int NodeIdx, ArrayList<ConstraintTreeNode> G){
		ConstraintTreeNode u = G.get(NodeIdx);
		if(NodeIdx == G.size()-1){ return u.GetIntervalsNext(startValue);}
		int y = startValue;
		int z = 0;
		do{
			z = NextChainVal(y, NodeIdx+1, G);
			y = u.GetIntervalsNext(z);
		}while(y != z);
		Constraint C = new Constraint(u.GetFullPatternCopy(),new IntPair(startValue-1, y));
		return y;
	}
	
	
	@Override
	// Algorithm 3 in paper - for beta-acyclic queries only
	public Tuple GetProbepoint() {
		int i = 0;
		Tuple t = new Tuple(GAOLength);
		while(i < GAOLength){
			ArrayList<ConstraintTreeNode> G = PrincipalFilter(new Pattern(t.Value));
			if(G.isEmpty()){
				t.AddVal(-1);
				i++;
			} else{
				ConstraintTreeNode u = G.get(0);
				int newTupleElem = NextChainVal(-1,0,G);
				t.AddVal(i,newTupleElem);
				int i0 = -1;
				for(int k = 0; k < i; k++){
					if(u.GetPatternLength() >  0 && u.GetPatternElement(k) != Constraint.WILDCARD){ i0 = k;}
				}
				if(newTupleElem == Integer.MAX_VALUE){
					if(i0 < 0){ return null;}
					// generate new constraint
					ArrayList<Integer> V = i0 > 0 ? u.GetPatternCopy(0,i0-1) : new ArrayList<Integer>();
					IntPair I = new IntPair(u.GetPatternElement(i0)-1, u.GetPatternElement(i0)+1);
					InsertConstraint(new Constraint(V,I));
					//Dump();
					// backtrack
					i = i0;
					t.Truncate(i0);
				} else{
					i++;
				}
			}
		}
		t.Truncate(GAOLength);
		return t;
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
		System.out.print("\n");
		Root.Dump();
	}
	
}
