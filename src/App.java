import java.time.LocalDate;

import medical.Consultation;
import medical.Patient; 




public class App
{
	
    public static void main(String[] args) throws Exception {
    	
    	
    	new Fenetre();
    	
    	
        Patient.initialiserPatients();

        Patient moi = new Patient("adrian", "lagasse", "9849839", LocalDate.of(2003, 3, 9));
        
        Patient pasMoi = new Patient("jean", "mi", "1122334", LocalDate.of(2003, 5, 14));

        Patient.ajouterPatient(moi);
        Patient.ajouterPatient(moi);
        
        String[] patho = {"toux"};
        
        Consultation consult = new Consultation(1, "DrHouse", LocalDate.of(2021, 1, 5), patho);
        
        Consultation.ajouterConsultation(consult);
        
        
    }
}
