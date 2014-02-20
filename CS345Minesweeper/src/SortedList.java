import java.util.TreeSet;
import java.util.NavigableSet;

public class SortedList {
	TreeSet<Integer> Data;
	
	// construct an empty SortedList
	public SortedList() {
		Data = new TreeSet();
	}
	
	// find if an integer exists in the SortedList
	public boolean Find(Integer v){
		return Data.contains(v);
	}
	
	// return the smallest x such that x >= v
	public Integer FindLub(Integer v){
		return Data.ceiling(v);
	}

	public Insert(Integer v){
		Data.add(v);
	}
	
	public Delete(Integer v){
		Data.remove(v);
	}

	// delete all integers from l to v, exclusive
	public DeleteInterval(Integer l, Integer r){
		NavigableSet interval = Data.subSet(l, false, r, false);
		interval.clear();
	}

}
