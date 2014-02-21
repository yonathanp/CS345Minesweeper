import java.util.TreeMap;
import java.util.NavigableMap;

public class SortedList {
	TreeMap<Integer,ConstraintTreeNode> Data;
	
	// construct an empty SortedList
	public SortedList() {
		Data = new TreeMap<Integer,ConstraintTreeNode>();
	}
	
	// find if an integer exists in the SortedList
	public boolean Find(Integer v){
		return Data.containsKey(v);
	}
	
	// return the smallest x such that x >= v
	public Integer FindLub(Integer v){
		return Data.ceilingKey(v);
	}

	public void Insert(Integer v, ConstraintTreeNode n){
		Data.put(v, n);
	}
	
	public void Delete(Integer v){
		Data.remove(v);
	}

	// delete all integers from l to v, exclusive
	public void DeleteInterval(Integer l, Integer r){
		NavigableMap<Integer,ConstraintTreeNode> interval = Data.subMap(l, false, r, false);
		interval.clear();
	}

}
