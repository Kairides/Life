import java.awt.Color;

public class Constantes {
	
	public static int ToursMS = 200;
	protected static int TAILLE_CELLULE = 20;	// taille d'une cellule 
	protected static int HAUTEUR_PLATEAU = 30;  // Hauteur du plateau
	protected static int LARGEUR_PLATEAU = 30;  // Largeur du plateau
	protected static int ACCELERATION = 25;
	protected static int TAILLE_IMAGE = 150;
	protected static int POSITION_IMAGE_X = 260;
	protected static int POSITION_IMAGE_Y = 250;
	
	protected static Color COULEUR_FOND = Color.white; 	//Eviter la couleur de grille et la couleur des cellules
	protected static Color COULEUR_CELLULE = Color.black;	//Eviter la couleur de grille et la couleur de fond
	protected static Color COULEUR_GRILLE = Color.gray;		//Eviter la couleur de fond et la couleur des cellules

	
	public static void setHaut(int newH)
	{
		HAUTEUR_PLATEAU = newH;
	}
	
	public static void setLarg(int newL)
	{
		LARGEUR_PLATEAU = newL;
	}
	
}
