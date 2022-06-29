module Projet_Annuaire {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens fr.isika.cda18.projet1.javaFX to javafx.graphics, javafx.fxml;
}
