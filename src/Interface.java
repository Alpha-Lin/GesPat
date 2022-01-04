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
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
//import javax.swing.table.TableModel;
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

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


public class Interface extends JFrame implements ActionListener
{
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
			case 1:            // L'agent d'administration
			{
				
		        JPanel panel = new JPanel(new GridBagLayout());
		        
		        frame.setTitle("GesPat-Agent Administration");
		        
		        JLabel titre = new JLabel("Interface Administration");
		        
		        titre.setFont(new Font("Arial", Font.BOLD, 40));
		        
		        //JTextField searchBarI = new JTextField(2);
		        JTextField searchBarP = new JTextField(2); // recherche par prenom
		        JTextField searchBarN = new JTextField(2); // recherche par nom
		        JTextField searchBarNS = new JTextField(2); // recherche par numero de securité social
		        JTextField searchBarDN = new JTextField(0); // recherche par date de naissance
		        
		        JButton retour = new JButton("Menu principal");
		        
		        JButton bouton = new JButton("Rechercher");
		        
		        JButton boutonAjout = new JButton("Ajouter...");
		        
		        JButton boutonModif = new JButton("Modifier...");
		        
		        JButton boutonSupr = new JButton("Supprimmer");
		        
		        boutonModif.setEnabled(false);
		        
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
		        
		        
		        //JLabel sousTitre0 = new JLabel("ID :");
		        JLabel sousTitre1 = new JLabel("Pr"+"\u00e9"+"nom :");
		        JLabel sousTitre2 = new JLabel("Nom :");
		        JLabel sousTitre3 = new JLabel("Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" :");
		        JLabel sousTitre4 = new JLabel("Date de naissance :");
		        
		        
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
		        
		        /*
		        
		        // Sous titre id
		        
		        gbc.gridx = 0;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre0, gbc);
		        
		        */
		        
		        
		        gbc.gridwidth = 1;
		        // Sous titre prenom
		        
		        gbc.gridx = 0;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre1, gbc);
		        
		        // sous titre nom
		        gbc.gridx = 1;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;        
		        panel.add(sousTitre2, gbc);
		        
		        // sous titre numero
		        gbc.gridx = 2;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 70, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre3, gbc);
		        
		        //sous titre date
		        gbc.gridx = 3;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        panel.add(sousTitre4, gbc);
		        
		        /*
		        
		        // Search Bar id
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 0;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		       
		       
		        panel.add(searchBarI, gbc);
		        
		        */
		        
		        // Search Bar Prenom 
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 0;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		       
		        panel.add(searchBarP, gbc);
		        
		        // Search Bar Nom 
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 1;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        panel.add(searchBarN, gbc);
		        
