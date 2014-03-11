import java.util.ArrayList;


public class MinesweeperTest {

	public static void main(String[] args) {
		// Example 1: List Intersection
		/*
		ArrayList<String> S = new ArrayList<String>();
		S.add("A");
		int[] V1 = {1,2,4,6,7,8};
		int[] V2 = {1,4,5,6,8,9};
		int[] V3 = {3,4,7,19,32};
		Relation R1 = GenerateListRelation(V1, 6, S);
		Relation R2 = GenerateListRelation(V2, 6, S);
		Relation R3 = GenerateListRelation(V3, 5, S);
		ArrayList<Relation> Rels = new ArrayList<Relation>();
		Rels.add(R1);
		Rels.add(R2);
		Rels.add(R3);
		Minesweeper MS = new Minesweeper(Rels, S);
		MS.Join(Rels).Dump();
		*/
		
		// Example: Bow-Tie Query
		/*
		ArrayList<String> SR = new ArrayList<String>();
		ArrayList<String> SS = new ArrayList<String>();
		ArrayList<String> ST = new ArrayList<String>();
		SR.add("X");
		SS.add("X");
		SS.add("Y");
		ST.add("Y");
		int[] VR = {1,2,3,4,5};
		IntPair[] VS = {new IntPair(7,12), new IntPair(2,19), new IntPair(2,13), new IntPair(2,12), new IntPair(3,14), new IntPair(3,12), new IntPair(7,11)};
		int[] VT = {12,13,14,15};
		Relation R = GenerateListRelation(VR, 5, SR);
		Relation S = GenerateTwoAttrRelation(VS, 7, SS);
		Relation T = GenerateListRelation(VT, 4, ST);
		ArrayList<Relation> Rels = new ArrayList<Relation>();
		Rels.add(R);
		Rels.add(S);
		Rels.add(T);
		Minesweeper MS = new Minesweeper(Rels, SS);
		MS.Join(Rels).Dump();
		*/
		
		// Example from Appendix D1
		ArrayList<String> SR = new ArrayList<String>();
		ArrayList<String> SS = new ArrayList<String>();
		ArrayList<String> ST = new ArrayList<String>();
		ArrayList<String> SU = new ArrayList<String>();
		SR.add("A1");
		SS.add("A1");
		SS.add("A2");
		ST.add("A2");
		ST.add("A3");
		SU.add("A3");
		int N = 100;
		int[] VR = new int[N];
		for(int i = 0; i < N; i++){
			VR[i] = i+1;
		}
		IntPair[] VS = new IntPair[N*N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				VS[N*i + j] = new IntPair(i+1,j+1);
			}
		}
		IntPair[] VT = {new IntPair(2,2), new IntPair(2,4)};
		int[] VU = {1,3};
		Relation R = GenerateListRelation(VR, N, SR);
		Relation S = GenerateTwoAttrRelation(VS, N*N, SS);
		Relation T = GenerateTwoAttrRelation(VT, 2, ST);
		Relation U = GenerateListRelation(VU, 2, SU);
		ArrayList<Relation> Rels = new ArrayList<Relation>();
		Rels.add(R);
		Rels.add(S);
		Rels.add(T);
		Rels.add(U);
		ArrayList<String> Schema = new ArrayList<String>();
		Schema.add("A1");
		Schema.add("A2");
		Schema.add("A3");
		Minesweeper MS = new Minesweeper(Rels, Schema);
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
	
	private static Relation GenerateTwoAttrRelation(IntPair[] Values, int L, ArrayList<String> S){
		Relation R = new Relation();
		for(int i = 0; i < L; i++){
			ArrayList<Integer> V = new ArrayList<Integer>(L);
			V.add(Values[i].GetVal1());
			V.add(Values[i].GetVal2());
			Tuple t = new Tuple(V);
			R.AddTuple(t);
		}
		R.SetSchema(S);
		return R;
	}

}
