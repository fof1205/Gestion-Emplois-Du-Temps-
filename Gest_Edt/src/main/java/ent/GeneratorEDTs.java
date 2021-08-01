package ent;

import java.util.ArrayList;

import exception.AucuneSalleLibreException;
import exception.DispoNonCommuneExeption;
import main.java.models.Classe;
import main.java.models.Cours;
import main.java.models.Disponibilite;
import main.java.models.Matiere;
import main.java.models.Professeur;
import main.java.models.Salle;
import main.java.models.SalleAndDispo;


public class GeneratorEDTs {

	private int nombreClasseNonTraite = 0; // uilisé pour savoir quend finir et initialisé par setClasses((
	private ArrayList<Classe> classes = new ArrayList<Classe>();
	private ArrayList<Salle> salles = new ArrayList<Salle>();
	private ArrayList<Professeur> professeurs = new ArrayList<Professeur>();

	
	private boolean isFinish() {
		return nombreClasseNonTraite == 0;
	}

	
	public void genererEmploiDuTemps() throws Exception {
		while (isFinish() == false) {
			for (Classe classe : getClasses()) {
				if (classe.toutLesCoursPlacer() == false) {
					placerAleatoirementUnCours(classe);
				}
			}
		}
		System.out.println("Tout les cours sont placees !");
		for (Classe classe : getClasses()) {
			System.out.println(classe.getAllCours());
		}
	}

	private void placerAleatoirementUnCours(Classe classe) throws DispoNonCommuneExeption, AucuneSalleLibreException {
		Cours cours = trouverAleatoirementUnCoursAleatoirement(classe);
		positionnementDuCours(cours);
		if(classe.toutLesCoursPlacer()) {
			nombreClasseNonTraite=nombreClasseNonTraite-1;
		}
	}

	private Cours trouverAleatoirementUnCoursAleatoirement(Classe classe)
			throws DispoNonCommuneExeption, AucuneSalleLibreException {
		Matiere matiere = classe.getRandomMatiere();
		ArrayList<Disponibilite> dispoProfEtClasse = classe.getDisponibilite(matiere);
		SalleAndDispo salleAndDispo = findSalleLibre(dispoProfEtClasse, matiere.getDuree());
		Cours cours = new Cours(salleAndDispo.getDisponibilite().getJour(),
		salleAndDispo.getDisponibilite().getHeureDebut(), matiere.getDuree());
		cours.setMatiere(matiere);
		cours.setClasse(classe);
		cours.setProf(matiere.getProfesseur());
		cours.setSalle(salleAndDispo.getSalle());

		return cours;

	}
	private SalleAndDispo findSalleLibre(ArrayList<Disponibilite> dispoClasseProf, int duree)
			throws AucuneSalleLibreException {
		for (Salle salle : salles) {
                    ArrayList<Disponibilite> dispoSalle = salle.getSharedDisponibilite(dispoClasseProf);
                    for (Disponibilite dispo : dispoSalle) {
                        if (dispo.getDuree() >= duree) {
                            return new SalleAndDispo(salle, dispo);
                        }
                    }
		}
		throw new AucuneSalleLibreException();
	}
        
	private void positionnementDuCours(Cours cours) {
		Professeur professeur = cours.getProf();
		Salle salle = cours.getSalle();
		Classe classe = cours.getClasse();

		professeur.placerCours(cours);
		salle.placerCours(cours);
		classe.placerCours(cours);
	}

	public ArrayList<Classe> getClasses() {
		return classes;
	}
        
	public void setClasses(ArrayList<Classe> classes) {
		setNombreClasseNonTraite(classes.size());
		this.classes = classes;
	}

	public ArrayList<Salle> getSalles() {
		return salles;
	}

	public void setSalles(ArrayList<Salle> salles) {
		this.salles = salles;
	}

	public ArrayList<Professeur> getProfesseurs() {
		return professeurs;
	}

	public void setProfesseurs(ArrayList<Professeur> professeurs) {
		this.professeurs = professeurs;
	}

	public int getNombreClasseNonTraite() {
		return nombreClasseNonTraite;
	}

	public void setNombreClasseNonTraite(int nombreClasseNonTraite) {
		this.nombreClasseNonTraite = nombreClasseNonTraite;
	}

}
