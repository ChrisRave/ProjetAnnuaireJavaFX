package fr.isika.cda18.projet1.javaFX;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda18.projet1.entites.ArbreBinaire;
import fr.isika.cda18.projet1.entites.Noeud;
import fr.isika.cda18.projet1.entites.Stagiaire;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	public static ArbreBinaire arbre;
	public static RandomAccessFile raf;
	
	 static Utilisateur user  = new Utilisateur(false); 

	static {
		arbre = new ArbreBinaire();
		try {
			raf = new RandomAccessFile("src/mesFichiers/listeStagiaires.bin", "rw");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		try {

			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("InterfaceAccueil.fxml"));
			Scene scene = new Scene(root, 1000, 600);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		if (raf.length() == 0) {
			arbre.importFichier();
			for (int i = 0; i < ArbreBinaire.stagiaires.size(); i++) {
				arbre.ajouterRacine(new Noeud(ArbreBinaire.stagiaires.get(i), -1, -1));
			}
		}
		launch(args);
	}
}