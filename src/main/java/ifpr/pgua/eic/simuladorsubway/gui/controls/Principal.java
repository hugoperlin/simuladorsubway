package ifpr.pgua.eic.simuladorsubway.gui.controls;

import ifpr.pgua.eic.simuladorsubway.Main;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.IngredienteRepository;
import javafx.fxml.FXML;

public class Principal {

    private IngredienteRepository ingredienteRepository;

    public Principal(IngredienteRepository ingredienteRepository){
        this.ingredienteRepository = ingredienteRepository;
    }



    @FXML
    private void cadastarIngrediente(){
        Main.mudaCena(Main.ADICIONARINGREDIENTE,(aClass)->new AdicionarIngrediente(ingredienteRepository));
    }

}
