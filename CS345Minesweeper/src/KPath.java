import java.io.IOException;
import java.util.ArrayList;


public class KPath {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Integer K = 3;
		ArrayList<Relation> Relations = new ArrayList<Relation>();
		String EdgeFile = "edges.tsv";
		ArrayList<String> GAO = new ArrayList<String>();
		for ( Integer i = 0; i < K + 1; i++){
			GAO.add(i.toString());
		}
		System.out.println("GAO: " + GAO);
		ArrayList<String> schema = null;
		ArrayList<Integer> AO = null;
		for ( int i = 0; i < K; i++){
			schema = new ArrayList<String>();
			schema.add(GAO.get(i));
			schema.add(GAO.get(i+1));
			Relation R = new Relation(schema, EdgeFile);
			Relations.add(R);
		}
		Minesweeper MS = new Minesweeper(Relations, GAO);
		for ( int i = 0; i< Relations.size(); i++){
			Relations.get(i).Dump();
		}
		MS.Join(Relations).Dump();
	}
}
