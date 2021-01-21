
public class Aktivitet {

	private String title;
	private double starttid;
	private double sluttid;
	private String dag;
	private String lösen;
	private int index;

	public String getTitle() {
		return title;
	}
	
	public String getLösen() {
		return lösen;
	}

	public double getStarttid() {
		return starttid;
	}

	public double getSluttid() {
		return sluttid;
	}

	public String getDag() {
		return dag;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	//här definierar vi att en aktivitet ska innehålla en titel, starttid, sluttid, en dag och sist ett lösenord som är valfritt.
	public Aktivitet(String title, double starttid, double sluttid, String dag, String lösen) {
		this.title = title;
		this.starttid = starttid;
		this.sluttid = sluttid;
		this.dag = dag;
		this.lösen = lösen;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title + " " + starttid +  "-" + sluttid + " " + dag;
	}

}
