package ifpr.pgua.eic.simuladorsubway.gui.controls;

import ifpr.pgua.eic.simuladorsubway.Main;
import ifpr.pgua.eic.simuladorsubway.models.Bebida;
import ifpr.pgua.eic.simuladorsubway.models.Cliente;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.models.Pedido;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.BebidaRepository;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.ClienteRepository;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.IngredienteRepository;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.PedidoRepository;
import ifpr.pgua.eic.simuladorsubway.utils.GeradorPdf;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Principal extends JanelaBase{

    @FXML
    ListView<Ingrediente> ltwIngredientes;

    @FXML
    ListView<Cliente> ltwClientes;

    @FXML
    ListView<Bebida> ltwBebidas;

    @FXML
    TableView<Pedido> tvwPedidos;

    @FXML
    TableColumn<Pedido, Integer> tcId;

    @FXML
    TableColumn<Pedido, String> tcCliente;

    @FXML
    TableColumn<Pedido, Double> tcValor;

    @FXML
    private Label lbRelogio;

    @FXML
    private Text txtIdentificacaoTela;

    @FXML
    private ProgressIndicator piIndicador;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");


    private IngredienteRepository ingredienteRepository;
    private ClienteRepository clienteRepository;
    private BebidaRepository bebidaRepository;
    private PedidoRepository pedidoRepository;

    public Principal(IngredienteRepository ingredienteRepository,
                     ClienteRepository clienteRepository,
                     BebidaRepository bebidaRepository,
                     PedidoRepository pedidoRepository){
        this.ingredienteRepository = ingredienteRepository;
        this.clienteRepository = clienteRepository;
        this.bebidaRepository = bebidaRepository;
        this.pedidoRepository = pedidoRepository;

    }




    @FXML
    private void initialize(){

        //criando o relogio
        Task relogio = controlaRelogio();
        new Thread(relogio).start();
        lbRelogio.textProperty().bind(relogio.messageProperty());


        inicializaListViews();
        inicializaTablePedidos();

        //carregando os dados das listviews
        try{
            ltwIngredientes.setItems(ingredienteRepository.lista());
            ltwClientes.setItems(clienteRepository.lista());
            ltwBebidas.setItems(bebidaRepository.lista());
            tvwPedidos.setItems(pedidoRepository.lista());
        }catch (SQLException e){
            mostraMensagem(Alert.AlertType.ERROR, e.getMessage());
        }

    }

    private void inicializaTablePedidos(){


        tcId.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("id"));
        //tcCliente.setCellValueFactory(new PropertyValueFactory<Pedido, String>("cliente"));
        tcCliente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pedido, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pedido, String> celula) {
                return new SimpleStringProperty(celula.getValue().getCliente().getNome());
            }
        });

        tcValor.setCellValueFactory(new PropertyValueFactory<Pedido, Double>("valorTotal"));

    }


    private void inicializaListViews(){
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

        ltwBebidas.setCellFactory(bebidaListView -> new ListCell<>(){
            @Override
            protected void updateItem(Bebida bebida, boolean b) {
                super.updateItem(bebida, b);

                if(bebida != null){
                    setText(bebida.getNome());
                }else{
                    setText("");
                }
            }
        });

    }

    @FXML
    private void executaTarefa(){

        piIndicador.setVisible(true);

        Task tarefa = new Task() {
            @Override
            protected Object call() throws Exception {
                try{
                    ingredienteRepository.tarefaDemorada();
                    Platform.runLater(()->piIndicador.setVisible(false));
                }catch (SQLException e){
                    Platform.runLater(()-> mostraMensagem(Alert.AlertType.ERROR,e.getMessage()));
                }
                return null;
            }
        };

        new Thread(tarefa).start();

    }


    @FXML
    private void gerarRelatorioIngredientes(){
        try{

            GeradorPdf geradorPdf = new GeradorPdf(ingredienteRepository);

            FileChooser fc = new FileChooser();
            File f = fc.showSaveDialog(null);


            if(f != null) {
                String arq = f.getAbsolutePath();
                geradorPdf.criaPdf(arq);
            }

        }catch (SQLException e){
            mostraMensagem(Alert.AlertType.ERROR,e.getMessage());
        }
    }


    @FXML
    private void mudaCena(ActionEvent evt) {

        String texto = ((Button)evt.getSource()).getText();

        txtIdentificacaoTela.setText(texto);

        Main.mudaCena(Main.ADICIONARINGREDIENTE,(aClass)->new AdicionarIngrediente(ingredienteRepository));


    }


    @FXML
    private void cadastrarPedido(){
        Main.mudaCena(Main.ADICIONARPEDIDO, (aClass) -> new AdicionarPedido(ingredienteRepository,bebidaRepository,clienteRepository,pedidoRepository));
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
    private void cadastrarBebida(){
        Main.mudaCena(Main.ADICIONARBEBIDA, (aClass) -> new AdicionarBebida(bebidaRepository));
    }

    @FXML
    private void editarBebida(MouseEvent evt){

        if(evt.getClickCount() == 2){
            Bebida bebida = ltwBebidas.getSelectionModel().getSelectedItem();

            if(bebida != null){
                Main.mudaCena(Main.ADICIONARBEBIDA, (aClass) -> new AdicionarBebida(bebidaRepository, bebida));
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

    @FXML
    private void sair(){
        Platform.exit();
    }

    private Task controlaRelogio(){
        return new Task() {
            @Override
            protected Object call() throws Exception {

                while(true){
                    String hora = formatter.format(LocalDateTime.now());
                    this.updateMessage(hora);

                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
    }

}
