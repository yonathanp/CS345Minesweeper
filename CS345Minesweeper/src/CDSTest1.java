import java.util.ArrayList;


public class CDSTest1 {

	public static void main(String[] args) {
		ConstraintTree CDS;
		Tuple T;
		// Single attribute ConstraintTree
		
		CDS = new ConstraintTree(1);
		CDS.InsertConstraint(new Constraint(new ArrayList<Integer>(), new IntPair(Integer.MIN_VALUE, 2)));
		CDS.InsertConstraint(new Constraint(new ArrayList<Integer>(), new IntPair(3, 7)));
		//CDS.Dump();
		T = CDS.GetProbepoint();	
		System.out.println("probe point = " + T.Value.toString());  // expected: (2)
		CDS.InsertConstraint(new Constraint(new ArrayList<Integer>(), new IntPair(1, 3)));
		//CDS.Dump();
		T = CDS.GetProbepoint();	// expected: (3)
		System.out.println("probe point = " + T.Value.toString());
		
		
		// two-attribute constraint tree
		CDS = new ConstraintTree(2);
		CDS.InsertConstraint(new Constraint(new ArrayList<Integer>(), new IntPair(1, 3)));	// insert constraint <(1,3),*>
		ArrayList<Integer> V = new ArrayList<Integer>();
		V.add(0);
		CDS.InsertConstraint(new Constraint(V, new IntPair(Integer.MIN_VALUE,8)));	// insert constraint <=0,(-Inf,8)>
		//CDS.Dump();	
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());	// expect: (-1,-1)
		V.set(0, Constraint.WILDCARD);
		CDS.InsertConstraint(new Constraint(new ArrayList<Integer>(), new IntPair(Integer.MIN_VALUE, 0)));  	// insert constraint <(-Inf,0),*>
		//CDS.Dump();
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());	// expect: (0,8)
		CDS.InsertConstraint(new Constraint(V, new IntPair(3,12)));	// insert constraint <*,(3,12)>
		//CDS.Dump();
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());	// expect: (0,12)

		// three-attribute constraint tree (sequence of CDS insertions and probing as appears in appendix D.1)
		CDS = new ConstraintTree(3);
		T = CDS.GetProbepoint();	// expected: (-1,-1,-1)
		System.out.println("probe point = " + T.Value.toString());
		CDS.InsertConstraint(new Constraint(new ArrayList<Integer>(), new IntPair(Integer.MIN_VALUE,1)));	// insert <(-Inf,1),*,*>
		V = new ArrayList<Integer>();
		V.add(1);
		CDS.InsertConstraint(new Constraint(V, new IntPair(Integer.MIN_VALUE,1)));	// insert <1,(-Inf,1),*>
		V.set(0, Constraint.WILDCARD);
		CDS.InsertConstraint(new Constraint(V, new IntPair(Integer.MIN_VALUE,2)));	// insert <*,(-Inf,2),*>
		V.add(2);
		CDS.InsertConstraint(new Constraint(V, new IntPair(Integer.MIN_VALUE,2)));	// insert <*,=2,(-Inf,2)>
		V.set(1, Constraint.WILDCARD);
		CDS.InsertConstraint(new Constraint(V, new IntPair(Integer.MIN_VALUE,1)));	// insert <*,*,(-Inf,1)>
		//CDS.Dump();
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());	// expected: (1,2,2)
		CDS.InsertConstraint(new Constraint(V, new IntPair(1,3)));	// insert <*,*,(1,3)>
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());	// expected: (1,2,3)
		V.set(1,2);
		CDS.InsertConstraint(new Constraint(V, new IntPair(2,4)));	// insert <*,=2,(2,4)>
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());	// expected: (1,2,4)
		V.set(1,Constraint.WILDCARD);
		CDS.InsertConstraint(new Constraint(V, new IntPair(3,Integer.MAX_VALUE)));	// insert <*,*,(3,+Inf)>
		//CDS.Dump();
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());	// expected: (1,3,1)
		V.remove(1);
		CDS.InsertConstraint(new Constraint(V, new IntPair(3,Integer.MAX_VALUE)));	// insert <*,(3,+Inf),*>
		V.add(2);
		CDS.InsertConstraint(new Constraint(V, new IntPair(4,Integer.MAX_VALUE)));	// insert <*,=2,(4,+Inf)>
		//CDS.Dump();
		T = CDS.GetProbepoint();
		V.remove(1);
		CDS.InsertConstraint(new Constraint(V, new IntPair(2,4)));
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());	// expected: exception - T is null
	}

}
