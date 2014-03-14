import java.io.IOException;
import java.util.ArrayList;


public class BeerAdvocateJoin {

	public static void main(String[] args) throws NumberFormatException, IOException {
		//String InFile = args[0];
		String InFile = "beeradvocate_dummy_big.tsv";
		ArrayList<Relation> Relations = new ArrayList<Relation>();
		ArrayList<String> Schema = new ArrayList<String>();
		Schema.add("BeerId");
		Schema.add("BrewerId");
		Schema.add("BeerABV");
		Schema.add("Appearance");
		Schema.add("Aroma");
		Schema.add("Palate");
		Schema.add("Taste");
		Schema.add("Overall");
		Schema.add("Time");
		ArrayList<String> SchemaS = new ArrayList<String>(Schema);
		SchemaS.remove(SchemaS.size()-1);
		SchemaS.add("Time1");	// don't want to join on time stamp ..
		Relation R = new Relation(Schema, InFile);
		Relation S = new Relation(SchemaS, InFile);
		Relations.add(R);
		Relations.add(S);
		ArrayList<String> GAO = new ArrayList<String>();
		GAO.addAll(Schema);
		GAO.add("Time1");
		Benchmark.Test(Relations, GAO);
		System.out.println("-----------------------------");
		Schema.remove(0);
		Schema.remove(0);
		R.Project(Schema);
		SchemaS.remove(0);
		SchemaS.remove(0);
		S.Project(SchemaS);
		GAO.remove(0);
		GAO.remove(0);
		
		Benchmark.Test(Relations, GAO);
	}

}
