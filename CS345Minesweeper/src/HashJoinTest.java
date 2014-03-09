import java.util.ArrayList;
import java.util.Iterator;


public class HashJoinTest {
	public static void main(String[] args) {
		Relation R1 = new Relation();
		ArrayList<String> S = new ArrayList<String>();
		S.add("A");
		S.add("B");
		S.add("C");
		R1.SetSchema(S);
		ArrayList<Integer> AO = new ArrayList<Integer>();
		AO.add(0);
		AO.add(2);
		AO.add(1);
		R1.SetAttributeOrder(AO);	// GAO: A1,A3,A2
		ArrayList<Integer> V = new ArrayList<Integer>();
		V.add(1);
		V.add(2);
		V.add(3);
		R1.AddTuple(new Tuple(V));	// add tuple (1,2,3)
		V.set(0, 2);
		R1.AddTuple(new Tuple(V));	// add tuple (2,2,3)
		V.set(1,3);
		R1.AddTuple(new Tuple(V));	// add tuple (2,3,3)
		V.set(2,2);
		R1.AddTuple(new Tuple(V));	// add tuple (2,3,2)

		
		Relation R2 = new Relation();
		S = new ArrayList<String>();
		S.add("C");
		S.add("D");
		S.add("E");
		R2.SetSchema(S);
		AO = new ArrayList<Integer>();
		AO.add(0);
		AO.add(2);
		AO.add(1);
		R2.SetAttributeOrder(AO);	// GAO: A1,A3,A2
		V = new ArrayList<Integer>();
		V.add(1);
		V.add(2);
		V.add(3);
		R2.AddTuple(new Tuple(V));	// add tuple (1,2,3)
		V.set(0, 2);
		R2.AddTuple(new Tuple(V));	// add tuple (2,2,3)
		V.set(1,3);
		R2.AddTuple(new Tuple(V));	// add tuple (2,3,3)
		V.set(2,2);
		R2.AddTuple(new Tuple(V));	// add tuple (2,3,2)
		
		ArrayList<String> GAO = new ArrayList<String>();
		GAO.add("A");
		GAO.add("B");
		GAO.add("C");
		GAO.add("D");
		GAO.add("E");

		R1.Dump();
		
		R2.Dump();
		HashJoin H = new HashJoin(R1, R2, GAO);
		H.Join();
		Iterator<Tuple> i = H.Output.iterator();
		Tuple t = i.next();
		System.out.println("Output of Join:");
		while(i.hasNext()){
			t.Dump();
			t = i.next();
		}
		t.Dump();
	}
}
