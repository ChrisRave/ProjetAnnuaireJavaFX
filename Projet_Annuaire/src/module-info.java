module Projet_Annuaire {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	exports fr.isika.cda18.projet1.entites;
	
	
	opens fr.isika.cda18.projet1.javaFX to javafx.graphics, javafx.fxml, javafx.base, javafx.controls;
}
