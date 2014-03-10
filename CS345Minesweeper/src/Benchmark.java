import java.io.IOException;
import java.util.ArrayList;


public class Benchmark {

	public static void main(String[] args) {
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
		long startTime = System.nanoTime();
		R1.CreateIndex();
		R2.CreateIndex();
		long endTime = System.nanoTime();
		
		System.out.println("Minesweeper Index Creation: " + (endTime - startTime));
		
		ArrayList<String> GAO = new ArrayList<String>();
		GAO.add("A");
		GAO.add("B");
		GAO.add("C");
		ArrayList<Relation> Relations = new ArrayList<Relation>();
		Relations.add(R1);
		Relations.add(R2);
		Minesweeper m = new Minesweeper(Relations, GAO);
		
		startTime = System.nanoTime();
		m.Join(Relations);
		endTime = System.nanoTime();
		
		System.out.println("Minesweeper Join: " + (endTime - startTime));
		
		HashJoin H = new HashJoin(R1, R2, GAO);
		
		startTime = System.nanoTime();
		H.CreateIndex();
		endTime = System.nanoTime();
		
		System.out.println("Hash Index Creation: " + (endTime - startTime));

		startTime = System.nanoTime();
		H.Join();
		endTime = System.nanoTime();
		
		System.out.println("Hash Join: " + (endTime - startTime));

		//TODO: compare output of hash and minesweeper join to make sure they are identical
	}

}
