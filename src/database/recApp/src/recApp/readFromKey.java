package recApp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class readFromKey {
	private String urlText;
	private  Document doc;
	private  PrintWriter zapis, skladnik;
	private  BufferedImage image;
	private int n, s;
	private String tab[] = new String[3];
	private ArrayList<Integer> catList;
	
	readFromKey(){
		readDescribe.pisz("Wpisz ilosc insertow do utworzenia: ");
		n = readDescribe.in.nextInt();
		readDescribe.pisz("Napisz pierwszy indeks od ktorego ma zostac numerowane");
		s = readDescribe.in.nextInt();
		catList = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			readDescribe.pisz("Podaj strone " + (i + 1) + ":");
			urlText = readDescribe.in.next();
			init(i+s);
			}
	}
	
	public void init(int inx){
		try{
		zapis = new PrintWriter("inserts" + String.valueOf(inx) + ".txt");
		skladnik = new PrintWriter("sklad" + String.valueOf(inx) + ".txt");
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
		case 0: if(catList.isEmpty())
			readDescribe.pisz("Przepis musi nalezec do kategorii. Wpisz jeszcze raz");
		else
			tr = false;
		break;
		case 1: catList.add(1);
		break;
		case 2: catList.add(2);
		break;
		case 3: catList.add(3);
		break;
		case 4: catList.add(4);
		break;
		case 5: catList.add(5);
		break;
		case 6: catList.add(6);
		break;
		case 7: catList.add(7);
		break;
		case 8: catList.add(8);
		break;
		case 9: catList.add(9);
		break;
		case 10: catList.add(10);
		break;
		case 11: catList.add(11);
		break;
		case 12: catList.add(12);
		break;
		case 13: catList.add(13);
		break;
		case 14: catList.add(14);
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
//		zapis.println("<title>" + tmp + "</title>");
		tab[0] = tmp;

		/**
		 * Bierze skladniki
		 */
		klas = doc.getElementsByClass("post_content2");
		tmp = klas.toString();
		tmp = tmp.substring(66, tmp.length() - 11);
		tmp = tmp.replace("<br />", "\n");
		tmp = tmp.replace("&oacute;", "�");
		tmp = tmp.replace("- ", "");
		// pisz(tmp);
		skladnik.println(tmp);

		/**
		 * bierze opis
		 */
		klas = doc.getElementsByClass("post_content3");
		tmp = klas.toString();
		tmp = tmp.substring(159, tmp.length() - 33);
		tmp = tmp.replace("<br />", " ");
		tmp = tmp.replace("&oacute;", "�");
		// pisz(tmp);
//		zapis.println("<des>" + tmp + "</des>");
		tab[1]=tmp;

		klas = doc.getElementsByClass("img");
		tmp = klas.toString();
		tmp = tmp.substring(tmp.indexOf("pictures", 0));
		tmp = "http://www.kulinarne-odkrycia.pl/" + tmp;
		String tmp2[] = tmp.split(" ");
		tmp2[0]=tmp2[0].substring(0, tmp2[0].length()-1);
		//pisz(tmp2[0]);
		tab[2]= (String.valueOf(inx) + ".jpg");
		URL net = new URL(tmp2[0]); 
		image = ImageIO.read(net);
		File imageFile = new File(String.valueOf(inx) + ".jpg");
		ImageIO.write(image, "jpg", imageFile);
		
		try{
		while(catList.size()!=0){
			zapis.println("INSERT OR IGNORE INTO przepis VALUES (" + inx +", " + catList.remove(0) + ", '" + tab[0] + "', '" + tab[1] + "', '" + tab[2] + "');");
		}}catch(Exception e) {
			e.printStackTrace();
		}
		
		zapis.close();
		skladnik.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		
	}
	
}
