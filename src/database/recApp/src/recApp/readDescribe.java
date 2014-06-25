package recApp;

import java.io.IOException;
import java.util.Scanner;

public class readDescribe {
	static Scanner in = new Scanner(System.in);

	public static void pisz(String txt) {
		System.out.println(txt);
	}

	public static void main(String[] args) throws IOException {


		boolean tr = true;
		
		while(tr){
			pisz("Z czego chcesz czytac:");
			pisz("1) Z klawiatury");
			pisz("2) Z pliku");
			pisz("3) Styl stary");
			pisz("0) Zamknij");
			
			switch(readDescribe.in.nextInt()){
			case 0: tr = false;
			break;
			case 1: new readFromKey();
			tr = false;
			break;
			case 2: new readFromFile();
			tr = false;
			break;
			case 3: new oldStyle();
			tr = false;
			break;
			default: pisz("Komenda niezrozumiala");
			break;
			}
		
		}}}
