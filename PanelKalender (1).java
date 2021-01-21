import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.*;

public class PanelKalender extends JPanel implements ActionListener {
	private JFrame f;
	private JFrame ny;
	Scanner input = new Scanner(System.in);
	private JPanel mån = new JPanel();
	private JPanel tis = new JPanel();
	private JPanel ons = new JPanel();
	private JPanel tors = new JPanel();
	private JPanel fre = new JPanel();
	private JPanel lör = new JPanel();
	private JPanel sön = new JPanel();
	private HanteraAktiviteter spara;
	private ArrayList<Aktivitet> sparadAk1;
	private HashMap<JButton, Aktivitet> aktivitetMap = new HashMap<>();
	private ArrayList<String> höjder = new ArrayList<>();
	private ArrayList<String> placering = new ArrayList<>();
	private ArrayList<String> bredd = new ArrayList<>();
	private ArrayList<String> horisontellt = new ArrayList<>();
	private ArrayList<String> titel = new ArrayList<>();
	private JButton jb;

	public PanelKalender(HanteraAktiviteter spara) {
		this.spara = spara;
	}
	
	//metod som ritar ut alla aktiviteter i det grafiska fönstret
	public void addToCalender() {
		sparadAk1 = spara.getAktiviteter();
		rensaAktivitet();
		höjder.clear();
		placering.clear();
		bredd.clear();
		horisontellt.clear();
		titel.clear();
		höjdAk();
		placering();
		bredd();
		kollaOmLåst(); 
		
		for (int i = 0; i < sparadAk1.size(); i++) {
			if(sparadAk1.get(i).getDag().contains("måndag")){
				jb = new JButton(titel.get(i));
				aktivitetMap.put(jb, sparadAk1.get(i));
				jb.setLocation(Integer.valueOf(horisontellt.get(i)), Integer.valueOf(placering.get(i)));
				jb.setSize(Integer.valueOf(bredd.get(i)),Integer.valueOf(höjder.get(i)));
				mån.add(jb);
				mån.setLayout(null);
				jb.addActionListener(this);
			}

			else if (sparadAk1.get(i).getDag().contains("tisdag")) {
				jb = new JButton(titel.get(i));
				aktivitetMap.put(jb, sparadAk1.get(i));
				jb.setLocation(Integer.valueOf(horisontellt.get(i)),Integer.valueOf(placering.get(i)));
				jb.setSize(Integer.valueOf(bredd.get(i)),Integer.valueOf(höjder.get(i)));
				tis.add(jb);
				tis.setLayout(null);
				jb.addActionListener(this);
			}

			else if (sparadAk1.get(i).getDag().contains("onsdag")) {
				jb = new JButton(titel.get(i));
				aktivitetMap.put(jb, sparadAk1.get(i));
				jb.setLocation(Integer.valueOf(horisontellt.get(i)), Integer.valueOf(placering.get(i)));
				jb.setSize(Integer.valueOf(bredd.get(i)), Integer.valueOf(höjder.get(i)));
				ons.add(jb);
				ons.setLayout(null);
				jb.addActionListener(this);

			} else if (sparadAk1.get(i).getDag().contains("torsdag")) {
				JButton jb = new JButton(titel.get(i));
				aktivitetMap.put(jb, sparadAk1.get(i));
				jb.setLocation(Integer.valueOf(horisontellt.get(i)), Integer.valueOf(placering.get(i)));
				jb.setSize(Integer.valueOf(bredd.get(i)), Integer.valueOf(höjder.get(i)));
				tors.add(jb);
				tors.setLayout(null);
				jb.addActionListener(this);
			}

			else if (sparadAk1.get(i).getDag().contains("fredag")) {
				jb = new JButton(titel.get(i));
				aktivitetMap.put(jb, sparadAk1.get(i));
				jb.setLocation(Integer.valueOf(horisontellt.get(i)),Integer.valueOf(placering.get(i)));
				jb.setSize(Integer.valueOf(bredd.get(i)),Integer.valueOf(höjder.get(i)));
				fre.add(jb);
				fre.setLayout(null);
				jb.addActionListener(this);
			}

			else if (sparadAk1.get(i).getDag().contains("lördag")) {
				jb = new JButton(titel.get(i));
				aktivitetMap.put(jb, sparadAk1.get(i));
				jb.setLocation(Integer.valueOf(horisontellt.get(i)), Integer.valueOf(placering.get(i)));
				jb.setSize(Integer.valueOf(bredd.get(i)),Integer.valueOf(höjder.get(i)));
				lör.add(jb);
				lör.setLayout(null);
				jb.addActionListener(this);
			}

			else if (sparadAk1.get(i).getDag().contains("söndag")) {
				jb = new JButton(titel.get(i));
				aktivitetMap.put(jb, sparadAk1.get(i));
				jb.setLocation(Integer.valueOf(horisontellt.get(i)),Integer.valueOf(placering.get(i)));
				jb.setSize(Integer.valueOf(bredd.get(i)),Integer.valueOf(höjder.get(i)));
				sön.add(jb);
				sön.setLayout(null);
				jb.addActionListener(this);
			}
		}
	}
	//här kollar vi om varje aktivitet är låst eller ej, och bestämmer titel utefter detta.
	//titlarna sparas i en arraylist för vidare bruk.
	public void kollaOmLåst(){
		for (int a = 0; a < sparadAk1.size(); a++) {
			if(sparadAk1.get(a).getLösen() != null){
				titel.add("LÅST");
			}
			else{
				titel.add(sparadAk1.get(a).getTitle());
			}	
		}
	}
	//tar bort alla aktiviteter från panelerna
	public void rensaAktivitet() {
		mån.removeAll();
		tis.removeAll();
		ons.removeAll();
		tors.removeAll();
		fre.removeAll();
		lör.removeAll();
		sön.removeAll();
	}
	
