import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import medical.Consultation;
import medical.Patient;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


public class Interface extends JFrame implements ActionListener
{
	public static Path consultations_path = Paths.get("consultations.txt");
	
	public int nbLignes = nbLine("patients.txt"); 
	
	public int nbPatients = 0;
	
	public int nbConsultation = 0;
    
    public String[][] patient = new String[nbPatients][4];
    
    public String[][] miniTab = new String[nbPatients][2];
    
    public String[][] consultation = new String[nbPatients][6];
    
    public String[] header1 = {"Pr"+"\u00e9"+"nom", "Nom", "Num. S"+"\u00e9"+"cu.", "Date de naissance"};
    
    public String[] header2 = {"Nom patient", "Nom m"+"\u00e9"+"decin", "Date consultation", "Appareillage n"+"\u00e9"+"cessaire", "Octroy"+"\u00e9"+" ou non", "patho"};
    
    String[] header3 = {"Pr"+"\u00e9"+"nom", "Nom", "num", "date"};
    
    JTable tablePatient;
    
    JTable tableConsultation;

    boolean check = false;
    
    boolean check2 = false;
    
    int id[]; 
    String prenom;
	String nom;
	String numSecu;
	String dateNaissance;
	
	String nomMedecin;
	String dateConsult;
	String appareil;
    
	String[] appareillage = {"Pansement", "Attelle", "Fauteuil roulant", "Lit m"+"\u00e9"+"dicalis"+"\u00e9", "B"+"\u00e9"+"quilles"};
	String[] pathologies = {"Arthrose", "Contusion musculaire","Dystrophie musculaire", "Entorse l"+"\u00e9"+"g"+"\u00e8"+"re/moyenne", "Fracture osseuse","H"+"\u00e9"+"mipl"+"\u00e9"+"gie", "L"+"\u00e9"+"sions de la moelle "+"\u00e9"+"pini"+"\u00e8"+"re", "Maladie d'Alzheimer"
			, "Maladie de Parkinson", "Maladie du motoneurone", "Maladie neuro-d"+"\u00e9"+"g"+"\u00e9"+"n"+"\u00e9"+"rative", "N"+"\u00e9"+"crose s"+"\u00e8"+"che", "Ost"+"\u00e9"+"og"+"\u00e9"+"n"+"\u00e8"+"se imparfaite", "Paralysie c"+"\u00e9"+"r"+"\u00e9"+"brale"
			, "Peau p"+"\u00e9"+"riph"+"\u00e9"+"rique alt"+"\u00e9"+"r"+"\u00e9"+"e", "Plaie superficielle/chirurgicale","Plaie bourgeonnante", "Plaie fibrineuse exsudative", "Spina Bifida", "Tendinite du poignet", "Vieillesse"};
	
	int row = 0; 
	
	int rowConsult = 0;
	
	DefaultTableModel tableModel;
	
	DefaultTableModel tableModelConsult;
	
	JScrollPane panneau;
	
	public Interface(int profession)
	{
		final JFrame frame = new JFrame();
		
		
		switch(profession)
		{
			case 1:            // Interface de l'agent d'administration
			{
				
		        JPanel panel = new JPanel(new GridBagLayout());
		        
		        frame.setTitle("GesPat-Agent Administration");
		        
		        JLabel titre = new JLabel("Interface Administration");
		        
		        titre.setFont(new Font("Arial", Font.BOLD, 40));
		        
		        JTextField searchBarP = new JTextField(2); // recherche par prenom
		        JTextField searchBarN = new JTextField(2); // recherche par nom
		        JTextField searchBarNS = new JTextField(2); // recherche par numéro de sécurité social
		        JTextField searchBarDN = new JTextField(0); // recherche par date de naissance
		        
		        JButton retour = new JButton("Menu principal"); 
		        
		        JButton bouton = new JButton("Rechercher");
		        
		        JButton boutonAjout = new JButton("Ajouter...");
		        
		        JButton boutonModif = new JButton("Modifier...");
		        
		        JButton boutonSupr = new JButton("Supprimmer");
		        
		        boutonModif.setEnabled(false); // on désactive le bouton initialement
		        
		        boutonSupr.setEnabled(false); // on désactive le bouton initialement
		        
		        searchBarDN.setText("AAAA-MM-JJ");
		        searchBarDN.setForeground(new Color(153, 153, 153));
		        
		        
		        searchBarDN.addFocusListener(new FocusListener() 
		        		{
		        			@Override
		        			public void focusGained(FocusEvent e) // change le texte et la couleur du texte lorsque le formulaire est sélectionné
		        			{
		        				searchBarDN.setText(null);
		        				searchBarDN.setForeground(new Color(0, 0, 0));
		        			}
		        			
		        			@Override
		        			public void focusLost(FocusEvent e)
		        			{
		        				
		        			}
		        		}
		        );
		        
		        // labels qui donne le nom des différent formulaire
		        
		        JLabel sousTitre1 = new JLabel("Pr"+"\u00e9"+"nom :"); 
		        JLabel sousTitre2 = new JLabel("Nom :");
		        JLabel sousTitre3 = new JLabel("Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" :");
		        JLabel sousTitre4 = new JLabel("Date de naissance :");
		        
		        
		        panel.setLayout(new GridBagLayout());
		        
		        // Contraintes de layout du titre
		        
		        GridBagConstraints gbc = new GridBagConstraints();
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.gridx = 0;
		        gbc.gridy = 0;
		        gbc.weightx = 1;
		        gbc.weighty = 1;
		        gbc.gridwidth = 4;
		        
		        gbc.anchor = GridBagConstraints.NORTH;
		        
		        titre.setHorizontalAlignment(JLabel.CENTER);
		        
		        panel.add(titre, gbc);
		        
		        
		        gbc.gridwidth = 1;
		        
		        // Contraintes de layout du sous titre 1 : prenom
		        
		        gbc.gridx = 0;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre1, gbc);
		        
		        // Contraintes de layout du sous titre 2 : nom
		        
		        gbc.gridx = 1;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;        
		        panel.add(sousTitre2, gbc);
		        
		        // Contraintes de layout du sous titre 3 : numero
		        
		        gbc.gridx = 2;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 70, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre3, gbc);
		        
		        // Contraintes de layout du sous titre 4 : date de naissance
		        
		        gbc.gridx = 3;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        panel.add(sousTitre4, gbc);
		        
		        // Contraintes de layout de la barre de recherche : prenom
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 0;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		       
		        panel.add(searchBarP, gbc);
		        
		        // Contraintes de layout de la barre de recherche : nom
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 1;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        panel.add(searchBarN, gbc);
		        
		        // Contraintes de layout de la barre de recherche : numero
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 70, 0, 70);
		        gbc.gridx = 2;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(searchBarNS, gbc);
		        
		        // Contraintes de layout de la barre de recherche : date de naissance
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 70, 0, 70);
		        gbc.gridx = 3;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(searchBarDN, gbc);
		        
		        // Contraintes de layout du bouton de recherche
		        
		        GridBagConstraints gbc2 = new GridBagConstraints();
		        gbc2.gridx = 0;
		        gbc2.gridy = 3;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 5;
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(bouton, gbc2);
		        
		        
		        // Contraintes de layout du tableau des patients
		        
		        gbc.gridx = 0;
		        gbc.gridy = 4;
		        gbc.weightx = 1;
		        gbc.gridwidth = 4;
		        gbc.insets = new Insets(0, 0, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        // Contraintes de layout du bouton de retour au menu principal
		        
		        gbc2.gridx = 0;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(retour, gbc2);
		        
		        // Contraintes de layout du bouton d'ajout des patients
		        
		        gbc2.gridx = 1;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonAjout, gbc2);
		        
		        // Contraintes de layout du bouton de modification des patients
		        
