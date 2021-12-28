import java.time.LocalDate;
import medical.Patient; 




public class App
{
	
    public static void main(String[] args) throws Exception {
    	
    	
    	new Fenetre();
    	
    	
        Patient.initialiserPatients();

        Patient moi = new Patient(1, "adrian", "lagasse", "9849839", LocalDate.of(2003, 3, 9));

        Patient.ajouterPatient(moi);
        Patient.ajouterPatient(moi);
    }
}
