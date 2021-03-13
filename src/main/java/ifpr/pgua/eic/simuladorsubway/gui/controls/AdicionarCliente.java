package ifpr.pgua.eic.simuladorsubway.gui.controls;

import ifpr.pgua.eic.simuladorsubway.Main;
import ifpr.pgua.eic.simuladorsubway.models.Cliente;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.ClienteRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AdicionarCliente extends JanelaBase{


    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfEmail;

    @FXML
    private Button btAdicionar;

    private ClienteRepository clienteRepository;
    private Cliente clienteOriginal;

    public AdicionarCliente(ClienteRepository clienteRepository, Cliente cliente){
        this.clienteRepository = clienteRepository;
        this.clienteOriginal = cliente;
    }


    public AdicionarCliente(ClienteRepository clienteRepository){
        this(clienteRepository,null);
    }


    @FXML
    private void initialize(){

        if(clienteOriginal != null){
            tfNome.setText(clienteOriginal.getNome());
            tfTelefone.setText(clienteOriginal.getTelefone());
            tfEmail.setText(String.valueOf(clienteOriginal.getEmail()));

            btAdicionar.setText("Alterar");

        }

    }



    @FXML
    private void adicionar(){
        String nome = tfNome.getText();
        String telefone = tfTelefone.getText();
        String email = tfEmail.getText();


        if(nome.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Nome inválido!!");
            alert.showAndWait();
            return;
        }

        if(telefone.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Telefone inválido!!");
            alert.showAndWait();
            return;
        }

        if(email.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Email inválido!!");
            alert.showAndWait();
            return;
        }




        Cliente cliente = new Cliente(nome,email,telefone);

        try{
            if(clienteOriginal != null){
                clienteRepository.editar(clienteOriginal.getId(),cliente);
            }else{
                clienteRepository.adicionar(cliente);
            }
        }catch (SQLException e){
            e.printStackTrace();
            mostraMensagem(Alert.AlertType.ERROR,e.getMessage());
        }

        Main.voltaPrincipal();


    }

    @FXML
    private void cancelar(){
        Main.voltaPrincipal();
    }


}
