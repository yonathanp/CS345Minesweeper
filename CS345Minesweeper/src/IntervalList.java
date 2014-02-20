import java.util.TreeMap;
import java.util.NavigableMap;

public static final int L = 0;
public static final int M = 1;
public static final int R = 2;

public class IntervalList {
	TreeMap<Integer,Integer> Data;
	
	// construct an empty IntervalList
	public IntervalList() {
		Data = new TreeMap();
	}
	
	// returns the smallest integer r suche that r >= v and r is not in any interval
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
		if ( u == null ){
			return false;
		}

		Integer intervalType = Data.get(u);

		if (intervalType != L && u != v) {
			return true;
		}
		return false;
	}

	public Insert(Integer l, Integer r){
		lCovered = Covers(l);
		rCovered = Covers(r);
		NavigableMap interval = Data.subMap(l, false, r, false);
		interval.clear();
		if ( lCovered ) {
			Data.put(l, M);
		}
		Integer lType = Data.get(l);
		if ( lType == R ){
			Data.put(l, M);
		}
		if ( lType == null ){
			Data.put(l, L);
		}

		if ( rCovered ) {
			Data.put(r, M);
		}
		Integer rType = Data.get(r);
		if ( rType == L ){
			Data.put(r, M);
		}
		if ( rType == null ){
			Data.put(r, R);
		}

	}
}
