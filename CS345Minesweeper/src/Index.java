import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Index {
	int Height;
	IndexNode Root;
	
	// construct an Index; assuming data is a SET of tuples
	public Index(Relation R){
		int IdCounter = 0;
		ArrayList<Integer> AttributeOrder = R.GetAttributeOrder();
		Height = AttributeOrder.size();
		Root = new IndexNode();
		Root.SetId(0);
		IdCounter++;
		for(int TupleIdx = 0; TupleIdx < R.GetSize(); TupleIdx++){
			Tuple t = R.GetTuple(TupleIdx);
			IndexNode CurrNode = Root;
			for(int i = 0; i < Height; i++){
				int Key = t.GetAttrVal(AttributeOrder.get(i));
				int KeyIdx = CurrNode.GetKeyIndex(Key);
				//System.out.print(TupleIdx);
				if(KeyIdx >= 0){
					CurrNode = CurrNode.GetChild(KeyIdx);
				} else{
					int InsertIdx = -KeyIdx-1;
					CurrNode.AddKey(InsertIdx, Key);
					CurrNode.AddRecordIdx(InsertIdx,TupleIdx);
					if(i < Height-1){
						CurrNode.AddChild(InsertIdx);
						CurrNode = CurrNode.GetChild(InsertIdx);
						CurrNode.SetId(IdCounter);
						IdCounter++;
						// no more common path - continue inserting the rest of the tuple in its own chain
						for(int j = i+1; j < Height; j++){
							CurrNode.AddKey(0, t.GetAttrVal(AttributeOrder.get(j)));
							CurrNode.AddRecordIdx(0,TupleIdx);
							if(j < Height-1){
								CurrNode.AddChild(0);
								CurrNode = CurrNode.GetChild(0);
								CurrNode.SetId(IdCounter);
								IdCounter++;
							}
						}
					}
					break;	// move on to next tuple
				}
			}
		}
		//System.out.println("Index has " + IdCounter + " nodes");
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
	
	// retrieve relation value for an index tuple (a tuple of POSITIVE integers of arity <= Index.Height)
	// Example: for (1,2) find the record with the second smallest
	// value of A2 among the records with the smallest value of A1
	public int RetrieveIndexTuple(ArrayList<Integer> IndexTuple){
		if(IndexTuple.size() > Height){ return -1;}
		IndexNode CurrNode = Root;
		for(int i = 0; i < IndexTuple.size(); i++){
			if(IndexTuple.get(i) > CurrNode.GetSize()){ return -1;}
			if(i == IndexTuple.size()-1){ return CurrNode.GetRecordId(IndexTuple.get(i)-1);}
			CurrNode = CurrNode.GetChild(IndexTuple.get(i)-1);
		}
		return -1; // should never reach here
	}
	
	// see description in page 3
	public IntPair FindGap(ArrayList<Integer> IndexTuple, int a){
		if(IndexTuple.size() > Height-1){ return null;}
		IndexNode CurrNode = Root;
		for(int i = 0; i < IndexTuple.size(); i++){
			if(IndexTuple.get(i) > CurrNode.GetSize()){ return null;}
			CurrNode = CurrNode.GetChild(IndexTuple.get(i)-1);
		}
		System.out.println(CurrNode.GetId());
		return CurrNode.FindKey(a);
	}
	
	// prints the keys and record ids of all the nodes in BFS order
	public void Dump(File F) throws FileNotFoundException{
		PrintWriter Writer = new PrintWriter(F);
		ArrayList<IndexNode> Q = new ArrayList<IndexNode>();
		Q.add(Root);
		int i = 0;
		while(i < Q.size()){
			IndexNode CurrNode = Q.get(i);
			//System.out.println(CurrNode.GetId());
			CurrNode.Dump(Writer);
			i++;
			if(!CurrNode.IsLeaf()){
				for(int j = 0; j < CurrNode.GetSize(); j++){
						Q.add(CurrNode.GetChild(j));
				}
			}
		}
		Writer.close();
	}
	
}
