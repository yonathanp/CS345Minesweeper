import java.util.ArrayList;


public class Index {
	int Height;
	IndexNode Root;
	
	// construct an Index; assuming data is a SET of tuples
	public Index(ArrayList<Tuple> Data, ArrayList<Integer> AttributeOrder){
		Height = AttributeOrder.size();
		Root = new IndexNode();
		for(int TupleIdx = 0; TupleIdx < Data.size(); TupleIdx++){
			Tuple t = Data.get(TupleIdx);
			IndexNode CurrNode = Root;
			for(int i = 0; i < Height; i++){
				int Key = t.GetAttrVal(AttributeOrder.get(i));
				int KeyIdx = CurrNode.GetKeyIndex(Key);
				if(KeyIdx >= 0){
					CurrNode = CurrNode.GetChild(KeyIdx);
				} else{
					int InsertIdx = -KeyIdx-1;
					CurrNode.AddKey(InsertIdx, Key);
					CurrNode.AddRecordIdx(InsertIdx,TupleIdx);
					CurrNode.AddChild(InsertIdx);
					CurrNode = CurrNode.GetChild(InsertIdx);
					// no more common path - continue inserting the rest of the tuple in its own chain
					for(int j = i+1; j < Height; j++){
						CurrNode.AddKey(0, t.GetAttrVal(AttributeOrder.get(j)));
						CurrNode.AddRecordIdx(0,TupleIdx);
						if(j < Height-1){
							CurrNode.AddChild(0);
							CurrNode.GetChild(0);
						}
					}
					break;	// move on to next tuple
				}
			}
		}
	}
	
	// lookup a specific value in index
	public int Search(Tuple T, ArrayList<Integer> AttributeOrder){
		int ItemLen = T.GetArity() > AttributeOrder.size() ? AttributeOrder.size() : T.GetArity();
		if(ItemLen > Height){ return -1;}
		IndexNode CurrNode = Root;
		for(int i = 0; i < ItemLen; i++){
			int Idx =  CurrNode.GetKeyIndex(T.GetAttrVal(AttributeOrder.get(i)));
			if(Idx < 0){ return -1;}
			if(i == ItemLen-1){ return CurrNode.GetRecordId(Idx);}
			CurrNode = CurrNode.GetChild(Idx);
		}
		return -1;  // should never reach here
	}
	
	// retrieve relation value for an index tuple - e.g. for (1,2) find the record with the second smallest
	// value of A2 among the records with the smallest value of A1
	public int RetrieveIndexTuple(ArrayList<Integer> IndexTuple){
		if(IndexTuple.size() > Height){ return -1;}
		IndexNode CurrNode = Root;
		for(int i = 0; i < IndexTuple.size(); i++){
			if(IndexTuple.get(i) > CurrNode.GetSize()){ return -1;}
			if(i == IndexTuple.size()-1){ return CurrNode.GetRecordId(IndexTuple.get(i));}
			CurrNode = CurrNode.GetChild(IndexTuple.get(i));
		}
		return -1; // should never reach here
	}
	
	// see description in page 3
	public IntPair FindGap(ArrayList<Integer> IndexTuple, int a){
		if(IndexTuple.size() > Height-1){ return null;}
		IndexNode CurrNode = Root;
		for(int i = 0; i < IndexTuple.size()-1; i++){
			if(IndexTuple.get(i) > CurrNode.GetSize()){ return null;}
			CurrNode = CurrNode.GetChild(IndexTuple.get(i));
		}
		return CurrNode.FindKey(a);
	}
	
	public void Dump(String OutFileName){}
}
