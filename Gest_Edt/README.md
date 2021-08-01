# ***************************************************

## BOULKHAIRY SY                                                                             
## MASTER 1 INTELLIGENCE  ARTIFICIELLE                                                   
## INE : ET041221                                                                          
## Mhtuw100@gmail.com                                                                                             
## ABDOULAYE FOFANA
## MASTER 1 INTELLIGENCE  ARTIFICIELLE
## INE : ET041121
## fofanaablaye@gmail.com

# ******************************************************

# Algorithme de génération d'emploi du temps

## Génération de l'excutable
utilisation de maven pour gérer ces dépendances. 
### Installation des dépendances du projet: 
mvn clean install
### Génération du fat-jar:
mvn clean package
Le fat sera généré dans target/
java -jar target/ent-0.0.1-SNAPSHOT-fat.jar args1 args2
```
args1 est le chemin du dossier contenant les imports
 
args2 est le chemin du dossier qui contiendra les exports

## Données
Le dossier des imports :
```
imports
 |_ classes
 |_ niveaux
 |_ professeurs
 |_ salles
 ```
 ### Exemples des données
 classe.json
 ```
 {
  "niveau": 1,
  "id": 1,
  "matieres": [
    {
      "idMatiere": 1, // id de la matière dans le niveau
      "idProfesseur": 4
    },
    {
      "idMatiere": 4,
      "idProfesseur": 6
    }
  ]
}
```
niveau.json
```
{
  "id": 1,
  "intitule": "ter",
  "matieres": [
    {
      "id": 1,
      "intitule": "Francais",
      "nbHeure": 4,
      "duree": 0
    },
    {
      "id": 4,
      "intitule": "Mat",
      "nbHeure": 4,
      "duree": 0
    }
  ]
}
```
professeur.json
```
{
  "nom": "angilasP",
  "id": 6,
  "edt": {
    "disponibilites": [],
    "coursList": []
  }
}
```
salle.json
```
{
  "nom": "salle1",
  "id": 3,
  "edt": {
    "disponibilites": [],
    "coursList": []
  }
}
```
