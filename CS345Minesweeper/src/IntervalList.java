import java.util.TreeMap;
import java.util.NavigableMap;



public class IntervalList {
	public static final int L = 0;
	public static final int M = 1;
	public static final int R = 2;
	private TreeMap<Integer,Integer> Data;
	
	// construct an empty IntervalList
	public IntervalList() {
		Data = new TreeMap<Integer,Integer>();
	}
	
	// returns the smallest integer r such that r >= v and r is not in any interval
	public Integer Next(Integer v){
		Integer u = Data.ceilingKey(v);
		if ( u == null ){
			return v;
		}
		int intervalType = Data.get(u);
		if (intervalType == L) {
			return v;
		}
		return u;
	}
	
	// returns true if v is covered by some interval in the IntervalList
	public boolean Covers(Integer v){
		Integer u = Data.ceilingKey(v);
		if ( u == null || u.equals(v)){
			return false;
		}
		Integer intervalType = Data.get(u);
		if (intervalType != L && u != v) {
			return true;
		}
		return false;
	}

	public void Insert(Integer l, Integer r){
		boolean lCovered = Covers(l);
		boolean rCovered = Covers(r);
		NavigableMap<Integer,Integer> interval = Data.subMap(l, false, r, false);
		interval.clear();
		if(!lCovered){
			Integer lType = Data.get(l);
			if ( lType == null ){
				Data.put(l, L);
			}
			else if ( lType == R ){
				Data.put(l, M);
			}
		}
		if(!rCovered){
			Integer rType = Data.get(r);
			if ( rType == null ){
				Data.put(r, R);
			}
			else if ( rType == L ){
				Data.put(r, M);
			}
		}

	}
	
	public String toString(){
		return Data.toString();
	}
	
	public boolean IsEmpty(){
		return Data.isEmpty();
	}
}
