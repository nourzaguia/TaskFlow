## 1. Objectif du produit

TaskFlow est une application Java en console permettant à un utilisateur de gérer ses tâches et de suivre leur avancement.

L’objectif du produit est de proposer une application simple permettant de :

- Créer et consulter des tâches.
- Marquer une tâche comme terminée.
- Modifier sa priorité.
- Attribuer, modifier ou retirer une échéance.
- Sauvegarder les tâches dans un fichier local.
- Retrouver les tâches au démarrage suivant.

## 2. Organisation de l’équipe selon Scrum

Le projet utilise une démarche agile Scrum. Le travail est organisé en itérations, appelées **Sprints**, afin de construire progressivement une version utilisable de l’application.

| Responsabilité Scrum | Membre | Responsabilités dans le projet |
| --- | --- | --- |
| Product Owner | Yasmina Belambri | Définir l’objectif du produit, clarifier les besoins et ordonner le Product Backlog selon la valeur attendue. |
| Scrum Master | Nour Zaguia | Faciliter l’application de Scrum, aider à résoudre les obstacles et favoriser l’amélioration du fonctionnement de l’équipe. |
| Developers | Abd El Illah Kasbadji / Asma Noufel / Fairouz Maameri / Nour Zaguia / Yasmina Belambri | Estimer le travail, préparer le Sprint Backlog, développer, vérifier, revoir et intégrer les fonctionnalités. |

Le Product Owner décide de l’ordre des besoins dans le Product Backlog. Les Developers déterminent comment les réaliser et la quantité de travail qu’ils peuvent prendre en charge pendant un Sprint.
La qualité est une responsabilité collective : les fonctionnalités doivent respecter la définition de terminé avant d’être considérées comme terminées.

## 3. Product Backlog


| Ressource | Lien |
| --- | --- |
| Dépôt du projet | https://github.com/nourzaguia/TaskFlow |
| Issues | https://github.com/nourzaguia/TaskFlow/issues |
| Pull requests | https://github.com/nourzaguia/TaskFlow/pulls |
| GitHub Projects | https://github.com/users/nourzaguia/projects/1 |

### 3.2. Récits utilisateurs et correspondance avec le code

Les identifiants ci-dessous correspondent aux douze récits visibles dans les captures GitHub. Le traitement d’un échec de sauvegarde est inclus dans **US08**.

