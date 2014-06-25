
import javax.swing.JFileChooser;

public class Baza {
	public static void main(String[] args) {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		System.out.println("#" + fc.getSelectedFile().toString());
		String tmp = "";
		if ((tmp = fc.getSelectedFile().toString()).compareTo("") != 0) {
			DBloader db = new DBloader(tmp);
			db.generateDatabase();
		}
	}
}
