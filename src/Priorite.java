// L’énumération limite les priorités aux trois valeurs autorisées.
public enum Priorite {
    BASSE("Basse"), NORMALE("Normale"), ELEVEE("Élevée");

    private final String libelle;

    Priorite(String libelle) {
        this.libelle = libelle;
    }

    public static Priorite lire(String texte) {
        for (Priorite priorite : values()) {
            if (priorite.libelle.equalsIgnoreCase(texte.trim())) {
                return priorite;
            }
        }
        throw new IllegalArgumentException("la priorité doit être Basse, Normale ou Élevée.");
    }

    @Override
    public String toString() {
        return libelle;
    }
}