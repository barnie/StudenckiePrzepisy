package recApp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class readFromKey {
	private  String urlText; // adres www
	private  Document doc;
	private  PrintWriter zapis;
	private  BufferedImage image;
	private int n, s;
	
	readFromKey() throws IOException{
		readDescribe.pisz("Starting create amount: ");
		s = readDescribe.in.nextInt();
		readDescribe.pisz("Numbers of amount: ");
		n = readDescribe.in.nextInt();

		for (int i = 0; i < n; i++) {
			readDescribe.pisz("Podaj strone " + (i + 1) + ":");
			urlText = readDescribe.in.next();
			readDane(String.valueOf(i + s));
			}
	}
	
	public void readDane(String file) throws IOException {
		try {
			zapis = new PrintWriter(file + ".txt");
			doc = Jsoup.connect(urlText).get();
			String tmp = "";
			boolean tr = true;

			System.out.println("KATEGORIE");
			System.out.println("1) mieso i wedliny");
			System.out.println("2) ryby i owoce morza");
			System.out.println("3) nabial");
			System.out.println("4) warzywa");
			System.out.println("5) owoce");
			System.out.println("6) pieczywo");
			System.out.println("7) grzyby");
			System.out.println("8) zupy");
			System.out.println("9) sosy");
			System.out.println("10) przetwory");
			System.out.println("11) desery");
			System.out.println("12) napoje");
			System.out.println("13) salatki");
			System.out.println("14) studenckie");
			System.out.println("0) konczy");
			while(tr){
			readDescribe.pisz("Podaj kategorie: ");
			switch(readDescribe.in.nextInt()){
			case 0: tr = false;
			break;
			case 1: zapis.println("<cat>mieso<cat>");
			break;
			case 2: zapis.println("<cat>ryby<cat>");
			break;
			case 3: zapis.println("<cat>nabial<cat>");
			break;
			case 4: zapis.println("<cat>warzywa<cat>");
			break;
			case 5: zapis.println("<cat>owoce<cat>");
			break;
			case 6: zapis.println("<cat>pieczywo<cat>");
			break;
			case 7: zapis.println("<cat>grzyby<cat>");
			break;
			case 8: zapis.println("<cat>zupy<cat>");
			break;
			case 9: zapis.println("<cat>sosy<cat>");
			break;
			case 10: zapis.println("<cat>przetwory<cat>");
			break;
			case 11: zapis.println("<cat>desery<cat>");
			break;
			case 12: zapis.println("<cat>napoje<cat>");
			break;
			case 13: zapis.println("<cat>salatki<cat>");
			break;
			case 14: zapis.println("<cat>studenckie<cat>");
			break;
			default: readDescribe.pisz("Podany numer jest bledny. Sprobuj jeszcze raz.");
			break;
			}
			}
			
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
			//pisz(tmp2[0]);
			URL net = new URL(tmp2[0]); 
			image = ImageIO.read(net);
			File imageFile = new File(file + ".jpg");
			ImageIO.write(image, "jpg", imageFile);
			
			
			zapis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
