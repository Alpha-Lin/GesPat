import javax.swing.BorderFactory;
//import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JTextArea;
import javax.swing.JButton;
//import javax.swing.JPanel;


//import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class Fenetre extends JFrame implements ActionListener
{
	int profession = 0;
	
	JButton bouton1 = new JButton("Agents d'administration");
    JButton bouton2 = new JButton("M"+"\u00e9"+"decin"); // unicode pour l'accent sur le e
    JButton bouton3 = new JButton("Technicien");
    JButton bouton4 = new JButton("Statistiques");
	
	public Fenetre()
	{
		
        GridBagConstraints gbc = new GridBagConstraints(); 
        
        this.setLayout(new GridBagLayout());  
        
        setTitle("GesPat");  
        
         
        
        //contrainte titre
        
        gbc.gridx = 0;  
        gbc.gridy = 0; 
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 50, 200, 50);
        
        JLabel titre = new JLabel("Logiciel de gestion des patients");
        
        titre.setForeground(new Color(8, 143, 143));
        
        
        titre.setFont(new Font("Arial", Font.BOLD, 40));
        
        titre.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0 ,new Color(8, 143, 143)));
        
        this.add(titre, gbc);
        
        
        
        // contrainte bouton 1
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 50, 0, 50);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        Dimension dimensionBouton = new Dimension(40, 40);
        
        bouton1.setBackground(Color.LIGHT_GRAY);
        bouton2.setBackground(Color.LIGHT_GRAY);
        bouton3.setBackground(Color.LIGHT_GRAY);
        bouton4.setBackground(Color.LIGHT_GRAY);
        
        bouton1.setPreferredSize(dimensionBouton);
        
        bouton2.setPreferredSize(dimensionBouton);
        
        bouton3.setPreferredSize(dimensionBouton);
        
        bouton4.setPreferredSize(dimensionBouton);
        
        this.add(bouton1, gbc); 
        
        
        
        //contrainte bouton 2
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;  
        gbc.gridy = 1;  
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 50, 0, 50);
        
        
        this.add(bouton2, gbc);  
        
    
        //contrainte bouton 3
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;  
        gbc.gridy = 1;  
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 50, 0, 50);
        
        
        
        this.add(bouton3, gbc);
        
        //contrainte bouton 4
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;  
        gbc.gridy = 1;  
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 50, 0, 50);
        
        this.add(bouton4, gbc);
        
        // Action boutons
        
        bouton1.addActionListener(this);
        bouton2.addActionListener(this);
        bouton3.addActionListener(this);
        bouton4.addActionListener(this);
        
        
        
        
        
        setSize(1230, 600);
        this.getContentPane().setBackground(Color.WHITE);
        this.setBackground(Color.RED);
        this.setMinimumSize(new Dimension(950, 400));
        setPreferredSize(getSize());    
        //this.pack();            je sais pas si c'est une bonne idée
        setVisible(true);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == bouton1)
		{
			this.setVisible(false);
			profession = 1;
			new Interface(profession);
		}
		else if(e.getSource() == bouton2)
		{
			this.setVisible(false);
			profession = 2;
			new Interface(profession);
		}
		else if(e.getSource() == bouton3)
		{
			this.setVisible(false);
			profession = 3;
			new Interface(profession);
			
		}
		else
		{
			this.setVisible(false);
			profession = 4;
			new Interface(profession);
		}
		
		
	}
}
