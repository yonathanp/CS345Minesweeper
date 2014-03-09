import java.util.ArrayList;

// Assumption: all attributes of all relations are restricted to unsigned integers only (natural numbers)
public class Relation {
	private ArrayList<Tuple> Data;
	private ArrayList<String> Schema;
	private ArrayList<Integer> AttributeOrder;
	private Index I;
	
	public Relation(){
		Data = new ArrayList<Tuple>();
	}
	// TODO: builders for Relation class from input files
	public Relation(String InFileName){}  // implement a constructor for every input file format we want to support
	public Relation(ArrayList<String> Schema, String InFileName){}  // implement a constructor for every input file format we want to support
	
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
	
	public Integer GetField(int Idx, String Column){
		return GetTuple(Idx).GetAttrVal(Schema.indexOf(Column));
	}
	
	public Integer GetField(Tuple t, String Column){
		return t.GetAttrVal(Schema.indexOf(Column));
	}

	public void CreateIndex(){
		I = new Index(this);
	}
	 
	public void Dump(){
		System.out.print("Relation:\n");
		for ( int i = 0; i < GetSize(); i++){
			GetTuple(i).Dump();
		}
		System.out.print("\n");
	}
}