| Récit | Fonctionnalité | Issue | Réalisation dans le code fourni |
| --- | --- | --- | --- |
| US01 | Ajouter une tâche | [#3](https://github.com/nourzaguia/TaskFlow/issues/3) | `Main.ajouter()` gère la saisie et `GestionTaches.ajouter()` crée la tâche avec un identifiant unique. |
| US02 | Afficher les tâches | [#4](https://github.com/nourzaguia/TaskFlow/issues/4) | `Main.afficher()` présente les informations en colonnes et traite la liste vide. |
| US03 | Terminer une tâche | [#5](https://github.com/nourzaguia/TaskFlow/issues/5) | `Main.terminer()` sélectionne la tâche et appelle `Tache.terminer()`. |
| US04 | Modifier la priorité | [#6](https://github.com/nourzaguia/TaskFlow/issues/6) | `Main.modifierPriorite()` utilise `Priorite.lire()` pour vérifier la valeur saisie. |
| US05 | Attribuer une échéance | [#7](https://github.com/nourzaguia/TaskFlow/issues/7) | L’option 5 permet de définir une date, validée par `Tache.lireDate()`. |
| US06 | Modifier une échéance | [#8](https://github.com/nourzaguia/TaskFlow/issues/8) | L’option 5 permet de remplacer la date existante. |
| US07 | Retirer une échéance | [#12](https://github.com/nourzaguia/TaskFlow/issues/12) | L’option 5 permet de retirer la date avec `setEcheance(null)`. |
| US08 | Sauvegarder les tâches | [#13](https://github.com/nourzaguia/TaskFlow/issues/13) | `FichierTaches.sauvegarder()` écrit les données. `Main.sauvegarder()` affiche le résultat ou une erreur. |
| US09 | Recharger les tâches sauvegardées | [#14](https://github.com/nourzaguia/TaskFlow/issues/14) | `FichierTaches.charger()` reconstruit les tâches au démarrage. |
| US10 | Démarrer sans fichier de données | [#15](https://github.com/nourzaguia/TaskFlow/issues/15) | `Main.executer()` initialise une liste vide lorsque le fichier est absent. |
| US11 | Être informé d’un fichier illisible | [#16](https://github.com/nourzaguia/TaskFlow/issues/16) | Une erreur de lecture interrompt le démarrage et affiche un message. |
| US12 | Être informé d’un fichier mal formé | [#17](https://github.com/nourzaguia/TaskFlow/issues/17) | Le chargement vérifie la structure et les valeurs, puis interrompt le démarrage si le contenu est invalide. |

### 3.3. Tâches techniques

| Tâche technique | Issue | Objectif |
| --- | --- | --- |
| Initialiser la structure du projet | [#1](https://github.com/nourzaguia/TaskFlow/issues/1) | Mettre en place les dossiers et le point d’entrée de l’application. |
| Définir la structure d’une tâche | [#9](https://github.com/nourzaguia/TaskFlow/issues/9) | Définir les données et les règles de validation d’une tâche. |
| Corriger l’erreur de compilation dans `Main.java` | [#24](https://github.com/nourzaguia/TaskFlow/issues/24) | Corriger la structure des blocs pour rétablir la compilation. |

## 4. Priorités et estimations

### 4.1. Principes de priorisation

Les priorités représentent l’ordre de réalisation retenu :

| Priorité | Signification |
| --- | --- |
| P1 | Construire les fonctions de base permettant de créer et de consulter les tâches. |
| P2 | Compléter la gestion des tâches avec le statut, les priorités et les échéances. |
| P3 | Conserver les données et gérer les erreurs liées aux fichiers. |

Les fonctionnalités P3 restent nécessaires au produit final. Leur position dépend de la disponibilité préalable de la structure des tâches et du choix du format de données.
Les estimations sont exprimées en **heures de travail estimées**. 

### 4.2. Estimation des récits

| Récit | Fonctionnalité | Priorité et justification | Estimation et justification |
| --- | --- | --- | --- |
| US01 | Ajouter une tâche | **P1** — Permet de créer les données nécessaires aux autres fonctionnalités. | **4 h** — Saisie, validations, annulation, identifiant unique et confirmation. |
| US02 | Afficher les tâches | **P1** — Permet de consulter les tâches créées. | **2 h** — Affichage en colonnes, nombre de tâches et traitement de la liste vide. |
| US03 | Terminer une tâche | **P2** — Permet de suivre l’avancement du travail. | **2 h** — Recherche par identifiant, changement de statut et tâche déjà terminée. |
| US04 | Modifier la priorité | **P2** — Permet d’ajuster l’importance d’une tâche. | **2 h** — Saisie, validation et conservation de l’ancienne valeur en cas d’erreur. |
| US05 | Attribuer une échéance | **P2** — Permet de fixer une date de réalisation. | **2 h** — Attribution, validation du format et vérification de la date. |
| US06 | Modifier une échéance | **P2** — Permet d’adapter une date existante. | **1 h** — Réutilisation de la recherche et de la validation des dates. |
| US07 | Retirer une échéance | **P2** — Permet de conserver une tâche sans date. | **1 h** — Retrait de la date et affichage de l’absence d’échéance. |
| US08 | Sauvegarder les tâches et signaler un échec | **P3** — Permet de conserver les données et d’informer l’utilisateur si l’écriture échoue. | **5 h** — Format TSV, écriture temporaire, remplacement du fichier et traitement des erreurs. |
| US09 | Recharger les tâches sauvegardées | **P3** — Permet de reprendre le travail après une fermeture. | **3 h** — Lecture, reconstruction des tâches et calcul du prochain identifiant. |
| US10 | Démarrer sans fichier de données | **P3** — Permet une première utilisation de l’application. | **1 h** — Détection du fichier absent et initialisation d’une liste vide. |
| US11 | Être informé d’un fichier illisible | **P3** — Évite de poursuivre avec des données impossibles à lire. | **2 h** — Traitement de l’erreur, message et interruption du démarrage. |
| US12 | Être informé d’un fichier mal formé | **P3** — Empêche le chargement de données incohérentes. | **3 h** — Vérification de l’en-tête, des champs, des valeurs et des doublons. |
| **Total** | | | **28 h** |

Ce total reprend les estimations des récits. Il n’inclut pas séparément les événements Scrum, les tâches techniques et les corrections imprévues.

## 5. Définition de terminé

Un élément est considéré comme **Terminé** lorsque toutes les conditions suivantes sont satisfaites.

| Condition | Vérification attendue |
| --- | --- |
| Critères d’acceptation respectés | Le comportement correspond aux critères définis dans l’Issue. |
| Compilation réussie | Les sources compilent avec le JDK retenu par l’équipe. |
| Vérifications réalisées | Le fonctionnement attendu, les cas particuliers et les erreurs pertinentes ont été vérifiés. |
| Absence de régression constatée | Les fonctionnalités existantes concernées fonctionnent toujours après le changement. |
| Revue effectuée | Un autre membre a examiné la pull request et les corrections demandées ont été apportées. |
| Intégration effectuée | Les changements sont intégrés à la branche commune et vérifiés dans cette version. |
| Documentation actualisée | Les informations utiles sont mises à jour lorsque le comportement change. |
| Traçabilité disponible | L’Issue, la pull request et les résultats de vérification permettent de retrouver le travail réalisé. |

La présence d’une fonctionnalité dans le code ne suffit pas à prouver qu’elle respecte cette définition. L’élément est déplacé vers **Terminé** après vérification de toutes les conditions.

## 6. Objectifs et résultats des itérations

La répartition suivante reprend les quatre itérations du document de travail.

### 6.1. Synthèse des Sprints

| Sprint | Objectif | Récits sélectionnés | Incrément visé | Estimation |
| --- | --- | --- | --- | --- |
| 1 | Créer et consulter les tâches. | US01 et US02 | Une application permettant d’ajouter des tâches et d’afficher leurs informations. | 6 h |
| 2 | Suivre l’avancement et ajuster les priorités. | US03 et US04 | Une application permettant de terminer une tâche et de modifier sa priorité. | 4 h |
| 3 | Gérer les échéances. | US05, US06 et US07 | Une application permettant d’attribuer, de modifier et de retirer une échéance. | 4 h |
| 4 | Conserver et retrouver les tâches. | US08 à US12 | Une application permettant de sauvegarder, de recharger et de traiter les erreurs de fichier. | 14 h |

### 6.2. Sprint 1 — Créer et consulter les tâches

**Objectif du Sprint :** permettre à l’utilisateur de créer une tâche et de consulter la liste.

| Travail prévu | Résultat observable dans le code fourni |
| --- | --- |
| Définir la structure d’une tâche. | La classe `Tache` contient l’identifiant, le titre, la priorité, l’échéance et le statut. |
| Ajouter une tâche. | L’ajout est disponible avec l’option 1. |
| Vérifier les saisies. | Le titre est obligatoire, les priorités sont contrôlées et les dates sont validées. |
| Permettre l’annulation. | La commande `/annuler` interrompt l’ajout. |
| Afficher les tâches. | L’option 2 présente les tâches en colonnes et traite le cas vide. |

**Vérifications à consigner :** ajout valide, titre vide, priorité par défaut, échéance absente, valeurs invalides, annulation et affichage d’une liste vide.

**Bilan réel du Sprint :** 

### 6.3. Sprint 2 — Suivre l’avancement et ajuster les priorités

**Objectif du Sprint :** permettre de terminer une tâche et d’adapter sa priorité.

| Travail prévu | Résultat observable dans le code fourni |
| --- | --- |
| Sélectionner une tâche par identifiant. | `choisirTache()` vérifie la saisie et recherche la tâche. |
| Terminer une tâche. | L’option 3 attribue le statut « Terminée ». |
| Traiter une tâche déjà terminée. | Un message indique qu’aucune modification n’est nécessaire. |
| Modifier la priorité. | L’option 4 accepte Basse, Normale ou Élevée. |
| Refuser une priorité invalide. | Un message est affiché et la priorité actuelle est conservée. |

**Vérifications à consigner :** identifiant invalide ou inconnu, changement de statut, tâche déjà terminée et modification de priorité.

**Bilan réel du Sprint :** 

### 6.4. Sprint 3 — Gérer les échéances

**Objectif du Sprint :** permettre d’attribuer, de modifier et de retirer une date.

| Travail prévu | Résultat observable dans le code fourni |
| --- | --- |
| Attribuer une échéance. | L’option 5 permet de définir une date. |
| Modifier une échéance. | La même option permet de remplacer une date existante. |
| Retirer une échéance. | L’action 2 du sous-menu retire la date. |
| Valider les dates. | `Tache.lireDate()` vérifie le format et la validité de la date. |
| Annuler l’opération. | L’action 0 du sous-menu conserve les données actuelles. |

**Vérifications à consigner :** attribution, modification, retrait, annulation, format incorrect, date impossible et conservation de l’échéance après un refus.

**Bilan réel du Sprint :** 

### 6.5. Sprint 4 — Conserver et retrouver les tâches

**Objectif du Sprint :** conserver les tâches entre deux utilisations et gérer les erreurs de fichier.

| Travail prévu | Résultat observable dans le code fourni |
| --- | --- |
| Sauvegarder les tâches. | L’option 6 enregistre les données dans `data/taches.tsv`. |
| Réduire le risque d’écriture partielle. | L’écriture passe par un fichier temporaire avant le remplacement du fichier cible. |
| Signaler un échec de sauvegarde. | Un message indique que les modifications n’ont pas été sauvegardées. |
| Recharger au démarrage. | Le fichier est lu automatiquement lorsqu’il existe. |
| Préserver les identifiants. | Les identifiants sont rechargés et le prochain est calculé à partir du maximum existant. |
| Démarrer sans fichier. | Une liste vide est initialisée. |
| Traiter un fichier illisible ou mal formé. | Le démarrage est interrompu et le fichier n’est pas modifié. |

**Vérifications à consigner :** sauvegarde suivie d’un redémarrage, conservation des champs, nouvel identifiant distinct, fichier absent, erreur d’écriture et fichier invalide.

**Bilan réel du Sprint :** 

## 7. Ajustements apportés à la démarche

### 7.1. Ajustements visibles dans les éléments fournis (À faire)

## 8. Contribution de chaque membre (À FAIRE)
