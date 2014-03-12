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
		
		long startTime = System.nanoTime();
		Minesweeper MS = new Minesweeper(Relations, GAO);
		long endTime = System.nanoTime();
		System.out.println("Minesweeper Index Creation: " + (endTime - startTime)/1000000000.0);
		
		
		startTime = System.nanoTime();
		Relation MineOut = MS.Join(Relations);
		endTime = System.nanoTime();
		System.out.println("Minesweeper Join: " + (endTime - startTime)/1000000000.0);
		
		System.out.println("Found " + MineOut.GetSize() + " k paths!");

		Relation LastRelation = Relations.get(0);
		long indexTime = 0;
		long joinTime = 0;

		for ( int i = 1; i < Relations.size(); i++){
			HashJoin H = new HashJoin(LastRelation, Relations.get(i), GAO);
			startTime = System.nanoTime();
			H.CreateIndex();
			endTime = System.nanoTime();
			indexTime += endTime - startTime;
			
			startTime = System.nanoTime();
			LastRelation = H.JoinR();
			endTime = System.nanoTime();
			joinTime += endTime - startTime;
		}
		System.out.println("Hash Index Creation: " + (indexTime)/1000000000.0);
		System.out.println("Hash Join: " + (joinTime)/1000000000.0);
		
		System.out.println("Found " + LastRelation.GetSize() + " k paths!");
	}
}
