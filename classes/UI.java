import java.io.*;

/**
 * @author BERNARD Victor
 */
public class UI {
	private static boolean isInstantiated = false;

	private int height;
	private int width;

	private char[][] buffer;
	private int[][] fgColor;
	private int[][] bgColor;

	private Terrain t = new Terrain(20, 20);

	/**
	 * 
	 * @param height
	 * @param width
	 */
	private UI(int height, int width) {
		this.height = height;
		this.width = width;
		buffer = new char[height][width];
		fgColor = new int[height][width];
		bgColor = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				fgColor[i][j] = 0; bgColor[i][j] = 0;
			}
		}
	}

	/**
	 * Permet de créer une nouvelle interfae
	 * @param height
	 * @param width
	 * @return Une nouvelle interface
	 * @throws InstantiationException
	 */
	public static UI getNewUI(int height, int width) throws BadInstantiation {
		if (isInstantiated) throw new BadInstantiation("UI Already Instantiated");
		isInstantiated = true;
		return new UI(height, width);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param c
	 * @param colors
	 */
	public void setCell(int x, int y, char c, int... colors) {
		buffer[y][x] = c;
		fgColor[y][x] = colors.length > 0 ? colors[0] : fgColor[y][x];
		bgColor[y][x] = colors.length > 1 ? colors[1] : bgColor[y][x];
	}

	/**
	 * Permet de supprimer ce qu'il y a sur l'écran de sortie. 
	 */
	public void clearScreen() {
		System.out.print("\033[2J\033[H");
	}

	/**
	 * 
	 */
	public void printBuffer() {
		int fg = 0;
		int bg = 0;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(String.format("%s%s%c", fg != fgColor[i][j] ? String.format("\033[38;5;%dm", fgColor[i][j]) : "", bg != bgColor[i][j] ? String.format("\033[48;5;%dm", bgColor[i][j]) : "", buffer[i][j]));
				fg = fg != fgColor[i][j] ? fgColor[i][j] : fg;
				bg = bg != bgColor[i][j] ? bgColor[i][j] : bg;
			}
			System.out.print("\n");
		}
	}

	/**
	 * 
	 */
	public void getInput() {
		try (InputStreamReader isr = new InputStreamReader(System.in);) {
			char in = (char) isr.read();
			switch (in) {
			default:
				return;
			}
		}
		catch (IOException ioe) { System.out.println("IOException raised"); }
	}
}