	//den här metoden tar hänsyn till tiden på aktiviteterna och bestämmer höjden utifrån det
	public void höjdAk() { // här valde vi int för att jFrame endast tar emot int
		for (int b = 0; b < sparadAk1.size(); b++) {
			double starttid = sparadAk1.get(b).getStarttid();
			double sluttid = sparadAk1.get(b).getSluttid();
			int aktivitetTid = (int) ((sluttid - starttid) * 60);
			höjder.add(String.valueOf(aktivitetTid)); // de sparas i en arraylist
		}
	}
	//placering i y-led
	public void placering() {
		for (int b = 0; b < sparadAk1.size(); b++) {
			double starttid = sparadAk1.get(b).getStarttid() - 6;
			int början = (int) (starttid * 60);
			placering.add(String.valueOf(början)); //de sparas i en arraylist
		}
		
	}
	/*bestämmer bredden på aktivteterna och vart aktiviteterna hamnar horisontellt på de olika dagarna, och tar hänsyn till
	 om aktiviteter läggs till på samma dag och samma tid, vilket gör att de halveras, delas i tre osv.*/
	public void bredd(){
		for (int i = 0; i < sparadAk1.size(); i++) {
			//int count = 1;
			ArrayList <Aktivitet> tempAk = new ArrayList<>();
			for (int j = 0; j < sparadAk1.size(); j++) {
			
				double starttidI = sparadAk1.get(i).getStarttid();
				double sluttidI = sparadAk1.get(i).getSluttid();
				double starttidJ = sparadAk1.get(j).getStarttid();
				double sluttidJ = sparadAk1.get(j).getSluttid();
				
				if(sparadAk1.get(i).getDag().equals(sparadAk1.get(j).getDag()) && sparadAk1.get(j) != sparadAk1.get(i)){
					if((starttidI >= starttidJ && starttidI < sluttidJ) || 
							(sluttidI > starttidJ && sluttidI <= sluttidJ)){
						//count++;
						tempAk.add(sparadAk1.get(j));
					}
					else if((starttidJ >= starttidI && starttidJ < sluttidI) || 
							(sluttidJ > starttidI && sluttidJ <= sluttidI)){
						//count++;
						tempAk.add(sparadAk1.get(j));
					}
				}
			}
			
			int aktivitetBredd = 0;
			if(tempAk.isEmpty()){
				aktivitetBredd = 200;
			}
			else if(sparadAk1.get(i).getIndex() < tempAk.get(tempAk.size()-1).getIndex()){
				aktivitetBredd = 200/(tempAk.get(tempAk.size()-1).getIndex() + 1);
			}
			else{
				aktivitetBredd = 200/(sparadAk1.get(i).getIndex() + 1);
			}
			bredd.add(String.valueOf(aktivitetBredd));
			int placeringX = aktivitetBredd * sparadAk1.get(i).getIndex();
			horisontellt.add(String.valueOf(placeringX));
			
		}
	}

