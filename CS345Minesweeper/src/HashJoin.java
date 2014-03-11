import java.util.ArrayList;
import java.util.HashSet;

public class HashJoin {
	Relation R1;
	Relation R2;
	HashIndex R1Index;
	ArrayList<String> JoinAttributes;
	ArrayList<String> GAO;
	HashSet<Tuple> Output;
	
	public HashJoin(Relation R1, Relation R2, ArrayList<String> GAO){
		this.GAO = new ArrayList<String>(GAO);
		
		if ( R1.GetSize() < R2.GetSize()){
			this.R1 = R1;	
			this.R2 = R2;
		}
		else {
			this.R1 = R2;
			this.R2 = R1;
		}
		
		JoinAttributes = new ArrayList<String>(R1.GetSchema());

		for ( int i = 0; i < JoinAttributes.size(); i++){
			if (!R2.GetSchema().contains(JoinAttributes.get(i))){
				JoinAttributes.remove(i);
				i--;
			}
		}
		
	}
	public void CreateIndex(){
		R1Index = new HashIndex(R1, JoinAttributes);
	}
	
	public HashSet<Tuple> Join(){
		Output = new HashSet<Tuple>();
		for( int i =0; i< R2.GetSize(); i++){
			Tuple t2 = R2.GetTuple(i);
			ArrayList<Integer> Key = new ArrayList<Integer>();
			for ( int j =0; j < JoinAttributes.size(); j++){
				String Field = JoinAttributes.get(j);
				Key.add(R2.GetField(i, Field));
			}
			ArrayList<Tuple> Candidates = R1Index.GetTuples(Key);
			if (Candidates == null){
				continue;
			}
			for ( int j=0; j<Candidates.size(); j++){
				Tuple t1 = Candidates.get(j);
				Tuple OutputTuple = new Tuple();
				for( int k=0; k<GAO.size(); k++){
					if ( R1.GetSchema().contains(GAO.get(k)) ){
						OutputTuple.AddVal(R1.GetField(t1, GAO.get(k)));
					}
					else if ( R2.GetSchema().contains(GAO.get(k))) {
						OutputTuple.AddVal(R2.GetField(t2, GAO.get(k)));
					}
				}
				Output.add(OutputTuple);
			}
		}
	return Output;
	}
}
