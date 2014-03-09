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
	private ArrayList<Tuple> Data;
	private ArrayList<String> Schema;
	private ArrayList<Integer> AttributeOrder;
	private Index I;

	public Relation(){
		Data = new ArrayList<Tuple>();
	}
	// TODO: builders for Relation class from input files
	public Relation(String InFileName){}  // implement a constructor for every input file format we want to support

	public Relation(ArrayList<String> Schema, String InFileName) throws NumberFormatException, IOException{
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
			String[] values = line.split(" ");
			Tuple T = new Tuple();
			for ( int i = 0; i<values.length; i++){
				int v = Integer.parseInt(values[i]);
				T.AddVal(v);
			}
			AddTuple(T);
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