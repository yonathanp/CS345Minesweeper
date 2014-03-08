import java.util.HashMap;
import java.util.ArrayList;

public class HashIndex {
	Relation R;
	HashMap <ArrayList<Integer>, ArrayList<Tuple>> Data;
	ArrayList<String> Fields;
	
	public HashIndex(Relation R, ArrayList<String> Fields){
		this.Data = new HashMap <ArrayList<Integer>, ArrayList<Tuple>>();
		this.R = R;
		this.Fields = Fields;
		for ( int i = 0; i < R.GetSize(); i++){
			ArrayList<Integer> Key = new ArrayList<Integer>();
			for ( int j =0; j < Fields.size(); j++){
				String Field = Fields.get(j);
				Key.add(R.GetField(i, Field));
			}
			ArrayList<Tuple> Bucket = this.Data.get(Key);
			Bucket.add(R.GetTuple(i));
			this.Data.put(Key,Bucket);
		}
	}
	
	public ArrayList<Tuple> GetTuples(ArrayList<Integer> Key){
		return Data.get(Key);
	}
	
}
