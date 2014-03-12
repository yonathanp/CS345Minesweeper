
public interface CDS {
	Tuple GetProbepoint();
	void InsertConstraint(Constraint C);
	void Dump();
	long GetProbeCounter();
	long GetInsertCounter();
	double GetProbeTimer();
	double GetInsertTimer();
}
