package recApp;

import java.io.IOException;
import java.util.Scanner;

public class readDescribe {
	public static Scanner in = new Scanner(System.in);

	public static void pisz(String txt) {
		System.out.println(txt);
	}

	public static void main(String[] args) throws IOException {

		pisz("Z czego chcesz czytac:");
		pisz("1) Z klawiatury");
		pisz("2) Z pliku");
		pisz("0) Zamknij");
		boolean tr = true;
		
		while(tr){
			switch(readDescribe.in.nextInt()){
			case 0: tr = false;
			break;
			case 1: new readFromKey();
			break;
			case 2: new readFromFile();
			default: pisz("Komenda niezrozumiala");
			break;
			}
		
		
		}}}
