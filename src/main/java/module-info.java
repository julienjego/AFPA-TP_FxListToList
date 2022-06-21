module fr.afpajulien.fxlisttolist {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.afpajulien.fxlisttolist to javafx.fxml;
    exports fr.afpajulien.fxlisttolist;
}