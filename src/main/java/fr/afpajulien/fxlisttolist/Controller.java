package fr.afpajulien.fxlisttolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * The type Controller.
 */
public class Controller implements Initializable {

    @FXML
    private Button btnMove;
    @FXML
    private Button btnMoveAll;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnRemoveAll;
    @FXML
    private Button btnUp;
    @FXML
    private Button btnDown;
    @FXML
    private ComboBox<String> cbxSource;
    private final ArrayList<String> countries = new ArrayList<>();
    @FXML
    private ListView<String> lstCible;
    private final ObservableList<String> countriesCible = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countries.add("France");
        countries.add("Belgique");
        countries.add("Allemagne");
        countries.add("Portugal");
        countries.add("Japon");

        btnMove.setDisable(true);
        btnMoveAll.setDisable(true);
        btnRemove.setDisable(true);
        btnRemoveAll.setDisable(true);
        btnUp.setDisable(true);
        btnDown.setDisable(true);

        cbxSource.getItems().addAll(countries);
        lstCible.setItems(countriesCible);

    }

    /**
     * Disable button.
     */
    public void disableButton() {
        btnMove.setDisable(cbxSource.getSelectionModel().isEmpty());
        btnMoveAll.setDisable(cbxSource.getSelectionModel().isEmpty());
        btnRemove.setDisable(lstCible.getSelectionModel().isEmpty());
        btnRemoveAll.setDisable(lstCible.getSelectionModel().isEmpty());
        btnUp.setDisable(lstCible.getSelectionModel().isEmpty());
        btnDown.setDisable(lstCible.getSelectionModel().isEmpty());
    }

    /**
     * Move to list.
     */
    public void moveToList() {
        if (cbxSource.getValue() != null) {
            String country = cbxSource.getValue();
            countriesCible.add(country);
            cbxSource.getItems().remove(country);
            countries.remove(country);
        }
        cbxSource.getPromptText();
    }

    /**
     * Move all to list.
     */
    public void moveAllToList() {
        if (cbxSource.getValue() != null) {
            countriesCible.addAll(countries);
            cbxSource.getItems().removeAll(countries);
            countries.clear();
        }
    }

    /**
     * Move to combo.
     */
    public void moveToCombo() {
        if (lstCible.getSelectionModel().getSelectedItem() != null) {
            btnRemove.setDisable(false);
            String country = lstCible.getSelectionModel().getSelectedItem();
            countries.add(country);
            cbxSource.getItems().add(country);
            lstCible.getItems().remove(country);
            countriesCible.remove(country);
        }
    }

    /**
     * Move all to combo.
     */
    public void moveAllToCombo() {
        if (lstCible.getSelectionModel().getSelectedItem() != null) {
            btnRemoveAll.setDisable(false);
            countries.addAll(countriesCible);
            cbxSource.getItems().addAll(countriesCible);
            lstCible.getItems().removeAll(countriesCible);
        }
    }

    /**
     * Move down.
     */
    public void moveDown() {
        String selected = lstCible.getSelectionModel().getSelectedItem();
        int index = lstCible.getItems().indexOf(selected);
        try {
            Collections.swap(countriesCible, index, index + 1);
            lstCible.getSelectionModel().select(index+1);
        } catch (RuntimeException e) {
            System.out.println("impossible de descendre plus bas");
        }
    }

    /**
     * Move up.
     */
    public void moveUp() {
        String selected = lstCible.getSelectionModel().getSelectedItem();
        int index = lstCible.getItems().indexOf(selected);
        try {
            Collections.swap(countriesCible, index, index - 1);
            lstCible.getSelectionModel().select(index-1);
        } catch (RuntimeException e) {
            System.out.println("impossible de remonter plus haut");
        }
    }
}