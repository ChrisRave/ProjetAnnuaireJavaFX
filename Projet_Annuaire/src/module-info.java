module Projet_Annuaire {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	opens fr.isika.cda18.projet1.javaFX to javafx.graphics, javafx.fxml;
}
