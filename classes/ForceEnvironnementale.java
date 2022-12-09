/**
 * @author BERNARD Victor
 * @author PIRON-PALLISER Maximilien
 */
public interface ForceEnvironnementale{
	/**
	 * 
	 * @param t
	 * @param a
	 */
	public void appliquer(Terrain t, ForceEnvironnementale a);

	/**
	 * 
	 * @param r
	 * @param t
	 */
	public void agir(Ressource r, Terrain t);
}
