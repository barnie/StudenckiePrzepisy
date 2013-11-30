package recApp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Read_Describe {
	private static String urlText; // adres www
	private static Document doc;
	private static PrintWriter zapis;
	private static BufferedImage image;

	public static void readDane(String file) throws IOException {
		try {
			zapis = new PrintWriter(file + ".txt");
			doc = Jsoup.connect(urlText).get();
			String tmp = "";

			/**
			 * Bierze nazwe przepisu
			 */
			Elements klas = doc.getElementsByClass("przepisNazwa");
			tmp = klas.toString();
			tmp = tmp.substring(32, tmp.length() - 12);
			// pisz(tmp);
			zapis.println("<title>" + tmp + "</title>");

			/**
			 * Bierze skladniki
			 */
			klas = doc.getElementsByClass("post_content2");
			tmp = klas.toString();
			tmp = tmp.substring(66, tmp.length() - 11);
			tmp = tmp.replace("<br />", "<next>");
			tmp = tmp.replace("&oacute;", "ó");
			tmp = tmp.replace("- ", "");
			// pisz(tmp);
			zapis.println("<components>" + tmp + "</components>");

			/**
			 * bierze opis
			 */
			klas = doc.getElementsByClass("post_content3");
			tmp = klas.toString();
			tmp = tmp.substring(159, tmp.length() - 33);
			tmp = tmp.replace("<br />", " ");
			tmp = tmp.replace("&oacute;", "ó");
			// pisz(tmp);
			zapis.println("<des>" + tmp + "</des>");

			klas = doc.getElementsByClass("img");
			tmp = klas.toString();
			tmp = tmp.substring(tmp.indexOf("pictures", 0));
			tmp = "http://www.kulinarne-odkrycia.pl/" + tmp;
			String tmp2[] = tmp.split(" ");
			tmp2[0]=tmp2[0].substring(0, tmp2[0].length()-1);
			pisz(tmp2[0]);
			URL net = new URL(tmp2[0]); 
			image = ImageIO.read(net);
			File imageFile = new File(file + ".jpg");
			ImageIO.write(image, "jpg", imageFile);
			
			
			zapis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pisz(String txt) {
		System.out.println(txt);
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int s, n;

		pisz("Starting create amount: ");
		s = in.nextInt();
		pisz("Numbers of amount: ");
		n = in.nextInt();

		for (int i = 0; i < n; i++) {
			pisz("Podaj strone " + (i + 1) + ":");
			urlText = in.next();
			readDane(String.valueOf(i + s));
		}
	}

}
