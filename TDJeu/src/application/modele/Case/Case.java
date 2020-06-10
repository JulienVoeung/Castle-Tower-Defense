package application.modele.Case;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Case{

    private boolean traversable;
    private StringProperty nom;
    private int id;
    private boolean marquage;

    public Case(String nomBlock, int id, boolean traversable) {
        this.nom = new SimpleStringProperty(nomBlock);
        this.id = id;
        this.traversable = traversable;
        this.marquage = false;
    }

    public boolean getTraversable() {
        return this.traversable;
    } 

    public int getId() {
        return this.id;
    }

    public StringProperty getNom() {
        return this.nom;
    }

    public boolean getMarquage() {
        return this.marquage; 
    }

    public void setMarquage(boolean b) {
        this.marquage = b; 
    }

}