import java.util.ArrayList;


public class Minesweeper {
	ArrayList<Relation> Relations;
	ArrayList<String> GAO;
	//GAOIds[r][a] = for relation r: index of the a-th attribute of the relation (according to global order, not relation's schema) in the GAO
	ArrayList<ArrayList<Integer>> GAOIds; 
	CDS MyCDS;
	int LoopCounter;
	
	Minesweeper(ArrayList<Relation> Rels, ArrayList<String> AttributeOrder){
		Relations = new ArrayList<Relation>(Rels);
		GAO = new ArrayList<String>(AttributeOrder);
		MyCDS = new ConstraintTree(GAO.size());
		GAOIds = new ArrayList<ArrayList<Integer>>(Relations.size());
		for(int r = 0; r < Relations.size(); r++){
			GAOIds.add(new ArrayList<Integer>());
		}
		// populate the attribute order array of each relation according to the GAO
		for(int r = 0; r < Relations.size(); r++){
			Relation R = Relations.get(r);
			ArrayList<Integer> AttrOrder = new ArrayList<Integer>();
			for(int a = 0; a < GAO.size(); a++){
				int AttrId = R.GetAttributeId(GAO.get(a));
				if(AttrId >= 0){ 
					AttrOrder.add(AttrId);
					GAOIds.get(r).add(a);
				}
			}
			R.SetAttributeOrder(AttrOrder);
			R.CreateIndex();
		}
		LoopCounter = 0;
	}
	
	// Algorithm 2 in paper
	Relation Join(ArrayList<Relation> Query){ 
		ArrayList<Tuple> Output = new ArrayList<Tuple>();
		Tuple t;
		//Tuple t_prev = null;
		while((t = MyCDS.GetProbepoint()) != null){
			//t.Dump();
			/*
			if(t_prev != null && t.equals(t_prev)){
				int LSVal = t.GetAttrVal(t.GetArity()-1);
				t.Truncate(t.GetArity()-1);
				Constraint DupConstraint = new Constraint(t.Value, new IntPair(LSVal-1,LSVal+1));
				//MyCDS.Dump();
				//System.out.println("=======================================");
				//DupConstraint.Dump();
				MyCDS.InsertConstraint(DupConstraint);
				//System.out.println("=======================================");
				//MyCDS.Dump();
				continue;
			}
			t_prev = t;
			*/
			//sMyCDS.Dump();
			//if(t.GetAttrVal(0) == 4 && t.GetAttrVal(1) == 144){ MyCDS.Dump();}
			LoopCounter++;
			boolean OutputTupleFlag  = true;
			// RIndices = Indices[r]: tuple indices for relation Query[r]
			// RIndices[i] : the i-th index tuple in the count {[i(l)],[i(r)],[i(l),i(ll)],[i(l),i(lh)],[i(h),i(hl)],[i(h),i(hh)],...}
			ArrayList<ArrayList<ArrayList<Integer>>> Indices = new ArrayList<ArrayList<ArrayList<Integer>>>(Query.size());
			for(int r = 0;  r < Query.size(); r++){
				ArrayList<ArrayList<Integer>> RIndices = new ArrayList<ArrayList<Integer>>();
				Relation R = Query.get(r);
				ArrayList<Integer> AttrOrder = R.GetAttributeOrder();
				RIndices.add(new ArrayList<Integer>());	// insert empty index tuple
				int k = R.GetArity();
				int s = 0;
				int e = 1;
				for(int p = 0; p < k; p++){
					int CurrAttrIdx = AttrOrder.get(p);
					//System.out.println("S: " + s + " , E: " + e);
					int cnt = 0;
					for(int i = s; i < e; i++){
						IntPair Gap = R.FindGap(RIndices.get(i), t.GetAttrVal(GAOIds.get(r).get(p)));
						if(Gap == null){ continue;}	// Coordinates of index tuple are out-of-range
						ArrayList<Integer> IL = new ArrayList<Integer>(RIndices.get(i));
						IL.add(Gap.GetVal1());
						RIndices.add(IL);
						ArrayList<Integer> IH = new ArrayList<Integer>(RIndices.get(i));
						IH.add(Gap.GetVal2());
						RIndices.add(IH);
						if(R.RetrieveIndexTuple(IH) == null){ 
							OutputTupleFlag = false;
						} else{
							OutputTupleFlag = OutputTupleFlag && (R.RetrieveIndexTuple(IH).GetAttrVal(CurrAttrIdx) == t.GetAttrVal(GAOIds.get(r).get(p)));
						}	
						cnt += 2;
					}
					s = e;
					e += cnt;
				}
				//System.out.println("Relation " + r + " RIndices: " + RIndices.toString());
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
					Relation R = Query.get(r);
					ArrayList<ArrayList<Integer>> RIndices = Indices.get(r);
					for(int i = 1; i < RIndices.size(); i+=2){
						ArrayList<Integer> V = new ArrayList<Integer>(RIndices.get(i));
						V.remove(V.size()-1);
						if(R.IndexTupleInRange(V)){
							Tuple tb = R.RetrieveIndexTuple(V);
							Tuple tl = R.RetrieveIndexTuple(RIndices.get(i));
							Tuple th = R.RetrieveIndexTuple(RIndices.get(i+1));
							// construct appropriate constraint
							ArrayList<Integer> CV = new ArrayList<Integer>();
							for(int j = 0; j <= V.size(); j++){
								int NextAttrIdx = GAOIds.get(r).get(j);
								while(CV.size() < NextAttrIdx){
									CV.add(Constraint.WILDCARD);
								}
								if(j < V.size()){ CV.add(tb.GetAttrVal(j));}
							}
							//System.out.println("tl:");
							//if(tl != null){tl.Dump();}
							int lval = (tl == null) ? Integer.MIN_VALUE : tl.GetAttrVal(Query.get(r).GetAttributeOrder().get(V.size()));
							//System.out.println("th:");
							//if(th != null){th.Dump();}
							int hval = (th == null) ? Integer.MAX_VALUE : th.GetAttrVal(Query.get(r).GetAttributeOrder().get(V.size()));
							if(hval != lval){
								Constraint C = new Constraint(CV,new IntPair(lval, hval));
								//System.out.println("\nRelation " + r); MyCDS.Dump();
								MyCDS.InsertConstraint(C);
								// MyCDS.Dump();
								//System.out.print("\n");C.Dump(); MyCDS.Dump(); System.out.println("------------------------");
							}
						}
					}
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
		int TotalIndexAccessCounter = 0;
		for(Relation R : Query){
			TotalIndexAccessCounter += R.GetIndexAccessCounter();
		}
		System.out.println("LoopCounter: " + LoopCounter);
		System.out.println("IndexAccessCounter: " + TotalIndexAccessCounter);
		System.out.println("ProbeCounter: " + MyCDS.GetProbeCounter());
		System.out.println("ProbeTimer: " + MyCDS.GetProbeTimer());
		System.out.println("InsertCounter: " + MyCDS.GetInsertCounter());
		System.out.println("InsertTimer: " + MyCDS.GetInsertTimer());
		return Result;
	}
}
