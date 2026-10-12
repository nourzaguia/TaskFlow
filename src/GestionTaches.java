import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestionTaches {
    private final List<Tache> taches;
    private long prochainId;

    public GestionTaches(List<Tache> tachesChargees) {
        taches = new ArrayList<>(tachesChargees);
        prochainId = 1;
        for (Tache tache : taches) {
            prochainId = Math.max(prochainId, (long) tache.getId() + 1);
        }
    }

    public Tache ajouter(String titre, Priorite priorite, LocalDate echeance) {
        if (prochainId > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("aucun nouvel identifiant n’est disponible.");
        }

        // Le compteur distingue les tâches même lorsque leurs titres sont identiques.
        // false attribue le statut À faire à la nouvelle tâche.
        Tache tache = new Tache((int) prochainId, titre, priorite, echeance, false);
        taches.add(tache);
        prochainId++;
        return tache;
    }

    public List<Tache> getTaches() {
        return Collections.unmodifiableList(taches);
    }
}