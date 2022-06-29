package fr.isika.cda18.projet1.entites;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Noeud {

	private Stagiaire stagiaire;
	private int filsGauche;
	private int filsDroit;

	// Constructeur
	public Noeud(Stagiaire stagiaire, int filsGauche, int filsDroit) {
		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public int getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	// Méthodes spécifiques
	public static void ecritureBinaire(RandomAccessFile raf, Noeud noeud) throws IOException {
		try {
			raf.writeChars(noeud.getStagiaire().agrandirNom());
			raf.writeChars(noeud.getStagiaire().agrandirPrenom());
			raf.writeChars(noeud.getStagiaire().getDepartement());
			raf.writeChars(noeud.getStagiaire().agrandirPromo());
			raf.writeChars(noeud.getStagiaire().getAnnee());
			raf.writeInt(noeud.getFilsGauche());
			raf.writeInt(noeud.getFilsDroit());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void lectureBinaire(RandomAccessFile raf) throws IOException {
		try {
			raf.seek(0);
			for (int j = 0; j < raf.length() / Stagiaire.TAILLE_OBJET_OCTET; j++) {
				String nom = "";
				String prenom = "";
				String departement = "";
				String promo = "";
				String annee = "";
				for (int i = 0; i < Stagiaire.TAILLE_NOM; i++) {
					nom += raf.readChar();
				}
				for (int i = 0; i < Stagiaire.TAILLE_PRENOM; i++) {
					prenom += raf.readChar();
				}
				for (int i = 0; i < Stagiaire.TAILLE_DEPARTEMENT; i++) {
					departement += raf.readChar();
				}
				for (int i = 0; i < Stagiaire.TAILLE_PROMO; i++) {
					promo += raf.readChar();
				}
				for (int i = 0; i < Stagiaire.TAILLE_ANNEE; i++) {
					annee += raf.readChar();
				}
				int filsGauche = raf.readInt();
				int filsDroit = raf.readInt();
				System.out
						.println("Nom :" + nom.trim() + "\t Prenom :" + prenom.trim() + "\t Departement :" + departement
								+ "\t Promotion :" + promo.trim() + "\t Annee : " + annee + filsDroit + filsGauche);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "*****Lecture Noeud*****\n"+ stagiaire + "\t Index Gauche " + filsGauche + "\t Index Droit " + filsDroit;
	}

	public static Noeud lectureNoeud(RandomAccessFile raf) throws IOException {
		String nom = "";
		String prenom = "";
		String departement = "";
		String promo = "";
		String annee = "";
		for (int i = 0; i < Stagiaire.TAILLE_NOM; i++) {
			nom += raf.readChar();
		}
		for (int i = 0; i < Stagiaire.TAILLE_PRENOM; i++) {
			prenom += raf.readChar();
		}
		for (int i = 0; i < Stagiaire.TAILLE_DEPARTEMENT; i++) {
			departement += raf.readChar();
		}
		for (int i = 0; i < Stagiaire.TAILLE_PROMO; i++) {
			promo += raf.readChar();
		}
		for (int i = 0; i < Stagiaire.TAILLE_ANNEE; i++) {
			annee += raf.readChar();
		}
		int filsGauche = raf.readInt();
		int filsDroit = raf.readInt();
		Stagiaire stg = new Stagiaire(nom.trim(), prenom.trim(), departement.trim(), promo.trim(), annee.trim());
		Noeud noeud = new Noeud(stg, filsGauche, filsDroit);
		return noeud;
	}

	public static void chercherStagiaire(String StagiaireAChercher, RandomAccessFile raf) throws IOException {
		Noeud racine = Noeud.lectureNoeud(raf);
		try {
			if (racine.getStagiaire().getNom().compareTo(StagiaireAChercher) > 0) {
				if (racine.getFilsGauche() != -1) {
					raf.seek(racine.getFilsGauche() * Stagiaire.TAILLE_OBJET_OCTET);
					chercherStagiaire(StagiaireAChercher, raf);
				}
			} else if (racine.getStagiaire().getNom().compareTo(StagiaireAChercher) < 0) {
				if (racine.getFilsDroit() != -1) {
					raf.seek(racine.getFilsDroit() * Stagiaire.TAILLE_OBJET_OCTET);
					chercherStagiaire(StagiaireAChercher, raf);
				}
			} else {
				System.out.println(racine);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void affichageInfixe(RandomAccessFile raf) throws IOException {
		if (this.getFilsGauche() != -1) {
			raf.seek(this.getFilsGauche() * Stagiaire.TAILLE_OBJET_OCTET);
			Noeud filsGauche = lectureNoeud(raf);
			filsGauche.affichageInfixe(raf);
		}
		System.out.println(this);
		if (this.getFilsDroit() != -1) {
			raf.seek(this.getFilsDroit() * Stagiaire.TAILLE_OBJET_OCTET);
			Noeud filsDroit = lectureNoeud(raf);
			filsDroit.affichageInfixe(raf);
		}
	}
}