import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
//import javax.swing.table.TableModel;
import javax.swing.JOptionPane;


import medical.Patient;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;


public class Interface extends JFrame implements ActionListener
{
	public int nbLignes = nbLine("patients.txt");  
	
	public int nbPatients = 0;
    
    public String[][] patient = new String[nbPatients][4];
    
    public String[][] miniTab = new String[nbPatients][2];
    
    public String[][] consultation = new String[nbPatients][5];
    
    public String[] header1 = {"Pr"+"\u00e9"+"nom", "Nom", "Num. S"+"\u00e9"+"cu.", "Date de naissance"};
    
    public String[] header2 = {"Nom patient", "Nom m"+"\u00e9"+"decin", "Date consultation", "Pathologies", "Appareillage n"+"\u00e9"+"cessaire"};
    
    String[] header3 = {"Prenom", "Nom", "num", "date"};
    
    JTable tablePatient;
    
    JTable tableConsultation;
    
    boolean check = false;
    
    boolean check2 = false;
    
    int id[]; 
    String prenom;
	String nom;
	String numSecu;
	String dateNaissance;
    
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
		        
		       
		       
		        
		        
		        bouton.addActionListener(new ActionListener()
		        		{
		        			@Override
							public void actionPerformed(ActionEvent e) 
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
						      
						        
						        if(prenom.matches(".*\\d.*") || nom.matches(".*\\d.*"))
						        {
						        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des lettre pour les champs suivants :"+"\n"
									        +"Pr"+"\u00e9"+"nom, Nom !");
						        	dispose();
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
						        }
						        
						        if(numSecu.matches("[a-zA-Z]+"))
						        {
						        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des chiffres pour le champ suivant :"+"\n"
									        +"Numéro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale !");
						        	dispose();
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
						        }
						        
						        try
						        {
						        	LocalDate.parse(dateNaissance);
						        }
						        catch(DateTimeParseException error)
						        {
						        	check = true;
						        	
						        	/* dispose() ne marche pas ici
						        	 
						        	JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqué : AAAA-MM-JJ !");
						        	dateNaissance = "";
						        	dispose();
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
						        	
						        	*/
						        }
						        
						        
						        
