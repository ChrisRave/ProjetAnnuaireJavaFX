package fr.isika.cda18.projet1.entites;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArbreBinaire {

	public static ObservableList<Stagiaire> stagiaires =FXCollections.observableArrayList();

	public void importFichier() {
		try {
			FileReader fr = new FileReader("src/mesFichiers/STAGIAIRES.DON");
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				Stagiaire stagiaire = new Stagiaire(null, null, null, null, null);
				stagiaire.setNom(br.readLine());
				stagiaire.setPrenom(br.readLine());
				stagiaire.setDepartement(br.readLine());
				stagiaire.setPromotion(br.readLine());
				stagiaire.setAnnee(br.readLine());
				br.readLine();
				stagiaires.add(stagiaire);
			}
			//System.out.println("*****Liste stagiaires*****\n" + stagiaires);
			br.close();
			fr.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void chercherStagiaire(RandomAccessFile raf, String StagiaireAChercher) {
		try {
			raf.seek(0);
			Noeud.chercherStagiaire(StagiaireAChercher, raf);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public List<Stagiaire> affichageInfixe() throws IOException {
		
		RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaires.bin", "rw");
		List<Stagiaire> stagiaires = new ArrayList<>();
		if (raf.length() != 0) {
			raf.seek(0);
			Noeud.lectureNoeud(raf).affichageInfixe(raf, stagiaires);
		}
		return stagiaires;
	}

	public void ajouterRacine(Noeud noeud) throws IOException {

		RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaires.bin", "rw");
		//System.out.println("ajouter " + noeud.getStagiaire().nom);
		if (raf.length() == 0) {
			raf.seek(0);
			Noeud.ecritureBinaire(raf, noeud);
		} else {
			raf.seek(0);
			this.ajouterStagiaire(raf, noeud);
		}
		raf.close();
	}

	public void ajouterStagiaire(RandomAccessFile raf, Noeud stagiaireAjouter) throws IOException {

		Noeud racine = Noeud.lectureNoeud(raf);

		if (racine.getStagiaire().getNom().compareTo(stagiaireAjouter.getStagiaire().getNom()) > 0) {

			if (racine.getFilsGauche() == -1) {
				raf.seek(raf.getFilePointer() - 8);
				raf.writeInt((int) (raf.length() / Stagiaire.TAILLE_OBJET_OCTET));
				raf.seek(raf.length());
				Noeud.ecritureBinaire(raf, stagiaireAjouter);
			} else {
				raf.seek(racine.getFilsGauche() * Stagiaire.TAILLE_OBJET_OCTET);
				this.ajouterStagiaire(raf, stagiaireAjouter);
			}
		} else {
			if (racine.getFilsDroit() == -1) {
				raf.seek(raf.getFilePointer() - 4);
				raf.writeInt((int) (raf.length() / Stagiaire.TAILLE_OBJET_OCTET));
				raf.seek(raf.length());
				Noeud.ecritureBinaire(raf, stagiaireAjouter);
			} else {
				raf.seek(racine.getFilsDroit() * Stagiaire.TAILLE_OBJET_OCTET);
				this.ajouterStagiaire(raf, stagiaireAjouter);
			}
		}
	}

	public void chercherValeur(Stagiaire valeurAChercher, RandomAccessFile raf) {
		try {
			List<Stagiaire> stagiaires = new ArrayList<>();
			raf.seek(0);
			Noeud noeud = Noeud.lectureNoeud(raf);
			noeud.chercherValeur(valeurAChercher, raf, stagiaires);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	

}