package ifpr.pgua.eic.simuladorsubway.gui.controls;

import javafx.scene.control.Alert;

public class JanelaBase {


    protected void mostraMensagem(Alert.AlertType icone, String mensagem){
        Alert alert = new Alert(icone,mensagem);
        alert.showAndWait();
    }

}
