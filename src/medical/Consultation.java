package medical;

import java.time.LocalDate;

public class Consultation {
    private String nom_patient;
    private String nom_medecin;
    private LocalDate date_consultation;
    private String[] pathologies;

    public Consultation(String nom_patient, String nom_medecin, LocalDate date_consultation, String[] pathologies)
    {
        this.nom_patient = nom_patient;
        this.nom_medecin = nom_medecin;
        this.date_consultation = date_consultation;
        this.pathologies = pathologies;
    }
}
