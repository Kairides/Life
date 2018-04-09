import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	private static volatile boolean pPressed = false;
	private static volatile boolean rPressed = false;
	private static volatile boolean upPressed = false;
	private static volatile boolean downPressed = false;
	//private static volatile boolean enterPressed = false;
	
	//private static boolean configure = false;
	
	
	public static boolean isPPressed()
	{
		synchronized (Main.class){
			return pPressed;
		}
	}
	
	public static boolean isUpPressed()
	{
		synchronized (Main.class){
			return upPressed;
		}
	}
	
	public static boolean isDownPressed()
	{
		synchronized (Main.class){
			return downPressed;
		}
	}
	
	
	public static boolean isRPressed()
	{
		synchronized (Main.class){
			return rPressed;
		}
	}
	
	
	//Fonction de parsing pour les modèles préfaits
	public static Plateau parse(File f) throws IOException
	{
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		Plateau grille;
		int nbCellule,larg,haut;
		int x,y;
		
		String[] conf = br.readLine().split(",");
		
		larg = Double.valueOf(conf[0]).intValue();
		Constantes.setLarg(larg);
		
		
		haut = Double.valueOf(conf[1]).intValue();
		Constantes.setHaut(haut);
		
		grille = new Plateau();
		
		
		nbCellule = Double.valueOf(conf[2]).intValue();
		
		for(int i = 0; i < nbCellule; i++)
		{
			
			String[] cell = br.readLine().split(",");
			x = Double.valueOf(cell[0]).intValue();
			y = Double.valueOf(cell[1]).intValue();
			
			Cellule newCell = new Cellule(x,y);
			
			grille.getCelluleC(x, y).setVivant();
			grille.getVivantes().add(newCell);
			
		}
		
		return grille;
	}
	
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		String nomFichier = "croisseur.txt";
		File f = new File(nomFichier);
	
		
		Configuration config = new Configuration();
		Fenetre panneauConfig = new Fenetre(config);
		config.rafraichir();
		
		
		boolean pause = true;
		Plateau grille = new Plateau();
		//Plateau grilleParse = parse(f);
		//grille.set(grilleParse);
		int nbTour = 0;
		Affichage panneau = new Affichage();
		Fenetre fenetre = new Fenetre(panneau);
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher(){

			@Override
			public boolean dispatchKeyEvent(KeyEvent k){
				synchronized (Main.class){
					switch (k.getID()){
					case KeyEvent.KEY_PRESSED:
						if (k.getKeyCode() == KeyEvent.VK_P){
							pPressed = !pPressed;
						}else if (k.getKeyCode() == KeyEvent.VK_UP){
							upPressed = !upPressed;
						}else if(k.getKeyCode() == KeyEvent.VK_DOWN){
							downPressed = !downPressed;
						}else if (k.getKeyCode() == KeyEvent.VK_R){
							rPressed = !rPressed;
						}
						break;
					}
					return false;
				}	
			}	
		});
				
		panneau.affichageS(grille);
		
		while(true){
			if(Main.isPPressed()){
				pause = false;
			}
		
			while(!pause){
				nbTour++;
				
				//Pause
				if(Main.isPPressed()){
					pause = !pause;
				}
				
				//Acceleration
				if(Main.isUpPressed()){
					Constantes.ToursMS -= Constantes.ACCELERATION ;
					Main.upPressed = false;
				}
				
				//Ralentissement
				if(Main.isDownPressed()){
					Constantes.ToursMS += Constantes.ACCELERATION ;
					Main.downPressed = false;
				}
				
				//Reinitialisation
				if(Main.isRPressed()){
					grille.getVivantes().clear();
					
					grille.annihilation();
					Main.pPressed = !Main.pPressed;
					Main.rPressed = false;
					nbTour = 0;
					panneau.affichageS(grille);
				}
				
				panneau.rafraichir(grille,nbTour);
			
				Thread.sleep(Constantes.ToursMS);
			
			}
		}
	}
	
}
