import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;


import medical.Patient;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Interface extends JFrame implements ActionListener
{
	public int nbLignes = nbLine("patients.txt");  
	
	public int nbPatients = 0;
    
    public String[][] patient = new String[nbPatients][5];
    
    public String[] header = {"id", "Prénom", "Nom", "Num. Sécu", "Date de naissance"};
    
    JTable tablePatient;
    
    boolean check = false;
    
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
		        
		        JTextField searchBarI = new JTextField(2);
		        JTextField searchBarP = new JTextField(2); // recherche par prenom
		        JTextField searchBarN = new JTextField(2); // recherche par nom
		        JTextField searchBarNS = new JTextField(2); // recherche par numero de securité social
		        JTextField searchBarDN = new JTextField(0); // recherche par date de naissance
		        
		        JButton bouton = new JButton("Rechercher");
		        
		        JButton boutonAjout = new JButton("Ajouter...");
		        
		        JButton boutonSupr = new JButton("Suprimmer");
		        
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
		        
		        
		        JLabel sousTitre0 = new JLabel("ID :");
		        JLabel sousTitre1 = new JLabel("Prénom :");
		        JLabel sousTitre2 = new JLabel("Nom :");
		        JLabel sousTitre3 = new JLabel("Numéro de sécurité :");
		        JLabel sousTitre4 = new JLabel("Date de naissance :");
		        
		        
		        panel.setLayout(new GridBagLayout());
		        
		        GridBagConstraints gbc = new GridBagConstraints();
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.gridx = 0;
		        gbc.gridy = 0;
		        gbc.weightx = 1;
		        gbc.weighty = 1;
		        gbc.gridwidth = 5;
		        
		        gbc.anchor = GridBagConstraints.NORTHWEST;
		        
		        titre.setHorizontalAlignment(JLabel.CENTER);
		        
		        panel.add(titre, gbc);
		        
		        
		        // Sous titre id
		        
		        gbc.gridx = 0;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre0, gbc);
		        
		        
		        
		        gbc.gridwidth = 1;
		        // Sous titre prenom
		        
		        gbc.gridx = 1;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre1, gbc);
		        
		        // sous titre nom
		        gbc.gridx = 2;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 35, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;        
		        panel.add(sousTitre2, gbc);
		        
		        // sous titre numero
		        gbc.gridx = 3;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.insets = new Insets(0, 70, 0, 0);
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(sousTitre3, gbc);
		        
		        //sous titre date
		        gbc.gridx = 4;
		        gbc.gridy = 1;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        panel.add(sousTitre4, gbc);
		        
		        
		        // Search Bar id
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 0;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		       
		        panel.add(searchBarI, gbc);
		        
		        // Search Bar Prenom 
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 1;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		       
		        panel.add(searchBarP, gbc);
		        
		        // Search Bar Nom 
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 30, 0, 30);
		        gbc.gridx = 2;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        
		        panel.add(searchBarN, gbc);
		        
		        // Search Bar Numero de securité social
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 70, 0, 70);
		        gbc.gridx = 3;
		        gbc.gridy = 2;
		        gbc.weightx = 1;
		        gbc.anchor = GridBagConstraints.LINE_START;
		        
		        panel.add(searchBarNS, gbc);
		        
		        // Search Bar date de naissance
		        
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.insets = new Insets(0, 70, 0, 70);
		        gbc.gridx = 4;
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
		        
		        // bouton supression
		        
		        gbc2.gridx = 3;
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
		        				
		        				
		        				String id = searchBarI.getText();
								String prenom = searchBarP.getText();
								String nom = searchBarN.getText();
								String numSecu = searchBarNS.getText();
								String dateNaissance = searchBarDN.getText();
								
								
								
						        boolean checkId = searchBarI.getText().equals("");
						        boolean checkPrenom = searchBarP.getText().equals("");
						        boolean checkNom = searchBarN.getText().equals("");
						        boolean checkNS = searchBarNS.getText().equals("");
						        boolean checkDN = searchBarDN.getText().equals("AAAA-MM-JJ");
						        
						        if(checkId || checkPrenom || checkNom || checkNS || checkDN)
								{
						        	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
									dispose();
									searchBarI.setText(null);
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
						        	check = true;
						        	System.out.println(check);
								}
						        else
						        {
						        	System.out.println(dateNaissance);
								
						        	Patient search = new Patient(Integer.parseInt(id), prenom, nom, numSecu, LocalDate.parse(dateNaissance));
								
						        	if(Patient.rechercherPatient(search))
						        	{
						        		Patient[] listePatient = Patient.rechercherPatients(search);
									
						        		nbPatients = listePatient.length;
						        		
						        		String[][] patients = new String[nbPatients][5];
								
						        		for(int i = 0 ; i < nbPatients ; i++)
						        		{	
						        			System.out.println(i);
						        			patients[i][0] = Integer.toString(listePatient[i].getId());
						        			patients[i][1] = listePatient[i].getNom();
						        			patients[i][2] = listePatient[i].getPrenom();
						        			patients[i][3] = listePatient[i].getSecu();
											patients[i][4] = (listePatient[i].getNaissance()).toString();
										
										
								        
											
						        		}
						        		DefaultTableModel tableModel = new DefaultTableModel(patients, header);
						        		tablePatient.setModel(tableModel);
						        		tablePatient = new JTable(patients, header);
						        		
							        	panel.add(new JScrollPane(tablePatient), gbc);
							        	frame.add(panel);
								        frame.setVisible(true);
						        		
						        	}
						        	
						        	
						        	searchBarI.setText(null);
						        	searchBarP.setText(null);
						        	searchBarN.setText(null);
						        	searchBarNS.setText(null);
						        	searchBarDN.setText(null);
						        }
								
								
							}
		        			
		        			
		        		}
		        
		        		);
		        
		        System.out.println(check);
		        
		        tablePatient = new JTable(patient, header);
		        
		        panel.add(new JScrollPane(tablePatient), gbc);
		        
		        frame.add(panel);
		        frame.setVisible(true);  
		        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		        
		        break;
			}
			
			case 2:             // Le médecin
			{
				GridBagConstraints gbc = new GridBagConstraints(); 
		        
		        this.setLayout(new GridBagLayout());  
		        
		        setTitle("GesPat-M"+"\u00e9"+"decin"); 
		        
		        
		        
		        
		        setVisible(true);  
		        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		frame.setMinimumSize(new Dimension(950, 800));
		
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
