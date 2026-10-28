# TaskFlow 

## 1. Objectif du produit

TaskFlow est une application Java en console qui permet à un utilisateur de gérer ses tâches et de suivre leur avancement. Elle permet de :

- créer et consulter des tâches ;
- marquer une tâche comme terminée ;
- modifier la priorité d'une tâche ;
- attribuer, modifier ou retirer une échéance ;
- sauvegarder les tâches dans un fichier local et les retrouver au démarrage suivant.

## 2. Organisation de l'équipe

Le projet suit la démarche agile Scrum : le travail est organisé en itérations (**Sprints**) afin de construire progressivement une version utilisable de l'application.

### 2.1. Rôles

| Rôle Scrum | Membre(s) | Responsabilités |
| --- | --- | --- |
| Product Owner | Yasmina Belambri | Définir l'objectif du produit, clarifier les besoins et ordonner le Product Backlog selon la valeur attendue. |
| Scrum Master | Asma Noufel | Faciliter l'application de Scrum, aider à lever les obstacles et favoriser l'amélioration du fonctionnement de l'équipe. |
| Developers | Abd El Illah Kasbadji, Asma Noufel, Fairouz Maameri, Nour Zaguia, Yasmina Belambri | Estimer le travail, préparer le Sprint Backlog, développer, vérifier, revoir et intégrer les fonctionnalités. |

Le Product Owner décide de l'ordre des besoins dans le Product Backlog. Les Developers déterminent comment les réaliser et la quantité de travail qu'ils prennent en charge pendant un Sprint. La qualité est une responsabilité collective : une fonctionnalité n'est terminée que si elle respecte la définition de terminé (section 5).

### 2.2. Règles de fonctionnement

| Élément | Fonctionnement |
| --- | --- |
| Communication | Réunions sur Microsoft Teams pour répartir le travail, poser des questions et signaler les difficultés. |
| Prise de décision | Discussion en équipe, en comparant les solutions selon les exigences du projet. À défaut de consensus, la décision est prise à la majorité et consignée. |
| Blocages | Signalés dans une Issue décrivant le problème et l'aide nécessaire. |
| Développement | Chaque travail est associé à une Issue et réalisé dans une branche distincte. |
| Revue | Une pull request est examinée par un autre membre, qui précise le code relu, les vérifications faites et les corrections nécessaires. |
| Intégration | Les corrections demandées sont apportées avant la fusion. La version intégrée est compilée et les fonctionnalités concernées sont vérifiées. |

### 2.3. Suivi du travail

Le tableau GitHub Projects utilise quatre états :

| État | Condition de passage |
| --- | --- |
| À faire | Travail identifié, non commencé. |
| En cours | Le membre commence le travail associé à l'Issue. |
| À vérifier | Le code est prêt à être relu et la pull request est ouverte. |
| Terminé | Le travail respecte la définition de terminé (section 5). |

## 3. Product Backlog

### 3.1. Ressources du projet

| Ressource | Lien |
| --- | --- |
| Dépôt du projet | https://github.com/nourzaguia/TaskFlow |
| Issues | https://github.com/nourzaguia/TaskFlow/issues |
| Pull requests | https://github.com/nourzaguia/TaskFlow/pulls |
| GitHub Projects | https://github.com/users/nourzaguia/projects/1 |

### 3.2. Récits utilisateurs

Les douze récits utilisateurs sont suivis dans les Issues GitHub, qui présentent les besoins, les critères d'acceptation et l'état du travail. Le tableau indique également leur réalisation dans le code fourni.

