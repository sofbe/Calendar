import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HanteraAktiviteter {
	
	private ArrayList<Aktivitet> aktiviteter = new ArrayList<Aktivitet>(); //här skapas en arrayList med alla aktiviteter
	private Scanner input = new Scanner(System.in);
	
	//metod som används för att få alla aktiviteter i Panelkalendern
	public ArrayList<Aktivitet> getAktiviteter(){
		return aktiviteter;
	}
	//printar ut alla aktiviteter när användaren ska välja vilken aktivitet den vill radera
	public void print(){
		for (int i = 0; i < aktiviteter.size(); i++){
			System.out.println(i + ": " + aktiviteter.get(i));
		}
	}
	
	public String contains(String dag){
		aktiviteter.contains(dag);
		return dag;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (Aktivitet a : aktiviteter){
			s += a + "\n";
		}
		return s;
	}
	
	//sparar alla våra aktiviteter till en fil så att man ska kunna få upp aktiviteterna igen när programmet stängts ner med hjälp av metoden load()
	public void saveToFile(){
		File ordFil = new File("/home/johge201/minfil.txt");
		try {
			PrintWriter importeradPrint = new PrintWriter (ordFil);
			for (int i = 0; i < aktiviteter.size(); i++){
					importeradPrint.write(aktiviteter.get(i).getDag());
					importeradPrint.print(", ");
					importeradPrint.write(aktiviteter.get(i).getTitle());
					importeradPrint.print(", ");
					importeradPrint.write(String.valueOf(aktiviteter.get(i).getStarttid()));
					importeradPrint.print(", ");
					importeradPrint.write(String.valueOf(aktiviteter.get(i).getSluttid()));
					importeradPrint.print(", ");
					importeradPrint.write(String.valueOf(aktiviteter.get(i).getLösen()));
					importeradPrint.print(", ");
					importeradPrint.write(String.valueOf(aktiviteter.get(i).getIndex()));
					importeradPrint.println();
			}
			importeradPrint.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	//metod som laddar in alla aktiviteter från filen där de sparats, och visas därmed i den grafiksa kalendern 
	public void load(){
		File ordFil = new File("/home/johge201/minfil.txt");
	    try {
		   BufferedReader importeradBuff = new BufferedReader(new FileReader(ordFil));
		   while(importeradBuff.ready()){
		    	String nextLine = importeradBuff.readLine();
		    	String[] splitta = nextLine.split(", ");
		    	
		    	if(nextLine != ""){	
		    		if(splitta[4].equals("null")){
		    			Aktivitet sparadAktivitet = new Aktivitet(splitta[1], Double.parseDouble(splitta[2]), Double.parseDouble(splitta[3]), splitta[0], null);
		    			sparadAktivitet.setIndex(Integer.valueOf(splitta[5]));
				    	aktiviteter.add(sparadAktivitet);
		    		}
		    		else{
		    			Aktivitet sparadAktivitet = new Aktivitet(splitta[1], Double.parseDouble(splitta[2]), Double.parseDouble(splitta[3]), splitta[0], splitta[4]);
		    			sparadAktivitet.setIndex(Integer.valueOf(splitta[5]));
		    			aktiviteter.add(sparadAktivitet);
		    		}
		    	}
		    }
		    importeradBuff.close();
	    }
		    catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//används i vår sökfunktion i Run
	public void sökTitel(String titel){
		boolean matchFound = false;
		for (int i = 0; i < aktiviteter.size(); i++){
			if(aktiviteter.get(i).getTitle().contains(titel)){
				System.out.println(i + ": " + aktiviteter.get(i));
				matchFound = true;
			}
		}
		if(!matchFound){
			System.out.println("Det finns ingen match. Försök igen!");
		}
		
	}
	//används i vår sökfunktion i Run
	public void sökTid(String starttid){
		boolean matchFound = false;
		for (int i = 0; i < aktiviteter.size(); i++){
			if(String.valueOf(aktiviteter.get(i).getStarttid()).contains(starttid)){
				System.out.println(i + ": " + aktiviteter.get(i));
				matchFound = true;
			}
		}
		if(!matchFound){
			System.out.println("Det finns ingen match. Försök igen!");
		}
		
	}
	
	public void läggtill(){
		
		System.out.println("LÄGG TILL NY AKTIVITET");
		System.out.println("===================");

		String dag = "";
		boolean dagenFinns = false;
		while(!dagenFinns){
			String[] dagar = {"måndag", "tisdag", "onsdag", "torsdag", "fredag", "lördag", "söndag"};
			List<String> dagarLista = Arrays.asList(dagar);
					
			System.out.println("Vilken dag gäller det?");
			dag = input.nextLine();
			if(!dagarLista.contains(dag)){
				System.out.println("Denna dag existerar inte. Försök igen.");
			}
			else{
				dagenFinns = true;
			}
		}
		
		double starttid = 0;
		boolean starttidFinns = false;
		while(!starttidFinns){
			try{
				System.out.println("Starttid?");
				starttid = input.nextDouble();
				input.nextLine();
				
				if(starttid < 6 || starttid > 22){
					System.out.println("Denna starttid existerar inte. Försök igen.");
				}
				else{
					starttidFinns = true;
				}
			}
			catch(InputMismatchException exception){
			    System.out.println("Jag förstår inte ditt svar. Försök igen.");
			    input.nextLine();
			}
		}
		
		double sluttid = 0;
		boolean sluttidFinns = false;
		while(!sluttidFinns){
			try {
				System.out.println("Sluttid?");
				sluttid = input.nextDouble();
				input.nextLine();
				
				if(sluttid < 6 || sluttid > 22){
					System.out.println("Denna sluttid existerar inte. Försök igen.");
				}
				else if(sluttid <= starttid){
					System.out.println("Du måste ange en tid större än din starttid.");
				}
				else{
					sluttidFinns = true;
				}
			} 
			catch(InputMismatchException exception){
			    System.out.println("Jag förstår inte ditt svar. Försök igen.");
			    input.nextLine();
			}
		}
		
		System.out.println("Ange titel på aktivitet");
		String titel = input.nextLine();
		
		//ger användaren ett val om den vill göra sin aktivitet hemlig eller inte
		boolean lösenSvar = false;
		while(!lösenSvar){
			System.out.println("Vill du lägga till ett lösenord? Ange ja/nej.");
			String svar = input.nextLine();
		
			if (svar.equals("ja")){
				System.out.println("Ange ditt lösenord: ");
				String lösen = input.nextLine();
				Aktivitet nyAktivitet = new Aktivitet(titel, starttid, sluttid, dag, lösen);
				geIndex(nyAktivitet);
				aktiviteter.add(nyAktivitet);
				lösenSvar = true;
			}
			else if (svar.equals("nej")){
				Aktivitet nyAktivitet = new Aktivitet(titel, starttid, sluttid, dag, null);
				
				geIndex(nyAktivitet);
				aktiviteter.add(nyAktivitet);
				lösenSvar = true;
			}
			else{
				System.out.println("Jag förstår inte ditt svar. Försök igen.");
			}
		}
	}
	
	//metod som raderar aktiviteter från kalendern då användaren anger i Run vilken aktivitetet som ska raderas. Bestämmer om efterliggande aktiviteter
	//ska få ett nytt index.
	public void radera(int a){
		for (int b = 0; b < aktiviteter.size(); b++) {
			
			double starttidB = aktiviteter.get(b).getStarttid();
			double sluttidB = aktiviteter.get(b).getSluttid();
			double starttidA = aktiviteter.get(a).getStarttid();
			double sluttidA = aktiviteter.get(a).getSluttid();
			
			if(aktiviteter.get(a).getDag().equals(aktiviteter.get(b).getDag()) && aktiviteter.get(b) != aktiviteter.get(a)){
				if((starttidB >= starttidA && starttidB <= sluttidA) || 
						(sluttidB >= starttidA && sluttidB <= sluttidA)){
					if(aktiviteter.get(b).getIndex() > aktiviteter.get(a).getIndex()){
						aktiviteter.get(b).setIndex(aktiviteter.get(b).getIndex() - 1);
					}
				}
				else if((starttidA >= starttidB && starttidA <= sluttidB) || 
						(sluttidA >= starttidB && sluttidA <= sluttidB)){
					if(aktiviteter.get(b).getIndex() > aktiviteter.get(a).getIndex()){
						aktiviteter.get(b).setIndex(aktiviteter.get(b).getIndex() - 1);
					}
				}
			}
		}
		aktiviteter.remove(a);
	}
	
	//bestämmer index på de aktiviteter som överlappar varandra, så att de smidigt kan placeras i panelkalender.
	public void geIndex(Aktivitet a){
			int count = 0;
			ArrayList <Aktivitet> tempAk = new ArrayList<>();
			for (int j = 0; j < aktiviteter.size(); j++) {
				
				double starttidA = a.getStarttid();
				double sluttidA = a.getSluttid();
				double starttidJ = aktiviteter.get(j).getStarttid();
				double sluttidJ = aktiviteter.get(j).getSluttid();
				
				if(a.getDag().equals(aktiviteter.get(j).getDag()) && aktiviteter.get(j) != a){
					if((starttidA >= starttidJ && starttidA < sluttidJ) || 
							(sluttidA > starttidJ && sluttidA <= sluttidJ)){
						count++;
						tempAk.add(aktiviteter.get(j));
					}
					else if((starttidJ >= starttidA && starttidJ < sluttidA) || 
							(sluttidJ > starttidA && sluttidJ <= sluttidA)){
						count++;
						tempAk.add(aktiviteter.get(j));
					}
				}
			}
			if(count == 0){
				a.setIndex(count);
			}
			else{
				a.setIndex(tempAk.get(tempAk.size()-1).getIndex() + 1);
			}
		}
	
}
