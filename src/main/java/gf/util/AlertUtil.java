package gf.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

public class AlertUtil extends Alert {

    public AlertUtil(AlertType alertType, String contentText, Window owner, String title, String header) {
        super(alertType, contentText);
        this.initOwner(owner);
        this.setTitle(title);
        this.setHeaderText(header);
    }
}
