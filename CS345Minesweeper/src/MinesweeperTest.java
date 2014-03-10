import java.util.ArrayList;


public class MinesweeperTest {

	public static void main(String[] args) {
		// Example 1: List Intersection
		ArrayList<String> S = new ArrayList<String>();
		S.add("A");
		int[] V1 = {1,2,4,6,7,8};
		int[] V2 = {1,4,5,6,8,9};
		Relation R1 = GenerateListRelation(V1, 6, S);
		Relation R2 = GenerateListRelation(V2, 6, S);
		ArrayList<Relation> Rels = new ArrayList<Relation>();
		Rels.add(R1);
		Rels.add(R2);
		Minesweeper MS = new Minesweeper(Rels, S);
		R1.CreateIndex();
		R2.CreateIndex();
		MS.Join(Rels).Dump();
	}
	
	private static Relation GenerateListRelation(int[] Values, int L, ArrayList<String> S){
		Relation R = new Relation();
		for(int i = 0; i < L; i++){
			ArrayList<Integer> V = new ArrayList<Integer>(L);
			V.add(Values[i]);
			Tuple t = new Tuple(V);
			R.AddTuple(t);
		}
		R.SetSchema(S);
		return R;
	}

}
