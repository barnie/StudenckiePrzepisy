
public class Przepis {

	private int id_kategorii;
	private String nazwa;
	private String opis;
	private String zdjecie;

	public Przepis(int id_kategorii, String nazwa, String opis,
			String zdjecie) {
		super();
		this.id_kategorii = id_kategorii;
		this.nazwa = nazwa;
		this.opis = opis;
		this.zdjecie = zdjecie;
	}

	public int getId_kategorii() {
		return id_kategorii;
	}

	public void setId_kategorii(int id_kategorii) {
		this.id_kategorii = id_kategorii;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getZdjecie() {
		return zdjecie;
	}

	public void setZdjecie(String zdjecie) {
		this.zdjecie = zdjecie;
	}

}

