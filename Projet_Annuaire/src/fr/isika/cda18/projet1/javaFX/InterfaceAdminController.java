package fr.isika.cda18.projet1.javaFX;

import java.io.IOException;

import fr.isika.cda18.projet1.entites.ArbreBinaire;
import fr.isika.cda18.projet1.entites.Stagiaire;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
	private Button btnRetourAccueil;

	@FXML
	private void btnRetourAcceuilHandler(Event e) throws IOException {

		Stage primaryStage = (Stage) btnRetourAccueil.getScene().getWindow();
		BorderPane interfaceAdmin = (BorderPane) FXMLLoader.load(getClass().getResource("InterfaceAccueil.fxml"));
		Scene adminScene = new Scene(interfaceAdmin, 1030, 600);
		adminScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(adminScene);

	}

	@FXML
	private void btnListeHandler(Event e) throws IOException {

		Stage primaryStage = (Stage) btnListe.getScene().getWindow();
		AnchorPane interfaceAdmin = (AnchorPane) FXMLLoader.load(getClass().getResource("ListeStagiaires.fxml"));
		Scene adminScene = new Scene(interfaceAdmin, 1030, 600);
		adminScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(adminScene);
	}

	@FXML
	private void btnInscriptionHandler(Event e) throws IOException {

		String nom = txtNom.getText();
		String prenom = txtPrenom.getText();
		String departement = txtDepartement.getText();
		String promotion = txtPromotion.getText();
		String annee = txtAnnee.getText();

		Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, promotion, annee);
		ArbreBinaire.stagiaires.add(stagiaire);
		System.out.println(ArbreBinaire.stagiaires);
		btnListeHandler(e);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INSCRIPTION VALIDEE");
		alert.setHeaderText("Bienvenue à ISIKA");
		alert.setContentText("Félicitations, la session demarre en Septembre 2022");
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