import java.io.IOException;
import java.time.LocalDate;

import medical.Consultation;
import medical.Patient; 

public class App
{
    public static void main(String[] args) throws IOException {
   	
    	new Fenetre();
    	
        Patient.initialiserPatients();

        Patient moi = new Patient("lagasse", "adrian", "9849839", LocalDate.of(2003, 3, 9));
        
        Patient pasMoi = new Patient("jean", "adrian", "1122334", LocalDate.of(2003, 5, 14));

        Patient.ajouterPatient(moi);
        Patient.ajouterPatient(pasMoi);
        
        String[] patho = {"toux", "rhume", "artrose", "grippe", "covid"};
        
        Consultation.initialiserConsultation();
        
        Consultation consult = new Consultation(2, "DrHouse", LocalDate.of(2021, 1, 5), "fauteuil roulant", patho);
        
        Consultation consultation = new Consultation(5, "DrHouse", LocalDate.of(2021, 5, 5), "fauteuil roulant", patho);

        Consultation.ajouterConsultation(consult);
        Consultation.ajouterConsultation(consultation);
        
        //Consultation.ajouterConsultation(consultation);
        
    }
}