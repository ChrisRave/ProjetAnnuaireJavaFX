package fr.isika.cda18.projet1.javaFX;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.isika.cda18.projet1.entites.ArbreBinaire;
import fr.isika.cda18.projet1.entites.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ListeDesStagiairesController implements Initializable {

	@FXML
	private TableColumn<Stagiaire, String> NomC;

	@FXML
	private TableColumn<Stagiaire, String> prenomC;

	@FXML
	private TableColumn<Stagiaire, String> departementC;

	@FXML
	private TableColumn<Stagiaire, String> promotionC;

	@FXML
	private TableColumn<Stagiaire, String> anneeC;

	@FXML
	public TableView<Stagiaire> tblStagiaires;

	@FXML
	private Button btnRetourInterface;

	@FXML
	private void btnRetourInterface(Event e) throws IOException {

	}

	@FXML
	private void btnRetourInterfaceUser(Event e) throws IOException {

		Stage primaryStage = (Stage) btnRetourInterface.getScene().getWindow();
		BorderPane interfaceListe = (BorderPane) FXMLLoader.load(getClass().getResource("InterfaceUser.fxml"));
		Scene sceneList = new Scene(interfaceListe, 1030, 600);
		sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(sceneList);

	}

	@FXML
	private void btnRetourInterfaceAdmin(Event e) throws IOException {

		Stage primaryStage = (Stage) btnRetourInterface.getScene().getWindow();
		BorderPane interfaceListe = (BorderPane) FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
		Scene sceneList = new Scene(interfaceListe, 1030, 600);
		sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(sceneList);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			ArbreBinaire arbre = new ArbreBinaire();
			List<Stagiaire> stagiaires = new ArrayList<>();
			stagiaires = arbre.affichageInfixe();
			NomC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Nom"));
			prenomC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
			departementC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
			promotionC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promotion"));
			anneeC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));
			tblStagiaires.setItems(FXCollections.observableArrayList(stagiaires));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}