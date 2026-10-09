import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Cette classe regroupe les cinq informations d’une tâche.
public class Tache {
    private final int id;
    private final String titre;
    private Priorite priorite;
    private LocalDate echeance;
    private boolean terminee;

    public Tache(int id, String titre, Priorite priorite, LocalDate echeance, boolean terminee) {
        if (id <= 0 || titre == null || titre.isBlank() || priorite == null
                || titre.contains("\n") || titre.contains("\r")) {
            throw new IllegalArgumentException("Données de tâche invalides.");
        }
        this.id = id;
        this.titre = titre.trim();
        this.priorite = priorite;
        this.echeance = echeance;
        this.terminee = terminee;
    }

    public static LocalDate lireDate(String texte) {
        if (!texte.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            throw new IllegalArgumentException("« " + texte + " » n’est pas un format de date valide.\n"
                    + "Le format attendu est AAAA-MM-JJ.");
        }

        // Après le format, LocalDate vérifie que la date existe dans le calendrier.
        try {
            return LocalDate.parse(texte);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(texte + " n’est pas une date valide.");
        }
    }

    public int getId() { return id; }
    public String getTitre() { return titre; }
    public Priorite getPriorite() { return priorite; }
    public LocalDate getEcheance() { return echeance; }
    public boolean estTerminee() { return terminee; }
    public String getStatut() { return terminee ? "Terminée" : "À faire"; }
    public String afficherEcheance() { return echeance == null ? "Aucune" : echeance.toString(); }
}