		        gbc2.gridx = 0;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 4;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonModif, gbc2);
		        
		        // Contraintes de layout du bouton de suppression des patients
		        
		        gbc2.gridx = 2;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 2;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonSupr, gbc2);
		        
		       
		       retour.addActionListener(new ActionListener() // dès que le bouton retour est pressé, on retourne au menu principal
		    		   {
		    	   			public void actionPerformed(ActionEvent e)
		    	   			{
		    	   				frame.setVisible(false);
		    	   				new Fenetre();
		    	   			}
		    		   }
		    	);
		        
		        
		        bouton.addActionListener(new ActionListener() // dès que le bouton de recherche est pressé...
		        		{
		        			@Override
							public void actionPerformed(ActionEvent e) 
							{
		        				try
								{
		        					//... les variables suivantes prennent les valeurs des différents formulaires
		        					
		        					prenom = searchBarP.getText();
									nom = searchBarN.getText();
									numSecu = searchBarNS.getText();
									dateNaissance = searchBarDN.getText();
									
									
									// Variables booléennes qui revoie vrai si les formulaires n'ont pas été remplie
							        boolean checkPrenom = searchBarP.getText().equals("");
							        boolean checkNom = searchBarN.getText().equals("");
							        boolean checkNS = searchBarNS.getText().equals("");
							        boolean checkDN = searchBarDN.getText().equals("AAAA-MM-JJ");
							        
							        
							        
							        if(checkPrenom || checkNom || checkNS || checkDN) // message d'erreur quand au moins un des champs est vide
									{
							        	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
										dispose();
										searchBarP.setText(null);
							        	searchBarN.setText(null);
							        	searchBarNS.setText(null);
							        	searchBarDN.setText("AAAA-MM-JJ");
							        	searchBarDN.setForeground(new Color(153, 153, 153));
									}
							        else if((prenom.matches(".*\\d.*") || nom.matches(".*\\d.*"))) // message d'erreur lorsque les champs nom et prenom on des nombres 
							        {
							        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des lettre pour les champs suivants :"+"\n"
										        +"Pr"+"\u00e9"+"nom, Nom !");
							        	dispose();
							        	searchBarP.setText(null);
							        	searchBarN.setText(null);
							        	searchBarNS.setText(null);
							        	searchBarDN.setText("AAAA-MM-JJ");
							        	searchBarDN.setForeground(new Color(153, 153, 153));
							        }
							        else if(!(numSecu.matches("[0-9]+"))) // message d'erreur lorsque le numéro de sécurité inscrit ne contient pas que des chiffres
							        {
							        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des chiffres pour le champ suivant :"+"\n"
										        +"Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale !");
							        	dispose();
							        	searchBarP.setText(null);
							        	searchBarN.setText(null);
							        	searchBarNS.setText(null);
							        	searchBarDN.setText("AAAA-MM-JJ");
							        	searchBarDN.setForeground(new Color(153, 153, 153));
							        }
							        else 
							        {
							        	// on crée un patient de test...
							        	
							        	Patient search = new Patient(prenom, nom, numSecu, dateNaissance.equals("AAAA-MM-JJ") ? null :  LocalDate.parse(dateNaissance));
							        	
										if(Patient.rechercherPatient(search)) // on vérifie qu'un patient identique au patient test existe...
							        	{

							        		Patient[] listePatient = Patient.rechercherPatients(search); // ...on insère tous les patients identique au patient test dans un array...

							        		nbPatients = listePatient.length;

							        		String[][] patients = new String[nbPatients][5];

							        		id = new int[nbPatients]; 

							        		for(int i = 0 ; i < nbPatients ; i++) // ... et on met toutes les informations de ces patients dans array à deux dimensions...
							        		{	
							        			id[i] = listePatient[i].getId();
							        			patients[i][0] = listePatient[i].getNom();
							        			patients[i][1] = listePatient[i].getPrenom();
							        			patients[i][2] = listePatient[i].getSecu();
												patients[i][3] = (listePatient[i].getNaissance()).toString();
							        		}

							        		tableModel = new DefaultTableModel(patients, header1); // ... pour changer les afficher sur le tableau des patients
							        		tablePatient.setModel(tableModel);


								        	panel.add(new JScrollPane(tablePatient), gbc);
								        	frame.add(panel);
									        frame.setVisible(true);

							        	}
							        	else // message d'erreur quand le patient n'existe pas
							        	{
							        		JOptionPane.showMessageDialog(null, "Patient inconnu, veuillez l'ajouter ou vérifier les paramètres entrée !");
							        	}
							        	
							        	searchBarP.setText(null);
							        	searchBarN.setText(null);
							        	searchBarNS.setText(null);
							        	searchBarDN.setText("AAAA-MM-JJ");
							        	searchBarDN.setForeground(new Color(153, 153, 153));
							        }
		        				
								}
								catch(DateTimeParseException error) // message d'erreur lorsque le formulaire de date de naissance est mal rempli
								{
									JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqu"+"\u00e9"+" : AAAA-MM-JJ !");
									dateNaissance = "";
									dispose();
									searchBarP.setText(null);
									searchBarN.setText(null);
									searchBarNS.setText(null);
									searchBarDN.setText("AAAA-MM-JJ");
									searchBarDN.setForeground(new Color(153, 153, 153));
								}
								catch(NumberFormatException error) // message d'erreur lorsque le formulaire du numéro de sécurité social est mal rempli
								{
									JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des chiffres pour le champ suivant :"+"\n"
											+"Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale !");
									dispose();
									searchBarP.setText(null);
									searchBarN.setText(null);
									searchBarNS.setText(null);
									searchBarDN.setText("AAAA-MM-JJ");
									searchBarDN.setForeground(new Color(153, 153, 153));
								}
		        				
							}
		        		}
						     
		        	);
		        
		        
		        tablePatient = new JTable(patient, header1);
		        
		        panel.add(new JScrollPane(tablePatient), gbc);
		        
		        
		        tablePatient.addMouseListener(new MouseAdapter() // permet de savoir quel ligne du tableau est pressé
		        		{
		        			public void mousePressed(MouseEvent e)
		        			{
		        				Point p = e.getPoint();
		        				row = tablePatient.rowAtPoint(p);
		        			}
		        		}
		        );
		        
		        tablePatient.addFocusListener(new FocusListener() // active les bouton de modification et de suppression lorsqu'une ligne du tableau est sélectionnée
		        		{
		        			@Override
		        			public void focusGained(FocusEvent e)
		        			{
		        				boutonModif.setEnabled(true);
		        				boutonSupr.setEnabled(true);
		        			}
		        			
		        			public void focusLost(FocusEvent e)
		        			{
		        				
		        			}
		        		});
		      
		        boutonAjout.addActionListener(new ActionListener() // permet d'ajouter un patient non-existant
		        		{
		        			public void actionPerformed(ActionEvent e)
		        			{
		        				JFrame fen = new JFrame(); // création d'une nouvelle fenetre avec des formulaires
		        				
		        				fen.setLayout(new GridBagLayout());
		        				
		        				GridBagConstraints gbc = new GridBagConstraints();
		        				
		        				JLabel titre = new JLabel("Nouveau patient :");
		        				
		        				JLabel sousTitre1 = new JLabel("Pr"+"\u00e9"+"nom : ");
		        				
		        				JLabel sousTitre2 = new JLabel("Nom :");
		        				
		        				JLabel sousTitre3 = new JLabel("Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale :");
		        				
		        				JLabel sousTitre4 = new JLabel("Date de naissance :");
		        				
		        				JTextField fieldPrenom = new JTextField(10); // champ prenom du patient
		        				
		        				JTextField fieldNom = new JTextField(10); // champ nom du patient
		        				
		        				JTextField fieldNumSecu = new JTextField(10); // champ numéro de sécurité social du patient
		        				
		        				JTextField fieldDateNaissance = new JTextField(10); // champ date de naissance du patient
		        				
		        				JButton validation = new JButton("Valider");
		        				
		        				fieldDateNaissance.setText("AAAA-MM-JJ");
		        				fieldDateNaissance.setForeground(new Color(153, 153, 153));
		        				
		        				
		        				// Contrainte de layout du titre
		        				
		        				
		        				gbc.anchor = GridBagConstraints.NORTH;
		        				gbc.gridx = 0;
		        				gbc.gridy = 0;
		        				gbc.gridwidth = 2;
		        				gbc.weightx = 1;
		        				gbc.weighty = 1;
		        				gbc.insets = new Insets(0, 100, 0, 0);
		        				titre.setAlignmentX(JLabel.CENTER);
		        				
		        				fen.add(titre, gbc);
		        				
		        				// Contrainte de layout du sous titre 1 : prenom
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 1;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre1, gbc);
		        				
		        				// Contrainte de layout du sous titre 2 : nom
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 2;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre2, gbc);
		        				
		        				// Contrainte de layout du sous titre 3 : numéro de sécurité social
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 3;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre3, gbc);
		        				
		        				// Contrainte de layout du sous titre 4 : date de naissance
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 4;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre4, gbc);
		        				
		        				// Contrainte de layout du formulaire : prenom
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 1;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldPrenom, gbc);
		        				
		        				// Contrainte de layout du formulaire : nom
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 2;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldNom, gbc);
		        				
		        				// Contrainte de layout du formulaire : numéro de sécurité social
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 3;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldNumSecu, gbc);
		        				
		        				// Contrainte de layout du formulaire : date de naissance
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 4;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldDateNaissance, gbc);
		        				
		        				// Contrainte de layout du bouton de validation
		        				
		        				gbc.anchor = GridBagConstraints.SOUTH;
		        				gbc.gridx = 0;
		        				gbc.gridy = 5;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 2;
		        				gbc.insets = new Insets(0, 100, 10, 0);
		        				validation.setAlignmentX(JButton.CENTER);
		        				
		        				fen.add(validation, gbc);
		        				
		        				fieldDateNaissance.addFocusListener(new FocusListener()
		 		        		{
		 		        			@Override
		 		        			public void focusGained(FocusEvent e)
		 		        			{
		 		        				fieldDateNaissance.setText(null);
		 		        				fieldDateNaissance.setForeground(new Color(0, 0, 0));
		 		        			}
		 		        			
		 		        			@Override
		 		        			public void focusLost(FocusEvent e)
		 		        			{
		 		        				
		 		        			}
		 		        		}
		        				);
		        				
		        				
		        				validation.addActionListener(new ActionListener() // lorsque le bouton de validation est pressé....
		        						{
		        							public void actionPerformed(ActionEvent e)
		        							{
		        								
		        								try // le contenu des formulaires sont récupérés
		        								{
			        								String prenom = fieldPrenom.getText();
			        		        				String nom = fieldNom.getText();
			        		        				String numSecu = fieldNumSecu.getText();
			        		        				String dateNaissance = fieldDateNaissance.getText();
			        		        				
			        		        				// variable booléenne qui renvoie vrai si les formulaires sont vide
			        		        				
			        		        				boolean checkPrenom = fieldPrenom.getText().equals("");
			        						        boolean checkNom = fieldNom.getText().equals("");
			        						        boolean checkNS = fieldNumSecu.getText().equals("");
			        						        boolean checkDN = fieldDateNaissance.getText().equals("AAAA-MM-JJ") || fieldDateNaissance.getText().equals("");
			        						        
			        						       
			        						       
			        						        if(checkPrenom || checkNom || checkNS || checkDN) // message d'erreur lorsque au moins un champ est vide
			        								{
			        						        	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
			        									dispose();
			        									//searchBarI.setText(null);
			        									fieldPrenom.setText(null);
			        						        	fieldNom.setText(null);
			        						        	fieldNumSecu.setText(null);
			        						        	fieldDateNaissance.setText("AAAA-MM-JJ");
			        						        	fieldDateNaissance.setForeground(new Color(153, 153, 153));
			        								}
			        						        else if(prenom.matches(".*\\d.*") || nom.matches(".*\\d.*")) // message d'erreur lorsque les champs prenom et nom contienne des chiffres
			        						        {
			        						        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des lettre pour les champs suivants :"+"\n"
			        									        +"Pr"+"\u00e9"+"nom, Nom !");
			        						        	dispose();
			        						        	fieldPrenom.setText(null);
			        						        	fieldNom.setText(null);
			        						        	fieldNumSecu.setText(null);
			        						        	fieldDateNaissance.setText("AAAA-MM-JJ");
			        						        	fieldDateNaissance.setForeground(new Color(153, 153, 153));
			        						        }
			        						        else if(!(numSecu.matches("[0-9]+"))) // message d'erreur lorsque le champ de numéro de sécurité social contient autre chose que des chiffres
			        						        {
			        						        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des chiffres pour le champ suivant :"+"\n"
			        									        +"Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale !");
			        						        	dispose();
			        						        	fieldPrenom.setText(null);
			        						        	fieldNom.setText(null);
			        						        	fieldNumSecu.setText(null);
			        						        	fieldDateNaissance.setText("AAAA-MM-JJ");
			        						        	fieldDateNaissance.setForeground(new Color(153, 153, 153));
			        						        }
			        						        else
			        						        { 
			        						        	// on crée un nouveau patient... 
			        						        	Patient nouvPatient = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
			        						        	
			        						        	if(Patient.rechercherPatient(nouvPatient)) // ... si il existe déja un message d'erreur apparaît
			        						        	{
			        						        		JOptionPane.showMessageDialog(null, "Erreur, ce patient existe");
				        						        	dispose();
				        						        	fieldPrenom.setText(null);
				        						        	fieldNom.setText(null);
				        						        	fieldNumSecu.setText(null);
				        						        	fieldDateNaissance.setText("AAAA-MM-JJ");
				        						        	fieldDateNaissance.setForeground(new Color(153, 153, 153));
			        						        	}
			        						        	else
			        						        	{
			        						        		// si il n'existe pas le patient est ajouté et un message de confirmation apparaît
			        						        		try
					        		        				{
					        		        					Patient.ajouterPatient(nouvPatient);
					        		        					fen.setVisible(false);
						        		        				JOptionPane.showMessageDialog(null, "Nouveau patient ajout"+"\u00e9"+" !");
						        		        				fen.dispose();
					        		        				}
					        		        				catch(IOException error)
					        		        				{
					        		        					
					        		        				}
			        						        	}
			        						        }
		        								}
			        							catch(DateTimeParseException error) // message d'erreur lorsque le champ de date de naissance est mal rempli
			        							{
			        								JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqu"+"\u00e9"+" : AAAA-MM-JJ !");
			        								dateNaissance = "";
			        								dispose();
			        								fieldPrenom.setText(null);
			        					        	fieldNom.setText(null);
			        					        	fieldNumSecu.setText(null);
			        					        	fieldDateNaissance.setText("AAAA-MM-JJ");
			        					        	fieldDateNaissance.setForeground(new Color(153, 153, 153));
			        							}
			        							catch(NumberFormatException error) // message d'erreur lorsque le champ du numéro de sécurité est mal rempli
			        						    {
			        						        JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des chiffres pour le champ suivant :"+"\n"
			        							        +"Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale !");
			        					        	dispose();
			        					        	fieldPrenom.setText(null);
			        					        	fieldNom.setText(null);
			        					        	fieldNumSecu.setText(null);
			        					        	fieldDateNaissance.setText("AAAA-MM-JJ");
			        					        	fieldDateNaissance.setForeground(new Color(153, 153, 153));
			        						    }
		        							}
		        						}
		        					);
		        				
		        				
		        				
		        				fen.setVisible(true);
		        				fen.setSize(400, 200);
		        				fen.setPreferredSize(getSize());
		        				fen.setMinimumSize(new Dimension(500, 300));
		        				fen.setMaximumSize(new Dimension(600, 300));
		        			}
		        		}
		        );
		        
		        boutonModif.addActionListener(new ActionListener() 
		        		{
		    
		        			public void actionPerformed(ActionEvent e)
		        			{
		        				boutonModif.setEnabled(false);
		        				if(row > -1) 
		        		        {
		        					// on récupère les valeurs de la ligne séléctionnée
		        					
		        		        	prenom = tablePatient.getModel().getValueAt(row, 0).toString();
		        		        	nom = tablePatient.getModel().getValueAt(row, 1).toString();
		        		        	numSecu = tablePatient.getModel().getValueAt(row, 2).toString();
		        		        	dateNaissance = tablePatient.getModel().getValueAt(row, 3).toString();
		        		        	
		        		        	// on définit des variables qui représente les valeurs intitiale qui représente le patient séléctionné
		        		        	
		        		        	final String prenomInitial = prenom;
    		        				final String nomInitial = nom;
    		        				final String numSecuInitial = numSecu;
    		        				final String dateNaissanceInitial = dateNaissance;
		        		        
		        				
		        				JFrame fen = new JFrame(); // on crée une nouvelle fenetre avec des formulaire
		        				
		        				fen.setLayout(new GridBagLayout());
		        				
		        				GridBagConstraints gbc = new GridBagConstraints();
		        				
		        				JLabel titre = new JLabel("Modification :");
		        				
		        				JLabel sousTitre1 = new JLabel("Pr"+"\u00e9"+"nom : ");
		        				
		        				JLabel sousTitre2 = new JLabel("Nom :");
		        				
		        				JLabel sousTitre3 = new JLabel("Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale :");
		        				
		        				JLabel sousTitre4 = new JLabel("Date de naissance :");
		        				
		        				JTextField fieldPrenom = new JTextField(10);
		        				
		        				fieldPrenom.setText(prenom); // on donne le prenom initiale au formulaire
		        				
		        				JTextField fieldNom = new JTextField(10);
		        				
		        				fieldNom.setText(nom); // on donne le nom initiale au formulaire
		        				
		        				JTextField fieldNumSecu = new JTextField(10);
		        				
		        				fieldNumSecu.setText(numSecu); // on donne le numéro de sécurité social initiale au formulaire
		        				
		        				JTextField fieldDateNaissance = new JTextField(10);
		        				
		        				fieldDateNaissance.setText(dateNaissance); // on donne la date de naissance initiale au formulaire
		        				
		        				JButton validation = new JButton("Valider");
		        				
		        				
		        				
		        				// Contrainte de layout du titre
		        				
		        				
		        				gbc.anchor = GridBagConstraints.NORTH;
		        				gbc.gridx = 0;
		        				gbc.gridy = 0;
		        				gbc.gridwidth = 2;
		        				gbc.weightx = 1;
		        				gbc.weighty = 1;
		        				gbc.insets = new Insets(0, 100, 0, 0);
		        				titre.setAlignmentX(JLabel.CENTER);
		        				
		        				fen.add(titre, gbc);
		        				
		        				// Contrainte de layout du sous titre 1 : prenom
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 1;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre1, gbc);
		        				
		        				// Contrainte de layout du sous titre 2 : nom
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 2;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre2, gbc);
		        				
		        				// Contrainte de layout du sous titre 3 : numéro de sécurité social
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 3;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre3, gbc);
		        				
		        				// Contrainte de layout du sous titre 4 : date de naissance
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 4;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre4, gbc);
		        				
		        				// Contrainte de layout du formulaire : prenom
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 1;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldPrenom, gbc);
		        				
		        				// Contrainte de layout du formulaire : nom
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 2;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldNom, gbc);
		        				
		        				// Contrainte de layout du formulaire : numéro de sécurité social
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 3;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldNumSecu, gbc);
		        				
		        				// Contrainte de layout du formulaire : date de naissance
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 4;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldDateNaissance, gbc);
		        				
		        				// Contrainte de layout du bouton de validation
		        				
		        				gbc.anchor = GridBagConstraints.SOUTH;
		        				gbc.gridx = 0;
		        				gbc.gridy = 5;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 2;
		        				gbc.insets = new Insets(0, 100, 10, 0);
		        				validation.setAlignmentX(JButton.CENTER);
		        				
		        				fen.add(validation, gbc);
		        				
		        				validation.addActionListener(new ActionListener() // lorsque le bouton de validation est pressé...
		        						{
		        							public void actionPerformed(ActionEvent e)
		        							{
		        								try
		        								{
		        									
			        		        				// On crée un patient de test
			        		        				
			        		        				Patient search = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
			        		        				
			        		        				Patient[] listePatient = Patient.rechercherPatients(search); // on met dans un array tous les patients qui sont identique au patient test
			        		        				
			        		        				
			        		        				try
			        								{
				        								// on définit des variable booléenees qui renvoient lorsque leurs formulaires respectif est vide
				        		        				
				        		        				boolean checkPrenom = fieldPrenom.getText().equals("");
				        						        boolean checkNom = fieldNom.getText().equals("");
				        						        boolean checkNS = fieldNumSecu.getText().equals("");
				        						        boolean checkDN = fieldDateNaissance.getText().equals("AAAA-MM-JJ") || fieldDateNaissance.getText().equals("");
				        						        
				        						       
				        						       
				        						        if(checkPrenom || checkNom || checkNS || checkDN) // message d'erreur lorsque les formulaire sont vides
				        								{
				        						        	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
				        									dispose();
				        									fieldPrenom.setText(prenomInitial);
				        						        	fieldNom.setText(nomInitial);
				        						        	fieldNumSecu.setText(numSecuInitial);
				        						        	fieldDateNaissance.setText(dateNaissanceInitial);
				        								}
				        						        else if(prenom.matches(".*\\d.*") || nom.matches(".*\\d.*")) // message d'erreur lorsque les champs prenom et nom ont été rempli avec des chiffres et non des lettres
				        						        {
				        						        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des lettre pour les champs suivants :"+"\n"
				        									        +"Pr"+"\u00e9"+"nom, Nom !");
				        						        	dispose();
				        						        	fieldPrenom.setText(prenomInitial);
				        						        	fieldNom.setText(nomInitial);
				        						        	fieldNumSecu.setText(numSecuInitial);
				        						        	fieldDateNaissance.setText(dateNaissanceInitial);
				        						        }
				        						        else if(!(numSecu.matches("[0-9]+"))) // message d'erreur lorsque le champ du numéro de sécurité social est rempli avec autre chose que des chiffres
				        						        {
				        						        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des chiffres pour le champ suivant :"+"\n"
				        									        +"Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale !");
				        						        	dispose();
				        						        	fieldPrenom.setText(prenomInitial);
				        						        	fieldNom.setText(nomInitial);
				        						        	fieldNumSecu.setText(numSecuInitial);
				        						        	fieldDateNaissance.setText(dateNaissanceInitial);
				        						        }
				        						        else
				        						        {
				        						        	// on modifie les valeurs du patient séléctionné par les nouvelle valeur entrée avant validation
				        						        	
				        						        	listePatient[row].setPrenom(fieldPrenom.getText());
					        		        				listePatient[row].setNom(fieldNom.getText());
					        		        				listePatient[row].setSecu(fieldNumSecu.getText());
					        		        				listePatient[row].setNaissance(LocalDate.parse(fieldDateNaissance.getText()));
					        		        				
					        		        				int idPatient = listePatient[0].getId();
					        		        				prenom =  listePatient[0].getPrenom();
					        		        				nom =listePatient[0].getNom();
					        		        				numSecu =listePatient[0].getSecu();
					        		        				
					        		        				// pour valider la modification on crée un nouveau patient avec les valeurs entrée...
				        						        	
				        						        	Patient patientModif = new Patient(idPatient, prenom, nom, numSecu, listePatient[0].getNaissance());
					        		        				
				        						        	// on supprime le patient avec les anciennes valeurs
				        						        	
					        		        				Patient.supprimerPatient(listePatient[row]);
					        		        				
					        		        				// et on en crée un nouveau qui sera écrit dans le fichier patients.txt
					        		        				
					        		        				Patient.ajouterPatient(patientModif);
					        		        				
					        		        				// on met à jour le tableau
					        		        				
					        		        				String[][] patients = new String[nbPatients][4];
											        		
											        		id = new int[nbPatients]; 
													
											        		for(int i = 0 ; i < nbPatients ; i++)
											        		{	
											        			id[i] = listePatient[i].getId();
											        			patients[i][0] = listePatient[i].getPrenom();
											        			patients[i][1] = listePatient[i].getNom();
											        			patients[i][2] = listePatient[i].getSecu();
																patients[i][3] = (listePatient[i].getNaissance()).toString();
											        		}
											        		
											        		
											        		
											        		
											        		tableModel = new DefaultTableModel(patients, header1);
											        		tablePatient.setModel(tableModel);
											        		
												        	frame.add(panel);
													        frame.setVisible(true);
					        		        				
					        		        				
					        		        				fen.setVisible(false);
					        		        				JOptionPane.showMessageDialog(null, "Patient modifi"+"\u00e9"+" !");
					        		        				fen.dispose();
				        						        	}
			        									}
					        						catch(DateTimeParseException error) // message d'erreur lorsque le champ de la date de naissance est mal rempli
					        						{
					        							JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqu"+"\u00e9"+" : AAAA-MM-JJ !");
					        							dateNaissance = "";
					        							dispose();
					        							fieldPrenom.setText(prenomInitial);
			        						        	fieldNom.setText(nomInitial);
			        						        	fieldNumSecu.setText(numSecuInitial);
			        						        	fieldDateNaissance.setText(dateNaissanceInitial);
					        						}
					       							catch(NumberFormatException error) // message d'erreur lorsque le champ du numéro de sécurité sociale est mal rempli
					       						    {
					       						        JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des chiffres pour le champ suivant :"+"\n"
					       							        +"Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale !");
					       					        	dispose();
					       					        	fieldPrenom.setText(prenomInitial);
			        						        	fieldNom.setText(nomInitial);
			        						        	fieldNumSecu.setText(numSecuInitial);
			        						        	fieldDateNaissance.setText(dateNaissanceInitial);
				        						    }
		        								}
			        		        			catch(IOException error)
			        		        			{
		        							
			        		       				}
		        								
		        							}
		        						}
		        					);
		        				
		        				
		        				
		        				fen.setVisible(true);
		        				fen.setSize(400, 200);
		        				fen.setPreferredSize(getSize());
		        				fen.setMinimumSize(new Dimension(500, 300));
		        				fen.setMaximumSize(new Dimension(600, 300));
		        		        }
		        			}
		        		
		        		}
		        		
		        	);
		        
		        
		        boutonSupr.addActionListener(new ActionListener() // permet de supprimer le patient
		        		{
		        			
		        			
		        			public void actionPerformed(ActionEvent e) 
		        			{
		        				boutonSupr.setEnabled(false); // on désactive le bouton après activation
		        				if(row > -1)
		        		        {
		        					// on récupère les valeurs du patient à supprimer 
		        					
		        		        	prenom = tablePatient.getModel().getValueAt(row, 0).toString();
		        		        	nom = tablePatient.getModel().getValueAt(row, 1).toString();
		        		        	numSecu = tablePatient.getModel().getValueAt(row, 2).toString();
		        		        	dateNaissance = tablePatient.getModel().getValueAt(row, 3).toString();
		        		        	
		        		        	// on crée un patient test
		        		        	
		        		        	Patient aSupprimer = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
		        		        	
		        		        	// on récupère le patient identique au patient test
		        		        	
		        		        	Patient[] listePatient = Patient.rechercherPatients(aSupprimer);
		        		        	
		        		        	// on supprime le patient de la ligne séléctionnée
		        		        	
		        		        	try
		        		        	{
		        		        		Patient.supprimerPatient(listePatient[row]);
		        		        	}
		        		        	catch(IOException error)
		        		        	{
		        		        		
		        		        	}
		        		        	
		        		        	// on vide tableau des patient
		        		        	
		        		        	tableModel.setRowCount(0);
					        		tablePatient.setModel(tableModel);
		        		        	
					        		
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
		        		        	
		        		        	
		        		        }
		        			}

		        		}
		        	);
		        
		        
		        
		       
		       
		        
		        
		        
		        
		        frame.add(panel);
		        frame.setVisible(true);  
		        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		        
		        break;
			}
			
			case 2:             // L'interface du médecin
			{
				JPanel panel = new JPanel(new GridBagLayout()); 
		        
		        frame.setTitle("GesPat-Agent M"+"\u00e9"+"decin");
		        
		        JLabel titre = new JLabel("Interface M"+"\u00e9"+"decin");
		        
		        titre.setFont(new Font("Arial", Font.BOLD, 40));
		        
		        JTextField searchBarP = new JTextField(2); // recherche par prenom
		        JTextField searchBarN = new JTextField(2); // recherche par nom
		        JTextField searchBarNS = new JTextField(2); // recherche par numero de securité sociale
		        JTextField searchBarDN = new JTextField(0); // recherche par date de naissance
		        
		        JButton retour = new JButton("Menu Principal");
		        
		        JButton bouton = new JButton("Rechercher");
		        
		        JButton boutonAjout = new JButton("Ajouter...");
		        
		        JButton boutonModif = new JButton("Modifier...");
		        
		        JButton boutonSupr = new JButton("Supprimmer");
		        
		        JButton boutonInfo = new JButton("Information..."); // bouton d'information des consultations, affiche les pathologies
		        
		        
		        tableModel = new DefaultTableModel(miniTab, header3); // on définit un model pour le petit tableau des patients
		        
		        tableModelConsult = new DefaultTableModel(consultation, header2); // et un autre model pour le grand tableau des consultations
		        
		        tablePatient = new JTable(tableModel);
		        
		        // on rend invisible la 3éme colonne du tableau des patients
		        
		        tablePatient.getColumnModel().getColumn(2).setMinWidth(0);
        		tablePatient.getColumnModel().getColumn(2).setMaxWidth(0);
        		tablePatient.getColumnModel().getColumn(2).setWidth(0);
        		
        		// on rend invisible la 4éme colonne du tableau des patients
        		
        		tablePatient.getColumnModel().getColumn(3).setMinWidth(0);
        		tablePatient.getColumnModel().getColumn(3).setMaxWidth(0);
        		tablePatient.getColumnModel().getColumn(3).setWidth(0);
		        
		        panneau = new JScrollPane(tablePatient); 
		        
		        panneau.setPreferredSize(new Dimension(30, 80));
		        
		        tableConsultation = new JTable(tableModelConsult);
		        
		        // on désactive les boutons d'ajout, de modification, d'information et de suppression des consultations
		        
		        boutonAjout.setEnabled(false);
		        
		        boutonModif.setEnabled(false);
		        
		        boutonInfo.setEnabled(false);
		        
		        boutonSupr.setEnabled(false);
		        
		        searchBarDN.setText("AAAA-MM-JJ");
		        searchBarDN.setForeground(new Color(153, 153, 153));
		        
		        
		        searchBarDN.addFocusListener(new FocusListener()
		        		{
		        			@Override
		        			public void focusGained(FocusEvent e)
		        			{
		        				searchBarDN.setText(null);
		        				searchBarDN.setForeground(new Color(0, 0, 0));
		        			}
		        			
		        			@Override
		        			public void focusLost(FocusEvent e)
		        			{
		        				
		        			}
		        		}
		        );
		        
		        
		        JLabel sousTitre1 = new JLabel("Pr"+"\u00e9"+"nom :");
		        JLabel sousTitre2 = new JLabel("Nom :");
		        JLabel sousTitre3 = new JLabel("Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" :");
		        JLabel sousTitre4 = new JLabel("Date de naissance :");
		        JLabel sousTitre5 = new JLabel("Selectionner un patient :");
		        
		        
		        panel.setLayout(new GridBagLayout());
		        
		        GridBagConstraints gbc = new GridBagConstraints();
		        
		        // Contrainte de layout du titre
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.gridx = 0;
		        gbc.gridy = 0;
		        gbc.weightx = 1;
		        gbc.weighty = 1;
		        gbc.gridwidth = 5;
		        
		        gbc.anchor = GridBagConstraints.NORTH;
		        
		        titre.setHorizontalAlignment(JLabel.CENTER);
		        
		        panel.add(titre, gbc);
		        
		        
		        gbc.gridwidth = 1; 
		        
		        
		        //Contrainte de layout du sous titre 1 : prenom
		        
		        gbc.gridx = 0;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre1, gbc);
		        
		      //Contrainte de layout du sous titre 2: nom
		        
		        gbc.gridx = 1;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;        
		        panel.add(sousTitre2, gbc);
		        
		        //Contrainte de layout du sous titre 3 : numéro de sécurité sociale
		        gbc.gridx = 2;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 70, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre3, gbc);
		        
		      //Contrainte de layout du sous titre 4 : date de naissance
		        
		        gbc.gridx = 3;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre4, gbc);
		        
		        //Contrainte de layout du sous titre 5 : tableau des patients
		        
		        gbc.gridx = 4;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        panel.add(sousTitre5, gbc);
		       
		        
		        //Contrainte de layout du formulaire : prenom
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 0;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		       
		        panel.add(searchBarP, gbc);
		        
		        //Contrainte de layout du formulaire : nom
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 1;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        panel.add(searchBarN, gbc);
		        
		        //Contrainte de layout du formulaire : numero de sécurité social
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 70, 0, 70);
		        gbc.gridx = 2;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(searchBarNS, gbc);
		        
		        //Contrainte de layout du formulaire : date de naissance
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 70, 0, 70);
		        gbc.gridx = 3;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(searchBarDN, gbc);
		        
		        //Contrainte de layout du tableau des patients
		        
		       	gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 70, 0, 70);
		        gbc.gridx = 4;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.gridheight = 2;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        panel.add(panneau, gbc);
		        gbc.gridheight = 1;
		        
		        //Contrainte de layout du bouton de recherche
		        
		        GridBagConstraints gbc2 = new GridBagConstraints();
		        gbc2.gridx = 0;
		        gbc2.gridy = 3;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 5;
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(bouton, gbc2);
		        
		        
		        //Contrainte de layout du tableau des consultation
		        
		        gbc.gridx = 0;
		        gbc.gridy = 4;
		        gbc.weightx = 1;
		        gbc.gridwidth = 5;
		        gbc.insets = new Insets(0, 0, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        //Contrainte de layout du bouton de retour au menu
		        
		        gbc2.gridx = 0;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(retour, gbc2);
		        
		      //Contrainte de layout du bouton d'ajout des consultations
		        
		        gbc2.gridx = 1;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonAjout, gbc2);
		        
		      //Contrainte de layout du bouton de modification des consultations
		        
		        gbc2.gridx = 2;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonModif, gbc2);
		        
		      //Contrainte de layout du bouton d'information des consultations
		        
		        gbc2.gridx = 3;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonInfo, gbc2);
		        
		      //Contrainte de layout du bouton de suppression des consultations
		        
		        gbc2.gridx = 4;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonSupr, gbc2);
		        
		       
		        retour.addActionListener(new ActionListener() 
	    		   {
	    	   			public void actionPerformed(ActionEvent e)
	    	   			{
	    	   				frame.setVisible(false);
	    	   				new Fenetre();
	    	   			}
	    		   }
	    	);
		        
		        
		        bouton.addActionListener(new ActionListener() 
		        		{
		        			@Override
							public void actionPerformed(ActionEvent e) 
							{
		        				
		        				try
		        				{
		        					// récupère les informations inséré dans les formulaires
		        					
									prenom = searchBarP.getText();
									nom = searchBarN.getText();
									numSecu = searchBarNS.getText();
									dateNaissance = searchBarDN.getText();
									
									// on définit des variables booléennes qui renvoie true lorsque les formulaires sont vides
							        boolean checkPrenom = searchBarP.getText().equals("");
							        boolean checkNom = searchBarN.getText().equals("");
							        boolean checkNS = searchBarNS.getText().equals("");
							        boolean checkDN = searchBarDN.getText().equals("AAAA-MM-JJ");
							      
							        if(checkPrenom || checkNom || checkNS || checkDN) // message d'erreur lorsqu'au moins un des champs est vide
									{
							        	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
										dispose();
										searchBarP.setText(null);
							        	searchBarN.setText(null);
							        	searchBarNS.setText(null);
							        	searchBarDN.setText("AAAA-MM-JJ");
							        	searchBarDN.setForeground(new Color(153, 153, 153));
									}
							        else if(prenom.matches(".*\\d.*") || nom.matches(".*\\d.*")) // message d'erreur lorsque les champs prenom et nom contiennent des chiffres
							        {
							        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des lettre pour les champs suivants :"+"\n"
										        +"Pr"+"\u00e9"+"nom, Nom !");
							        	dispose();
							        	searchBarP.setText(null);
							        	searchBarN.setText(null);
							        	searchBarNS.setText(null);
							        	searchBarDN.setText("AAAA-MM-JJ");
							        	searchBarDN.setForeground(new Color(153, 153, 153));
							        }
							        else if(!(numSecu.matches("[0-9]+"))) // message d'erreur lorsque le champs numéro de sécurité sociale contient autre chose des chiffres
							        {
							        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des chiffres pour le champ suivant :"+"\n"
										        +"Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale !");
							        	dispose();
							        	searchBarP.setText(null);
							        	searchBarN.setText(null);
							        	searchBarNS.setText(null);
							        	searchBarDN.setText("AAAA-MM-JJ");
							        	searchBarDN.setForeground(new Color(153, 153, 153));
							        }
							        else
							        {
							        	// création d'un patient test
							        
							        	Patient search = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
							        	
							        	
							        	
							        	if(Patient.rechercherPatient(search))
							        	{
							        		// on récupère tous les patients identique au patient test
							        		
							        		Patient[] listePatient = Patient.rechercherPatients(search);
										
							        		nbPatients = listePatient.length;
							        		
							        		// on met à jour le tableau des patients
							        		
							        		String[][] patients = new String[nbPatients][4];
							        		
							        		id = new int[nbPatients]; 
									
							        		for(int i = 0 ; i < nbPatients ; i++)
							        		{	
							        			id[i] = listePatient[i].getId();
							        			patients[i][0] = listePatient[i].getNom();
							        			patients[i][1] = listePatient[i].getPrenom();
							        			patients[i][2] = listePatient[i].getSecu();
							        			patients[i][3] = (listePatient[i].getNaissance()).toString();
							        		}
							        		
							        		
							        		
							        		
							        		tableModel = new DefaultTableModel(patients, header3);
							        		
							        		tablePatient.setModel(tableModel);
							        		
							        		// on rend invisible les colonnes 3 et 4
							        		
							        		tablePatient.getColumnModel().getColumn(2).setMinWidth(0);
							        		tablePatient.getColumnModel().getColumn(2).setMaxWidth(0);
							        		tablePatient.getColumnModel().getColumn(2).setWidth(0);
							        		
							        		tablePatient.getColumnModel().getColumn(3).setMinWidth(0);
							        		tablePatient.getColumnModel().getColumn(3).setMaxWidth(0);
							        		tablePatient.getColumnModel().getColumn(3).setWidth(0);
							        		
									        panneau.setViewportView(tablePatient);
							        	
							        		
								        	frame.add(panel);
									        frame.setVisible(true);
							        		
							        	}
							        	else // message d'erreur quand le patient rechercher n'existe pas
							        	{
							        		JOptionPane.showMessageDialog(null, "Patient inconnu, veuillez l'ajouter ou vérifier les paramétres entrée !");
							        	}
							        	searchBarP.setText(null);
							        	searchBarN.setText(null);
							        	searchBarNS.setText(null);
							        	searchBarDN.setText(null);
							        }
						        
								}
			        			catch(DateTimeParseException error) // message d'erreur lorsque le champ de la date de naissance est mal rempli
								{
									JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqu"+"\u00e9"+" : AAAA-MM-JJ !");
									dateNaissance = "";
									dispose();
									searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText("AAAA-MM-JJ");
						        	searchBarDN.setForeground(new Color(153, 153, 153));
								}
								
							}
		        			
		        			
		        		}
		        
		        	);
		        
		        
		        
		        tableConsultation = new JTable(consultation, header2);
		        
		        panel.add(new JScrollPane(tableConsultation), gbc);
		        
		        // on rend invisible la colonne 6 du tableau des consultations
		        
		        tableConsultation.getColumnModel().getColumn(5).setMinWidth(0);
        		tableConsultation.getColumnModel().getColumn(5).setMaxWidth(0);
        		tableConsultation.getColumnModel().getColumn(5).setWidth(0);
		        
		        tablePatient.addMouseListener(new MouseAdapter() // on récupère la ligne séléctionnée dans le tableau des consultations
        			{
        				public void mousePressed(MouseEvent e)
        				{
        					Point p = e.getPoint();
        					row = tablePatient.rowAtPoint(p);
        					
        					prenom = tablePatient.getModel().getValueAt(row, 0).toString();
        		        	nom = tablePatient.getModel().getValueAt(row, 1).toString();
        		        	numSecu = tablePatient.getModel().getValueAt(row, 2).toString();
        		        	dateNaissance = tablePatient.getModel().getValueAt(row, 3).toString();
        		        	
        		        	Patient search = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
        		        	
        		        	Patient[] listePatient = Patient.rechercherPatients(search);
        		        	
        		        	
        		        	
        		        	Consultation[] listeConsultation = Consultation.rechercherConsultations(listePatient[0].getId());
        		        	
        		        	int nbConsultation = listeConsultation.length;
        		        	
        		        	if(nbConsultation > 0)
        		        	{
	        		        	consultation = new String[nbConsultation][6];
	        					
	        					for(int i = 0 ; i < nbConsultation ; i++)
	        					{
	        						
	        						consultation[i][0] = nom;
	        						consultation[i][1] = listeConsultation[i].getMedecin();
	        						consultation[i][2] = listeConsultation[i].getDateConsultation().toString();
	        						consultation[i][3] = listeConsultation[i].getAppareil();
	        						consultation[i][4] = listeConsultation[i].getOctroie();
	        						consultation[i][5] = Consultation.pathologiesToString(listeConsultation[i].getPathologies());
	        					}
	        					
	        					tableModelConsult = new DefaultTableModel(consultation, header2);
				        		tableConsultation.setModel(tableModelConsult);
				        		
				        		tableConsultation.getColumnModel().getColumn(5).setMinWidth(0);
				        		tableConsultation.getColumnModel().getColumn(5).setMaxWidth(0);
				        		tableConsultation.getColumnModel().getColumn(5).setWidth(0);
				        		
				        		panel.add(new JScrollPane(tableConsultation), gbc);
				        		frame.add(panel);
						        frame.setVisible(true);
        		        	}
        		        	else
        		        	{
        		        		JOptionPane.showMessageDialog(null, "Le patient n'a eu aucune consultation, vous pouvez en ajouter une !");
        		        	}
        				}
        			}
		        );
		        
		        
		        tableConsultation.addMouseListener(new MouseAdapter()
		        		{
		        			public void mousePressed(MouseEvent e)
		        			{
		        				Point p = e.getPoint();
		        				rowConsult = tableConsultation.rowAtPoint(p);
		        			}
		        		}
		        );
		        
		        tablePatient.addFocusListener(new FocusListener()
        		{
        			@Override
        			public void focusGained(FocusEvent e)
        			{
        				boutonAjout.setEnabled(true);
        			}
        			
        			public void focusLost(FocusEvent e)
        			{
        				
        			}
        		});
		        
		        tableConsultation.addFocusListener(new FocusListener()
		        		{
		        			@Override
		        			public void focusGained(FocusEvent e)
		        			{
		        				boutonModif.setEnabled(true);
		        				boutonSupr.setEnabled(true);
		        				boutonInfo.setEnabled(true);
		        			}
		        			
		        			public void focusLost(FocusEvent e)
		        			{
		        				
		        			}
		        		});
		      
		        boutonAjout.addActionListener(new ActionListener()
		        		{
		        			public void actionPerformed(ActionEvent e)
		        			{
		        				boutonAjout.setEnabled(false);
		        				
		        				JFrame fen = new JFrame();
		        				
		        				fen.setLayout(new GridBagLayout());
		        				
		        				GridBagConstraints gbc = new GridBagConstraints();
		        				
		        				JLabel titre = new JLabel("Nouvelle Consultation :");
		        				
		        				JLabel sousTitre1 = new JLabel("Nom du m"+"\u00e9"+"decin : ");
		        				
		        				JLabel sousTitre2 = new JLabel("Date de consultation :");
		        				
		        				JLabel sousTitre3 = new JLabel("Maintenez controle pour selectionner plusieurs pathologies :");
		        				
		        				JTextField fieldMedecin = new JTextField(10);
		        				
		        				JTextField fieldDateConsultation = new JTextField(10);
		        				
		        				JList listeAppareil = new JList(appareillage);
		        				
		        				JList listePatho = new JList(pathologies);
		        
		        				
		        				JButton validation = new JButton("Valider");
		        				
		        				fieldDateConsultation.setText("AAAA-MM-JJ");
		        				fieldDateConsultation.setForeground(new Color(153, 153, 153));
		        				
		        				fieldDateConsultation.addFocusListener(new FocusListener()
		 		        		{
		 		        			@Override
		 		        			public void focusGained(FocusEvent e)
		 		        			{
		 		        				fieldDateConsultation.setText(null);
		 		        				fieldDateConsultation.setForeground(new Color(0, 0, 0));
		 		        			}
		 		        			
		 		        			@Override
		 		        			public void focusLost(FocusEvent e)
		 		        			{
		 		        				
		 		        			}
		 		        		}
		        				);
		        				
		        				// titre
		        				
		        				
		        				gbc.anchor = GridBagConstraints.NORTH;
		        				gbc.gridx = 0;
		        				gbc.gridy = 0;
		        				gbc.gridwidth = 2;
		        				gbc.weightx = 1;
		        				gbc.weighty = 1;
		        				gbc.insets = new Insets(0, 100, 0, 0);
		        				titre.setAlignmentX(JLabel.CENTER);
		        				
		        				fen.add(titre, gbc);
		        				
		        				// label
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 1;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre1, gbc);
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 2;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre2, gbc);
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 3;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre3, gbc);
		        				
		        				GridBagConstraints gbc2 = new GridBagConstraints();
		        				
		        				gbc2.anchor = GridBagConstraints.WEST;
		        				gbc2.gridx = 0;
		        				gbc2.gridy = 4;
		        				gbc2.weightx = 1;
		        				gbc2.weighty = 0;
		        				gbc2.gridwidth = 2;
		        				gbc2.insets = new Insets(0, 10, 10, 0);
		        				
		        				listePatho.setVisibleRowCount(3);
		        				
		        				int[] selectPatho = new int[22];
		        				
		        				listePatho.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		        				
		        				listePatho.setSelectedIndices(selectPatho);
		        				
		        				fen.add(new JScrollPane(listePatho), gbc2);
		        				
		        				
		        				gbc2.anchor = GridBagConstraints.EAST;
		        				gbc2.gridx = 0;
		        				gbc2.gridy = 4;
		        				gbc2.weightx = 1;
		        				gbc2.weighty = 0;
		        				gbc2.gridwidth = 2;
		        				gbc2.insets = new Insets(0, 10, 10, 0);
		        				
		        				listeAppareil.setVisibleRowCount(3);
		        				
		        				int[] selectAppareil = new int[5];
		        				
		        				
		        				listeAppareil.setSelectedIndices(selectAppareil);
		        				
		        				
		        				fen.add(new JScrollPane(listeAppareil), gbc2);
		        				
		        				
		        				// textfield
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 1;
		        				gbc.gridy = 1;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldMedecin, gbc);
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 1;
		        				gbc.gridy = 2;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldDateConsultation, gbc);
		        				
		        				// bouton
		        				
		        				gbc.anchor = GridBagConstraints.SOUTH;
		        				gbc.gridx = 0;
		        				gbc.gridy = 6;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 2;
		        				gbc.insets = new Insets(0, 100, 10, 0);
		        				validation.setAlignmentX(JButton.CENTER);
		        				
		        				fen.add(validation, gbc);
		        				
		        				
		        				validation.addActionListener(new ActionListener()
		        						{
		        							public void actionPerformed(ActionEvent e)
		        							{
		        								try
		        								{
		        									boolean checkMedecin = fieldMedecin.getText().equals("");
			        						        boolean checkDate = fieldDateConsultation.getText().equals("");
			        						        boolean checkAppareil = listeAppareil.isSelectionEmpty();
			        						        boolean checkPatho = listePatho.isSelectionEmpty();
			        						        
			        						        nomMedecin = fieldMedecin.getText();
			        						       
			        						        if(checkMedecin || checkDate || checkAppareil || checkPatho)
			        								{
			        						        	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
			        									dispose();
			        									fieldMedecin.setText(null);
			        						        	fieldDateConsultation.setText("AAAA-MM-JJ");
			        						        	fieldDateConsultation.setForeground(new Color(153, 153, 153));
			        								}
			        						        else if(nomMedecin.matches(".*\\d.*"))
			        						        {
			        						        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des lettres pour le champ :"+"\n"
			        									        +"Nom m"+"\u00e9"+"decin !");
			        						        	dispose();
			        						        	fieldMedecin.setText(null);
			        						        	fieldDateConsultation.setText("AAAA-MM-JJ");
			        						        	fieldDateConsultation.setForeground(new Color(153, 153, 153));
			        						        }
		        									else
		        									{
				        		        				String dateConsultation = fieldDateConsultation.getText();
				        		        				
				        		        				int taille = listePatho.getSelectedValuesList().size();
				        		        				List<String> liste = listePatho.getSelectedValuesList();
				        		        				String[] patho = liste.toArray(new String[taille]);
				        		        				String appareil = listeAppareil.getSelectedValue().toString();
				        		        				
				        		        				Consultation nouvelleConsult = new Consultation(id[row], nomMedecin, LocalDate.parse(dateConsultation), appareil, patho);
				        		        				
				        		        				try
				        		        				{
				        		        					Consultation.ajouterConsultation(nouvelleConsult);
				        		        				}
				        		        				catch(IOException error)
				        		        				{
				        		        					
				        		        				}
				        		        				
				        		        				
				        		        				fen.setVisible(false);
				        		        				JOptionPane.showMessageDialog(null, "Nouvelle consultation ajout"+"\u00e9"+" !");
				        		        				fen.dispose();
		        									}
		        								}
		        								catch(DateTimeParseException error)
		        								{
		        									JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqu"+"\u00e9"+" : AAAA-MM-JJ !");
				        							dateNaissance = "";
				        							dispose();
				        							fieldMedecin.setText(null);
		        						        	fieldDateConsultation.setText("AAAA-MM-JJ");
		        						        	fieldDateConsultation.setForeground(new Color(153, 153, 153));
		        								}
		        							}
		        						}
		        				);
		        				
		        				
		        				
		        				fen.setVisible(true);
		        				fen.setSize(500, 300);
		        				fen.setPreferredSize(getSize());
		        			}
		        		}
		        );
		        
		        boutonModif.addActionListener(new ActionListener()
		        		{
		    
		        			public void actionPerformed(ActionEvent e)
		        			{
		        				boutonSupr.setEnabled(false);
		        				if(rowConsult > -1)
		        		        {
		        					nom = tableConsultation.getModel().getValueAt(rowConsult, 0).toString();
		        		        	nomMedecin = tableConsultation.getModel().getValueAt(rowConsult, 1).toString();
		        		        	dateConsult = tableConsultation.getModel().getValueAt(rowConsult, 2).toString();
		        		        	
		        		        	Consultation[] listeConsult = Consultation.rechercherConsultations(id[row]);
		        		        	
		        		        	final String medecinInitial = nomMedecin;
    		        				final String dateInitial = dateConsult;
		        		        
    		        				System.out.println(rowConsult);
    		        				System.out.println(listeConsult[rowConsult].getOctroie());
		        				
    		        				JFrame fen = new JFrame();
    		        				
    		        				fen.setLayout(new GridBagLayout());
    		        				
    		        				GridBagConstraints gbc = new GridBagConstraints();
    		        				
    		        				JLabel titre = new JLabel("Nouvelle Consultation :");
    		        				
    		        				JLabel sousTitre1 = new JLabel("Nom du m"+"\u00e9"+"decin : ");
    		        				
    		        				JLabel sousTitre2 = new JLabel("Date de consultation :");
    		        				
    		        				JLabel sousTitre3 = new JLabel("Maintenez controle pour selectionner plusieurs pathologies :");
    		        				
    		        				JTextField fieldMedecin = new JTextField(10);
    		        				
    		        				JTextField fieldDateConsultation = new JTextField(10);
    		        				
    		        				JList listeAppareil = new JList(appareillage);
    		        				
    		        				JList listePatho = new JList(pathologies);
    		        
    		        				JButton validation = new JButton("Valider");
    		        				
    		        				fieldMedecin.setText(medecinInitial);
    		        				
    		        				fieldDateConsultation.setText(dateInitial);
    		        				
    		        				
    		        				
    		        				// titre
    		        				
    		        				
    		        				gbc.anchor = GridBagConstraints.NORTH;
    		        				gbc.gridx = 0;
    		        				gbc.gridy = 0;
    		        				gbc.gridwidth = 2;
    		        				gbc.weightx = 1;
    		        				gbc.weighty = 1;
    		        				gbc.insets = new Insets(0, 100, 0, 0);
    		        				titre.setAlignmentX(JLabel.CENTER);
    		        				
    		        				fen.add(titre, gbc);
    		        				
    		        				// label
    		        				
    		        				gbc.anchor = GridBagConstraints.WEST;
    		        				gbc.gridx = 0;
    		        				gbc.gridy = 1;
    		        				gbc.weightx = 0;
    		        				gbc.weighty = 0;
    		        				gbc.gridwidth = 1;
    		        				gbc.insets = new Insets(0, 10, 10, 0);
    		        				
    		        				fen.add(sousTitre1, gbc);
    		        				
    		        				gbc.anchor = GridBagConstraints.WEST;
    		        				gbc.gridx = 0;
    		        				gbc.gridy = 2;
    		        				gbc.weightx = 0;
    		        				gbc.weighty = 0;
    		        				gbc.gridwidth = 1;
    		        				gbc.insets = new Insets(0, 10, 10, 0);
    		        				
    		        				fen.add(sousTitre2, gbc);
    		        				
    		        				gbc.anchor = GridBagConstraints.WEST;
    		        				gbc.gridx = 0;
    		        				gbc.gridy = 3;
    		        				gbc.weightx = 0;
    		        				gbc.weighty = 0;
    		        				gbc.gridwidth = 1;
    		        				gbc.insets = new Insets(0, 10, 10, 0);
    		        				
    		        				fen.add(sousTitre3, gbc);
    		        				
    		        				GridBagConstraints gbc2 = new GridBagConstraints();
    		        				
    		        				gbc2.anchor = GridBagConstraints.WEST;
    		        				gbc2.gridx = 0;
    		        				gbc2.gridy = 4;
    		        				gbc2.weightx = 1;
    		        				gbc2.weighty = 0;
    		        				gbc2.gridwidth = 2;
    		        				gbc2.insets = new Insets(0, 10, 10, 0);
    		        				
    		        				listePatho.setVisibleRowCount(3);
    		        				
    		        				int[] selectPatho = new int[22];
    		        				
    		        				listePatho.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    		        				
    		        				listePatho.setSelectedIndices(selectPatho);
    		        				
    		        				fen.add(new JScrollPane(listePatho), gbc2);
    		        				
    		        				
    		        				gbc2.anchor = GridBagConstraints.EAST;
    		        				gbc2.gridx = 0;
    		        				gbc2.gridy = 4;
    		        				gbc2.weightx = 1;
    		        				gbc2.weighty = 0;
    		        				gbc2.gridwidth = 2;
    		        				gbc2.insets = new Insets(0, 10, 10, 0);
    		        				
    		        				listeAppareil.setVisibleRowCount(3);
    		        				
    		        				int[] selectAppareil = new int[5];
    		        				
    		        				
    		        				listeAppareil.setSelectedIndices(selectAppareil);
    		        				
    		        				
    		        				fen.add(new JScrollPane(listeAppareil), gbc2);
    		        				
    		        				
    		        				// textfield
    		        				
    		        				gbc.anchor = GridBagConstraints.EAST;
    		        				gbc.gridx = 2;
    		        				gbc.gridy = 1;
    		        				gbc.weightx = 0;
    		        				gbc.weighty = 0;
    		        				gbc.gridwidth = 1;
    		        				gbc.insets = new Insets(0, 0, 10, 10);
    		        				
    		        				fen.add(fieldMedecin, gbc);
    		        				
    		        				gbc.anchor = GridBagConstraints.EAST;
    		        				gbc.gridx = 2;
    		        				gbc.gridy = 2;
    		        				gbc.weightx = 0;
    		        				gbc.weighty = 0;
    		        				gbc.gridwidth = 1;
    		        				gbc.insets = new Insets(0, 0, 10, 10);
    		        				
    		        				fen.add(fieldDateConsultation, gbc);
    		        				
    		        				
    		        				// bouton
    		        				
    		        				gbc.anchor = GridBagConstraints.SOUTH;
    		        				gbc.gridx = 0;
    		        				gbc.gridy = 7;
    		        				gbc.weightx = 0;
    		        				gbc.weighty = 0;
    		        				gbc.gridwidth = 2;
    		        				gbc.insets = new Insets(0, 100, 10, 0);
    		        				validation.setAlignmentX(JButton.CENTER);
    		        				
    		        				fen.add(validation, gbc);
		        				
		        				validation.addActionListener(new ActionListener()
		        						{
		        							public void actionPerformed(ActionEvent e)
		        							{
		        								try
		        								{
		        									
			        		        				try
			        								{
				        								
				        		        				
				        		        				boolean checkMedecin = fieldMedecin.getText().equals("");
				        						        boolean checkDate = fieldDateConsultation.getText().equals("");
				        						        boolean checkAppareil = listeAppareil.isSelectionEmpty();
				        						        boolean checkPatho = listePatho.isSelectionEmpty();
				        						        
				        						       
				        						        if(checkMedecin || checkDate || checkAppareil || checkPatho)
				        								{
				        						        	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
				        									dispose();
				        									fieldMedecin.setText(medecinInitial);
				        									fieldDateConsultation.setText(dateInitial);
				        								}
				        						        else if(nomMedecin.matches(".*\\d.*"))
				        						        {
				        						        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des lettres pour le champ :"+"\n"
				        									        +"Nom m"+"\u00e9"+"decin !");
				        						        	dispose();
				        						        	fieldMedecin.setText(medecinInitial);
				        									fieldDateConsultation.setText(dateInitial);
				        						        }
				        						        else
				        						        {
				        						        	int taille = listePatho.getSelectedValuesList().size();
					        		        				List<String> liste = listePatho.getSelectedValuesList();
					        		        				String[] patho = liste.toArray(new String[taille]);
					        		        				
				        						        	
				        						        	listeConsult[rowConsult].setMedecin(fieldMedecin.getText());
				        						        	listeConsult[rowConsult].setDateConsultation(LocalDate.parse(fieldDateConsultation.getText()));
				        						        	listeConsult[rowConsult].setAppareil(listeAppareil.getSelectedValue().toString());
				        						        	listeConsult[rowConsult].setPathologies(patho);
				        						        	
					        		        				
					        		        				int idPatient = listeConsult[rowConsult].getIdPatient();
					        		        				nomMedecin =  listeConsult[rowConsult].getMedecin();
					        		        				appareil = listeConsult[rowConsult].getAppareil();
					        		        				
					        		        				
				        						        	Consultation consultationModif = new Consultation(idPatient, nomMedecin, listeConsult[rowConsult].getDateConsultation(), appareil, patho);
				        						        	
				        						        	
					        		        				Consultation.supprimerConsultation(listeConsult[rowConsult]);
					        		        				
					        		        				
					        		        				Consultation.ajouterConsultation(consultationModif);
					        		        				
					        		        				consultation = new String[listeConsult.length][6];
					        		        				
											        		 
											        		for(int i = 0 ; i < nbPatients ; i++)
											        		{	
											        			consultation[i][0] = nom;
											        			consultation[i][1] = listeConsult[i].getMedecin();
											        			consultation[i][2] = (listeConsult[i].getDateConsultation()).toString();
											        			consultation[i][3] = listeConsult[i].getAppareil();
											        			consultation[i][4] = listeConsult[i].getOctroie();
											        			consultation[i][5] = Consultation.pathologiesToString(listeConsult[i].getPathologies());
											        		}
											        		
											        		
											        		
											        		
											        		tableModelConsult = new DefaultTableModel(consultation, header1);
											        		tableConsultation.setModel(tableModelConsult);
											        		
												        	frame.add(panel);
													        frame.setVisible(true);
					        		        				
					        		        				
					        		        				fen.setVisible(false);
					        		        				JOptionPane.showMessageDialog(null, "Consultation modifi"+"\u00e9"+" !");
					        		        				fen.dispose();
				        						        	}
			        									}
					        						catch(DateTimeParseException error)
					        						{
					        							JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqu"+"\u00e9"+" : AAAA-MM-JJ !");
					        							dateNaissance = "";
					        							dispose();
					        							fieldMedecin.setText(medecinInitial);
			        						        	fieldDateConsultation.setText(dateInitial);
					        						}
		        								}
			        		        			catch(IOException error)
			        		        			{
			        		        				error.printStackTrace();
			        		       				}
		        								
		        							}
		        						}
		        					);
		        				
		        				
		        				
		        				fen.setVisible(true);
		        				fen.setSize(500, 300);
		        				fen.setPreferredSize(getSize());
		        		        }
		        			}
		        		
		        		}
		        		
		        	);
		        
		        
		        boutonInfo.addActionListener(new ActionListener()
		        		{
		        			public void actionPerformed(ActionEvent e)
		        			{
		        				boutonInfo.setEnabled(false);
		        				if(rowConsult > -1)
		        		        {
		        					nom = tableConsultation.getModel().getValueAt(rowConsult, 0).toString();
		        		        	nomMedecin = tableConsultation.getModel().getValueAt(rowConsult, 1).toString();
		        		        	dateConsult = tableConsultation.getModel().getValueAt(rowConsult, 2).toString();
		        		        	
		        		        	Consultation[] listeConsult = Consultation.rechercherConsultations(id[row]);
		        		        	
		        		        	int taille = listeConsult[rowConsult].getPathologies().length;
		        		        	
		        		        	final String medecinInitial = nomMedecin;
    		        				final String dateInitial = dateConsult;
		        		        
    		        				JFrame fen = new JFrame();
    		        				
    		        				
    		        				GridBagConstraints gbc = new GridBagConstraints();
    		        				
    		        				String[] header = {"Pathologies du patient"};
    		        				
    		        				String[][] infos = new String[taille][1];
    		        				
    		        				
    		        				
    		        				System.out.println(listeConsult[0].getPathologies()[0]);
    		        				System.out.println(id[row]);
    		        				
    		        				listeConsult[0].getPathologies();
    		        				
    		        				for(int i = 0 ; i < taille ; i++)
    		        				{
    		        					infos[i][0] = listeConsult[rowConsult].getPathologies()[i];
    		        				}
    		        				
    		        				DefaultTableModel model = new DefaultTableModel(infos, header);
    		        		        
    		        				
    		        				JTable tablePatho = new JTable(model);
    		        						
    		        				
    		        				fen.getContentPane().add(new JScrollPane(tablePatho), BorderLayout.CENTER);
    		        				fen.setVisible(true);
    		        				fen.setSize(400, 200);
    		        				fen.setPreferredSize(getSize());
    		        				fen.setMinimumSize(new Dimension(500, 300));
    		        				fen.setMaximumSize(new Dimension(600, 300));
		        		        }
		        				
		        			}
		        		}
		        	);
		        
		        boutonSupr.addActionListener(new ActionListener()
		        		{
		        			
		        			
		        			public void actionPerformed(ActionEvent e) 
		        			{
		        				boutonSupr.setEnabled(false);
		        				if(row > -1)
		        		        {
		        		        	prenom = tablePatient.getModel().getValueAt(row, 0).toString();
		        		        	nom = tablePatient.getModel().getValueAt(row, 1).toString();
		        		        	numSecu = tablePatient.getModel().getValueAt(row, 2).toString();
		        		        	dateNaissance = tablePatient.getModel().getValueAt(row, 3).toString();
		        		        	
		        		        	
		        		        	Patient aSupprimer = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
		        		        	
		        		        	Patient[] listePatient = Patient.rechercherPatients(aSupprimer);
		        		        	
		        		        	Consultation[] listeConsultation = Consultation.rechercherConsultations(id[row]);
		        		        	
		        		        	
					        		/*
					        		
					        		String[][] consultation = new String[nbConsultation][6];
							
					        		for(int i = 0 ; i < nbConsultation ; i++)
					        		{	
					        			consultation[i][0] = Integer.toString(listeConsultation[i].getIdPatient());
					        			consultation[i][1] = listeConsultation[i].getMedecin();
					        			consultation[i][2] = listeConsultation[i].getDateConsultation().toString();
					        			consultation[i][3] = (listeConsultation[i].getAppareil());
									
									
							        
										
					        		}*/
		        		        	try
		        		        	{
		        		        		Consultation.supprimerConsultation(listeConsultation[rowConsult]);
		        		        	}
		        		        	catch(IOException error)
		        		        	{
		        		        		
		        		        	}
		        		        	
		        		        	tableModelConsult.setRowCount(0);
					        		tableConsultation.setModel(tableModelConsult);
		        		        	
					        		
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
		        		        }
		        			}

		        		}
		        	);
		        
		        
		        
		       
		       
		        
		        
		        
		        frame.add(panel);
		        frame.setVisible(true); 
		        searchBarP.requestFocusInWindow();
		        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		        
		        
		        break;
			}
			
			case 3:            // Le technicien
			{
		        JPanel panel = new JPanel(new GridBagLayout());
		        
				frame.setTitle("GesPat-Technicien"); 
		        
		        JLabel titre = new JLabel("Interface Technicien");
		        
		        titre.setFont(new Font("Arial", Font.BOLD, 40));
		        
		        //JTextField searchBarI = new JTextField(2);
		        JTextField searchApp = new JTextField(2); // recherche par appareil
		        
		        JButton retour = new JButton("Menu Principal");
		        
		        JButton bouton = new JButton("Rechercher");
		        		        
		        JButton boutonModif = new JButton("Octroyer");
		        
		        tableModel = new DefaultTableModel(miniTab, header3);
		        
		        tableModelConsult = new DefaultTableModel(consultation, header2);
		        
		        tableConsultation = new JTable(tableModelConsult);
		        
		        boutonModif.setEnabled(false);
		        
		        //JLabel sousTitre0 = new JLabel("ID :");
		        JLabel sousTitre1 = new JLabel("Appareil :");
		        
		        panel.setLayout(new GridBagLayout());
		        
		        GridBagConstraints gbc = new GridBagConstraints();
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.gridx = 0;
		        gbc.gridy = 0;
		        gbc.weightx = 1;
		        gbc.weighty = 1;
		        gbc.gridwidth = 5;
		        
		        gbc.anchor = GridBagConstraints.NORTH;
		        
		        titre.setHorizontalAlignment(JLabel.CENTER);
		        
		        panel.add(titre, gbc);
		        
		        
		        gbc.gridwidth = 1; 
		        
		        
		        
		         
		        // Sous titre appareil
		        
		        gbc.gridx = 0;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre1, gbc);
		        
		        // Search Bar Appareil 
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 0;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		       
		        panel.add(searchApp, gbc);
		        
		        // bouton rechercher
		        
		        GridBagConstraints gbc2 = new GridBagConstraints();
		        gbc2.gridx = 0;
		        gbc2.gridy = 3;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 5;
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(bouton, gbc2);
		        
		        
		        // tableau consultations
		        
		        gbc.gridx = 0;
		        gbc.gridy = 4;
		        gbc.weightx = 1;
		        gbc.gridwidth = 5;
		        gbc.insets = new Insets(0, 0, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        // bouton retour menu
		        
		        gbc2.gridx = 0;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(retour, gbc2);
		        
		        // bouton modification
		        
		        gbc2.gridx = 2;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonModif, gbc2);
		       
		        retour.addActionListener(new ActionListener()
	    		   {
	    	   			public void actionPerformed(ActionEvent e)
	    	   			{
	    	   				frame.setVisible(false);
	    	   				new Fenetre();
	    	   			}
	    		   }
	    	);
		        
		        // Recherche les consultations
		        bouton.addActionListener(new ActionListener()
		        		{
		        			@Override
							public void actionPerformed(ActionEvent e) 
							{
								appareil = searchApp.getText();
								
								boolean checkApp = searchApp.getText().equals("");

								if(checkApp)
								{
									JOptionPane.showMessageDialog(null, "Veuillez rentrer le nom de l'appareil !");
									dispose();
									searchApp.setText(null);
								}
								else if(appareil.matches(".*\\d.*"))
								{
									JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des lettre pour les champs suivants :"+"\n"
											+"Pr"+"\u00e9"+"nom, Nom !");
									dispose();
									searchApp.setText(null);
								}
								else
								{
									Consultation[] listeConsultation = Consultation.rechercherConsultations(appareil);
									
									
						
									int nbConsultation = listeConsultation.length;
									
									if(nbConsultation > 0)
									{
										consultation = new String[nbConsultation][6];
										
										for(int i = 0 ; i < nbConsultation ; i++)
										{
											consultation[i][0] = (Patient.rechercherPatients(listeConsultation[i].getIdPatient()))[0].getNom();
											consultation[i][1] = listeConsultation[i].getMedecin();
											consultation[i][2] = listeConsultation[i].getDateConsultation().toString();
											consultation[i][3] = listeConsultation[i].getAppareil();
											consultation[i][4] = listeConsultation[i].getOctroie();
											consultation[i][5] = Integer.toString(listeConsultation[i].getIdPatient());
										}
										
										tableModelConsult = new DefaultTableModel(consultation, header2);
										tableConsultation.setModel(tableModelConsult);
										
										tableConsultation.getColumnModel().getColumn(5).setMinWidth(0);
										tableConsultation.getColumnModel().getColumn(5).setMaxWidth(0);
										tableConsultation.getColumnModel().getColumn(5).setWidth(0);
										
										panel.add(new JScrollPane(tableConsultation), gbc);
										frame.add(panel);
										frame.setVisible(true);
									}
									else
										JOptionPane.showMessageDialog(null, "Aucune consultation pour cette appareil !");
									
									frame.add(panel);
									frame.setVisible(true);
									
									//searchBarI.setText(null);
									searchApp.setText(null);
								}
							}
		        		}
		        
		        	);
		        
		        
		        
		        tableConsultation = new JTable(consultation, header2);
		        
		        panel.add(new JScrollPane(tableConsultation), gbc);
		        
		        tableConsultation.getColumnModel().getColumn(5).setMinWidth(0);
        		tableConsultation.getColumnModel().getColumn(5).setMaxWidth(0);
        		tableConsultation.getColumnModel().getColumn(5).setWidth(0);
		        
        		
		        
		        tableConsultation.addMouseListener(new MouseAdapter()
		        		{
		        			public void mousePressed(MouseEvent e)
		        			{
		        				Point p = e.getPoint();
		        				rowConsult = tableConsultation.rowAtPoint(p);
		        			}
		        		}
		        );

		        tableConsultation.addFocusListener(new FocusListener()
		        		{
		        			@Override
		        			public void focusGained(FocusEvent e)
		        			{
		        				boutonModif.setEnabled(true);
		        			}
		        			
		        			public void focusLost(FocusEvent e)
		        			{
		        				
		        			}
		        		});
		        
		        boutonModif.addActionListener(new ActionListener()
		        		{
		    
		        			public void actionPerformed(ActionEvent e)
		        			{
		        				LocalDate dateConsult = LocalDate.parse(tableConsultation.getModel().getValueAt(rowConsult, 2).toString());
		        				
		        				//change le string
								Consultation[] modif_oct = Consultation.rechercherConsultations(Integer.parseInt(tableConsultation.getModel().getValueAt(rowConsult, 5).toString()));//row 1 ou 0
								
								for(int i = 0 ; i < modif_oct.length ; i++)
								{
									if(modif_oct[i].getDateConsultation().equals(dateConsult))
									{
										try {
											modif_oct[i].setOctroie("Octroye");
										} catch (IOException e1) {}
									}
								}
								

								tableConsultation.setValueAt("Octroye", rowConsult, 4);
		        			}
		        		
		        		}
		        		
		        	);
		        
		        frame.add(panel);
		        frame.setVisible(true); 
		        searchApp.requestFocusInWindow();
		        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		        break;
			}
			case 4:
			{
				String[] column = {"Nombre d'appareils octroy"+"\u00e9"+"s/patients", ""};
				int[] appareil = new int[appareillage.length];
				int[] patho = new int[pathologies.length];
				
				try
				{
					if(Files.exists(consultations_path)) 
			        {
			            Files.readAllLines(consultations_path, StandardCharsets.ISO_8859_1).forEach((String consultation) -> { // regarde dans tous le fichier consultations.txt
			                String[] consultation_infos = consultation.split(";");
			                for(int i = 0 ; i < appareillage.length ; i++)			
			                {
			                	if(consultation_infos[3].equals(appareillage[i])) // lorsqu'une des consultations du fichier contient un des appareils de la liste des appareil le compteur de cette appareil augmente
				                {
				                	appareil[i]++;
				                }
			                }
			                
			            });
			    
			        }
				}
				catch(IOException error)
				{
					
				}
				
				try
				{
					if(Files.exists(consultations_path))
			        {
			            Files.readAllLines(consultations_path, StandardCharsets.ISO_8859_1).forEach((String consultation) -> { // regarde dans tous le fichier consultations.txt
			                String[] consultation_infos = consultation.split(";");
			                
			                for(int i = 0 ; i < pathologies.length ; i++)
			                {
			                	if(consultation_infos[5].contains(pathologies[i])) // lorsque une des consultations du fichier contient une des pathologies de la liste de pathologies le compteur de cette pathologie augmente
				                {
				                	patho[i]++;
				                }
			                }
			                
			            });
			    
			        }
				}
				catch(IOException error)
				{
					
				}
				
				//on défini un tableau du nombre de patient par pathologie
				
				miniTab = new String[pathologies.length][2]; 
				
				for(int i = 0 ; i < pathologies.length ; i++)
				{
					miniTab[i][0] = pathologies[i];
					miniTab[i][1] = Integer.toString(patho[i]);
				}
				
				column = new String[] {"Pathologies", "Nombre de patient/pathologies"};
			
				JTable voletStat1 = new JTable(miniTab, column);
				
				//on défini un tableau du nombre de patient par appareils octroyé
				
				miniTab = new String[appareillage.length][2];
				
				for(int i = 0 ; i < appareillage.length ; i++)
				{
					miniTab[i][0] = appareillage[i];
					miniTab[i][1] = Integer.toString(appareil[i]);
				}
				
				column = new String[] {"Appareils", "Nombre de patient/appareils"};
				
				JTable voletStat2 = new JTable(miniTab, column);
				
				JPanel panel = new JPanel(new GridBagLayout());
		        
		        frame.setTitle("GesPat-Interface Statistiques");
		        
		        JLabel titre = new JLabel("Interface Statistiques");
		        
		        titre.setFont(new Font("Arial", Font.BOLD, 40));
		        
		        JButton retour = new JButton("Menu principal");
		        
		        
		        panel.setLayout(new GridBagLayout());
		        
		        GridBagConstraints gbc = new GridBagConstraints();
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.gridx = 0;
		        gbc.gridy = 0;
		        gbc.weightx = 1;
		        gbc.weighty = 1;
		        gbc.gridwidth = 4;
		        
		        gbc.anchor = GridBagConstraints.NORTH;
		        
		        titre.setHorizontalAlignment(JLabel.CENTER);
		        
		        panel.add(titre, gbc);
		        
		        
		        gbc.gridwidth = 1;
		        
		        gbc.gridx = 0;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 0, 0, 0);
		        gbc.anchor = GridBagConstraints.NORTH;
		        
		        JScrollPane scrollPane = new JScrollPane(voletStat1);
		        
		        scrollPane.setPreferredSize(new Dimension(300, 359));
		        
		        panel.add(scrollPane, gbc);
		        
		        gbc.gridx = 1;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 0, 0, 0);
		        gbc.anchor = GridBagConstraints.NORTH;
		        
		        scrollPane = new JScrollPane(voletStat2);
		        
		        scrollPane.setPreferredSize(new Dimension(300, 103));
		        
		        panel.add(scrollPane, gbc);
		        
		        
		        GridBagConstraints gbc2 = new GridBagConstraints();
		        
		        
		        // bouton retour
		        
		        gbc2.gridx = 0;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 2;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        retour.setAlignmentX(JButton.CENTER_ALIGNMENT);
		        
		        panel.add(retour, gbc2);
		        
		       
		       retour.addActionListener(new ActionListener()
		    		   {
		    	   			public void actionPerformed(ActionEvent e)
		    	   			{
		    	   				frame.setVisible(false);
		    	   				new Fenetre();
		    	   			}
		    		   }
		    	);
		       
		       
		       	frame.add(panel);
		        frame.setVisible(true);  
		        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
		}
		
		frame.setSize(1500, 800);
		frame.setPreferredSize(getSize());  
		frame.setMinimumSize(new Dimension(1375, 800));
		
	}
	
	public static int nbLine(String fileName) // compte le nombre de ligne dans le fichier patients.txt
	{
		int lignes = 0;
		try
		{
			FileInputStream file = new FileInputStream("patients.txt");
        	
        	BufferedReader br = new BufferedReader(new InputStreamReader(file));
        	
        	while(br.readLine() != null)
        	{
        		lignes++;
        	}
        	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return lignes;
		
	}
	
	
	public  void actionPerformed(ActionEvent e)
	{
		
	}

	
	

}
