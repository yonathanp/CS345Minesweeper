import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class Benchmark {
	
	public static void Test(ArrayList<Relation> Relations, ArrayList<String> GAO){
		long startTime = System.currentTimeMillis();
		Minesweeper MS = new Minesweeper(Relations, GAO);
		long endTime = System.currentTimeMillis();
		System.out.println("Minesweeper Indices Creation: " + (endTime - startTime)/1000.0);
		
		
		startTime = System.currentTimeMillis();
		Relation MineOut = MS.Join(Relations);
		endTime = System.currentTimeMillis();
		System.out.println("Minesweeper Join: " + (endTime - startTime)/1000.0);
		System.out.println("Output Size " + MineOut.GetSize() + " tuples");

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
		System.out.println("Output Size: " + LastRelation.GetSize() + " tuples");
	}

	public static void main(String[] args) {
		/*
		ArrayList<String> schema1 = new ArrayList<String>(), schema2 = new ArrayList<String>();
		schema1.add("A");
		schema1.add("B");
		schema2.add("B");
		schema2.add("C");
		Relation R1 = null, R2 = null;
		try {
			R1 = new Relation(schema1, "R1.tsv");
			R2 = new Relation(schema2, "R2.tsv");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<Integer> AO1 = new ArrayList<Integer>();
		AO1.add(0);
		AO1.add(1);
		
		ArrayList<Integer> AO2 = new ArrayList<Integer>();
		AO2.add(0);
		AO2.add(1);
		
		R1.SetAttributeOrder(AO1);
		R2.SetAttributeOrder(AO2);
		long startTime = System.nanoTime();
		R1.CreateIndex();
		R2.CreateIndex();
		long endTime = System.nanoTime();
		
		System.out.println("Minesweeper Index Creation: " + (endTime - startTime)/1000000000.0);
		R1.Dump();R2.Dump();
		
		ArrayList<String> GAO = new ArrayList<String>();
		GAO.add("A");
		GAO.add("B");
		GAO.add("C");
		ArrayList<Relation> Relations = new ArrayList<Relation>();
		Relations.add(R1);
		Relations.add(R2);
		Minesweeper m = new Minesweeper(Relations, GAO);
		
		startTime = System.nanoTime();
		Relation MO = m.Join(Relations);
		endTime = System.nanoTime();
		HashSet<Tuple> MineOut = new HashSet<Tuple>();
		for( int i =0; i< MO.GetSize(); i++){
			Tuple t2 = MO.GetTuple(i);
			MineOut.add(t2);
		}
		
		System.out.println("Minesweeper Join: " + (endTime - startTime)/1000000000.0);
		
		HashJoin H = new HashJoin(R1, R2, GAO);
		
		startTime = System.nanoTime();
		H.CreateIndex();
		endTime = System.nanoTime();
		
		System.out.println("Hash Index Creation: " + (endTime - startTime)/1000000000.0);

		startTime = System.nanoTime();
		HashSet<Tuple> HashOut = H.Join();
		endTime = System.nanoTime();
		
		System.out.println("Hash Join: " + (endTime - startTime)/1000000000.0);

		//System.out.println(MineOut.equals(HashOut));
		//System.out.println(MineOut.toString());
		System.out.println("Minesweeper Out:");
		for(Tuple t : MineOut){
			t.Dump();
		}
		System.out.println("-----------------------_");
		//System.out.println(HashOut.toString());
		System.out.println("Hash-Join Out:");
		for(Tuple t : HashOut){
			t.Dump();
		}
		//TODO: compare output of hash and minesweeper join to make sure they are identical
	   */
	}

}
