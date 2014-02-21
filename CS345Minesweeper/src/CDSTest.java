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
		Constraint c = new Constraint();
		c.Interval = new IntPair(1,4);
		c.IntervalComponentIndex = 5;
		c.Values.add(-1);
		c.Values.add(1);
		c.Values.add(5);
		c.Values.add(-1);
		c.Values.add(3);
		c.Dump();
		
		ct.InsertConstraint(c);
		ct.Root.Dump();

		ct.InsertConstraint(c);
		c = new Constraint();
		c.Interval = new IntPair(4,6);
		c.IntervalComponentIndex = 4;
		c.Values.add(3);
		c.Values.add(2);
		c.Values.add(5);
		c.Values.add(-1);
		c.Dump();
		
		ct.InsertConstraint(c);
		ct.Root.Dump();

		
	}

}
