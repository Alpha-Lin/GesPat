package medical;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Patient {
    private static Path patients_path = Paths.get("patients.txt");
    private static List<Patient> patients = new ArrayList<>();
    private static int lastId;

    private int id;
    private String nom;
    private String prenom;
    private String numero_secu;
    private LocalDate date_naissance;

    public Patient(String nom, String prenom, String numero_secu, LocalDate date_naissance)
    {
        id = ++lastId;
        this.nom = nom;
        this.prenom = prenom;
        this.numero_secu = numero_secu;
        this.date_naissance = date_naissance;
    }

    public Patient(int id, String nom, String prenom, String numero_secu, LocalDate date_naissance)
    {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numero_secu = numero_secu;
        this.date_naissance = date_naissance;
    }

    // Permet d'intialiser la liste de patients
    public static void initialiserPatients() throws IOException
    {
        if(Files.exists(patients_path))
        {
            Files.readAllLines(patients_path).forEach((String patient) -> {
                String[] patient_infos = patient.split(";");
                patients.add(new Patient(Integer.parseInt(patient_infos[0]), patient_infos[1], patient_infos[2], patient_infos[3], LocalDate.parse(patient_infos[4])));
            });
    
            lastId = patients.get(patients.size() - 1).getId();
        }else
            lastId = 1;
    }

    /*-------------- Recherche les patients en fonction d'un attribut --------------*/

    private static List<Patient> rechercherPatientId(List<Patient> patients_prematch, int id)
    {
        if(id == 0)
            return patients_prematch;

        List<Patient> patients_match = new ArrayList<>();

        patients_prematch.forEach((Patient p) -> {
            if(id == p.id)
                patients_match.add(p);
        });

        return patients_match;
    }
    
    private static List<Patient> rechercherPatientNom(List<Patient> patients_prematch, String nom)
    {
        if(nom.equals(""))
            return patients_prematch;

        List<Patient> patients_match = new ArrayList<>();

        patients_prematch.forEach((Patient p) -> {
            if(nom.equals(p.nom))
                patients_match.add(p);
        });

        return patients_match;
    }

    private static List<Patient> rechercherPatientPrenom(List<Patient> patients_prematch, String prenom)
    {
        if(prenom.equals(""))
            return patients_prematch;

        List<Patient> patients_match = new ArrayList<>();

        patients_prematch.forEach((Patient p) -> {
            if(prenom.equals(p.prenom))
                patients_match.add(p);
        });

        return patients_match;
    }

    private static List<Patient> rechercherPatientSecu(List<Patient> patients_prematch, String numero_secu)
    {
        if(numero_secu.equals(""))
            return patients_prematch;

        List<Patient> patients_match = new ArrayList<>();

        patients_prematch.forEach((Patient p) -> {
            if(numero_secu.equals(p.numero_secu))
                patients_match.add(p);
        });

        return patients_match;
    }

    private static List<Patient> rechercherPatientNaissance(List<Patient> patients_prematch, LocalDate date_naissance)
    {
        if(date_naissance == null)
            return patients_prematch;

        List<Patient> patients_match = new ArrayList<>();

        patients_prematch.forEach((Patient p) -> {
            if(date_naissance.isEqual(p.date_naissance))
                patients_match.add(p);
        });

        return patients_match;
    }

    /*------------------------------------------------------------------------------*/

    // Retourne la liste des patients correspondant au patient recherche
    public static Patient[] rechercherPatients(Patient p)
    {
        List<Patient> patients_match = rechercherPatientNom(patients, p.nom);
        patients_match = rechercherPatientPrenom(patients_match, p.prenom);
        patients_match = rechercherPatientSecu(patients_match, p.numero_secu);
        patients_match = rechercherPatientNaissance(patients_match, p.date_naissance);

        return patients_match.toArray(new Patient[patients_match.size()]);
    }

    // Recherche si le patient est inscrit
    public static boolean rechercherPatient(Patient p_recherche)
    {
        for(Patient p : patients)
        {
            if(p_recherche.nom.equals(p.nom) && p_recherche.prenom.equals(p.prenom) && p_recherche.numero_secu.equals(p.numero_secu) && p_recherche.date_naissance.isEqual(p.date_naissance))
                return true;
        }

        return false;
    }

    // Inscrit le patient à condition qu'il ne le soit pas déjà
    public static void ajouterPatient(Patient p) throws IOException
    {        
        if(!rechercherPatient(p)){
            Files.write(patients_path, (p.id + ";".concat(p.nom).concat(";").concat(p.prenom).concat(";").concat(p.numero_secu).concat(";").concat(p.date_naissance.toString()).concat("\n")).getBytes(), Files.exists(patients_path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
            patients.add(p);
        }
    }

    // Supprime un patient de la liste
    public static void supprimerPatient(Patient p) throws IOException
    {
        int index = patients.indexOf(p);
        if(index != -1)
        {
            patients.remove(index);
            List<String> patients_string = Files.readAllLines(patients_path);
            patients_string.remove(index);
            Files.write(patients_path, patients_string);
        }        
    }

    /*--------------------------- Accesseurs et mutateurs ---------------------------*/

    public int getId()
    {
        return id;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom) throws IOException
    {
        setSomething(1, nom);

        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom) throws IOException
    {
        setSomething(2, prenom);

        this.prenom = prenom;
    }

    public String getSecu()
    {
        return numero_secu;
    }

    public void setSecu(String numero_secu) throws IOException
    {
        setSomething(3, numero_secu);

        this.numero_secu = numero_secu;
    }

    public LocalDate getNaissance()
    {
        return date_naissance;
    }

    public void setNaissance(LocalDate date_naissance) throws IOException
    {
        setSomething(4, date_naissance.toString());

        this.date_naissance = date_naissance;
    }

    public static int getNbPatients()
    {
        return patients.size();
    }

    private void setSomething(int v_index, String value) throws IOException
    {
        int index = patients.indexOf(this);

        List<String> patients_string = Files.readAllLines(patients_path,  StandardCharsets.ISO_8859_1);
        String[] modif = patients_string.get(index).split(";");

        modif[v_index] = value;

        patients_string.set(index, modif[0].concat(";").concat(modif[1]).concat(";").concat(modif[2]).concat(";").concat(modif[3]).concat(";").concat(modif[4]));
        Files.write(patients_path, patients_string);
    }

    /*-------------------------------------------------------------------------------*/
}
