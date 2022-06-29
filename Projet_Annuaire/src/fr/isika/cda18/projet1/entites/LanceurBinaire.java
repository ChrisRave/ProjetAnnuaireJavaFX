package fr.isika.cda18.projet1.entites;
import java.io.IOException;
import java.io.RandomAccessFile;
public class LanceurBinaire {
	public static void main(String[] args) throws IOException {
		try {
			RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaires.bin", "rw");
			raf.setLength(0);
			ArbreBinaire arbre = new ArbreBinaire();
			arbre.importFichier();
			Stagiaire s1 = new Stagiaire("GHAZALA", "Houssam", "91", "ATOD 6", "2009");		
			for (int i = 0; i < ArbreBinaire.stagiaires.size(); i++) {
				arbre.ajouterRacine(new Noeud(ArbreBinaire.stagiaires.get(i), -1, -1));
			}arbre.ajouterStagiaire(raf, new Noeud(s1, -1, -1));
			arbre.affichageInfixe(raf);
			
			
			arbre.chercherStagiaire(raf, "POTIN");
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
