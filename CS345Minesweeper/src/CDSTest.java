import java.util.ArrayList;


public class CDSTest {
	public static void main(String[] args) {
		SortedList sl = new SortedList();
		ConstraintTreeNode n = new ConstraintTreeNode();
		sl.Insert(1,n);
		sl.Insert(10,n);
		Integer a = 10;
		sl.Insert(11,n);
		sl.Insert(4,n);
		sl.Insert(7,n);

		
		System.out.println("Find Lowerbound " + a + " " + sl.FindLub(a));
		System.out.println("Find " + a + " " + sl.Find(a));
		sl.Delete(a);
		System.out.println("Find Lowerbound " + a + " " + sl.FindLub(a));
		System.out.println("Find " + a + " " + sl.Find(a));
		a = 1;
		System.out.println("Find Lowerbound " + a + " " + sl.FindLub(a));
		System.out.println("Find " + a + " " + sl.Find(a));
		a = 3;
		System.out.println("Find Lowerbound " + a + " " + sl.FindLub(a));
		System.out.println("Find " + a + " " + sl.Find(a));
		sl.DeleteInterval(1, 5);
		a = 1;
		System.out.println("Find Lowerbound " + a + " " + sl.FindLub(a));
		System.out.println("Find " + a + " " + sl.Find(a));
		a = 3;
		System.out.println("Find Lowerbound " + a + " " + sl.FindLub(a));
		System.out.println("Find " + a + " " + sl.Find(a));
		
		
		IntervalList il = new IntervalList();
		il.Insert(1, 5);
		il.Insert(4, 9);
		il.Insert(3, 9);

		for( a=0; a<=10; a++){
			System.out.println("Find Next " + a + " " + il.Next(a));
			System.out.println("Covers " + a + "? " + il.Covers(a));
		}	
		
		ConstraintTree ct = new ConstraintTree();
		IntPair P = new IntPair(1,4);
		ArrayList<Integer> V = new ArrayList<Integer>();
		V.add(Constraint.WILDCARD);
		V.add(1);
		V.add(5);
		V.add(Constraint.WILDCARD);
		V.add(3);
		Constraint c = new Constraint(V,P);
		c.Dump();
		
		ct.InsertConstraint(c);
		ct.Dump();
		P = new IntPair(4,6);
		V.add(3);
		V.add(2);
		V.add(5);
		V.add(-1);
		c = new Constraint(V,P);
		ct.InsertConstraint(c);
		c.Dump();
		
		ct.InsertConstraint(c);
		ct.Dump();	
	}

}
