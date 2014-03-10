import java.io.IOException;
import java.util.ArrayList;


public class RelationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> schema = new ArrayList<String>();
		schema.add("A");
		schema.add("B");
		Relation R = null;
		try {
			R = new Relation(schema, "testdata.tsv");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		R.Dump();
	}
}
