public class Vent extends Agent implements ForceEnvironnementale {
    public Vent(){}

    public void souffler(Consommable s, Terrain t){
        if (s.type != "Spore"){
            return;
        }
        int vitesse = (int)(Math.random()*(13-1+1)+1);
        int newX = (int)(Math.random()*(vitesse-0+1)+0);
        int newY = (int)(Math.random()*((vitesse-newX)-0+1)+0);
        t.setCase(newX,newY,s);
    }
}
