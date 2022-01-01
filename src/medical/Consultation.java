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
    private String[] pathologies;

    public Consultation(int id_patient, String nom_medecin, LocalDate date_consultation, String[] pathologies)
    {
        this.id_patient = id_patient;
        this.nom_medecin = nom_medecin;
        this.date_consultation = date_consultation;
        this.pathologies = pathologies;
    }

    // Inscrit la consultation dans le fichier
    public static void ajouterConsultation(Consultation c) throws IOException
    {
        Files.write(consultations_path, (c.id_patient + ";".concat(c.nom_medecin).concat(";").concat(c.date_consultation.toString()).concat(";").concat(pathologiesToString(c.pathologies)).concat("\n").getBytes(), StandardOpenOption.APPEND);
        consultations.add(c);
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

    public String[] getPathologies()
    {
        return pathologies;
    }

    public void setPathologies(String[] pathologies)
    {
        this.pathologies = pathologies;
    }
}
