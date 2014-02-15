import java.util.ArrayList;


public class Index {
	int Height;
	Data;
	
	Index(ArrayList<Tuple> Data, ArrayList<Integer> AttributeOrder){};
	// lookup a specific value in index
	boolean Search(Tuple Value){return false;};	
	// retrieve relation value for an index tuple - e.g. for (1,2) find the record with the second smallest
	// value of A2 among the records with the smallest value of A1
	ArrayList<Integer> RetrieveIndexTuple(ArrayList<Integer> IndexTuple){return null;};
	// see description in page 3
	IntPair FindGap(ArrayList<Integer> IndexTuple, int Value){return 0;};
}
