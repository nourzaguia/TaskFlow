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
                    case "0" -> { quitter(); return; }
                    default -> System.out.println("Erreur : choisissez 1 ou 0.");
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

    private void quitter() {
        System.out.println("\nFermeture de TaskFlow.\nAu revoir !");
    }

    private static class Annulation extends RuntimeException { }
    private static class FinSaisie extends RuntimeException { }
}