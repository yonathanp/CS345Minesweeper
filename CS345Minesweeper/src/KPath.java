import java.io.IOException;
import java.util.ArrayList;


public class KPath {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Integer K = 2;
		ArrayList<Relation> Relations = new ArrayList<Relation>();
		String EdgeFile = "edges.tsv";
		ArrayList<String> GAO = new ArrayList<String>();
		for ( Integer i = 0; i < K + 1; i++){
			GAO.add(i.toString());
		}
		System.out.println("GAO: " + GAO);
		ArrayList<String> schema = null;
		for ( int i = 0; i < K; i++){
			schema = new ArrayList<String>();
			schema.add(GAO.get(i));
			schema.add(GAO.get(i+1));
			Relation R = new Relation(schema, EdgeFile);
			Relations.add(R);
		}
		Minesweeper MS = new Minesweeper(Relations, GAO);
		System.out.println("Found " + MS.Join(Relations).GetSize() + " k paths!");
	}
}
