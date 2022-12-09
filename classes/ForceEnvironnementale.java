/**
 * @author BERNARD Victor
 * @author PIRON-PALLISER Maximilien
 */
public interface ForceEnvironnementale{
	public void appliquer(Terrain t, ForceEnvironnementale a);
	public void agir(Ressource r, Terrain t);
}
