package fr.isika.cda18.projet1.javaFX;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.isika.cda18.projet1.entites.Stagiaire;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			System.out.println("dans initialize");
			List<Stagiaire> stagiaires = new ArrayList<>();
			try {
				 stagiaires = Main.arbre.affichageInfixe(Main.raf);
				 System.out.println("taille de la lliste " + stagiaires.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
