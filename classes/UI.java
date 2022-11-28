import java.io.*;

public class UI {
	static boolean isInstantiated = false;

	int height;
	int width;

	char[][] buffer;
	int[][] fgColor;
	int[][] bgColor;

	int cursorX;
	int cursorY;

	Terrain t = new Terrain(20, 20);

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

		cursorX = (int) width / 2;
		cursorY = (int) height / 2;
	}

	public static UI getNewUI(int height, int width) throws InstantiationException {
		if (isInstantiated) throw new InstantiationException("UI Already Instantiated");
		return new UI(height, width);
	}

	public void setCell(int x, int y, char c, int... colors) {
		buffer[y][x] = c;
		fgColor[y][x] = colors.length > 0 ? colors[0] : fgColor[y][x];
		bgColor[y][x] = colors.length > 1 ? colors[1] : bgColor[y][x];
	}

	public void clearScreen() {
		System.out.print("\033[2J\033[H");
	}

	public void printBuffer() {
		int fg = 0;
		int bg = 0;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(String.format("%s%s%c", fg != fgColor[i][j] || bg != bgColor[i][j] ? String.format("\033[%d%d", fgColor[i][j], bgColor[i][j]) : "", buffer[i][j]));
				fg = fg != fgColor[i][j] ? fgColor[i][j] : fg;
				bg = bg != bgColor[i][j] ? bgColor[i][j] : bg;
			}
		}
	}

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
