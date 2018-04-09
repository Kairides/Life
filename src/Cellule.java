public class Cellule {

	protected int largeur, hauteur;
	protected int x,y; //coordonnées
	protected boolean vivant;
	
	Cellule(int nouveauX, int nouveauY)
	{
		this.largeur = Constantes.TAILLE_CELLULE;
		this.hauteur = Constantes.TAILLE_CELLULE;
		this.vivant = false;
		this.x = nouveauX;
		this.y = nouveauY;
	}
	
	public boolean estVivant()
	{
		return this.vivant;
	}
	
	public void setVivant()
	{
		this.vivant = true;
	}
	
	public void setMort()
	{
		this.vivant = false;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
}
