package fr.isika.cda18.projet1.javaFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class InterfaceAccueilController implements Initializable{
	
	@FXML
	private Button btnValider; 
	
	@FXML
	private TextField txtIdentifiant; 
	
	@FXML
	private TextField txtPassword; 
	
	@FXML
	public Label  loginMessageLabel; 
	
	
	
	@FXML
	private void btnValiderHandler (Event e) throws IOException {
		
		String identifiant = txtIdentifiant.getText(); 
		String password = txtPassword.getText(); 
		 
		
		if (identifiant.equalsIgnoreCase("stagiaire") &&  password.equalsIgnoreCase("motdepasse")) {
		Stage primaryStage = (Stage) btnValider.getScene().getWindow();
		BorderPane interfaceUser = (BorderPane)FXMLLoader.load(getClass().getResource("InterfaceUser.fxml"));
		Scene userScene = new Scene (interfaceUser,550, 400 ); 
		userScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(userScene);
		
		}else  if (identifiant.equalsIgnoreCase("admin") && password.equalsIgnoreCase("motdepasse")){
			Stage primaryStage = (Stage) btnValider.getScene().getWindow();
			BorderPane adminInterface = (BorderPane)FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
			Scene adminScene = new Scene (adminInterface,550, 400 ); 
			adminScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(adminScene);
			
		}
		
		else {
			
			loginMessageLabel.setText("Erreur de saisie");

		}
		resetForm();
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
	}
	
	public void resetForm() {
		txtIdentifiant.clear();
		txtIdentifiant.clear(); 
	}
}
