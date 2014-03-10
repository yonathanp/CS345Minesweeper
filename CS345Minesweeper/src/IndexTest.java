import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class IndexTest {

	public static void main(String[] args) {
		Relation R = new Relation();
		ArrayList<String> S = new ArrayList<String>();
		S.add("A");
		S.add("B");
		S.add("C");
		R.SetSchema(S);
		ArrayList<Integer> AO = new ArrayList<Integer>();
		AO.add(0);
		AO.add(2);
		AO.add(1);
		R.SetAttributeOrder(AO);	// GAO: A1,A3,A2
		ArrayList<Integer> V = new ArrayList<Integer>();
		V.add(1);
		V.add(2);
		V.add(3);
		ArrayList<Integer> V1 = new ArrayList<Integer>(V);
		R.AddTuple(new Tuple(V));	// add tuple (1,2,3)
		V.set(0, 2);
		R.AddTuple(new Tuple(V));	// add tuple (2,2,3)
		V.set(1,3);
		R.AddTuple(new Tuple(V));	// add tuple (2,3,3)
		V.set(2,2);
		R.AddTuple(new Tuple(V));	// add tuple (2,3,2)
		Index I = new Index(R);
		File F = new File("IndexTest.txt");
		try {
			I.Dump(F);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Is tuple (1,2,3) there ? " + I.Search(new Tuple(V1),AO));	// should be 0
		V1.set(2,2);
		System.out.println("Is tuple (1,2,2) there ? " + I.Search(new Tuple(V1),AO));	// should be -1
		V1.set(2,1);
		System.out.println("Key matching index tuple (1,2,1): " + I.RetrieveIndexTupleId(V1));  // should be -1
		V1.set(0,2);
		System.out.println("Key matching index tuple (2,2,1): " + I.RetrieveIndexTupleId(V1));  // should be 1
		ArrayList<Integer> U = new ArrayList<Integer>();
		U.add(2);
		U.add(2);
		IntPair P = I.FindGap(U, 2);
		System.out.println("Find((2,2),2) = (" + P.GetVal1() +"," + P.GetVal2() + ")");	// should be (1,1)
		U.remove(0);
		P = I.FindGap(U, 3);
		System.out.println("Find((2),3) = (" + P.GetVal1() +"," + P.GetVal2() + ")");	// should be (2,2)
		P = I.FindGap(U, 1);
		System.out.println("Find((2),1) = (" + P.GetVal1() +"," + P.GetVal2() + ")");	// should be (0,1)
		U.add(2);
		P = I.FindGap(U, 4);
		System.out.println("Find((2,2),4) = (" + P.GetVal1() +"," + P.GetVal2() + ")");	// should be (2,3)
	}

}
