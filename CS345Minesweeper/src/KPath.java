import java.io.IOException;
import java.util.ArrayList;


public class KPath {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// Join: R1(A0,A1), R2(A1,A2),....,Rk(Ak-1,Ak)
		// GAO: A1,A2,...,Ak,A0
		Integer K = Integer.parseInt(args[0]);
		//Integer K = 3;
		ArrayList<Relation> Relations = new ArrayList<Relation>(K);
		String EdgeFile = args[1];
		//String EdgeFile = "edges.tsv";
		ArrayList<String> GAO = new ArrayList<String>(K+1);
		for ( Integer i = 0; i <= K; i++){
			GAO.add("A" + i.toString());
		}
		System.out.println("GAO: " + GAO);
		ArrayList<String> schema = null;
		for ( int i = 0; i < K; i++){
			schema = new ArrayList<String>();
			schema.add(GAO.get(i));
			schema.add(GAO.get(i+1));
			Relation R = new Relation(schema, EdgeFile);
			Relations.add(R);
			//R.Dump();
		}
		
		Benchmark.Test(Relations, GAO);
		/*
		long startTime = System.currentTimeMillis();
		Minesweeper MS = new Minesweeper(Relations, GAO);
		long endTime = System.currentTimeMillis();
		System.out.println("Minesweeper Indices Creation: " + (endTime - startTime)/1000.0);
		
		
		startTime = System.currentTimeMillis();
		Relation MineOut = MS.Join(Relations);
		endTime = System.currentTimeMillis();
		System.out.println("Minesweeper Join: " + (endTime - startTime)/1000.0);
		
		System.out.println("Found " + MineOut.GetSize() + " k paths!");

		Relation LastRelation = Relations.get(0);
		double indexTime = 0;
		double joinTime = 0;

		for ( int i = 1; i < Relations.size(); i++){
			HashJoin H = new HashJoin(LastRelation, Relations.get(i), GAO);
			startTime = System.nanoTime();
			H.CreateIndex();
			endTime = System.nanoTime();
			indexTime += (endTime - startTime)/1000000.0;
			
			startTime = System.nanoTime();
			LastRelation = H.JoinR();
			endTime = System.nanoTime();
			joinTime += (endTime - startTime)/1000000.0;
		}
		System.out.println("Hash Index Creation: " + (indexTime)/1000.0);
		System.out.println("Hash Join: " + (joinTime)/1000.0);
		
		System.out.println("Found " + LastRelation.GetSize() + " k paths!");
		*/
	}
}
