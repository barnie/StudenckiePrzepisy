public class Przepis_Skladnik {
	private int id_przepis;
	private int id_skladnik;
	private String miara;
	private double ile;

	public Przepis_Skladnik(int id_przepis, int id_skladnik, String miara,
			double ile) {
		super();
		this.id_przepis = id_przepis;
		this.id_skladnik = id_skladnik;
		this.miara = miara;
		this.ile = ile;
	}

	public int getId_przepis() {
		return id_przepis;
	}

	public void setId_przepis(int id_przepis) {
		this.id_przepis = id_przepis;
	}

	public int getId_skladnik() {
		return id_skladnik;
	}

	public void setId_skladnik(int id_skladnik) {
		this.id_skladnik = id_skladnik;
	}

	public String getMiara() {
		return miara;
	}

	public void setMiara(String miara) {
		this.miara = miara;
	}

	public double getIle() {
		return ile;
	}

	public void setIle(double ile) {
		this.ile = ile;
	}

}
