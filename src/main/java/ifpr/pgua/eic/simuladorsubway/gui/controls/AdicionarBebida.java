package ifpr.pgua.eic.simuladorsubway.gui.controls;

import ifpr.pgua.eic.simuladorsubway.Main;
import ifpr.pgua.eic.simuladorsubway.models.Bebida;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.BebidaRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AdicionarBebida extends JanelaBase{


    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfValor;


    @FXML
    private Button btAdicionar;

    private BebidaRepository bebidaRepository;
    private Bebida bebidaOriginal;

    public AdicionarBebida(BebidaRepository bebidaRepository, Bebida cliente){
        this.bebidaRepository = bebidaRepository;
        this.bebidaOriginal = cliente;
    }


    public AdicionarBebida(BebidaRepository bebidaRepository){
        this(bebidaRepository,null);
    }


    @FXML
    private void initialize(){

        if(bebidaOriginal != null){
            tfNome.setText(bebidaOriginal.getNome());
            tfValor.setText(String.valueOf(bebidaOriginal.getValor()));

            btAdicionar.setText("Alterar");

        }

    }



    @FXML
    private void adicionar(){
        String nome = tfNome.getText();
        double valor=-1;
        try{
            valor = Double.valueOf(tfValor.getText());

        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Valor inválido!!");
            alert.showAndWait();
            return;
        }

        if(nome.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Nome inválido!!");
            alert.showAndWait();
            return;
        }

        if(valor <= 0.0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Valor inválido!!");
            alert.showAndWait();
            return;
        }

        Bebida bebida = new Bebida(nome,valor);

        try{
            if(bebidaOriginal != null){
                bebidaRepository.editar(bebidaOriginal.getId(),bebida);
            }else{
                bebidaRepository.adicionar(bebida);
            }
        }catch (SQLException e){
            mostraMensagem(Alert.AlertType.ERROR,e.getMessage());
        }

        Main.voltaPrincipal();


    }

    @FXML
    private void cancelar(){
        Main.voltaPrincipal();
    }


}
