import javax.swing.JFrame;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public Fenetre(Affichage pan)
	{
		this.setTitle("Game of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		add(pan);
		
		getContentPane().add(pan);
		pack();
		this.setVisible(true);
		
	}
	
	public Fenetre(Configuration config)
	{
		super("Configuration fenetre");
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		config.setFocusable(true);
		add(config);
		
		//TODO Verifier l'implémentation de bouton.
		//validation.addActionListener();
		/*validation.setLocation(config.getLarg()/2 - validation.getWidth()/2, config.getHaut() - validation.getHeight());
		config.add(validation);
		
		
		this.getContentPane().add(validation);
		validation.setVisible(true);*/
		
		this.getContentPane().add(config);
		pack();
		
		setVisible(true);
		setLocationRelativeTo(null);
		
	}
}
