
public class IntPair {
	private int Val1;
	private int Val2;
	
	public IntPair(int x, int y){
		Val1 = x;
		Val2 = y;
	}
	
	public int GetVal1(){ return Val1;}
	public int GetVal2(){ return Val2;}
	public boolean InRange(int x){ return (Val1 <= x) && (x <= Val2);}
}
