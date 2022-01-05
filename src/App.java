import java.io.IOException;
import java.time.LocalDate;

import medical.Consultation;
import medical.Patient; 

public class App
{
    public static void main(String[] args) throws IOException {
   	
    	Patient.initialiserPatients(); // Initialise les patients du fichier patients.txt
        
        Consultation.initialiserConsultation(); // Initialise les consultations du fichier consultations.txt
    	
    	new Fenetre();
        
    }
}