package fr.isika.cda18.projet1.javaFX;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InterfaceAdminController {

	 @FXML
	   private Button btnListe;
	 
	 @FXML
	   private void btnListeHandler(Event e) throws IOException {
		
				Stage primaryStage = (Stage) btnListe.getScene().getWindow();
				AnchorPane layoutListe = (AnchorPane)FXMLLoader.load(getClass().getResource("ListeStagiaires.fxml"));
				Scene sceneList = new Scene(layoutListe,900,400);
				sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(sceneList);
	   }
	
}
