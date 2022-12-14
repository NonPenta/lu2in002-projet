/**
 * @author BERNARD Victor
 * @author PIRON-PALLISER Maximilien
 */
public class Simulation {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		UI ui = null;
		try {
		ui = UI.getNewUI(16, 32);
		} catch (InstantiationException e) {
			System.out.println("Fin");
		}
		for (int i = 0; i < 32; i++)
			for (int j = 0; j < 16; j++)
				ui.setCell(i, j, '*', i * 8, j * 16);
		ui.clearScreen();
		ui.printBuffer();

		Terrain t = new Terrain();
	}
}
