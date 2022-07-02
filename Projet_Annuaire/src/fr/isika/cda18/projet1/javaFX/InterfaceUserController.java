package fr.isika.cda18.projet1.javaFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.isika.cda18.projet1.entites.Stagiaire;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InterfaceUserController implements Initializable{
	
	
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
	   private void btnListeHandler(Event e) throws IOException {
		
				Stage primaryStage = (Stage) btnListe.getScene().getWindow();
				AnchorPane layoutListe = (AnchorPane)FXMLLoader.load(getClass().getResource("ListeStagiaires.fxml"));
				Scene sceneList = new Scene(layoutListe,1030,600);
				sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(sceneList);
	   }
	   
	   @FXML
	   private void btnInscriptionHandler(Event e) {
		  
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String departement = txtDepartement.getText();
				String promotion = txtPromotion.getText();
				String annee = txtAnnee.getText();
			
				Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, promotion, annee);
	
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
			@Override
			public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				Main.arbre.affichageInfixe(Main.raf);
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			}
		
}
