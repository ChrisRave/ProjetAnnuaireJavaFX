package fr.isika.cda18.projet1.javaFX;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.isika.cda18.projet1.entites.ArbreBinaire;
import fr.isika.cda18.projet1.entites.Noeud;
import fr.isika.cda18.projet1.entites.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterfaceUserController implements Initializable {

	@FXML
	private Button btnInscription;

	@FXML
	private Button btnListe;

	@FXML
	private TextField txtNom;

	@FXML
	private TextField txtPrenom;

	@FXML
	private TextField txtDepartement;

	@FXML
	private TextField txtPromotion;

	@FXML
	private TextField txtAnnee;

	@FXML
	private Button btnRetourAccueil;

	@FXML
	private Button btnChercherStagiaire;
	
	//à remettre dans inscription handler si besoin
	ArbreBinaire arbre = new ArbreBinaire();

	@FXML
	private void btnRetourAccueilHandler(Event e) throws IOException {

		Stage primaryStage = (Stage) btnRetourAccueil.getScene().getWindow();
		BorderPane userInterface = (BorderPane) FXMLLoader.load(getClass().getResource("InterfaceAccueil.fxml"));
		Scene userScene = new Scene(userInterface, 1030, 600);
		userScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(userScene);

	}

	@FXML
	private void btnListeHandler(Event e) throws IOException {

		Stage primaryStage = (Stage) btnListe.getScene().getWindow();
		AnchorPane userInterface = (AnchorPane) FXMLLoader.load(getClass().getResource("ListeStagiaires.fxml"));
		Scene userScene = new Scene(((AnchorPane) userInterface), 1030, 600);
		userScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(userScene);

	}

	@FXML
	private void btnInscriptionHandler(Event e) throws IOException {
		
		
		RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaires.bin", "rw");
		
		String nom = txtNom.getText();
		String prenom = txtPrenom.getText();
		String departement = txtDepartement.getText();
		String promotion = txtPromotion.getText();
		String annee = txtAnnee.getText();

		Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, promotion, annee);
		arbre.ajouterRacine(new Noeud(stagiaire, -1, -1));
		raf.seek(0); 
		btnListeHandler(e);
		

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INSCRIPTION VALIDEE");
		alert.setHeaderText("Bienvenue à ISIKA");
		alert.setContentText("Félicitations, la session demarre en Septembre 2022");
		alert.showAndWait();
		

		reinitialisationFormulaire();

	}
	//à supprimer si besoin
	@FXML
	private void btnChercherStagiaireHandler(Event e) throws IOException {
		
		RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaires.bin", "rw");
		String nom = txtNom.getText();
		String prenom = txtPrenom.getText();
		String departement = txtDepartement.getText();
		String promotion = txtPromotion.getText();
		String annee = txtAnnee.getText();
		
		Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, promotion, annee);
		ObservableList<Stagiaire > resultat = arbre.chercherValeur(stagiaire, raf);
	
		ListeDesStagiairesController.tblStagiaires.setItems(resultat); 
		
		Stage primaryStage = (Stage) btnChercherStagiaire.getScene().getWindow();
		AnchorPane interfaceListe = (AnchorPane) FXMLLoader.load(getClass().getResource("ListeStagiaires.fxml"));
		Scene sceneList = new Scene(interfaceListe, 1030, 600);
		sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		primaryStage.setScene(sceneList);
		System.out.println( interfaceListe.getChildren().get(0));
		TableView<Stagiaire> tableau =(TableView<Stagiaire>) ((VBox) interfaceListe.getChildren().get(0)).getChildren().get(1);
		tableau.setItems(resultat);

		//		ListeDesStagiairesController controller = new ListeDesStagiairesController();
//		controller.afficherRecherche(resultat);
//		System.out.println(resultat.size());
		
		reinitialisationFormulaire();
		
	}
	public void reinitialisationFormulaire() {

		txtNom.clear();
		txtPrenom.clear();
		txtDepartement.clear();
		txtPromotion.clear();
		txtAnnee.clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Main.arbre.affichageInfixe();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}