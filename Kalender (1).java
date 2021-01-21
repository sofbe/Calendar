import java.util.Scanner;

public class Kalender {
	private Scanner input = new Scanner(System.in);

	//här skapas vår kalendermeny där användaren får sina olika val och trycks vidare utifrån valet den anger
	public void run() {
		final HanteraAktiviteter spara = new HanteraAktiviteter();
		final Menu huvudMeny = new Menu("DIN KALENDER");
		final Menu sök = new Menu("SÖK I DIN KALENDER");
		final PanelKalender panel = new PanelKalender(spara);
		spara.load(); //här laddas alla sparade aktiviteter upp automatiskt
		panel.addToCalender(); //och läggs till i den grafiska kalendern

		huvudMeny.add(new AbstraktMenu("Avsluta") {
			public void execute() {
				System.exit(0);
			}
		});
		//här får man upp den grafiska kalendern
		huvudMeny.add(new AbstraktMenu("Titta på din kalender") {
			public void execute() {
				panel.dinkalender();
				huvudMeny.execute();
			}
		});

		huvudMeny.add(new AbstraktMenu("Sök i din kalender") {
			public void execute() {
				sök.execute();
			}
		});
		/*här skapas en ny aktivitet som vi överför till HanteraAktiviteter där de sparas i en ArrayList och även läggs till i PanelKalendern,
		där även all typ av input användaren ger som på något sätt är fel och inte matchar vårt program tas hänsyn till*/
		huvudMeny.add(new AbstraktMenu("Lägg till ny aktivitet") {
			public void execute() {
				
				spara.läggtill();

				panel.addToCalender();
				spara.saveToFile();
				huvudMeny.execute();
			}
		});
		//här går det att radera aktivteter. Då körs metoden radera i HanteraAktiviteter
		huvudMeny.add(new AbstraktMenu("Vill du radera en aktivitet?") {
			public void execute() {
				System.out.println("Vilken aktivitet vill du radera? Ange nummer.");
				spara.print();
				while(true){
					try{
						int titel2 = input.nextInt();
						input.nextLine();
						spara.radera(titel2);
						break;
					}
					
					catch(Exception ex){
						System.out.println("Detta val finns inte. Ange en siffra från 0-" + (spara.getAktiviteter().size()-1) + ":");
						input.hasNextInt(); 
					}
				}
				spara.saveToFile();
				panel.addToCalender();
				huvudMeny.execute();
				
			}
		});
		
		sök.add(new AbstraktMenu("Tillbaka") {
			public void execute() {
				
				huvudMeny.execute();

			}
		});
		
		sök.add(new AbstraktMenu("Vill du söka på aktivitetens starttid?") {
			public void execute() {
				System.out.println("Vilken tid börjar din aktivitet?");
				String tid1 = input.nextLine();
				System.out.println("Din sökning matchade:");
				spara.sökTid(tid1);
				System.out.println();
				
				sök.execute();
			}
		});
		
		sök.add(new AbstraktMenu("Vill du söka på aktivitetens titel?") {
			public void execute() {
				System.out.println("Vilken titel har din aktivitet?");
				String titel1 = input.nextLine();
				System.out.println("Din sökning matchade:");
				spara.sökTitel(titel1);
				System.out.println();
				
				sök.execute();
			}
		});

		huvudMeny.execute();
	}

}
