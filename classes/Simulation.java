public class Simulation {
	public static void main(String[] args) {
		UI ui = null;
		try {
		ui = UI.getNewUI(20, 20);
		} catch (InstantiationException e) {
			System.out.println("déjà instantié ???");
		}
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++)
				ui.setCell(i, j, '*', (int) i/(20/7) + 30, (int) j/(20/7) + 40);
		ui.clearScreen();
		ui.printBuffer();
	}
}
