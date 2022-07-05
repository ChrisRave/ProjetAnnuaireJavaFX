package fr.isika.cda18.projet1.javaFX;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.isika.cda18.projet1.entites.ArbreBinaire;
import fr.isika.cda18.projet1.entites.Stagiaire;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
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
	public TableView<Stagiaire> tblStagiaires = new TableView<Stagiaire>();

	@FXML
	private Button btnRetourInterface;

	@FXML
	private Button btnSupprimerStagiaire;

	@FXML
	private void btnRetourInterfaceUser(Event e) throws IOException {

		if (Main.user.isAdmin()) {
			Stage primaryStage = (Stage) btnRetourInterface.getScene().getWindow();
			BorderPane interfaceListe = (BorderPane) FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
			Scene sceneList = new Scene(interfaceListe, 1030, 600);
			sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(sceneList);
		} else {
			Stage primaryStage = (Stage) btnRetourInterface.getScene().getWindow();
			BorderPane interfaceListe = (BorderPane) FXMLLoader.load(getClass().getResource("InterfaceUser.fxml"));
			Scene sceneList = new Scene(interfaceListe, 1030, 600);
			sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(sceneList);
		}
	}
// a supprimer si besoin
	@FXML
	public void btnSupprimerStagiaireHandler(Event e) throws IOException {
		ArbreBinaire arbre = new ArbreBinaire();
		RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaires.bin", "rw");
		
		System.out.println(tblStagiaires.getSelectionModel().isEmpty());
		Stagiaire stagiaire = tblStagiaires.getSelectionModel().getSelectedItem();
		System.out.println(stagiaire);
		arbre.supprimerNoeud(raf, stagiaire);
		
		ObservableList<Stagiaire> stagiaires = FXCollections.observableArrayList(arbre.affichageInfixe());
		tblStagiaires.setItems(stagiaires);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			ArbreBinaire arbre = new ArbreBinaire();

			NomC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Nom"));
			prenomC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
			departementC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
			promotionC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promotion"));
			anneeC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));
			if (tblStagiaires.getItems() == null) {
				List<Stagiaire> stagiaires = new ArrayList<>();
				stagiaires = arbre.affichageInfixe();
				tblStagiaires.setItems(FXCollections.observableArrayList(stagiaires));
			}
//			tblStagiaires.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//			tblStagiaires.getSelectionModel().setCellSelectionEnabled(true) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void afficherRecherche(ObservableList<Stagiaire> resultat) throws IOException {

		tblStagiaires = new TableView<>(resultat);
		NomC = new TableColumn<>();
		prenomC = new TableColumn<>();
		departementC = new TableColumn<>();
		promotionC = new TableColumn<>();
		anneeC = new TableColumn<>();
		NomC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Nom"));
		prenomC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		departementC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
		promotionC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promotion"));
		anneeC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));

		tblStagiaires.setItems(resultat);
		// tblStagiaires.refresh();

	}
}