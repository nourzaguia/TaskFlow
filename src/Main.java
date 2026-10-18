import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private final Scanner clavier = new Scanner(System.in, "UTF-8");
    private GestionTaches gestion;

    public static void main(String[] args) {
        new Main().executer();
    }

    private void executer() {
        System.out.println("TASKFLOW — Gestion des tâches\n");
        gestion = new GestionTaches(new ArrayList<>());
        afficherMenu();

        try {
            while (true) {
                switch (saisir("Votre choix : ")) {
                    case "1" -> ajouter();
                    case "2" -> afficher();
                    case "3" -> terminer();
                    
                    case "5" -> attribuerEcheance();

                    case "0" -> { quitter(); return; }
                    default -> System.out.println("Erreur : choisissez 0, 1, 2 ou 3.");
                }
                System.out.println();
            }
        } catch (FinSaisie e) {
            quitter();
        }
    }

    private void afficherMenu() {
        System.out.println("""

                1. Ajouter une tâche
                2. Afficher les tâches
                3. Marquer une tâche comme terminée
                5. Attribuer une échéance
                0. Quitter
                """);

                
    }

    private String saisir(String message) {
        System.out.print(message);
        if (!clavier.hasNextLine()) {
            throw new FinSaisie();
        }
        return clavier.nextLine().trim();
    }

    private String saisirAjout(String message) {
        String valeur = saisir(message);
        if (valeur.equalsIgnoreCase("/annuler")) {
            throw new Annulation();
        }
        return valeur;
    }

    private void ajouter() {
        System.out.println("\nAJOUTER UNE TÂCHE\n");

        try {
            String titre;
            while (true) {
                titre = saisirAjout("Titre : ");
                if (!titre.isBlank()) break;
                System.out.println("Erreur : le titre est obligatoire.");
                System.out.println("Veuillez saisir un titre ou annuler l’ajout avec /annuler.");
            }

            System.out.println("\nPriorité : Basse, Normale ou Élevée");
            System.out.println("Appuyez sur Entrée pour choisir Normale.");

            Priorite priorite;
            while (true) {
                String valeur = saisirAjout("Votre priorité : ");
                try {
                    priorite = valeur.isEmpty() ? Priorite.NORMALE : Priorite.lire(valeur);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Erreur : " + e.getMessage());
                    System.out.println("Corrigez la saisie ou tapez /annuler.");
                }
            }

            System.out.println("\nÉchéance au format AAAA-MM-JJ");
            System.out.println("Appuyez sur Entrée pour ne pas préciser d’échéance.");

            LocalDate echeance;
            while (true) {
                String valeur = saisirAjout("Votre échéance : ");
                try {
                    echeance = valeur.isEmpty() ? null : Tache.lireDate(valeur);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Erreur : " + e.getMessage());
                    System.out.println("Corrigez la saisie ou tapez /annuler.");
                }
            }

            // La création attend la validation de tous les champs :
            // une annulation ne crée donc aucune tâche.
            Tache tache = gestion.ajouter(titre, priorite, echeance);

            System.out.println("\nTâche ajoutée avec succès.\n");
            System.out.println("Identifiant : " + tache.getId());
            System.out.println("Titre       : " + tache.getTitre());
            System.out.println("Priorité    : " + tache.getPriorite());
            System.out.println("Échéance    : " + tache.afficherEcheance());
            System.out.println("Statut      : " + tache.getStatut());

        } catch (Annulation e) {
            System.out.println("Ajout annulé. Aucune tâche n’a été ajoutée.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void afficher() {
        if (gestion.getTaches().isEmpty()) {
            System.out.println("\nAucune tâche à afficher.");
            return;
        }

        System.out.println("\nLISTE DES TÂCHES\n");

        // La largeur suit le titre le plus long pour conserver des colonnes lisibles.
        int largeur = 55;
        for (Tache tache : gestion.getTaches()) {
            largeur = Math.max(largeur, tache.getTitre().length());
        }

        String format = "%-6s %-" + largeur + "s %-10s %-13s %s%n";

        System.out.printf(format, "ID", "Titre", "Priorité", "Échéance", "Statut");

        for (Tache tache : gestion.getTaches()) {
            System.out.printf(
                    format,
                    tache.getId(),
                    tache.getTitre(),
                    tache.getPriorite(),
                    tache.afficherEcheance(),
                    tache.getStatut()
            );
        }

        System.out.println("\nNombre de tâches : " + gestion.getTaches().size());
    }

    private void quitter() {
        System.out.println("\nFermeture de TaskFlow.\nAu revoir !");
    }

    private static class Annulation extends RuntimeException { }
    private static class FinSaisie extends RuntimeException { }

    private void terminer() {
        System.out.println("\nMARQUER UNE TÂCHE COMME TERMINÉE\n");

        if (gestion.getTaches().isEmpty()) {
            System.out.println("Aucune tâche à terminer.");
            return;
        }

        Tache tache = choisirTache();

        if (tache == null) {
            return;
        }

        if (tache.estTerminee()) {
            System.out.println("Cette tâche est déjà terminée.");
            return;
        }

        tache.terminer();

        System.out.println("La tâche « " + tache.getTitre()
                + " » est maintenant terminée.");
    }
    
private void attribuerEcheance() {
    System.out.println("\nATTRIBUER UNE ÉCHÉANCE\n");

    Tache tache = choisirTache();
    if (tache == null) return;

    if (tache.getEcheance() != null) {
        System.out.println("Cette tâche possède déjà une échéance.");
        return;
    }

    try {
        String valeur = saisir("Échéance (AAAA-MM-JJ) : ");
        LocalDate date = Tache.lireDate(valeur);

        tache.setEcheance(date);

        System.out.println("L’échéance de la tâche "
                + tache.getId() + " est maintenant : " + date + ".");

    } catch (IllegalArgumentException e) {
        System.out.println("Erreur : " + e.getMessage());
        System.out.println("Aucune échéance n’a été attribuée.");
    }
}

    private Tache choisirTache() {
        try {
            int id = Integer.parseInt(saisir("Identifiant de la tâche : "));
            if (id <= 0) {
                throw new NumberFormatException();
            }

            Tache tache = gestion.trouver(id);
            if (tache == null) {
                System.out.println("Erreur : identifiant inexistant.");
            }
            return tache;
        } catch (NumberFormatException e) {
            System.out.println("Erreur : l'identifiant doit être un entier positif.");
            return null;
        }
    }
}