import java.util.ArrayList;


public class Minesweeper {
	ArrayList<Relation> Relations;
	ArrayList<String> GAO;
	CDS MyCDS;
	
	Minesweeper(ArrayList<Relation> Rels, ArrayList<String> AttributeOrder){
		Relations = new ArrayList<Relation>(Rels);
		GAO = new ArrayList<String>(AttributeOrder);
		MyCDS = new ConstraintTree(GAO.size());
	}
	
	Relation Join(ArrayList<Relation> Query){ return null;}
}
