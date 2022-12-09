/**
 * @author BERNARD Victor
 * @author PIRON-PALLISER Maximilien
 */
public class TestSimulation {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		UI ui = null;
		try {
		ui = UI.getNewUI(256, 256);
		} catch (InstantiationException e) {
			System.out.println("Fin");
		}
		for (int i = 0; i < 256; i++)
			for (int j = 0; j < 256; j++)
				ui.setCell(i, j, '*', i, j);
		ui.clearScreen();
		ui.printBuffer();

		Terrain t = new Terrain();
		Bundle b = new Bundle();
		//Création des ressources dans un tableau
		RessourceDynamique [] tabDyn = new RessourceDynamique[20];
		RessourceStatique [] tabStat = new RessourceStatique[20];
		RessourceTerrain [] tabTer = new RessourceTerrain[20];

		for (int j=0;j<tabDyn.length;j++){
			int test = (int)(Math.random() * (3-1+1)+1);
			switch(test){
				case 1 :
					tabDyn[j] = new RessourceDynamique("Graminee",1);
				case 2 :
					tabDyn[j] = new RessourceDynamique("Champignon",1);
				case 3 :
					tabDyn[j] = new RessourceDynamique("MatBio",1);
			}
			b.addRessource(tabDyn[j].type, 1);
		}

		for (int j=0;j<tabStat.length;j++){
			int test = (int)(Math.random() * (2-1+1)+1);
			switch(test){
				case 1 :
					tabStat[j] = new RessourceStatique("Calcium",1);
				case 2 :
					tabStat[j] = new RessourceStatique("Moisissure",1);
			}
			b.addRessource(tabStat[j].type, 1);
		}

		for (int j=0;j<tabTer.length;j++){
			int test = (int)(Math.random() * (3-1+1)+1);
			switch(test){
				case 1 :
					tabTer[j] = new RessourceTerrain("Charbon");
				case 2 :
					tabTer[j] = new RessourceTerrain("Substrat");
				case 3 :
					tabTer[j] = new RessourceTerrain("Decors");
			}
		}

		for(int j=0;j<tabDyn.length;j++){
			int x = (int)(Math.random()* ((t.nbLignes-1)-0+1)+0);
			int y = (int)(Math.random()* ((t.nbColonnes-1)-0+1)+0);
			t.setCase(x, y, tabDyn[j]);
		}

		for(int j=0;j<tabStat.length;j++){
			int x = (int)(Math.random()* ((t.nbLignes-1)-0+1)+0);
			int y = (int)(Math.random()* ((t.nbColonnes-1)-0+1)+0);
			t.setCase(x, y, tabDyn[j]);
		}

		t.affiche(1);
	
		for(int i=0;i<100;i++){

		}
	}
}
