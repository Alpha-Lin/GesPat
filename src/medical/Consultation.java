package medical;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Consultation {
    private static Path consultations_path = Paths.get("consultations.txt");
    private static List<Consultation> consultations = new ArrayList<>();

    private int id_patient;
    private String nom_medecin;
    private LocalDate date_consultation;
    private String appareil;
    private String octroie;
    private String[] pathologies;

    public Consultation(int id_patient, String nom_medecin, LocalDate date_consultation, String appareil, String[] pathologies)
    {
        this.id_patient = id_patient;
        this.nom_medecin = nom_medecin;
        this.date_consultation = date_consultation;
        this.appareil = appareil;
        this.octroie = "En attente";
        this.pathologies = pathologies;
    }
    
    public static void initialiserConsultation() throws IOException
    {
        if(Files.exists(consultations_path))
        {
            Files.readAllLines(consultations_path).forEach((String consultation) -> {
                String[] consultation_infos = consultation.split(";");
                consultations.add(new Consultation(Integer.parseInt(consultation_infos[0]), consultation_infos[1], LocalDate.parse(consultation_infos[2]), consultation_infos[3], consultation_infos[4].split(",")));
            });
    
        }
    }
    
    
    // Inscrit la consultation dans le fichier
    public static void ajouterConsultation(Consultation c) throws IOException
    {
    	if(!rechercherConsultation(c))
    	{
    		Files.write(consultations_path, (c.id_patient + ";".concat(c.nom_medecin).concat(";").concat(c.date_consultation.toString()).concat(";").concat(c.appareil).concat(";").concat(pathologiesToString(c.pathologies)).concat("\n")).getBytes(), Files.exists(consultations_path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
	        consultations.add(c);
    	} 
    }
    
    

    // Supprime un patient de la liste
    public static void supprimerConsultation(Consultation c) throws IOException
    {
        int index = consultations.indexOf(c);
        if(index != -1)
        {
            consultations.remove(index);
            List<String> consultations_string = Files.readAllLines(consultations_path);
            consultations_string.remove(index);
            Files.write(consultations_path, consultations_string);
        }        
    }
    
    /*-------------- Recherche les patients en fonction d'un attribut --------------*/

    private static List<Consultation> rechercherConsultationId(List<Consultation> consultation_prematch, int id)
    {
        if(id == 0)
            return consultation_prematch;

        List<Consultation> patients_match = new ArrayList<>();

        consultation_prematch.forEach((Consultation c) -> {
            if(id == c.id_patient)
                patients_match.add(c);
        });

        return patients_match;
    }
    
    public static Consultation[] rechercherConsultations(int id)
    {
        List<Consultation> consultation_match = rechercherConsultationId(consultations, id);

        return consultation_match.toArray(new Consultation[consultation_match.size()]);
    }

    
    
    public static boolean rechercherConsultation(Consultation c_recherche)
    {
        for(Consultation c : consultations)
        {
            if(c_recherche.id_patient == (c.id_patient) && c_recherche.nom_medecin.equals(c.nom_medecin) && c_recherche.date_consultation.equals(c.date_consultation) && c_recherche.appareil.equals(c.appareil) && pathologiesToString(c_recherche.pathologies).equals(pathologiesToString(c.pathologies)))
                return true;
        }

        return false;
    }
    
    
    
    

    // Converti la liste des pathologies en un format récupérable par CSV
    private static String pathologiesToString(String[] pathologies)
    {
        StringBuilder pathoString = new StringBuilder();  

        for(String pathologie : pathologies)
            pathoString.append(pathologie.concat(","));

        pathoString.deleteCharAt(pathoString.length() - 1);

        return pathoString.toString();
    }

    public int getIdPatient()
    {
        return id_patient;
    }

    public String getMedecin()
    {
        return nom_medecin;
    }

    public void setMedecin(String nom_medecin)
    {
        this.nom_medecin = nom_medecin;
    }

    public LocalDate getDateConsultation()
    {
        return date_consultation;
    }

    public void setDateCultation(LocalDate date_consultation)
    {
        this.date_consultation = date_consultation;
    }

    public String getAppareil()
    {
    	return appareil;
    }
    
    public void setAppariel(String appareil)
    {
    	this.appareil = appareil;
    }
    
    public String getOctroie()
    {
    	return octroie;
    }
    
    public void setOctroie(String octroie)
    {
    	this.octroie = octroie;
    }
    
    public String[] getPathologies()
    {
        return pathologies;
    }

    public void setPathologies(String[] pathologies)
    {
        this.pathologies = pathologies;
    }
}
