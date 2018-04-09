import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Configuration extends JPanel{
	private static final long serialVersionUID = 1L;
	private int haut, larg;
	private BufferedImage monImage;
	
	private String commandes;
	
	Configuration()
	{
		this.haut = 750;
		this.larg = 500;
		
		this.commandes = "COMMANDES DU JEU DE LA VIE: \n \n"
				+ "- La touche \"p\" lance le jeu et permet de le mettre en pause \n"
				+ "- La flèche du haut accélère le jeu et la flèche du bas le ralentit \n"
				+ "- La touche \"r\" réinitialise le plateau\n"
				+ "- Cliquer sur l'écran pour rajouter des céllules\n"
				+ "- Appuyer sur le bouton \"Démarrer jeu libre\" pour commencer sur un plateau vide\n"
				+ "  ou selectionner une configuration existante et appuyer sur le bouton \"Démarrer\" \n"
				+ "  pour commencer sur un plateau préconçu. \n"
				+ "	 (la taille du plateau en jeu libre est ajustable)\n"
				+ "------------------------------------------------------------------------------------------------------------------------";
		
		setPreferredSize(new Dimension(larg,haut));
	}
	
	public void efface(Graphics g)
	{
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0,0, larg, haut);
				
		
	}
	
	public int getHaut()
	{
		return this.haut;
	}
	
	public int getLarg()
	{
		return this.larg;
	}
	
	public void afficheCommandes(Graphics g, String texte)
	{
		
		String[] sousChaines;
		g.setColor(Color.black);
		
		sousChaines = texte.split("\n");
		
		for(int i = 0; i < sousChaines.length ; i++){
			if(i == 0){
				g.drawString(sousChaines[i],larg/2 - 85,(i+1)*20);
			}else if(i == sousChaines.length-1){
				g.drawString(sousChaines[i],10,(i+1)*20);
			}else{
				g.drawString(sousChaines[i],30,(i+1)*20);
			}
		}
		
	}
	
	
	public void afficheImage(Graphics g) throws IOException
	{
		monImage = ImageIO.read(new File("croisseur.png"));
				
		//g.drawImage("image.png", 100, 200, 100, 200, 100, 100, 200, 100, 100);
		g.setColor(Color.GRAY);
		g.drawRect(Constantes.POSITION_IMAGE_X - 2, Constantes.POSITION_IMAGE_Y - 2, Constantes.TAILLE_IMAGE + 3, Constantes.TAILLE_IMAGE + 3);
		g.drawImage(monImage, Constantes.POSITION_IMAGE_X, Constantes.POSITION_IMAGE_Y,Constantes.TAILLE_IMAGE,Constantes.TAILLE_IMAGE, null);
	}

	
	@Override
	public void paintComponent(Graphics g)
	{
		
		efface(g);
		afficheCommandes(g, this.commandes);
		try {
			afficheImage(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void rafraichir()
	{
		this.repaint();
	}
	
	
}
