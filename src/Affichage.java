import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Affichage extends JPanel{

	private static final long serialVersionUID = 1L;
	private int haut,larg, tourCopy;
	private Plateau plateauCopy;
	private Plateau nouveauPlateau;
	
	public Affichage()
	{
	
		this.haut = Constantes.HAUTEUR_PLATEAU*Constantes.TAILLE_CELLULE;
		this.larg = Constantes.LARGEUR_PLATEAU*Constantes.TAILLE_CELLULE;
		
		//requestFocusInWindow();
		
		setPreferredSize(new Dimension(larg,haut));
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
							
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				int nouveauX = (e.getX()/Constantes.TAILLE_CELLULE);
				int nouveauY = (e.getY()/Constantes.TAILLE_CELLULE);
				
				//System.out.println(nouveauX);
				//System.out.println(nouveauY);
				
				//System.out.println(plateauCopy.getVivantes().size());
				
				if(plateauCopy.getCelluleC(nouveauX, nouveauY).estVivant()){
					//System.out.println("mort");
					
					plateauCopy.getCelluleC(nouveauX,nouveauY).setMort();
					plateauCopy.getVivantes().remove(plateauCopy.getCelluleC(nouveauX, nouveauY));
				}else{
					//System.out.println("naissance");
					
					plateauCopy.getCelluleC(nouveauX, nouveauY).setVivant();
					plateauCopy.getVivantes().add(plateauCopy.getCelluleC(nouveauX, nouveauY));
					
				}
				
				try {
					affichageS(plateauCopy);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void efface(Graphics g) {
		
		// espace : fond blanc
		g.setColor(Constantes.COULEUR_FOND);
		g.fillRect(0,0,larg,haut);
		
		// grille : lignes grises
		g.setColor(Constantes.COULEUR_GRILLE);
		g.drawLine(0, 0, 0, haut);
		for (int x=Constantes.TAILLE_CELLULE ; x<larg; x+=Constantes.TAILLE_CELLULE) {
			// affichage des lignes verticales
			g.drawLine(x, 0, x, haut);
		}
		g.drawLine(0, 0, larg, 0);
		for (int y=Constantes.TAILLE_CELLULE ; y<haut; y+=Constantes.TAILLE_CELLULE) {
			// affichage des lignes horizontales
			g.drawLine(0, y, larg, y);
		}
	}
	
	//Affichage des Cellules
	public void afficheCellules(Graphics g, ArrayList<Cellule> vivantes) {
		// calcul des bornes d'une cellule
		
		int x,y;
		int tc = Constantes.TAILLE_CELLULE - 1;
		g.setColor(Constantes.COULEUR_CELLULE);
		
		for(int i=0; i < vivantes.size(); i++){
			
			x = (vivantes.get(i).getX()*Constantes.TAILLE_CELLULE)+1;
			y = (vivantes.get(i).getY()*Constantes.TAILLE_CELLULE)+1;
					
			g.fillRect(x,y,tc,tc);
		}		
	}
	
	private void afficheTour(Graphics g,int tourCopy) {
		
		g.setColor(Color.RED);
		g.drawString("Tour:" + tourCopy, 0, Constantes.TAILLE_CELLULE/2);
		
	}
	
	@Override 
	public void paintComponent(Graphics g){
		
		efface(g);
		afficheCellules(g,plateauCopy.getVivantes());
		afficheTour(g,tourCopy);
						
	}
	
	
	

	public void rafraichir(Plateau plate, int nbTour)
	{
		this.plateauCopy = plate;
		this.tourCopy = nbTour;
		this.repaint();
		
		this.nouveauPlateau = this.plateauCopy.evolution();

		plate.set(this.nouveauPlateau);		
	}

	public void affichageS(Plateau plate)
	{
		this.plateauCopy = plate;
		this.repaint();
	}
	
}
