package fr.isika.cda18.projet1.entites;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Noeud implements InterfaceTailles {

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
		return "*****Lecture Noeud*****\n" + stagiaire + "\t Index Gauche " + filsGauche + "\t Index Droit "
				+ filsDroit;
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

			e.printStackTrace();
		}
	}

	public void affichageInfixe(RandomAccessFile raf, List<Stagiaire> stagiaires) throws IOException {
		if (this.getFilsGauche() != -1) {
			raf.seek(this.getFilsGauche() * Stagiaire.TAILLE_OBJET_OCTET);
			Noeud filsGauche = lectureNoeud(raf);
			filsGauche.affichageInfixe(raf, stagiaires);
		}
		System.out.println(this);
		stagiaires.add(this.stagiaire);

		if (this.getFilsDroit() != -1) {
			raf.seek(this.getFilsDroit() * Stagiaire.TAILLE_OBJET_OCTET);
			Noeud filsDroit = lectureNoeud(raf);
			filsDroit.affichageInfixe(raf, stagiaires);
		}
	}

	public List<Stagiaire> chercherValeur(Stagiaire valeurAChercher, RandomAccessFile raf, List<Stagiaire> stagiaires)
			throws IOException {

		try {
			if (this.getFilsGauche() != -1) {
				raf.seek(this.getFilsGauche() * Stagiaire.TAILLE_OBJET_OCTET);
				Noeud filsGauche = lectureNoeud(raf);
				filsGauche.chercherValeur(valeurAChercher, raf, stagiaires);
			}
			int critere = 0;
			int verification = 0;
			if (!(valeurAChercher.getPromotion().equals(""))) {
				critere++;
				if (this.getStagiaire().getPromotion().equals(valeurAChercher.getPromotion())) {
					verification++;
					if (critere == verification) {
						stagiaires.add(this.getStagiaire());
						System.out.println(this);
					}
				}
			}
			if (!(valeurAChercher.getDepartement().equals(""))) {
				critere++;
				if (this.getStagiaire().getDepartement().equals(valeurAChercher.getDepartement())) {
					verification++;
					if (critere == verification) {
						stagiaires.add(this.getStagiaire());
						System.out.println(this);
					}
				}
			}
			if (!(valeurAChercher.getPrenom().equals(""))) {
				critere++;
				if (this.getStagiaire().getPrenom().equals(valeurAChercher.getPrenom())) {
					verification++;
					if (critere == verification) {
						stagiaires.add(this.getStagiaire());
						System.out.println(this);
					}
				}
			}
			if (!(valeurAChercher.getAnnee().equals(""))) {
				critere++;
				if (this.getStagiaire().getAnnee().equals(valeurAChercher.getAnnee())) {
					verification++;
					if (critere == verification) {
						stagiaires.add(this.getStagiaire());
						System.out.println(this);
					}
				}
			}

			if (this.getFilsDroit() != -1) {
				raf.seek(this.getFilsDroit() * Stagiaire.TAILLE_OBJET_OCTET);
				Noeud filsDroit = lectureNoeud(raf);
				filsDroit.chercherValeur(valeurAChercher, raf, stagiaires);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stagiaires;

	}
	public Noeud supprimerNoeud(RandomAccessFile raf, Stagiaire stagiaireASupprimer) throws IOException {

		if(stagiaireASupprimer == null ) {
			return null; 
			
		}if (this.getStagiaire().getNom().compareTo(stagiaireASupprimer.getNom())>0) {
			if (this.filsGauche!= -1) {
				raf.seek(this.getFilsGauche() * Stagiaire.TAILLE_OBJET_OCTET);
				return this.supprimerNoeud(raf, stagiaireASupprimer);
			}else {
				return this; 
			}
			
		}else {
			if (this.filsDroit!= -1) {
				raf.seek(this.getFilsDroit() * Stagiaire.TAILLE_OBJET_OCTET);
				return this.supprimerNoeud(raf, stagiaireASupprimer);
			}else {
			return this; 
		}		

	}
//	public Noeud noeudSuccesseur() {
//		if (this.filsDroit == null) {
//			return this;
//		}
//		Noeud noeudCourant = this.filsDroit;
//		while (noeudCourant.filsGauche !=null) {
//			noeudCourant = noeudCourant.filsGauche;
//		}return noeudCourant;
//		
//	}
}
}