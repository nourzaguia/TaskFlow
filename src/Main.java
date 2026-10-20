import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;
public class Main {
    private final Scanner clavier = new Scanner(System.in, "UTF-8");
    private GestionTaches gestion;
    private final FichierTaches fichier =
        new FichierTaches(Path.of("data", "taches.tsv"));
    public static void main(String[] args) {
        new Main().executer();
    }

    private void executer() {
        System.out.println("TASKFLOW — Gestion des tâches\n");
        try {
    gestion = new GestionTaches(fichier.charger());
} catch (FichierTaches.FormatInvalideException e) {
    System.out.println("Erreur de format : " + e.getMessage());
    System.out.println("TaskFlow ne peut pas démarrer pour protéger vos données.");
    return;
} catch (IOException | SecurityException e) {
    System.out.println("Erreur de lecture : impossible d'accéder au fichier de données.");
    System.out.println("TaskFlow ne peut pas démarrer pour protéger vos données.");
    return;
}
        afficherMenu();

        try {
            while (true) {
                switch (saisir("Votre choix : ")) {
                    case "1" -> ajouter();
                    case "2" -> afficher();
                    case "3" -> terminer();
                    case "4" -> modifierPriorite();
                    case "5" -> gererEcheance();
                    case "6" -> sauvegarder();
                    case "0" -> {
    if (sauvegarder()) {
        quitter();
        return;
    }
    System.out.println("La fermeture est annulée. Vous pouvez réessayer.");
}
                    default -> System.out.println("Erreur : choisissez une option de 0 à 6.");
                }
                System.out.println();
            }
        } catch (FinSaisie e) {
    if (sauvegarder()) {
        quitter();
    } else {
        System.out.println("Les données n'ont pas été sauvegardées.");
        System.out.println("Le programme reste ouvert pour protéger vos données.");
    }
}
    }


private boolean sauvegarder() {
    System.out.println("\nSAUVEGARDE DES DONNÉES\n");

    try {
        fichier.sauvegarder(gestion.getTaches());
        System.out.println("Sauvegarde réussie.");
        return true;
    } catch (IOException | SecurityException e) {
        System.out.println("Erreur : impossible de sauvegarder les données.");
        return false;
    }
}


    private void afficherMenu() {
        System.out.println("""
                1. Ajouter une tâche
                2. Afficher les tâches
                3. Marquer une tâche comme terminée
                4. Modifier la priorité d’une tâche
                5. Gérer l’échéance d’une tâche
                6. Sauvegarder les données
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

private void gererEcheance() {
        System.out.println("\nGÉRER L’ÉCHÉANCE\n");
        Tache tache = choisirTache();
        if (tache == null) return;
        System.out.println("Échéance actuelle : " + tache.afficherEcheance());
        System.out.println("\n1. Définir ou modifier l’échéance\n2. Retirer l’échéance\n0. Annuler\n");
        switch (saisir("Votre action : ")) {
            case "1" -> {
                try {
                    LocalDate date = Tache.lireDate(saisir("Nouvelle échéance (AAAA-MM-JJ) : "));
                    tache.setEcheance(date);
                    System.out.println("\nL’échéance de la tâche " + tache.getId()
                            + " est maintenant : " + date + ".");
                } catch (IllegalArgumentException e) {
                    System.out.println("\nErreur : " + e.getMessage());
                    System.out.println("L’échéance actuelle est conservée.");
                }
            }
                        case "2" -> {
                tache.setEcheance(null);
                System.out.println("\nL’échéance de la tâche " + tache.getId() + " a été retirée.");
            }
            case "0" -> System.out.println("Modification annulée.");
            default -> System.out.println("Erreur : action invalide. L’échéance actuelle est conservée.");
        }
    }
    private void sauvegarder() {
    System.out.println("\nSAUVEGARDE DES DONNÉES\n");

    try {
        fichier.sauvegarder(gestion.getTaches());

        System.out.println("Sauvegarde réussie : "
                + gestion.getTaches().size()
                + " tâches enregistrées.");

    } catch (IOException | SecurityException e) {
        System.out.println("Erreur : impossible d'écrire dans le fichier de données.");
        System.out.println("Les modifications n'ont pas été sauvegardées.");
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

    private void modifierPriorite() {
        System.out.println("\nMODIFIER LA PRIORITÉ\n");

        Tache tache = choisirTache();
        if (tache == null) {
            return;
        }

        Priorite ancienne = tache.getPriorite();
        System.out.println("Tâche : " + tache.getTitre());
        System.out.println("Priorité actuelle : " + ancienne);
        System.out.println("Priorités possibles : Basse, Normale ou Élevée.");

        try {
            Priorite nouvelle = Priorite.lire(saisir("Nouvelle priorité : "));
            tache.setPriorite(nouvelle);
            System.out.println("La priorité de la tâche " + tache.getId()
                    + " a été modifiée : " + ancienne + " → " + nouvelle + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
            System.out.println("La priorité actuelle est conservée.");
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

}
