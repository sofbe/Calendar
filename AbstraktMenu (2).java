//här skapas en abstrakt klass av kalendern som används i Run där våra huvudmenyer skapas(det som står överst i menyerna). 
public abstract class AbstraktMenu implements MenuOb {
	
		private String title;

		public AbstraktMenu(String title) {
			this.title = title;
		}

		public abstract void execute();

		public String getTitle() {
			return title;
		}
}
