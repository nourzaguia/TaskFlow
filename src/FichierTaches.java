
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
                    + (tache.getEcheance() == null
                            ? ""
                            : tache.getEcheance()) + "\t"
                    + tache.estTerminee()
            );

            writer.newLine();
        }
    }
}
}