	//funktion som skapar ett nytt litet fönster med information om aktiviteten
	public void actionPerformed(ActionEvent e) {
		for (JButton pressedButton : aktivitetMap.keySet()) {
			if (e.getSource() == pressedButton) {
				Aktivitet a = aktivitetMap.get(pressedButton);
				if(((JButton) e.getSource()).getText() != ("LÅST")){ //körs om aktiviteten inte är låst
					JDialog d = new JDialog(ny, "Din aktivitet:", true);
					d.setLayout(new GridLayout(0, 1));
					JLabel dag = new JLabel("Dag: " + a.getDag());
					dag.setBounds(150, 50, 200, 50);
					d.add(dag);
					JLabel title = new JLabel("Titel: " + a.getTitle());
					title.setBounds(150, 100, 200, 50);
					d.add(title);
					JLabel start = new JLabel("Starttid: "
							+ String.valueOf(a.getStarttid()));
					start.setBounds(150, 150, 200, 50);
					d.add(start);
					JLabel slut = new JLabel("Sluttid: "
							+ String.valueOf(a.getSluttid()));
					slut.setBounds(150, 200, 200, 50);
					d.add(slut);
					d.setSize(150, 150);
					d.setLocation(660, 400);
					d.getContentPane().setBackground((new Color(211, 226, 237)));
					d.setVisible(true);
				}
				else{ //om aktiviteten är låst behövs rätt lösenord anges för att få fram informationen om den. Det görs här.
					String svar = JOptionPane.showInputDialog("Ange ditt lösenord: ");
					if (svar == null) continue; //här hanteras cancel
					if (svar.equals(a.getLösen())){
						JDialog d = new JDialog(ny, "Din aktivitet:", true);
						d.setLayout(new GridLayout(0, 1));
						JLabel dag = new JLabel("Dag: " + a.getDag());
						dag.setBounds(150, 50, 200, 50);
						d.add(dag);
						JLabel title = new JLabel("Titel: " + a.getTitle());
						title.setBounds(150, 100, 200, 50);
						d.add(title);
						JLabel start = new JLabel("Starttid: "
								+ String.valueOf(a.getStarttid()));
						start.setBounds(150, 150, 200, 50);
						d.add(start);
						JLabel slut = new JLabel("Sluttid: "
								+ String.valueOf(a.getSluttid()));
						slut.setBounds(150, 200, 200, 50);
						d.add(slut);
						d.setSize(150, 150);
						d.setLocation(660, 400);
						d.getContentPane().setBackground((new Color(211, 226, 237)));
						d.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Du har angett fel lösenord.");
					}
					
				}
			}
		}

	}
	
