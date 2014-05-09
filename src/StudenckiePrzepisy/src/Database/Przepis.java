package Database;

/**
 * Created by piotr on 21.04.14.
 */
public class Przepis {
    private int id;
    private int id_kategori;
    private String nazwa;
    private String opis;
    private String zdjecie;

    public Przepis(int id, int id_kategori, String nazwa, String opis, String zdjecie) {
        this.id = id;
        this.id_kategori = id_kategori;
        this.nazwa = nazwa;
        this.opis = opis;
        this.zdjecie = zdjecie;
    }

    public Przepis(int id_kategori, String nazwa, String opis, String zdjecie) {
        this.id = 0;
        this.id_kategori = id_kategori;
        this.nazwa = nazwa;
        this.opis = opis;
        this.zdjecie = zdjecie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(int id_kategori) {
        this.id_kategori = id_kategori;
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

    @Override
    public String toString() {
        return id_kategori + " " + nazwa + " " + opis + " " + zdjecie;
    }
}
