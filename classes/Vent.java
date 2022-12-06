public class Vent extends Agent implements ForceEnvironnementale {
	public Vent() {
		super("Vent");
	}

	public void appliquer(Terrain t, ForceEnvironnementale f) {
	 Ressource r = null;
		for (int l = 0; l < t.nbLignes; l++) {
			for (int c = 0; c < t.nbColonnes; c++) {
				if ((r = t.getCase(l, c)) != null) {
					f.agir(r, t);
				}
				r = null;
			}
		}
	}

	public void agir(Ressource r, Terrain t) {
		if (r.type == "Spore") {
			int vitesse = (int)(Math.random()*(13-1+1)+1);         // Initialisation vitesse du vent entre 130kmh et 10 kmh divisé par 10
			int newX = r.getX() + (int)(Math.random()*(vitesse-0+1)+0);       // Création du nouveau x entre 0 et la vitesse
			int newY = r.getY() + (vitesse-newX);// Création du nouveau y entre 0 et newX -> Afin que le déplacement ne soit pas seul un seul axe
			while(!(newX<=t.nbLignes-1) && !(newY <=t.nbColonnes-1)){
				if (newX >=t.nbLignes){newX=0;newY++;}
				if (newY >=t.nbColonnes){newY=0;newX++;}
			}
		t.setCase(r.getX()+newX,r.getY()+newY,r);
		}
	}
}
