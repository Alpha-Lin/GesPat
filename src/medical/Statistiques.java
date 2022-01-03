package medical;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Statistiques {
    private Path statistiques_path;
    private int nb_patients;
    private int nb_medecin;
    private int nb_app_oct;
    private int nb_path;
    private int nb_consultations;

    public Statistiques(Path path, int nb_patients, int nb_medecin, int nb_app_oct, int nb_path, int nb_consultations)
    {
        statistiques_path = path;
        this.nb_patients = nb_patients;
        this.nb_medecin = nb_medecin;
        this.nb_app_oct = nb_app_oct;
        this.nb_path = nb_path;
        this.nb_consultations = nb_consultations;
    }

    public static Statistiques importStats(Path path) throws IOException
    {
        String[] stats = Files.readAllBytes(path).toString().split(";");

        return new Statistiques(path, Integer.parseInt(stats[0]), Integer.parseInt(stats[1]), Integer.parseInt(stats[2]), Integer.parseInt(stats[3]), Integer.parseInt(stats[4]));
    }

    public void exportStats() throws IOException
    {
        Files.write(statistiques_path, (nb_patients + ";" + nb_medecin + ";" + nb_app_oct + ";" + nb_path + ";" + nb_consultations).getBytes());
    }

    public int getNbPatients()
    {
        return nb_patients;
    }

    public int getNbMedecin()
    {
        return nb_medecin;
    }

    public int getNbAppOct()
    {
        return nb_app_oct;
    }

    public int getNbPathologies()
    {
        return nb_path;
    }

    public int getNbConsultations()
    {
        return nb_consultations;
    }

    public int getNbPatientsParMedecin()
    {
        return nb_patients / nb_medecin;
    }

    public int getNbPathologiesParPatient()
    {
        return nb_path / nb_patients;
    }
}
