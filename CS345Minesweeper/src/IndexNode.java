import java.util.ArrayList;
import java.util.Arrays;


public class IndexNode {
	// a SORTED array of keys
	private ArrayList<Integer> Keys;
	// RecordIdx[i] = an index of a record with value Keys[i]
	private ArrayList<Integer> RecordIdx;
	private ArrayList<IndexNode> Children;
	
	public IndexNode(){
		Keys = new ArrayList<Integer>();
		RecordIdx = new ArrayList<Integer>();
		Children = new ArrayList<IndexNode>();
	}
	
	public IndexNode(int L){
		Keys = new ArrayList<Integer>(L);
		RecordIdx = new ArrayList<Integer>(L);
		Children = new ArrayList<IndexNode>(L);
	}
	
	// TODO: put in a try-catch block
	public IndexNode GetChild(int idx){
		return Children.get(idx);
	}
	
	// returns a pair (L,R) such that:
	// 1. 0 <= L <= R <= Length + 1
	// 2. Keys[L] <= a <= Keys[R]
	// 3. L is the maximum index satisfying this, and R is the minimum index satisfying this
	public IntPair FindKey(int a){
		// See http://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#binarySearch(int[], int)
		// for semantics of the Binary Search function
		int idx = Arrays.binarySearch(Keys.toArray(), a);
		return (idx >= 0) ? new IntPair(idx,idx) : new IntPair(-idx-1,-idx);
	}
	
	// TODO: put in a try-catch block
	public int GetRecordId(int idx){
		return RecordIdx.get(idx);
	}
	
	public void AddKey(int idx, int key){
		Keys.add(idx,key);
	}
	
	public int GetKeyIndex(int key){
		return Arrays.binarySearch(Keys.toArray(), key);
	}
	
	public void AddChild(int idx){
		Children.add(idx, new IndexNode());
	}
	
	public void AddRecordIdx(int idx, int recordIdx){
		RecordIdx.add(idx, recordIdx);
	}
	
	public int GetSize(){
		return Keys.size();
	}
}