						        if(checkPrenom || checkNom || checkNS || checkDN)
								{
						        	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
									dispose();
									//searchBarI.setText(null);
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
								}
						        else if(dateNaissance.matches("[a-zA-Z]+"))
						        {
						        	JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqu"+"\u00e9"+" : AAAA-MM-JJ !");
						        	dispose();
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
						        }
						        else if(check)
						        {
						        	JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqu"+"\u00e9"+" : AAAA-MM-JJ !");
						        	dateNaissance = "";
						        	dispose();
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
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
						        	searchBarDN.setText(null);
						        	
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
		        				
		        				// avant push ou fetch
		        				
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
		        								String prenom = fieldPrenom.getText();
		        		        				String nom = fieldNom.getText();
		        		        				String numSecu = fieldNumSecu.getText();
		        		        				String dateNaissance = fieldDateNaissance.getText();
		        		        				
		        		        				// test
		        		        				//Integer.parseInt(numSecu); // une exception sera levé si le numero de securite social contient des lettres
		        		        				
		        		        				
		        		        				
		        		        				boolean checkPrenom = fieldPrenom.getText().equals("");
		        						        boolean checkNom = fieldNom.getText().equals("");
		        						        boolean checkNS = fieldNumSecu.getText().equals("");
		        						        boolean checkDN = fieldDateNaissance.getText().equals("AAAA-MM-JJ") || fieldDateNaissance.getText().equals("");
		        						        
		        						        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		        						        
		        						        
		        						        Matcher matcherNum = pattern.matcher(numSecu);
		        						        
		        						       
		        						        boolean numInterdit = matcherNum.find();
		        		        				
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
		        						        else if(prenom.matches(".*\\d.*") /*|| prenom.matches("[^a-zA-Z]") */|| nom.matches(".*\\d.*") /*|| nom.matches("[^a-zA-Z]")*/)
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
		        						        else if(numInterdit)
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
			        		        				try
			        		        				{
			        		        					Patient.ajouterPatient(nouvPatient);
			        		        				}
			        		        				catch(IOException error)
			        		        				{
			        		        					
			        		        				}
			        		        				
			        		        				fen.setVisible(false);
			        		        				JOptionPane.showMessageDialog(null, "Nouveau patient ajout"+"\u00e9"+" !");
			        		        				fen.dispose();
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
		        								
		        		        				Patient search = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
		        		        				
		        		        				Patient[] listePatient = Patient.rechercherPatients(search);
		        		        				
		        		        				System.out.println(prenom+" "+nom+" "+numSecu+" "+dateNaissance);
		        		        				
		        		        				listePatient[0].setPrenom(fieldPrenom.getText());
		        		        				listePatient[0].setNom(fieldNom.getText());
		        		        				listePatient[0].setSecu(fieldNumSecu.getText());
		        		        				listePatient[0].setNaissance(LocalDate.parse(fieldDateNaissance.getText()));
		        		        				
		        		        				int idPatient = listePatient[0].getId();
		        		        				prenom =  listePatient[0].getPrenom();
		        		        				nom =listePatient[0].getNom();
		        		        				numSecu =listePatient[0].getSecu();
		        		        				
		        		        				
		        		        				Patient patientModif = new Patient(idPatient, prenom, nom, numSecu, listePatient[0].getNaissance());
		        		        				
		        		        				try
		    		        		        	{
		        		        					Patient.supprimerPatient(listePatient[row]);
		    		        		        		
		    		        		        		System.out.println("ok1");
		    		        		        	}
		    		        		        	catch(IOException error)
		    		        		        	{
		    		        		        		
		    		        		        	}
		        		        				try
		        		        				{
		        		        					Patient.ajouterPatient(patientModif);
		        		        				}
		        		        				catch(IOException error)
		        		        				{
		        		        					
		        		        				}
		        		        				
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
		        		        	
					        		
					        		
		        		        	
						        	/*if(Patient.rechercherPatient(aSupprimer))
						        	{
						        		System.out.println("ok2");
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
						        		tableModel = new DefaultTableModel(patients, header1);
						        		tablePatient.setModel(tableModel);
						        		tablePatient = new JTable(patients, header1);
						        		
							        	panel.add(new JScrollPane(tablePatient), gbc);
							        	frame.add(panel);
								        frame.setVisible(true);
						        		
						        	}
						        	else
						        	{
						        		System.out.println("oki");
						        		Patient[] listePatient = Patient.rechercherPatients(aSupprimer);
										
						        		nbPatients = listePatient.length;
						        		
						        		System.out.println(nbPatients);
						        		
						        		String[][] patients = new String[nbPatients][5];
								
						        		for(int i = 0 ; i < nbPatients ; i++)
						        		{	
						        			patients[i][0] = listePatient[i].getNom();
						        			patients[i][1] = listePatient[i].getPrenom();
						        			patients[i][2] = listePatient[i].getSecu();
											patients[i][3] = (listePatient[i].getNaissance()).toString();
										
										
						        		}
						        		//tableModel = new DefaultTableModel(patients, header1);
						        		tableModel.setRowCount(0);
						        		tablePatient.setModel(tableModel);
						        		
						        		tablePatient = new JTable(patients, header1);
						        		
							        	panel.add(new JScrollPane(tablePatient), gbc);
							        	frame.add(panel);
								        frame.setVisible(true);
						        	}*/
						        	
						        	
						        	//searchBarI.setText(null);
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
		        
		        JButton bouton = new JButton("Rechercher");
		        
		        JButton boutonAjout = new JButton("Ajouter...");
		        
		        JButton boutonModif = new JButton("Modifier...");
		        
		        JButton boutonSupr = new JButton("Supprimmer");
		        
		        
		        
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
		        
		       
		       
		        
		        
		        bouton.addActionListener(new ActionListener()
		        		{
		        			@Override
							public void actionPerformed(ActionEvent e) 
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
						      
						        
						        if(prenom.matches(".*\\d.*") || nom.matches(".*\\d.*"))
						        {
						        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des lettre pour les champs suivants :"+"\n"
									        +"Pr"+"\u00e9"+"nom, Nom !");
						        	dispose();
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
						        }
						        
						        if(numSecu.matches("[a-zA-Z]+"))
						        {
						        	JOptionPane.showMessageDialog(null, "Veuillez n'utilisez que des chiffres pour le champ suivant :"+"\n"
									        +"Numéro de s"+"\u00e9"+"curit"+"\u00e9"+" sociale !");
						        	dispose();
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
						        }
						        
						        try
						        {
						        	LocalDate.parse(dateNaissance);
						        }
						        catch(DateTimeParseException error)
						        {
						        	check = true;
						        	
						        	/* dispose() ne marche pas ici
						        	 
						        	JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqué : AAAA-MM-JJ !");
						        	dateNaissance = "";
						        	dispose();
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
						        	
						        	*/
						        }
						        
						        
						        
						        if(checkPrenom || checkNom || checkNS || checkDN)
								{
						        	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
									dispose();
									//searchBarI.setText(null);
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
								}
						        else if(dateNaissance.matches("[a-zA-Z]+"))
						        {
						        	JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqu"+"\u00e9"+" : AAAA-MM-JJ !");
						        	dispose();
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
						        }
						        else if(check)
						        {
						        	JOptionPane.showMessageDialog(null, "Veuillez utilisez le format indiqu"+"\u00e9"+" : AAAA-MM-JJ !");
						        	dateNaissance = "";
						        	dispose();
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
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
						        		
						        		TableColumnModel tcm = tablePatient.getColumnModel();
						        		
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
		        			
		        			
		        		}
		        
		        	);
		        
		        
		        tableConsultation = new JTable(consultation, header2);
		        
		        panel.add(new JScrollPane(tableConsultation), gbc);
		        
		        /* important
		         * 
		         * 
		         * tableModel.getValueAt(0, 3)
		         * 
		         * 
		         */
		        
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
        					
        		        	consultation = new String[nbPatients][5];
        					
        					for(int i = 0 ; i < nbPatients ; i++)
        					{
        						
        						consultation[i][0] = prenom;
        						consultation[i][1] = "DrHouse";
        						consultation[i][2] = "2022-02-01";
        						consultation[i][3] = "toux";
        						consultation[i][4] = "oui (pose pas de question)";
        						
        					}
        					
        					tableModelConsult = new DefaultTableModel(consultation, header2);
			        		tableConsultation.setModel(tableModelConsult);
			        		panel.add(new JScrollPane(tableConsultation), gbc);
			        		frame.add(panel);
					        frame.setVisible(true);
        				}
        			}
		        );
		        
		        
		        tableConsultation.addMouseListener(new MouseAdapter()
		        		{
		        			public void mousePressed(MouseEvent e)
		        			{
		        				Point p = e.getPoint();
		        				rowConsult = tableConsultation.rowAtPoint(p);
		        				System.out.println(rowConsult);
		        			}
		        		}
		        );
		        
		        tableConsultation.addFocusListener(new FocusListener()
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
		        								String prenom = fieldPrenom.getText();
		        		        				String nom = fieldNom.getText();
		        		        				String numSecu = fieldNumSecu.getText();
		        		        				String dateNaissance = fieldDateNaissance.getText();
		        		        				
		        		        				Patient nouvPatient = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
		        		        				try
		        		        				{
		        		        					Patient.ajouterPatient(nouvPatient);
		        		        				}
		        		        				catch(IOException error)
		        		        				{
		        		        					
		        		        				}
		        		        				
		        		        				fen.setVisible(false);
		        		        				JOptionPane.showMessageDialog(null, "Nouveau patient ajout"+"\u00e9"+" !");
		        		        				fen.dispose();
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
		        		        	prenom = tableConsultation.getModel().getValueAt(rowConsult, 0).toString();
		        		        	nom = tableConsultation.getModel().getValueAt(rowConsult, 1).toString();
		        		        	numSecu = tableConsultation.getModel().getValueAt(rowConsult, 2).toString();
		        		        	dateNaissance = tableConsultation.getModel().getValueAt(rowConsult, 3).toString();
		        		        
		        				
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
		        								
		        		        				Patient search = new Patient(prenom, nom, numSecu, LocalDate.parse(dateNaissance));
		        		        				
		        		        				Patient[] listePatient = Patient.rechercherPatients(search);
		        		        				
		        		        				System.out.println(prenom+" "+nom+" "+numSecu+" "+dateNaissance);
		        		        				
		        		        				listePatient[0].setPrenom(fieldPrenom.getText());
		        		        				listePatient[0].setNom(fieldNom.getText());
		        		        				listePatient[0].setSecu(fieldNumSecu.getText());
		        		        				listePatient[0].setNaissance(LocalDate.parse(fieldDateNaissance.getText()));
		        		        				
		        		        				int id = listePatient[0].getId();
		        		        				prenom =  listePatient[0].getPrenom();
		        		        				nom = listePatient[0].getNom();
		        		        				numSecu = listePatient[0].getSecu();
		        		        				
		        		        				
		        		        				Patient patientModif = new Patient(id, nom, prenom, numSecu, listePatient[0].getNaissance());
		        		        				
		        		        				try
		    		        		        	{
		        		        					Patient.supprimerPatient(search);
		    		        		        		
		    		        		        		System.out.println("ok1");
		    		        		        	}
		    		        		        	catch(IOException error)
		    		        		        	{
		    		        		        		
		    		        		        	}
		        		        				try
		        		        				{
		        		        					Patient.ajouterPatient(patientModif);
		        		        				}
		        		        				catch(IOException error)
		        		        				{
		        		        					
		        		        				}
		        		        				
		        		        				
		        		        				
		        		        				System.out.println();
		        		        				System.out.println();
		        		        				System.out.println();
		        		        				System.out.println();
		        		        				
		        		        				
		        		        				tableModel.fireTableDataChanged();
		        		        				tableConsultation.setModel(tableModel);
		        		        				
		        		        				fen.setVisible(false);
		        		        				JOptionPane.showMessageDialog(null, "Patient modifi"+"\u00e9"+" !");
		        		        				fen.dispose();
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
		        					System.out.println("la ligne "+rowConsult);
		        		        	prenom = tableConsultation.getModel().getValueAt(rowConsult, 0).toString();
		        		        	nom = tableConsultation.getModel().getValueAt(rowConsult, 1).toString();
		        		        	numSecu = tableConsultation.getModel().getValueAt(rowConsult, 2).toString();
		        		        	dateNaissance = tableConsultation.getModel().getValueAt(rowConsult, 3).toString();
		        		        	
		        		        	System.out.println(prenom+" "+nom+" "+numSecu+" "+LocalDate.parse(dateNaissance));
		        		        	
		        		        	
		        		        	Patient aSupprimer = new Patient("joe", "billy", "1234567", LocalDate.parse("2003-05-14"));
		        		        	
		        		        	System.out.println(Patient.rechercherPatient(aSupprimer));
		        		        	
		        		        	System.out.println("voici l'id du patient "+aSupprimer.getId());
		        		        	
		        		        	Patient[] listePatient = Patient.rechercherPatients(aSupprimer);
		        		        	
		        		        	
									
					        		nbPatients = listePatient.length;
					        		System.out.println("patients : "+listePatient.length);
					        		
					        		String[][] patients = new String[nbPatients][5];
							
					        		for(int i = 0 ; i < nbPatients ; i++)
					        		{	
					        			patients[i][0] = listePatient[i].getNom();
					        			patients[i][1] = listePatient[i].getPrenom();
					        			patients[i][2] = listePatient[i].getSecu();
										patients[i][3] = (listePatient[i].getNaissance()).toString();
									
									
							        
										
					        		}
					        		
					        		
		        		        
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
