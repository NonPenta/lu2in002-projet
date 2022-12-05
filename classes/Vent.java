public class Vent extends Agent implements ForceEnvironnementale {
    private Vent(){}

    public void souffler(Bundle b, Terrain t){
        for (Ressource r : b.getList()){
            if (r.type == "Spore"){
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
}
