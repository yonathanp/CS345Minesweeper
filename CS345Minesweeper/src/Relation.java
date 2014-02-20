import java.util.ArrayList;


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
	
	public void CreateIndex(){
		I = new Index(this);
	}
}
