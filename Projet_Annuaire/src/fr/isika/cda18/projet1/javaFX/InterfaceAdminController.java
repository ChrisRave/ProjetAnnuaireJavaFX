package fr.isika.cda18.projet1.javaFX;

import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda18.projet1.entites.ArbreBinaire;
import fr.isika.cda18.projet1.entites.Noeud;
import fr.isika.cda18.projet1.entites.Stagiaire;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterfaceAdminController {

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
	private Button btnInscription;
	
	@FXML
	private Button btnChercherStagiaire;

	@FXML
	private Button btnRetourAccueil;
	
	
	ArbreBinaire arbre = new ArbreBinaire();
	
	@FXML
	private void btnRetourAccueilHandler(Event e) throws IOException {

		Stage primaryStage = (Stage) btnRetourAccueil.getScene().getWindow();
		BorderPane interfaceAdmin = (BorderPane) FXMLLoader.load(getClass().getResource("InterfaceAccueil.fxml"));
		Scene adminScene = new Scene(interfaceAdmin, 1030, 600);
		adminScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(adminScene);

	}
	
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
	
	//	ListeDesStagiairesController.tblStagiaires.setItems(resultat); 
		
		Stage primaryStage = (Stage) btnChercherStagiaire.getScene().getWindow();
		AnchorPane interfaceListe = (AnchorPane) FXMLLoader.load(getClass().getResource("ListeStagiaires.fxml"));
		Scene sceneList = new Scene(interfaceListe, 1030, 600);
		sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		primaryStage.setScene(sceneList);

		TableView<Stagiaire> tableau =(TableView<Stagiaire>) ((VBox) interfaceListe.getChildren().get(0)).getChildren().get(1);
		tableau.setItems(resultat);


		
		reinitialisationFormulaire();
		
	}

	


	@FXML
	private void btnInscriptionHandler(Event e) throws IOException {
		
		
		RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaires.bin", "rw");
		ArbreBinaire arbre = new ArbreBinaire();
		String nom = txtNom.getText();
		String prenom = txtPrenom.getText();
		String departement = txtDepartement.getText();
		String promotion = txtPromotion.getText();
		String annee = txtAnnee.getText();

		Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, promotion, annee);
		arbre.ajouterRacine(new Noeud(stagiaire, -1, -1));
		raf.seek(0); 
		
		

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INSCRIPTION VALIDEE");
		alert.setHeaderText("Stagiaire ajouté");
		alert.showAndWait();
		

		reinitialisationFormulaire();
		

	} 
	

	public void reinitialisationFormulaire() {

		txtNom.clear();
		txtPrenom.clear();
		txtDepartement.clear();
		txtPromotion.clear();
		txtAnnee.clear();
	}
}