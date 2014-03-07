import java.util.ArrayList;


public class CDSTest1 {

	public static void main(String[] args) {
		ConstraintTree CDS;
		Tuple T;
		// Single attribute ConstraintTree
		/*
		CDS = new ConstraintTree(1);
		CDS.InsertConstraint(new Constraint(new ArrayList<Integer>(), new IntPair(Integer.MIN_VALUE, 2)));
		CDS.InsertConstraint(new Constraint(new ArrayList<Integer>(), new IntPair(3, 7)));
		//CDS.Dump();
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());
		CDS.InsertConstraint(new Constraint(new ArrayList<Integer>(), new IntPair(1, 3)));
		//CDS.Dump();
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());
		*/
		
		// two-attribute constraint tree
		CDS = new ConstraintTree(2);
		CDS.InsertConstraint(new Constraint(new ArrayList<Integer>(), new IntPair(1, 3)));	// insert constraint <(1,3),*>
		ArrayList<Integer> V = new ArrayList<Integer>();
		V.add(0);
		CDS.InsertConstraint(new Constraint(V, new IntPair(Integer.MIN_VALUE,8)));	// insert constraint <=0,(-Inf,8)>
		//CDS.Dump();
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());
		V.set(0, Constraint.WILDCARD);
		CDS.InsertConstraint(new Constraint(new ArrayList<Integer>(), new IntPair(Integer.MIN_VALUE, 0)));  	// insert constraint <(-Inf,0),*>
		//CDS.Dump();
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());
		CDS.InsertConstraint(new Constraint(V, new IntPair(3,12)));	// insert constraint <*,(3,12)>
		//CDS.Dump();
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());
		
		// three-attribute constraint tree (sequence of CDS insertions and probing as appears in appendix D.1)
		/*
		CDS = new ConstraintTree(3);
		T = CDS.GetProbepoint();
		System.out.println("probe point = " + T.Value.toString());
		*/
	}

}
