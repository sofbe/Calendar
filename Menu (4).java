import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu implements MenuOb {

	private String title;
	ArrayList<MenuOb> menyval = new ArrayList<MenuOb>();
	Scanner input = new Scanner(System.in);

	public Menu(String title) {
		this.title = title;
	}

	public void add(MenuOb item) {
		menyval.add(item);
	}

	public String getTitle() {
		return title;
	}
	//här skapas execute som körs i Run. Den här metoden skapar menyerna med dess alternativ.
	public void execute() {

		System.out.println(title);
		System.out.println("===================");

		for (int i = 0; i < menyval.size(); i++) {
			System.out.println(i + ": " + menyval.get(i).getTitle());
		}
		//här får användaren välja sitt val, där input som inte matchar en av de tillåtna siffrorna inte accepteras och ett nytt försök ges
		System.out.println("Ange ditt val: ");
		boolean menyvalFinns = false;
		while(!menyvalFinns){
			try{
				menyval.get(input.nextInt()).execute();
				menyvalFinns = true;
			}
			
			catch(IndexOutOfBoundsException ex){
				System.out.println("Detta val finns inte. Försök igen.");
				input.hasNextInt(); 
			}
			
			catch(InputMismatchException ex){
					System.out.println("Detta val finns inte. Försök igen.");
					input.nextLine(); 
			}
		}
				
	}
}
	
	

