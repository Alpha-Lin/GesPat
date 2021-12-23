import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Font;

public class Fenetre extends JFrame
{
	public Fenetre()
	{
		
		
        GridBagConstraints gbc = new GridBagConstraints(); 
        
        this.setLayout(new GridBagLayout());  
        
        setTitle("GesPat");  
        
        
        GridBagLayout layout = new GridBagLayout();
        
        this.setLayout(layout);  
        
        //contrainte titre
        
        gbc.gridx = 0;  
        gbc.gridy = 0; 
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 50, 200, 50);
        
        JLabel titre = new JLabel("Logiciel de gestion des patients");
        
        titre.setForeground(new Color(8, 143, 143));
        
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        
        this.add(titre, gbc);
        
        
        
        // contrainte bouton 1
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 50, 0, 50);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        
        
        
        Button bouton1 = new Button("Agents d'administration");
        Button bouton2 = new Button("M"+"\u00e9"+"decin"); // unicode pour les lettres avec accents
        Button bouton3 = new Button("Technicien");
        
        Dimension dimensionBouton = new Dimension(40, 20);
        
        bouton1.setPreferredSize(dimensionBouton);
        
        bouton2.setPreferredSize(dimensionBouton);
        
        bouton3.setPreferredSize(dimensionBouton);
        
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
        
        
        
        
        
        
        
        setSize(1230, 600);  
        this.setMinimumSize(new Dimension(900, 400));
        setPreferredSize(getSize());    
        //this.pack();            je sais pas si c'est une bonne idée
        setVisible(true);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
	}
}