| Récit | Fonctionnalité | Issue | Réalisation dans le code |
| --- | --- | --- | --- |
| US01 | Ajouter une tâche | [#3](https://github.com/nourzaguia/TaskFlow/issues/3) | `Main.ajouter()` gère la saisie et `GestionTaches.ajouter()` crée la tâche avec un identifiant unique. |
| US02 | Afficher les tâches | [#4](https://github.com/nourzaguia/TaskFlow/issues/4) | `Main.afficher()` présente les informations en colonnes et traite la liste vide. |
| US03 | Terminer une tâche | [#5](https://github.com/nourzaguia/TaskFlow/issues/5) | `Main.terminer()` sélectionne la tâche et appelle `Tache.terminer()`. |
| US04 | Modifier la priorité | [#6](https://github.com/nourzaguia/TaskFlow/issues/6) | `Main.modifierPriorite()` utilise `Priorite.lire()` pour valider la valeur saisie. |
| US05 | Attribuer une échéance | [#7](https://github.com/nourzaguia/TaskFlow/issues/7) | L'option 5 permet de définir une date, validée par `Tache.lireDate()`. |
| US06 | Modifier une échéance | [#8](https://github.com/nourzaguia/TaskFlow/issues/8) | L'option 5 permet de remplacer la date existante. |
| US07 | Retirer une échéance | [#12](https://github.com/nourzaguia/TaskFlow/issues/12) | L'option 5 permet de retirer la date avec `setEcheance(null)`. |
| US08 | Sauvegarder les tâches | [#13](https://github.com/nourzaguia/TaskFlow/issues/13) | `FichierTaches.sauvegarder()` écrit les données ; `Main.sauvegarder()` affiche le résultat ou une erreur. |
| US09 | Recharger les tâches sauvegardées | [#14](https://github.com/nourzaguia/TaskFlow/issues/14) | `FichierTaches.charger()` reconstruit les tâches au démarrage. |
| US10 | Démarrer sans fichier de données | [#15](https://github.com/nourzaguia/TaskFlow/issues/15) | `Main.executer()` initialise une liste vide lorsque le fichier est absent. |
| US11 | Être informé d'un fichier illisible | [#16](https://github.com/nourzaguia/TaskFlow/issues/16) | Une erreur de lecture interrompt le démarrage et affiche un message. |
| US12 | Être informé d'un fichier mal formé | [#17](https://github.com/nourzaguia/TaskFlow/issues/17) | Le chargement vérifie la structure et les valeurs, puis interrompt le démarrage si le contenu est invalide. |

### 3.3. Tâches techniques

| Tâche technique | Issue | Objectif |
| --- | --- | --- |
| Initialiser la structure du projet | [#1](https://github.com/nourzaguia/TaskFlow/issues/1) | Mettre en place les dossiers et le point d'entrée de l'application. |
| Définir la structure d'une tâche | [#9](https://github.com/nourzaguia/TaskFlow/issues/9) | Définir les données et les règles de validation d'une tâche. |
| Corriger l'erreur de compilation dans `Main.java` | [#24](https://github.com/nourzaguia/TaskFlow/issues/24) | Corriger la structure des blocs pour rétablir la compilation. |
| Corriger le chargement et la sauvegarde des tâches | [#30](https://github.com/nourzaguia/TaskFlow/issues/30) | Corriger les erreurs de compilation, le rechargement des priorités et l'arrêt du démarrage lorsque le fichier est illisible ou mal formé. |

## 4. Priorités et estimations

### 4.1. Niveaux de priorité

Les priorités représentent l'ordre de réalisation retenu.

| Priorité | Signification |
| --- | --- |
| P1 | Construire les fonctions de base : créer et consulter les tâches. |
| P2 | Compléter la gestion des tâches avec le statut, les priorités et les échéances. |
| P3 | Conserver les données et gérer les erreurs liées aux fichiers. |

Les fonctionnalités P3 restent nécessaires au produit final ; leur position dépend de la disponibilité préalable de la structure des tâches et du choix du format de données.

### 4.2. Estimation des récits

Les estimations sont exprimées en **heures de travail**.

| Récit | Fonctionnalité | Priorité | Justification de la priorité | Estimation | Justification de l'estimation |
| --- | --- | --- | --- | --- | --- |
| US01 | Ajouter une tâche | P1 | Crée les données nécessaires aux autres fonctionnalités. | 4 h | Saisie, validations, annulation, identifiant unique et confirmation. |
| US02 | Afficher les tâches | P1 | Permet de consulter les tâches créées. | 2 h | Affichage en colonnes, nombre de tâches et traitement de la liste vide. |
| US03 | Terminer une tâche | P2 | Permet de suivre l'avancement du travail. | 2 h | Recherche par identifiant, changement de statut et tâche déjà terminée. |
| US04 | Modifier la priorité | P2 | Permet d'ajuster l'importance d'une tâche. | 2 h | Saisie, validation et conservation de l'ancienne valeur en cas d'erreur. |
| US05 | Attribuer une échéance | P2 | Permet de fixer une date de réalisation. | 2 h | Attribution, validation du format et vérification de la date. |
| US06 | Modifier une échéance | P2 | Permet d'adapter une date existante. | 1 h | Réutilisation de la recherche et de la validation des dates. |
| US07 | Retirer une échéance | P2 | Permet de conserver une tâche sans date. | 1 h | Retrait de la date et affichage de l'absence d'échéance. |
| US08 | Sauvegarder les tâches | P3 | Conserve les données et informe l'utilisateur si l'écriture échoue. | 5 h | Format TSV, écriture des données et traitement des erreurs. |
| US09 | Recharger les tâches sauvegardées | P3 | Permet de reprendre le travail après une fermeture. | 3 h | Lecture, reconstruction des tâches et calcul du prochain identifiant. |
| US10 | Démarrer sans fichier de données | P3 | Permet une première utilisation de l'application. | 1 h | Détection du fichier absent et initialisation d'une liste vide. |
| US11 | Être informé d'un fichier illisible | P3 | Évite de poursuivre avec des données impossibles à lire. | 2 h | Traitement de l'erreur, message et interruption du démarrage. |
| US12 | Être informé d'un fichier mal formé | P3 | Empêche le chargement de données incohérentes. | 3 h | Vérification de l'en-tête, des champs, des valeurs et des doublons. |
| **Total** | | | | **28 h** | |

Ce total reprend uniquement les estimations des récits. Il n'inclut pas les événements Scrum, les tâches techniques ni les corrections imprévues.

## 5. Définition de terminé

Un élément est **Terminé** lorsque toutes les conditions suivantes sont satisfaites.

| Condition | Vérification attendue |
| --- | --- |
| Critères d'acceptation respectés | Le comportement correspond aux critères définis dans l'Issue. |
| Compilation réussie | Les sources compilent avec le JDK retenu par l'équipe. |
| Vérifications réalisées | Le fonctionnement attendu, les cas particuliers et les erreurs pertinentes ont été vérifiés. |
| Absence de régression | Les fonctionnalités existantes concernées fonctionnent toujours après le changement. |
| Revue effectuée | Un autre membre a examiné la pull request et les corrections demandées ont été apportées. |
| Intégration effectuée | Les changements sont intégrés à la branche commune et vérifiés dans cette version. |
| Documentation actualisée | Les informations utiles sont mises à jour lorsque le comportement change. |
| Traçabilité disponible | L'Issue, la pull request et les résultats de vérification permettent de retrouver le travail réalisé. |

La présence d'une fonctionnalité dans le code ne suffit pas à prouver qu'elle respecte cette définition : l'élément n'est déplacé vers **Terminé** qu'après vérification de toutes les conditions.

## 6. Sprints : objectifs et résultats

### 6.1. Vue d'ensemble

| Sprint | Objectif | Récits | Incrément visé | Estimation |
| --- | --- | --- | --- | --- |
| 1 | Créer et consulter les tâches. | US01, US02 | Ajouter des tâches et afficher leurs informations. | 6 h |
| 2 | Suivre l'avancement et ajuster les priorités. | US03, US04 | Terminer une tâche et modifier sa priorité. | 4 h |
| 3 | Gérer les échéances. | US05, US06, US07 | Attribuer, modifier et retirer une échéance. | 4 h |
| 4 | Conserver et retrouver les tâches. | US08 à US12 | Sauvegarder, recharger et traiter les erreurs de fichier. | 14 h |

Les résultats détaillés des tests de la version vérifiée figurent dans [verification.md](verification.md).

### 6.2. Sprint 1 – Créer et consulter les tâches

**Objectif :** permettre à l'utilisateur de créer une tâche et de consulter la liste.

| Travail prévu | Résultat dans le code |
| --- | --- |
| Définir la structure d'une tâche. | La classe `Tache` contient l'identifiant, le titre, la priorité, l'échéance et le statut. |
| Ajouter une tâche. | Option 1. |
| Vérifier les saisies. | Titre obligatoire, priorités contrôlées, dates validées. |
| Permettre l'annulation. | La commande `/annuler` interrompt l'ajout. |
| Afficher les tâches. | Option 2 : affichage en colonnes, liste vide traitée. |

**Vérifications à consigner :** ajout valide, titre vide, priorité par défaut, échéance absente, valeurs invalides, annulation, affichage d'une liste vide.

**Bilan :** l'ajout et l'affichage fonctionnent et les valeurs par défaut sont appliquées. Un titre composé d'espaces est refusé et l'ajout peut être annulé. Deux tâches peuvent avoir le même titre avec des identifiants différents.
*Tests : scénarios 1 à 6 de `verification.md`. Version obtenue : [1](https://github.com/nourzaguia/TaskFlow/commit/4bb2c160b3c23c1087b82911d339b2e27d0c5404).*

### 6.3. Sprint 2 – Suivre l'avancement et ajuster les priorités

**Objectif :** permettre de terminer une tâche et d'adapter sa priorité.

| Travail prévu | Résultat dans le code |
| --- | --- |
| Sélectionner une tâche par identifiant. | `choisirTache()` vérifie la saisie et recherche la tâche. |
| Terminer une tâche. | Option 3 : attribue le statut « Terminée ». |
| Traiter une tâche déjà terminée. | Un message indique qu'aucune modification n'est nécessaire. |
| Modifier la priorité. | Option 4 : accepte Basse, Normale ou Élevée. |
| Refuser une priorité invalide. | Un message est affiché et la priorité actuelle est conservée. |

**Vérifications à consigner :** identifiant invalide ou inconnu, changement de statut, tâche déjà terminée, modification de priorité.

**Bilan :** une tâche peut être terminée ; une tâche déjà terminée et un identifiant inconnu sont signalés. La priorité peut être modifiée et une valeur invalide est refusée.
*Tests : scénarios 7 à 11. Version obtenue : [2](https://github.com/nourzaguia/TaskFlow/commit/378049f394b55a978318f4f9001f7ff24759102a).*

### 6.4. Sprint 3 – Gérer les échéances

**Objectif :** permettre d'attribuer, de modifier et de retirer une date.

| Travail prévu | Résultat dans le code |
| --- | --- |
| Attribuer une échéance. | Option 5 : définition d'une date. |
| Modifier une échéance. | Option 5 : remplacement d'une date existante. |
| Retirer une échéance. | Action 2 du sous-menu. |
| Valider les dates. | `Tache.lireDate()` vérifie le format et la validité de la date. |
| Annuler l'opération. | Action 0 du sous-menu : les données actuelles sont conservées. |

**Vérifications à consigner :** attribution, modification, retrait, annulation, format incorrect, date impossible, conservation de l'échéance après un refus.

**Bilan :** l'attribution, la modification et le retrait fonctionnent. Un format incorrect est refusé et une date passée valide est acceptée.
*Tests : scénarios 12 et 13. Version obtenue : [3](https://github.com/nourzaguia/TaskFlow/commit/44d3f4ca5317bf963d21669e0daee505228b4345).*

### 6.5. Sprint 4 – Conserver et retrouver les tâches

**Objectif :** conserver les tâches entre deux utilisations et gérer les erreurs de fichier.

| Travail prévu | Résultat dans le code |
| --- | --- |
| Sauvegarder les tâches. | Option 6 : enregistrement dans `data/taches.tsv`. |
| Réduire le risque d'écriture partielle. | Écriture dans un fichier temporaire avant remplacement du fichier cible. |
| Signaler un échec de sauvegarde. | Un message indique que les modifications n'ont pas été sauvegardées. |
| Recharger au démarrage. | Lecture automatique du fichier lorsqu'il existe. |
| Préserver les identifiants. | Les identifiants sont rechargés ; le prochain est calculé à partir du maximum existant. |
| Démarrer sans fichier. | Une liste vide est initialisée. |
| Traiter un fichier illisible ou mal formé. | Le démarrage est interrompu et le fichier n'est pas modifié. |

**Vérifications à consigner :** sauvegarde suivie d'un redémarrage, conservation des champs, nouvel identifiant distinct, fichier absent, erreur d'écriture, fichier invalide.

**Bilan :** la sauvegarde et le rechargement ont été essayés. Une nouvelle tâche reçoit un identifiant distinct après rechargement. Les fichiers mal formés ou illisibles bloquent le démarrage et une erreur de sauvegarde est signalée.
*Tests : scénarios 14 à 18. Version obtenue : 4.*

## 7. Contribution de chaque membre

| Membre | Récits réalisés | Pull requests ouvertes | Revues effectuées | Tâches techniques et corrections |
| --- | --- | --- | --- | --- |
| **Abd El Illah Kasbadji** | US08 ([#13](https://github.com/nourzaguia/TaskFlow/issues/13)), US09 ([#14](https://github.com/nourzaguia/TaskFlow/issues/14)), US10 ([#15](https://github.com/nourzaguia/TaskFlow/issues/15)) | [#26](https://github.com/nourzaguia/TaskFlow/pull/26) US08 Sauvegarder les tâches<br>[#27](https://github.com/nourzaguia/TaskFlow/pull/27) US09 Recharger les tâches<br>[#28](https://github.com/nourzaguia/TaskFlow/pull/28) US10 Démarrer sans fichier | [#29](https://github.com/nourzaguia/TaskFlow/pull/29) Fichiers illisibles et mal formés (US11, US12) | – |
| **Asma Noufel** | US05 ([#7](https://github.com/nourzaguia/TaskFlow/issues/7)), US06 ([#8](https://github.com/nourzaguia/TaskFlow/issues/8)), US07 ([#12](https://github.com/nourzaguia/TaskFlow/issues/12)) | [#21](https://github.com/nourzaguia/TaskFlow/pull/21) Attribuer une échéance<br>[#22](https://github.com/nourzaguia/TaskFlow/pull/22) Modifier une échéance<br>[#23](https://github.com/nourzaguia/TaskFlow/pull/23) Retirer une échéance | [#19](https://github.com/nourzaguia/TaskFlow/pull/19) US03 Terminer une tâche<br>[#20](https://github.com/nourzaguia/TaskFlow/pull/20) US04 Modifier la priorité<br>[#25](https://github.com/nourzaguia/TaskFlow/pull/25) Correction du bloc catch et des accolades dans `Main.java` | – |
| **Fairouz Maameri** | US10 ([#15](https://github.com/nourzaguia/TaskFlow/issues/15), US11 ([#16](https://github.com/nourzaguia/TaskFlow/issues/16)), US12 ([#17](https://github.com/nourzaguia/TaskFlow/issues/17)) | [#29](https://github.com/nourzaguia/TaskFlow/pull/29) Fichiers illisibles et mal formés (US11, US12) | [#26](https://github.com/nourzaguia/TaskFlow/pull/26) US08<br>[#27](https://github.com/nourzaguia/TaskFlow/pull/27) US09<br>[#28](https://github.com/nourzaguia/TaskFlow/pull/28) US10 | – |
| **Nour Zaguia** | US01 ([#3](https://github.com/nourzaguia/TaskFlow/issues/3)), US02 ([#4](https://github.com/nourzaguia/TaskFlow/issues/4)) | [#2](https://github.com/nourzaguia/TaskFlow/pull/2) Initialiser la structure du projet<br>[#10](https://github.com/nourzaguia/TaskFlow/pull/10) Ajouter les classes de base<br>[#11](https://github.com/nourzaguia/TaskFlow/pull/11) US01 Ajouter une tâche<br>[#18](https://github.com/nourzaguia/TaskFlow/pull/18) Ajouter l'affichage des tâches<br>[#25](https://github.com/nourzaguia/TaskFlow/pull/25) Correction du bloc catch et des accolades<br>[#31](https://github.com/nourzaguia/TaskFlow/pull/31) Correction du chargement et de la sauvegarde | [#21](https://github.com/nourzaguia/TaskFlow/pull/21), [#22](https://github.com/nourzaguia/TaskFlow/pull/22), [#23](https://github.com/nourzaguia/TaskFlow/pull/23) (échéances) | Structure d'une tâche : Issue [#9](https://github.com/nourzaguia/TaskFlow/issues/9), PR [#10](https://github.com/nourzaguia/TaskFlow/pull/10)<br>Erreur de compilation : Issue [#24](https://github.com/nourzaguia/TaskFlow/issues/24), PR [#25](https://github.com/nourzaguia/TaskFlow/pull/25)<br>Chargement et sauvegarde : PR [#31](https://github.com/nourzaguia/TaskFlow/pull/31) |
| **Yasmina Belambri** | US03 ([#5](https://github.com/nourzaguia/TaskFlow/issues/5)), US04 ([#6](https://github.com/nourzaguia/TaskFlow/issues/6)) | [#19](https://github.com/nourzaguia/TaskFlow/pull/19) US03 Terminer une tâche<br>[#20](https://github.com/nourzaguia/TaskFlow/pull/20) US04 Modifier la priorité | [#11](https://github.com/nourzaguia/TaskFlow/pull/11) US01 Ajouter une tâche<br>[#18](https://github.com/nourzaguia/TaskFlow/pull/18) Ajouter l'affichage des tâches | – |
