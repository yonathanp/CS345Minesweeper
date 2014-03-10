import java.util.ArrayList;


public class Minesweeper {
	ArrayList<Relation> Relations;
	ArrayList<String> GAO;
	CDS MyCDS;
	
	Minesweeper(ArrayList<Relation> Rels, ArrayList<String> AttributeOrder){
		Relations = new ArrayList<Relation>(Rels);
		GAO = new ArrayList<String>(AttributeOrder);
		// TODO: set attribute order for all relations in query
		MyCDS = new ConstraintTree(GAO.size());
	}
	
	// Algorithm 2 in paper
	Relation Join(ArrayList<Relation> Query){ 
		ArrayList<Tuple> Output = new ArrayList<Tuple>();
		Tuple t;
		while((t = MyCDS.GetProbepoint()) != null){
			boolean OutputTupleFlag  = true;
			// loop lines 7-10
			// RIndices = Indices[r]: tuple indices for relation Query[r]
			// RIndices[i] : the i-th index tuple in the count {[i(l)],[i(r)],[i(l),i(ll)],[i(l),i(lh)],[i(h),i(hl)],[i(h),i(hh)],...}
			ArrayList<ArrayList<ArrayList<Integer>>> Indices = new ArrayList<ArrayList<ArrayList<Integer>>>();
			for(int r = 0;  r < Query.size(); r++){
				ArrayList<ArrayList<Integer>> RIndices = new ArrayList<ArrayList<Integer>>();
				Relation R = Query.get(r);
				ArrayList<Integer> AttrOrder = R.GetAttributeOrder();
				RIndices.add(new ArrayList<Integer>());	// insert empty index tuple
				int k = R.GetArity();
				int s = 0;
				int e = 1;
				int cnt = 0;
				for(int p = 0; p < k; p++){
					int CurrAttrIdx = AttrOrder.get(p);
					for(int i = s; i < e; i++){
						IntPair Gap = R.FindGap(RIndices.get(i), t.GetAttrVal(CurrAttrIdx));
						ArrayList<Integer> IL = new ArrayList<Integer>(RIndices.get(i));
						IL.add(Gap.GetVal1());
						RIndices.add(IL);
						ArrayList<Integer> IH = new ArrayList<Integer>(RIndices.get(i));
						IH.add(Gap.GetVal2());
						RIndices.add(IH);
						OutputTupleFlag = OutputTupleFlag && (R.RetrieveIndexTuple(IH).GetAttrVal(CurrAttrIdx) == t.GetAttrVal(CurrAttrIdx));
						cnt += 2;
					}
					s += cnt;
					e += cnt;
				}
				Indices.add(RIndices);
			}
			
			if(OutputTupleFlag){
				Output.add(t);
				ArrayList<Integer> V = new ArrayList<Integer>(t.Value);
				IntPair I = new IntPair(V.get(V.size()-1)-1, V.get(V.size()-1)+1);
				V.remove(V.size()-1);
				MyCDS.InsertConstraint(new Constraint(V,I));
			} else{
				for(int r = 0; r < Query.size(); r++){
				}
			}
		}
		
		// Construct a result relation out of output array
		Relation Result = new Relation(Output);
		Result.SetSchema(GAO);
		ArrayList<Integer> ResultAttrOrder = new ArrayList<Integer>(GAO.size());
		for(int i = 0; i < GAO.size(); i++){
			ResultAttrOrder.add(i);
		}
		return Result;
	}
}
