import java.util.ArrayList;

public class Plateau {

	protected Cellule[][] grille;
	protected ArrayList<Cellule> vivantes = new ArrayList<Cellule>();
	
	Plateau() // Constructeur
	{
		this.grille = new Cellule[Constantes.LARGEUR_PLATEAU][Constantes.HAUTEUR_PLATEAU];
		for(int i = 0; i < Constantes.LARGEUR_PLATEAU; i++){
			for(int j = 0; j < Constantes.HAUTEUR_PLATEAU; j++){
				this.grille[i][j] = new Cellule(i,j);
			}
		}
	}
	
	public Cellule[][] getCellule()
	{
		return this.grille;
	}
	
	public void annihilation()
	{
		for(int i = 0; i < Constantes.LARGEUR_PLATEAU; i++)
		{
			for(int j = 0; j < Constantes.HAUTEUR_PLATEAU; j++)
			{
				this.grille[i][j].setMort();
			}
		}
	}
	
	public Cellule getCelluleC(int x, int y) // Recuperation d'une cellule
	{
		int xRetour = x, yRetour = y;
		
		//Condition pour la coordonnée x
		if(x<0){
			xRetour = 0;
		}else if(x>Constantes.LARGEUR_PLATEAU){
			xRetour = Constantes.LARGEUR_PLATEAU;
		}
		
		//Condition pour la coordonnée y
		if(y<0){
			yRetour = 0;
		}else if(y>Constantes.HAUTEUR_PLATEAU){
			yRetour = Constantes.HAUTEUR_PLATEAU;
		}
		
		return this.grille[xRetour][yRetour];
	}
	
	
	public ArrayList<Cellule> getVivantes()
	{
		return this.vivantes;
	}
	
	
	//Fonction de calcul de la generation suivante
	public Plateau evolution()
	{
		Plateau nouvelleGrille = new Plateau();
		int voisinesVivantes;
		int borneInfX ;
		int borneSupX ;
		int borneInfY ;
		int borneSupY ;
			
		
		//Règles
		for(int i = 0; i < Constantes.LARGEUR_PLATEAU; i++){
			for(int j = 0; j < Constantes.HAUTEUR_PLATEAU; j++){
				voisinesVivantes = 0;
				
				borneInfX = -1;
				borneSupX = 1;
				borneInfY = -1;
				borneSupY = 1;
						
				//Definition des bornes de x d'après la position de la cellule
				if(i == 0){
					borneInfX = 0;
				}else if(i == Constantes.LARGEUR_PLATEAU-1){
					
					borneSupX = 0;
				}
						
				//Definition des bornes de y d'après la position de la cellule
				if(j == 0){
					borneInfY = 0;
				}else if(j == Constantes.HAUTEUR_PLATEAU-1){
					borneSupY = 0;
				}
				
				for(int x = this.getCelluleC(i, j).getX()+borneInfX; x <= this.getCelluleC(i, j).getX()+borneSupX; x++){
					for(int y = this.getCelluleC(i, j).getY()+borneInfY; y <= this.getCelluleC(i, j).getY()+borneSupY; y++){
						if(this.getCelluleC(x, y).estVivant()){
							voisinesVivantes++;
						}
					}
				}
				
				if(voisinesVivantes == 3 && !(this.getCelluleC(i, j).estVivant())){
					nouvelleGrille.getCelluleC(i, j).setVivant();
					nouvelleGrille.getVivantes().add(this.getCelluleC(i, j));
				}else if((voisinesVivantes == 3 || voisinesVivantes == 4) && this.getCelluleC(i, j).estVivant()){
					nouvelleGrille.getCelluleC(i,j).setVivant();
					nouvelleGrille.getVivantes().add(this.getCelluleC(i, j));
				}
			}
			
		}
		return nouvelleGrille;
	
	}
	
	public void set(Plateau p)
	{
		this.grille = p.getCellule();
		this.vivantes = p.getVivantes();
		
	}
	
}
