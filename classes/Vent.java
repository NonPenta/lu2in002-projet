public class Vent extends Agent implements ForceEnvironnementale {
    private Vent(){}

    public void souffler(Consommable s, Terrain t){
        if (s.type != "Spore"){
            return;
        }
        int vitesse = (int)(Math.random()*(13-1+1)+1);         // Initialisation vitesse du vent entre 130kmh et 10 kmh divisé par 10
        int newX = s.getX() + (int)(Math.random()*(vitesse-0+1)+0);       // Création du nouveau x entre 0 et la vitesse
        int newY = s.getY() + (int)(Math.random()*((vitesse-newX)-0+1)+0);// Création du nouveau y entre 0 et newX -> Afin que le déplacement ne soit pas seul un seul axe
        while(!(newX<=t.nbLignes-1) && !(newY <=t.nbColonnes-1)){
            if (newX >=t.nbLignes){newY++;}
            if (newY >=t.nbColonnes){newX++;}
        }
        t.setCase(s.getX()+newX,s.getY()+newY,s);
    }
}
