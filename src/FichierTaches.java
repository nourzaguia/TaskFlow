
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FichierTaches {
        public static class FormatInvalideException extends IOException {
        public FormatInvalideException(String message) {
            super(message);
        }

        public FormatInvalideException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    private final Path chemin;

    public FichierTaches(Path chemin) {
        this.chemin = chemin;
    }
public void sauvegarder(List<Tache> taches) throws IOException {
    Path dossier = chemin.getParent();

    if (dossier != null) {
        Files.createDirectories(dossier);
    }

    try (BufferedWriter writer = Files.newBufferedWriter(
            chemin, StandardCharsets.UTF_8)) {

        for (Tache tache : taches) {
            writer.write(
                tache.getId() + "\t"
                + tache.getTitre() + "\t"
                + tache.getPriorite() + "\t"
                + (tache.getEcheance() == null ? "" : tache.getEcheance().toString()) + "\t"
                + tache.estTerminee()
                    
            );

            writer.newLine();
        }
    }
}
public List<Tache> charger() throws IOException {
    List<Tache> taches = new ArrayList<>();

    if (!Files.exists(chemin)) {
        return taches;
    }

    try (BufferedReader reader = Files.newBufferedReader(
            chemin, StandardCharsets.UTF_8)) {

        String ligne;

        while ((ligne = reader.readLine()) != null) {
            String[] champs = ligne.split("\t", -1);

            if (champs.length != 5) {
                continue;
            }

            try {
                int id = Integer.parseInt(champs[0]);
                String titre = champs[1];
                Priorite priorite = Priorite.valueOf(champs[2]);

                LocalDate echeance = champs[3].isEmpty()
                        ? null
                        : Tache.lireDate(champs[3]);

                boolean terminee = Boolean.parseBoolean(champs[4]);

                taches.add(new Tache(
                        id, titre, priorite, echeance, terminee));
            } catch (IllegalArgumentException e) {
                System.err.println(
                        "Ligne ignorée : données de tâche invalides.");
            }
        }
    }

    return taches;
} }