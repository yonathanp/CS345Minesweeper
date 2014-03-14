import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

// Assumption: all attributes of all relations are restricted to unsigned integers only (natural numbers)
public class Relation {
	private int IndexAccessCounter;
	private ArrayList<Tuple> Data;
	private ArrayList<String> Schema;
	// specifies the order of the relation's attributes according to the Global attribute order
	// for instance for R(A,B,C) where the GAO is W,A,X,C,B, AttributeOrder will be [0,2,1]
	private ArrayList<Integer> AttributeOrder;
	// AttributeGAOId[i] = index in GAO of schema attribute i. 
	// for instance for R(A,B,C) where the GAO is W,A,X,C,B, AttributeGAOId will be [1,4,3]
	//private ArrayList<Integer> AttributeGAOId;
	private Index I;

	public Relation(){
		Data = new ArrayList<Tuple>();
	}
	
	public Relation(ArrayList<Tuple> D){
		IndexAccessCounter = 0;
		Data = D;
	}
	
	// TODO: builders for Relation class from input files
	public Relation(String InFileName){}  // implement a constructor for every input file format we want to support

	public Relation(ArrayList<String> Schema, String InFileName) throws NumberFormatException, IOException{
		IndexAccessCounter = 0;
		Data = new ArrayList<Tuple>();
		SetSchema(Schema);
		InputStream IS = null;
		try {
			IS = new FileInputStream(InFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader BR = new BufferedReader(new InputStreamReader(IS, Charset.forName("UTF-8")));
		String line;
		while ((line = BR.readLine()) != null){
			//String[] values = line.split("\\t");
			//String[] values = line.split("\\s+"); // captures both tabs and whitespaces
			String[] values = line.split("\t");	// captures tabs only
			Tuple T = new Tuple();
			for ( int i = 0; i<values.length; i++){
				if(Character.isDigit(values[i].charAt(0))){
					int v = -1;
					try{
						v = Integer.parseInt(values[i]);
					} catch(NumberFormatException e){
						double dv = -1;
						try{
							dv = Double.parseDouble(values[i]);
						} catch(NumberFormatException exp){
							continue;
						}
						v = (int) Math.round(dv);
					}
					T.AddVal(v);
				}	
			}
			if(T.GetArity() > 0){ AddTuple(T);}
		}
		try {
			BR.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BR = null;
		IS = null;
	}  // implement a constructor for every input file format we want to support

	public void SetSchema(ArrayList<String> S){
		Schema = new ArrayList<String>(S);
	}

	public ArrayList<String> GetSchema(){
		return Schema;
	}

	public void SetAttributeOrder(ArrayList<Integer> AO){
		AttributeOrder = new ArrayList<Integer>(AO);
	}

	public void AddTuple(Tuple T){
		Data.add(new Tuple(T));
	}

	public int GetSize(){
		return Data.size();
	}

	public ArrayList<Integer> GetAttributeOrder(){
		return new ArrayList<Integer>(AttributeOrder);
	}

	public Tuple GetTuple(int Idx){
		return new Tuple(Data.get(Idx));
	}

	public int GetField(int Idx, String Column){
		return GetTuple(Idx).GetAttrVal(Schema.indexOf(Column));
	}

	public int GetField(Tuple t, String Column){
		return t.GetAttrVal(Schema.indexOf(Column));
	}

	public void CreateIndex(){
		I = new Index(this);
	}

	public void Dump(){
		System.out.print("Relation:\n");
		if(Schema != null){ System.out.println("Schema: " + Schema.toString());}
		if(AttributeOrder != null){ System.out.println("AttributeOrder: " + AttributeOrder.toString());}
		//if(AttributeGAOId != null){ System.out.println("AttributeGAOIds: " + AttributeGAOId.toString());}
		System.out.println("Data:");
		for ( int i = 0; i < GetSize(); i++){
			GetTuple(i).Dump();
		}
		System.out.print("\n");
	}
	
	public int GetArity(){
		return Schema.size();
	}
	
	public IntPair FindGap(ArrayList<Integer> IndexTuple, int a){
		if(I == null){ return null;}
		IndexAccessCounter++;
		return I.FindGap(IndexTuple, a);
	}
	
	public Tuple RetrieveIndexTuple(ArrayList<Integer> IndexTuple){
		if(I == null){ return null;}
		IndexAccessCounter++;
		int TupleId = I.RetrieveIndexTupleId(IndexTuple);
		return (TupleId >= 0) ? GetTuple(TupleId) : null;
	}
	
	public int GetAttributeId(String Attr){
		for(int i = 0; i < Schema.size(); i++){
			if(Attr.equals(Schema.get(i))){ return i;}
		}
		return -1;
	}
	
	public boolean IndexTupleInRange(ArrayList<Integer> IndexTuple){
		IndexAccessCounter++;
		return I.IsInRange(IndexTuple);
	}
	
	public int GetIndexAccessCounter(){
		return IndexAccessCounter;
	}
	
	public void Project(ArrayList<String> Attrs){
		int j = 0;
		int i = 0;
		while(i < Schema.size()){
			if(!Schema.get(i).equals(Attrs.get(j))){
				Schema.remove(i);
				for(Tuple t : Data){
					t.Value.remove(i);
				}
				for(int k = 0; k < AttributeOrder.size(); k++){
					if(AttributeOrder.get(k) == i){ AttributeOrder.remove(k);}
				}
			} else{
				j++;
				i++;
			}
		}
	}
}
