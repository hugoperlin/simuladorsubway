package ifpr.pgua.eic.simuladorsubway.gui.controls;

import ifpr.pgua.eic.simuladorsubway.Main;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.IngredienteRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


public class Principal {

    @FXML
    ListView<Ingrediente> ltwIngredientes;


    private IngredienteRepository ingredienteRepository;

    public Principal(IngredienteRepository ingredienteRepository){
        this.ingredienteRepository = ingredienteRepository;
    }


    @FXML
    private void initialize(){

        ltwIngredientes.setCellFactory(new Callback<ListView<Ingrediente>, ListCell<Ingrediente>>() {
            @Override
            public ListCell<Ingrediente> call(ListView<Ingrediente> ingredienteListView) {
                return new ListCell<>(){
                    @Override
                    protected void updateItem(Ingrediente ingrediente, boolean b) {
                        super.updateItem(ingrediente, b);

                        if(ingrediente != null){
                            setText(ingrediente.getNome());
                        }else{
                            setText("");
                        }

                    }
                };
            }
        });

        ltwIngredientes.setItems(ingredienteRepository.lista());

    }


    @FXML
    private void cadastarIngrediente(){
        Main.mudaCena(Main.ADICIONARINGREDIENTE,(aClass)->new AdicionarIngrediente(ingredienteRepository));
    }

}
