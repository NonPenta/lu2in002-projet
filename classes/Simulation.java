public class Simulation {
	public static void main(String[] args) {
		UI ui = null;
		try {
		ui = UI.getNewUI(256, 256);
		} catch (InstantiationException e) {
			System.out.println("déjà instantié ???");
		}
		for (int i = 0; i < 256; i++)
			for (int j = 0; j < 256; j++)
				ui.setCell(i, j, '*', i, j);
		ui.clearScreen();
		ui.printBuffer();
	}
}