	/**
	 * skapar våran grafiska kalender med allt dess innehåll utan aktiviteterna, som tiderna, namnet på dagarna samt färgerna under till de, storleken på de 
	 * olika komponenterna osv..
	 */
	public void dinkalender() {
		f = new JFrame();

		JLabel kalender = new JLabel("DIN KALENDER");
		kalender.setFont(new Font("Serif", Font.PLAIN, 20));
		kalender.setBounds(655, 15, 200, 50);
		f.add(kalender);

		JLabel måndag = new JLabel("MÅNDAG");
		måndag.setBounds(110, 60, 200, 50);
		f.add(måndag);

		JLabel tisdag = new JLabel("TISDAG");
		tisdag.setBounds(310, 60, 200, 50);
		f.add(tisdag);

		JLabel onsdag = new JLabel("ONSDAG");
		onsdag.setBounds(510, 60, 200, 50);
		f.add(onsdag);

		JLabel torsdag = new JLabel("TORSDAG");
		torsdag.setBounds(710, 60, 200, 50);
		f.add(torsdag);

		JLabel fredag = new JLabel("FREDAG");
		fredag.setBounds(910, 60, 200, 50);
		f.add(fredag);

		JLabel lördag = new JLabel("LÖRDAG");
		lördag.setBounds(1110, 60, 200, 50);
		f.add(lördag);

		JLabel söndag = new JLabel("SÖNDAG");
		söndag.setBounds(1310, 60, 200, 50);
		f.add(söndag);

		mån.setBounds(40, 100, 200, 960);
		mån.setBackground(new Color(232, 232, 232));
		f.add(mån);
		tis.setBounds(240, 100, 200, 960);
		tis.setBackground(Color.LIGHT_GRAY);
		f.add(tis);
		ons.setBounds(440, 100, 200, 960);
		ons.setBackground(new Color(232, 232, 232));
		f.add(ons);
		tors.setBounds(640, 100, 200, 960);
		tors.setBackground(Color.LIGHT_GRAY);
		f.add(tors);
		fre.setBounds(840, 100, 200, 960);
		fre.setBackground(new Color(232, 232, 232));
		f.add(fre);
		lör.setBounds(1040, 100, 200, 960);
		lör.setBackground(Color.LIGHT_GRAY);
		f.add(lör);
		sön.setBounds(1240, 100, 200, 960);
		sön.setBackground(new Color(232, 232, 232));
		f.add(sön);

		JLabel tid = new JLabel("6");
		tid.setBounds(5, 80, 40, 50);
		f.add(tid);
		JLabel kl7 = new JLabel("7");
		kl7.setBounds(5, 140, 40, 50);
		f.add(kl7);
		JLabel klåtta = new JLabel("8");
		klåtta.setBounds(5, 200, 40, 50);
		f.add(klåtta);
		JLabel kl9 = new JLabel("9");
		kl9.setBounds(5, 260, 40, 50);
		f.add(kl9);
		JLabel kltio = new JLabel("10");
		kltio.setBounds(5, 320, 40, 50);
		f.add(kltio);
		JLabel kl11 = new JLabel("11");
		kl11.setBounds(5, 380, 40, 50);
		f.add(kl11);
		JLabel kl12 = new JLabel("12");
		kl12.setBounds(5, 440, 40, 50);
		f.add(kl12);
		JLabel kl13 = new JLabel("13");
		kl13.setBounds(5, 500, 40, 50);
		f.add(kl13);
		JLabel kl14 = new JLabel("14");
		kl14.setBounds(5, 560, 40, 50);
		f.add(kl14);
		JLabel kl15 = new JLabel("15");
		kl15.setBounds(5, 620, 40, 50);
		f.add(kl15);
		JLabel kl16 = new JLabel("16");
		kl16.setBounds(5, 680, 40, 50);
		f.add(kl16);
		JLabel kl17 = new JLabel("17");
		kl17.setBounds(5, 740, 40, 50);
		f.add(kl17);
		JLabel kl18 = new JLabel("18");
		kl18.setBounds(5, 800, 40, 50);
		f.add(kl18);
		JLabel kl19 = new JLabel("19");
		kl19.setBounds(5, 860, 40, 50);
		f.add(kl19);
		JLabel kl20 = new JLabel("20");
		kl20.setBounds(5, 920, 40, 50);
		f.add(kl20);
		JLabel kl21 = new JLabel("21");
		kl21.setBounds(5, 980, 40, 50);
		f.add(kl21);
		JLabel kl22 = new JLabel("22");
		kl22.setBounds(5, 1040, 40, 50);
		f.add(kl22);

		//skapar två knappar
		JButton tabort = new JButton("Ta bort");
		tabort.setBounds(1250, 5, 100, 30);
		tabort.setBackground(new Color(232, 194, 199));
		f.add(tabort);
		tabort.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e){
				  f.dispose(); // tar bort JFramen 
			  }
			});

		JButton läggtill = new JButton("Lägg till");					
		läggtill.setBounds(1150, 5, 100, 30);
		läggtill.setBackground(new Color(194, 220, 232));
		f.add(läggtill);
		läggtill.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			  f.dispose(); // tar bort JFramen 
			  }
		});

		f.setSize(1440, 1100);
		f.getContentPane().setBackground((new Color(216, 249, 207)));
		f.setLayout(null);
		f.setVisible(true);

	}
}
