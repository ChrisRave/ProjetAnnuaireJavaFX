package fr.isika.cda18.projet1.javaFX;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.isika.cda18.projet1.entites.Stagiaire;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ListeDesStagiairesController implements Initializable{
		
		@FXML
	    private TableColumn<Stagiaire, String> 	NomC;
		
		@FXML
	    private TableColumn<Stagiaire, String> prenomC;
		
		@FXML
	    private TableColumn<Stagiaire, String> departementC;
		
		@FXML
	    private TableColumn<Stagiaire, String> promotionC;
		
		@FXML
	    private TableColumn<Stagiaire, String> anneeC;
		
	    @FXML
	    private TableView<Stagiaire> tblStagiaires;	    
	    
	    @FXML
		   private Button btnRetourInterface; 
	    
	    BorderPane interfaceListe = new BorderPane(); 
	    
	    @FXML
		   private void btnRetourInterfaceHandler(Event e) throws IOException {
			
					Stage primaryStage = (Stage) btnRetourInterface.getScene().getWindow();
			
					
					if (interfaceListe == (BorderPane)FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"))) {
					Scene sceneListe = new Scene(interfaceListe,1030,600);
					sceneListe.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(sceneListe);
					} else {
						interfaceListe = (BorderPane)FXMLLoader.load(getClass().getResource("InterfaceUser.fxml"));
						Scene sceneListe = new Scene(interfaceListe,1030,600);
						sceneListe.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(sceneListe);
						
					} 
		   }
	    
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			System.out.println("dans initialize");
			List<Stagiaire> stagiaires = new ArrayList<>();
			try {
				 stagiaires = Main.arbre.affichageInfixe(Main.raf);
				 System.out.println("taille de la liste " + stagiaires.size());
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			NomC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Nom"));
			prenomC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
			departementC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
			promotionC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promotion"));
			anneeC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));
			tblStagiaires.setItems(FXCollections.observableArrayList(stagiaires));
			
		}
	}
