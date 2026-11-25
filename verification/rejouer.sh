#!/usr/bin/env bash
# Rejoue tous les scénarios et compare la sortie à la référence de la version v1.0.0.
#
# Depuis la racine du dépôt :
#   ./verification/rejouer.sh               compare chaque scénario à sa référence
#   ./verification/rejouer.sh --references  produit les références (seulement avec le code v1.0.0)

javac -encoding UTF-8 -d out src/*.java || exit 1

for scenario in verification/scenarios/*.txt; do
    nom=$(basename "$scenario")

    # État de données connu : fichier absent, ou copie d'un fichier de verification/donnees/.
    rm -f data/taches.tsv
    case "$nom" in
        03-*|04-*|05-*|06-*|07-*)
            mkdir -p data
            cp verification/donnees/taches-v1.tsv data/taches.tsv ;;
        08-*)
            mkdir -p data
            cp verification/donnees/taches-mal-forme.tsv data/taches.tsv ;;
    esac

    java -Dstdout.encoding=UTF-8 -cp out Main < "$scenario" > sortie.txt

    if [ "$1" = "--references" ]; then
        cp sortie.txt "verification/references/$nom"
        echo "Référence produite : $nom"
    else
        echo "=== $nom"
        diff "verification/references/$nom" sortie.txt
    fi
done

# Ménage : fichiers produits par le script.
rm -rf out sortie.txt data/taches.tsv
