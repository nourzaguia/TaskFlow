# TaskFlow

## Équipe
- Abd El Illah Kasbadji
- Asma Noufel
- Fairouz Maameri
- Nour Zaguia 
- Yasmina Belambri

## Les fonctionnalités disponibles

| Option | Fonctionnalité | Description |
| --- | --- | --- |
| 1 | Ajouter une tâche | Créer une tâche avec un titre obligatoire, une priorité et une échéance facultative. |
| 2 | Afficher les tâches | Afficher l’identifiant, le titre, la priorité, l’échéance et le statut de chaque tâche. |
| 3 | Terminer une tâche | Marquer une tâche comme terminée à partir de son identifiant. |
| 4 | Modifier la priorité | Attribuer la priorité Basse, Normale ou Élevée à une tâche. |
| 5 | Gérer l’échéance | Définir, modifier ou retirer l’échéance d’une tâche. |
| 6 | Sauvegarder | Enregistrer toutes les tâches dans le fichier de données. |
| 0 | Quitter | Fermer l’application sans sauvegarde automatique. |


## La version du JDK et les prérequis
- JDK 26

## Les commandes pour compiler et exécuter l'application

Compilation : javac -encoding UTF-8 -d out src/*.java
Exécution : java -Dfile.encoding=UTF-8 -cp out Main

## Un exemple d'utilisation

| Étape | Action | Résultat attendu |
| --- | --- | --- |
| 1 | Lancer l’application. | Les tâches existantes sont chargées. |
| 2 | Saisir `1`. | Le formulaire d’ajout apparaît. |
| 3 | Saisir `Préparer la présentation` comme titre. | Le titre est accepté. |
| 4 | Saisir `Élevée` comme priorité. | La priorité est définie. |
| 5 | Saisir `2026-11-15` comme échéance. | La tâche est créée avec le statut « À faire ». |
| 6 | Saisir `2`. | La nouvelle tâche apparaît dans la liste. |
| 7 | Saisir `3`, puis l’identifiant `1`. | La tâche `1` passe au statut « Terminée ». |
| 8 | Saisir `6`. | Les tâches sont sauvegardées. |
| 9 | Saisir `0`. | L’application se ferme. |
| 10 | Relancer l’application et saisir `2`. | La tâche est toujours présente avec le statut « Terminée ». |

## L’emplacement et le format du fichier de données

Les tâches sont enregistrées dans le fichier `data/taches.tsv`. 

Chaque ligne représente une tâche et contient cinq champs séparés par des tabulations :
1. Identifiant.
2. Titre.
3. Priorité : `Basse`, `Normale` ou `Élevée`.
4. Échéance au format `AAAA-MM-JJ`, ou champ vide si aucune échéance.
5. Statut : `false` pour une tâche à faire et `true` pour une tâche terminée.

## Le fonctionnement de la sauvegarde et du rechargement

1. L’option `6` du menu permet de sauvegarder les tâches. Le dossier `data` et le fichier `taches.tsv` sont créés au besoin. Chaque sauvegarde remplace le contenu du fichier par la liste actuelle des tâches.
2. Une sauvegarde est également effectuée lorsque l’utilisateur choisit `0` pour quitter.
3. Au démarrage, l’application recharge automatiquement les tâches depuis `data/taches.tsv`.
4. Si le fichier n’existe pas, l’application démarre avec une liste vide. Si le fichier est illisible ou mal formé, elle affiche un message d’erreur et interrompt son démarrage.