		        // Search Bar Numero de securité social
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 70, 0, 70);
		        gbc.gridx = 2;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(searchBarNS, gbc);
		        
		        // Search Bar date de naissance
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 70, 0, 70);
		        gbc.gridx = 3;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(searchBarDN, gbc);
		        
		        // bouton
		        
		        GridBagConstraints gbc2 = new GridBagConstraints();
		        gbc2.gridx = 0;
		        gbc2.gridy = 3;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 5;
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(bouton, gbc2);
		        
		        
		        // tableau
		        
		        gbc.gridx = 0;
		        gbc.gridy = 4;
		        gbc.weightx = 1;
		        gbc.gridwidth = 4;
		        gbc.insets = new Insets(0, 0, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        // bouton retour
		        
		        gbc2.gridx = 0;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(retour, gbc2);
		        
		        // bouton ajout
		        
		        gbc2.gridx = 1;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonAjout, gbc2);
		        
		        // bouton modification
		        
		        gbc2.gridx = 0;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 4;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonModif, gbc2);
		        
		        // bouton supression
		        
		        gbc2.gridx = 2;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 2;
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
								
		        					prenom = searchBarP.getText();
									nom = searchBarN.getText();
									numSecu = searchBarNS.getText();
									dateNaissance = searchBarDN.getText();
									
									
							        boolean checkPrenom = searchBarP.getText().equals("");
							        boolean checkNom = searchBarN.getText().equals("");
							        boolean checkNS = searchBarNS.getText().equals("");
							        boolean checkDN = searchBarDN.getText().equals("AAAA-MM-JJ");
							        
							        
							        
							        if(checkPrenom || checkNom || checkNS || checkDN)
									{
							        	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
										dispose();
										searchBarP.setText(null);
							        	searchBarN.setText(null);
							        	searchBarNS.setText(null);
							        	searchBarDN.setText("AAAA-MM-JJ");
							        	searchBarDN.setForeground(new Color(153, 153, 153));
									}
							        else if(prenom.matches(".*\\d.*") || nom.matches(".*\\d.*"))
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
							        else if(!(numSecu.matches("[0-9]+")))
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
							        	
							        	Patient search = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
							        	
							        	
							        	
							        	if(Patient.rechercherPatient(search))
							        	{
							        		
							        		Patient[] listePatient = Patient.rechercherPatients(search);
										
							        		nbPatients = listePatient.length;
							        		
							        		String[][] patients = new String[nbPatients][5];
							        		
							        		id = new int[nbPatients]; 
									
							        		for(int i = 0 ; i < nbPatients ; i++)
							        		{	
							        			id[i] = listePatient[i].getId();
							        			patients[i][0] = listePatient[i].getNom();
							        			patients[i][1] = listePatient[i].getPrenom();
							        			patients[i][2] = listePatient[i].getSecu();
												patients[i][3] = (listePatient[i].getNaissance()).toString();
							        		}
							        		
							        		tableModel = new DefaultTableModel(patients, header1);
							        		tablePatient.setModel(tableModel);
							        		
							        		
								        	panel.add(new JScrollPane(tablePatient), gbc);
								        	frame.add(panel);
									        frame.setVisible(true);
							        		
							        	}
							        	else
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
								catch(DateTimeParseException error)
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
								catch(NumberFormatException error)
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
		        
		        
		        // supprimer un patient
		        
		        
		        tablePatient.addMouseListener(new MouseAdapter()
		        		{
		        			public void mousePressed(MouseEvent e)
		        			{
		        				Point p = e.getPoint();
		        				row = tablePatient.rowAtPoint(p);
		        				System.out.println(row);
		        			}
		        		}
		        );
		        
		        tablePatient.addFocusListener(new FocusListener()
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
		      
		        boutonAjout.addActionListener(new ActionListener()
		        		{
		        			public void actionPerformed(ActionEvent e)
		        			{
		        				JFrame fen = new JFrame();
		        				
		        				fen.setLayout(new GridBagLayout());
		        				
		        				GridBagConstraints gbc = new GridBagConstraints();
		        				
		        				JLabel titre = new JLabel("Nouveau patient :");
		        				
		        				JLabel sousTitre1 = new JLabel("Pr"+"\u00e9"+"nom : ");
		        				
		        				JLabel sousTitre2 = new JLabel("Nom :");
		        				
		        				JLabel sousTitre3 = new JLabel("Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale :");
		        				
		        				JLabel sousTitre4 = new JLabel("Date de naissance :");
		        				
		        				JTextField fieldPrenom = new JTextField(10);
		        				
		        				JTextField fieldNom = new JTextField(10);
		        				
		        				JTextField fieldNumSecu = new JTextField(10);
		        				
		        				JTextField fieldDateNaissance = new JTextField(10);
		        				
		        				JButton validation = new JButton("Valider");
		        				
		        				fieldDateNaissance.setText("AAAA-MM-JJ");
		        				fieldDateNaissance.setForeground(new Color(153, 153, 153));
		        				
		        				
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
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 4;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre4, gbc);
		        				
		        				// textfield
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 1;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldPrenom, gbc);
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 2;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldNom, gbc);
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 3;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldNumSecu, gbc);
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 4;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldDateNaissance, gbc);
		        				
		        				// bouton
		        				
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
		        				
		        				
		        				validation.addActionListener(new ActionListener()
		        						{
		        							public void actionPerformed(ActionEvent e)
		        							{
		        								
		        								try
		        								{
			        								String prenom = fieldPrenom.getText();
			        		        				String nom = fieldNom.getText();
			        		        				String numSecu = fieldNumSecu.getText();
			        		        				String dateNaissance = fieldDateNaissance.getText();
			        		        				
			        		        				boolean checkPrenom = fieldPrenom.getText().equals("");
			        						        boolean checkNom = fieldNom.getText().equals("");
			        						        boolean checkNS = fieldNumSecu.getText().equals("");
			        						        boolean checkDN = fieldDateNaissance.getText().equals("AAAA-MM-JJ") || fieldDateNaissance.getText().equals("");
			        						        
			        						       
			        						       
			        						        if(checkPrenom || checkNom || checkNS || checkDN)
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
			        						        else if(prenom.matches(".*\\d.*") || nom.matches(".*\\d.*"))
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
			        						        else if(!(numSecu.matches("[0-9]+")))
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
			        						        	Patient nouvPatient = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
			        						        	if(Patient.rechercherPatient(nouvPatient))
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
			        							catch(DateTimeParseException error)
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
			        							catch(NumberFormatException error)
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
		        				boutonSupr.setEnabled(false);
		        				if(row > -1)
		        		        {
		        					System.out.println("la ligne "+row);
		        		        	prenom = tablePatient.getModel().getValueAt(row, 0).toString();
		        		        	nom = tablePatient.getModel().getValueAt(row, 1).toString();
		        		        	numSecu = tablePatient.getModel().getValueAt(row, 2).toString();
		        		        	dateNaissance = tablePatient.getModel().getValueAt(row, 3).toString();
		        		        	
		        		        	final String prenomInitial = prenom;
    		        				final String nomInitial = nom;
    		        				final String numSecuInitial = numSecu;
    		        				final String dateNaissanceInitial = dateNaissance;
		        		        
		        				
		        				JFrame fen = new JFrame();
		        				
		        				fen.setLayout(new GridBagLayout());
		        				
		        				GridBagConstraints gbc = new GridBagConstraints();
		        				
		        				JLabel titre = new JLabel("Modification :");
		        				
		        				JLabel sousTitre1 = new JLabel("Pr"+"\u00e9"+"nom : ");
		        				
		        				JLabel sousTitre2 = new JLabel("Nom :");
		        				
		        				JLabel sousTitre3 = new JLabel("Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale :");
		        				
		        				JLabel sousTitre4 = new JLabel("Date de naissance :");
		        				
		        				JTextField fieldPrenom = new JTextField(10);
		        				
		        				fieldPrenom.setText(prenom);
		        				
		        				JTextField fieldNom = new JTextField(10);
		        				
		        				fieldNom.setText(nom);
		        				
		        				JTextField fieldNumSecu = new JTextField(10);
		        				
		        				fieldNumSecu.setText(numSecu);
		        				
		        				JTextField fieldDateNaissance = new JTextField(10);
		        				
		        				fieldDateNaissance.setText(dateNaissance);
		        				
		        				JButton validation = new JButton("Valider");
		        				
		        				
		        				
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
		        				
		        				gbc.anchor = GridBagConstraints.WEST;
		        				gbc.gridx = 0;
		        				gbc.gridy = 4;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 10, 10, 0);
		        				
		        				fen.add(sousTitre4, gbc);
		        				
		        				// textfield
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 1;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldPrenom, gbc);
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 2;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldNom, gbc);
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 3;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldNumSecu, gbc);
		        				
		        				gbc.anchor = GridBagConstraints.EAST;
		        				gbc.gridx = 2;
		        				gbc.gridy = 4;
		        				gbc.weightx = 0;
		        				gbc.weighty = 0;
		        				gbc.gridwidth = 1;
		        				gbc.insets = new Insets(0, 0, 10, 10);
		        				
		        				fen.add(fieldDateNaissance, gbc);
		        				
		        				// bouton
		        				
		        				gbc.anchor = GridBagConstraints.SOUTH;
		        				gbc.gridx = 0;
		        				gbc.gridy = 5;
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
		        									
			        		        				
			        		        				
			        		        				Patient search = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
			        		        				
			        		        				Patient[] listePatient = Patient.rechercherPatients(search);
			        		        				
			        		        				
			        		        				try
			        								{
				        								
				        		        				
				        		        				boolean checkPrenom = fieldPrenom.getText().equals("");
				        						        boolean checkNom = fieldNom.getText().equals("");
				        						        boolean checkNS = fieldNumSecu.getText().equals("");
				        						        boolean checkDN = fieldDateNaissance.getText().equals("AAAA-MM-JJ") || fieldDateNaissance.getText().equals("");
				        						        
				        						       
				        						       
				        						        if(checkPrenom || checkNom || checkNS || checkDN)
				        								{
				        						        	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
				        									dispose();
				        									fieldPrenom.setText(prenomInitial);
				        						        	fieldNom.setText(nomInitial);
				        						        	fieldNumSecu.setText(numSecuInitial);
				        						        	fieldDateNaissance.setText(dateNaissanceInitial);
				        								}
				        						        else if(prenom.matches(".*\\d.*") || nom.matches(".*\\d.*"))
				        						        {
				        						        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des lettre pour les champs suivants :"+"\n"
				        									        +"Pr"+"\u00e9"+"nom, Nom !");
				        						        	dispose();
				        						        	fieldPrenom.setText(prenomInitial);
				        						        	fieldNom.setText(nomInitial);
				        						        	fieldNumSecu.setText(numSecuInitial);
				        						        	fieldDateNaissance.setText(dateNaissanceInitial);
				        						        }
				        						        else if(!(numSecu.matches("[0-9]+")))
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
				        						        	listePatient[0].setPrenom(fieldPrenom.getText());
					        		        				listePatient[0].setNom(fieldNom.getText());
					        		        				listePatient[0].setSecu(fieldNumSecu.getText());
					        		        				listePatient[0].setNaissance(LocalDate.parse(fieldDateNaissance.getText()));
					        		        				
					        		        				int idPatient = listePatient[0].getId();
					        		        				prenom =  listePatient[0].getPrenom();
					        		        				nom =listePatient[0].getNom();
					        		        				numSecu =listePatient[0].getSecu();
				        						        	
				        						        	Patient patientModif = new Patient(idPatient, prenom, nom, numSecu, listePatient[0].getNaissance());
					        		        				
					        		        				Patient.supprimerPatient(listePatient[row]);
					        		        				
					        		        				Patient.ajouterPatient(patientModif);
					        		        				
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
					        						catch(DateTimeParseException error)
					        						{
					        							JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqu"+"\u00e9"+" : AAAA-MM-JJ !");
					        							dateNaissance = "";
					        							dispose();
					        							fieldPrenom.setText(prenomInitial);
			        						        	fieldNom.setText(nomInitial);
			        						        	fieldNumSecu.setText(numSecuInitial);
			        						        	fieldDateNaissance.setText(dateNaissanceInitial);
					        						}
					       							catch(NumberFormatException error)
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
		        		        	
		        		        	
					        		nbPatients = listePatient.length;
					        		
					        		String[][] patients = new String[nbPatients][5];
							
					        		for(int i = 0 ; i < nbPatients ; i++)
					        		{	
					        			patients[i][0] = listePatient[i].getNom();
					        			patients[i][1] = listePatient[i].getPrenom();
					        			patients[i][2] = listePatient[i].getSecu();
										patients[i][3] = (listePatient[i].getNaissance()).toString();
									
									
							        
										
					        		}
					        		
					        		
		        		        	try
		        		        	{
		        		        		Patient.supprimerPatient(listePatient[row]);
		        		        		System.out.println("ok1");
		        		        	}
		        		        	catch(IOException error)
		        		        	{
		        		        		
		        		        	}
		        		        	
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
			
			case 2:             // Le médecin
			{
				JPanel panel = new JPanel(new GridBagLayout());
		        
		        frame.setTitle("GesPat-Agent M"+"\u00e9"+"decin");
		        
		        JLabel titre = new JLabel("Interface M"+"\u00e9"+"decin");
		        
		        titre.setFont(new Font("Arial", Font.BOLD, 40));
		        
		        //JTextField searchBarI = new JTextField(2);
		        JTextField searchBarP = new JTextField(2); // recherche par prenom
		        JTextField searchBarN = new JTextField(2); // recherche par nom
		        JTextField searchBarNS = new JTextField(2); // recherche par numero de securité social
		        JTextField searchBarDN = new JTextField(0); // recherche par date de naissance
		        
		        JButton retour = new JButton("Menu Principal");
		        
		        JButton bouton = new JButton("Rechercher");
		        
		        JButton boutonAjout = new JButton("Ajouter...");
		        
		        JButton boutonModif = new JButton("Modifier...");
		        
		        JButton boutonSupr = new JButton("Supprimmer");
		        
		        JButton boutonInfo = new JButton("Information...");
		        
		        
		        tableModel = new DefaultTableModel(miniTab, header3);
		        
		        tableModelConsult = new DefaultTableModel(consultation, header2);
		        
		        tablePatient = new JTable(tableModel);
		        
		        tablePatient.getColumnModel().getColumn(2).setMinWidth(0);
        		tablePatient.getColumnModel().getColumn(2).setMaxWidth(0);
        		tablePatient.getColumnModel().getColumn(2).setWidth(0);
        		
        		tablePatient.getColumnModel().getColumn(3).setMinWidth(0);
        		tablePatient.getColumnModel().getColumn(3).setMaxWidth(0);
        		tablePatient.getColumnModel().getColumn(3).setWidth(0);
		        
		        panneau = new JScrollPane(tablePatient);
		        
		        panneau.setPreferredSize(new Dimension(30, 80));
		        
		        tableConsultation = new JTable(tableModelConsult);
		        
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
		        
		        
		        //JLabel sousTitre0 = new JLabel("ID :");
		        JLabel sousTitre1 = new JLabel("Pr"+"\u00e9"+"nom :");
		        JLabel sousTitre2 = new JLabel("Nom :");
		        JLabel sousTitre3 = new JLabel("Num"+"\u00e9"+"ro de s"+"\u00e9"+"curit"+"\u00e9"+" :");
		        JLabel sousTitre4 = new JLabel("Date de naissance :");
		        JLabel sousTitre5 = new JLabel("Selectionner un patient :");
		        
		        
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
		        
		        
		        
		         
		        // Sous titre prenom
		        
		        gbc.gridx = 0;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre1, gbc);
		        
		        // sous titre nom
		        gbc.gridx = 1;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;        
		        panel.add(sousTitre2, gbc);
		        
		        // sous titre numero
		        gbc.gridx = 2;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 70, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre3, gbc);
		        
		        //sous titre date
		        gbc.gridx = 3;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        panel.add(sousTitre4, gbc);
		        
		        //sous titre tableau
		        gbc.gridx = 4;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        panel.add(sousTitre5, gbc);
		       
		        
		        // Search Bar Prenom 
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 0;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		       
		        panel.add(searchBarP, gbc);
		        
		        // Search Bar Nom 
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 1;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        panel.add(searchBarN, gbc);
		        
		        // Search Bar Numero de securité social
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 70, 0, 70);
		        gbc.gridx = 2;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(searchBarNS, gbc);
		        
		        // Search Bar date de naissance
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 70, 0, 70);
		        gbc.gridx = 3;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(searchBarDN, gbc);
		        
		        
		        
		        // tableau patient
		        
		       	gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 70, 0, 70);
		        gbc.gridx = 4;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.gridheight = 2;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        
		        panel.add(panneau, gbc);
		        gbc.gridheight = 1;
		        
		        // bouton
		        
		        GridBagConstraints gbc2 = new GridBagConstraints();
		        gbc2.gridx = 0;
		        gbc2.gridy = 3;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 5;
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(bouton, gbc2);
		        
		        
		        // tableau
		        
		        gbc.gridx = 0;
		        gbc.gridy = 4;
		        gbc.weightx = 1;
		        gbc.gridwidth = 5;
		        gbc.insets = new Insets(0, 0, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        // bouton retour
		        
		        gbc2.gridx = 0;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(retour, gbc2);
		        
		        // bouton ajout
		        
		        gbc2.gridx = 1;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonAjout, gbc2);
		        
		        // bouton modification
		        
		        gbc2.gridx = 2;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonModif, gbc2);
		        
		        // bouton information patient
		        
		        gbc2.gridx = 3;
		        gbc2.gridy = 5;
		        gbc2.weightx = 0;
		        gbc2.gridwidth = 1;
		        gbc2.insets = new Insets(0, 0, 10, 0);
		        gbc2.anchor = GridBagConstraints.CENTER;
		        
		        panel.add(boutonInfo, gbc2);
		        
		        // bouton supression
		        
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
									prenom = searchBarP.getText();
									nom = searchBarN.getText();
									numSecu = searchBarNS.getText();
									dateNaissance = searchBarDN.getText();
									
									
									
							        //boolean checkId = searchBarI.getText().equals("");
							        boolean checkPrenom = searchBarP.getText().equals("");
							        boolean checkNom = searchBarN.getText().equals("");
							        boolean checkNS = searchBarNS.getText().equals("");
							        boolean checkDN = searchBarDN.getText().equals("AAAA-MM-JJ");
							      
							        if(checkPrenom || checkNom || checkNS || checkDN)
									{
							        	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
										dispose();
										searchBarP.setText(null);
							        	searchBarN.setText(null);
							        	searchBarNS.setText(null);
							        	searchBarDN.setText("AAAA-MM-JJ");
							        	searchBarDN.setForeground(new Color(153, 153, 153));
									}
							        else if(prenom.matches(".*\\d.*") || nom.matches(".*\\d.*"))
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
							        else if(!(numSecu.matches("[0-9]+")))
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
							        
							        	Patient search = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
							        	
							        	
							        	
							        	if(Patient.rechercherPatient(search))
							        	{
							        		
							        		Patient[] listePatient = Patient.rechercherPatients(search);
										
							        		nbPatients = listePatient.length;
							        		
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
							        	else
							        	{
							        		JOptionPane.showMessageDialog(null, "Patient inconnu, veuillez l'ajouter ou vérifier les paramètres entrée !");
							        	}
							        	
							        	
							        	//searchBarI.setText(null);
							        	searchBarP.setText(null);
							        	searchBarN.setText(null);
							        	searchBarNS.setText(null);
							        	searchBarDN.setText(null);
							        	
							        }
						        
								}
			        			catch(DateTimeParseException error)
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
		        
		        tableConsultation.getColumnModel().getColumn(5).setMinWidth(0);
        		tableConsultation.getColumnModel().getColumn(5).setMaxWidth(0);
        		tableConsultation.getColumnModel().getColumn(5).setWidth(0);
		        
		        tablePatient.addMouseListener(new MouseAdapter()
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
				        								String nom = fieldMedecin.getText();
				        		        				String dateConsultation = fieldDateConsultation.getText();
				        		        				
				        		        				int taille = listePatho.getSelectedValuesList().size();
				        		        				List<String> liste = listePatho.getSelectedValuesList();
				        		        				String[] patho = liste.toArray(new String[taille]);
				        		        				String appareil = listeAppareil.getSelectedValue().toString();
				        		        				
				        		        				Consultation nouvelleConsult = new Consultation(id[row], nom, LocalDate.parse(dateConsultation), appareil, patho);
				        		        				
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
		        				boutonSupr.setEnabled(false);
		        				if(rowConsult > -1)
		        		        {
		        					nom = tableConsultation.getModel().getValueAt(rowConsult, 0).toString();
		        		        	nomMedecin = tableConsultation.getModel().getValueAt(rowConsult, 1).toString();
		        		        	dateConsult = tableConsultation.getModel().getValueAt(rowConsult, 2).toString();
		        		        	
		        		        	final String medecinInitial = nomMedecin;
    		        				final String dateInitial = dateConsult;
		        		        
		        				
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
		        									
			        		        				Consultation[] listeConsult = Consultation.rechercherConsultations(id[row]);
			        		        				
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
					        		        				
				        						        	
				        						        	listeConsult[0].setMedecin(fieldMedecin.getText());
				        						        	listeConsult[0].setDateConsultation(LocalDate.parse(fieldDateConsultation.getText()));
				        						        	listeConsult[0].setAppareil(listeAppareil.getSelectedValue().toString());
				        						        	listeConsult[0].setPathologies(patho);
				        						        	
					        		        				
					        		        				int idPatient = listeConsult[0].getIdPatient();
					        		        				nomMedecin =  listeConsult[0].getMedecin();
					        		        				appareil = listeConsult[0].getAppareil();
					        		        				
					        		        				
				        						        	Consultation consultationModif = new Consultation(idPatient, nomMedecin, listeConsult[0].getDateConsultation(), appareil, patho);
				        						        	
				        						        	
					        		        				Consultation.supprimerConsultation(listeConsult[0]);
					        		        				
					        		        				
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
		        				fen.setSize(400, 200);
		        				fen.setPreferredSize(getSize());
		        				fen.setMinimumSize(new Dimension(500, 300));
		        				fen.setMaximumSize(new Dimension(600, 300));
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
		        		        	
		        		        	Consultation[] listeConsultation = Consultation.rechercherConsultations(listePatient[row].getId());
		        		        	
		        		        	
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
		        		        		Consultation.supprimerConsultation(listeConsultation[row]);
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
				GridBagConstraints gbc = new GridBagConstraints(); 
		        
		        this.setLayout(new GridBagLayout());  
		        
		        setTitle("GesPat-Technicien"); 
		        
		        
		        
		        
		        
		        setVisible(true);  
		        setDefaultCloseOperation(EXIT_ON_CLOSE);
		        break;
			}
		}
		
		frame.setSize(1500, 800);
		frame.setPreferredSize(getSize());  
		frame.setMinimumSize(new Dimension(1375, 800));
		
	}
	
	public static int nbLine(String fileName)
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
