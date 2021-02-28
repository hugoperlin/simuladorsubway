package ifpr.pgua.eic.simuladorsubway.gui.controls;

import ifpr.pgua.eic.simuladorsubway.Main;
import ifpr.pgua.eic.simuladorsubway.models.Cliente;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.ClienteRepository;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.IngredienteRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


public class Principal {

    @FXML
    ListView<Ingrediente> ltwIngredientes;

    @FXML
    ListView<Cliente> ltwClientes;


    private IngredienteRepository ingredienteRepository;
    private ClienteRepository clienteRepository;

    public Principal(IngredienteRepository ingredienteRepository, ClienteRepository clienteRepository){
        this.ingredienteRepository = ingredienteRepository;
        this.clienteRepository = clienteRepository;
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


        ltwClientes.setCellFactory(clienteListView -> new ListCell<>(){
            @Override
            protected void updateItem(Cliente cliente, boolean b) {
                super.updateItem(cliente,b);

                if(cliente != null){
                    setText(cliente.getNome());
                }else{
                    setText("");
                }
            }
        });

        ltwClientes.setItems(clienteRepository.lista());
    }


    @FXML
    private void cadastrarCliente(){
        Main.mudaCena(Main.ADICIONARCLIENTE,(aClass) -> new AdicionarCliente(clienteRepository));
    }

    @FXML
    private void editarCliente(MouseEvent evt){

        if(evt.getClickCount() == 2){
            Cliente cliente = ltwClientes.getSelectionModel().getSelectedItem();

            if(cliente != null){
                Main.mudaCena(Main.ADICIONARCLIENTE,(aClass) -> new AdicionarCliente(clienteRepository,cliente));
            }
        }

    }


    @FXML
    private void cadastarIngrediente(){
        Main.mudaCena(Main.ADICIONARINGREDIENTE,(aClass)->new AdicionarIngrediente(ingredienteRepository));
    }

    @FXML
    private void editarIngrediente(MouseEvent evt){

        if(evt.getClickCount() == 2){

            Ingrediente ingrediente = ltwIngredientes.getSelectionModel().getSelectedItem();
            if(ingrediente != null){
                Main.mudaCena(Main.ADICIONARINGREDIENTE,(aClass) -> new AdicionarIngrediente(ingredienteRepository,ingrediente));
            }


        }

    }

}
