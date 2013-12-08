import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBloader {
	private String filePath;
	private static final String DRIVER = "org.sqlite.JDBC";
	public static String DB_URL = "jdbc:sqlite:/home/yoda/Projekty/text";

	private Connection conn;
	private Statement stat;

	public DBloader(String filepath) {
		this.filePath = filepath;
		// this.DB_URL += filePath;
		try {
			Class.forName(DBloader.DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Brak sterownika JDBC");
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(DB_URL);
			stat = conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Problem z otwarciem polaczenia");
			e.printStackTrace();
		}

	}

	private boolean insertKategorie(Kategorie k) {
		try {
			PreparedStatement prepStmt = conn
					.prepareStatement("insert into kategorie values (Null,?)");
			prepStmt.setString(1, k.getRodzaj());
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad Kategorii");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean insertPrzepis(Przepis p) {
		try {
			PreparedStatement prepStmt = conn
					.prepareStatement("insert into przepis values (Null,?,?,?,?)");
			prepStmt.setInt(1, p.getId_kategorii());
			prepStmt.setString(2, p.getNazwa());
			prepStmt.setString(3, p.getOpis());
			prepStmt.setString(4, p.getZdjecie());
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy dodawaniu przepisu");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addSkladnik(Skladnik s){
		try {
			PreparedStatement prepStmt = conn
					.prepareStatement("insert into skladnik values (Null,?)");
			prepStmt.setString(1, s.getNazwa());
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy dodawaniu Skladnika");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addPrzepis_SkladniK(Przepis_Skladnik ps){
		try {
			PreparedStatement prepStmt = conn
					.prepareStatement("insert into Przepis_skladnik values (?,?,?,?)");
			prepStmt.setInt(1, ps.getId_przepis());
			prepStmt.setInt(2, ps.getId_skladnik());
			prepStmt.setString(3, ps.getMiara());
			prepStmt.setDouble(4, ps.getIle());
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy Przepis_Skladnik");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void close(){
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void generateDatabase() {
		addPrzepis_SkladniK(new Przepis_Skladnik(1, 2, "aa", 3.0));
	}
}
