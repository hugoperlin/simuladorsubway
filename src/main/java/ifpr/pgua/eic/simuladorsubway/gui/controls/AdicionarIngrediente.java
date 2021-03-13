package ifpr.pgua.eic.simuladorsubway.gui.controls;

import ifpr.pgua.eic.simuladorsubway.Main;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.IngredienteRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AdicionarIngrediente  extends JanelaBase{


    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfValor;

    @FXML
    private Button btAdicionar;

    private IngredienteRepository ingredienteRepository;
    private Ingrediente ingredienteOriginal;

    public AdicionarIngrediente(IngredienteRepository ingredienteRepository, Ingrediente ingrediente){
        this.ingredienteRepository = ingredienteRepository;
        this.ingredienteOriginal = ingrediente;
    }


    public AdicionarIngrediente(IngredienteRepository ingredienteRepository){
        this(ingredienteRepository,null);
    }


    @FXML
    private void initialize(){

        if(ingredienteOriginal != null){
            tfNome.setText(ingredienteOriginal.getNome());
            tfDescricao.setText(ingredienteOriginal.getDescricao());
            tfValor.setText(String.valueOf(ingredienteOriginal.getValor()));

            btAdicionar.setText("Alterar");

        }

    }



    @FXML
    private void adicionar(){
        String nome = tfNome.getText();
        String descricao = tfDescricao.getText();
        double valor = -1;

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

        if(descricao.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Descrição inválida!!");
            alert.showAndWait();
            return;
        }

        if(valor <= 0.0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Valor inválido!!");
            alert.showAndWait();
            return;
        }




        Ingrediente ingrediente = new Ingrediente(nome,descricao,valor);

        try{
            if(ingredienteOriginal != null){
                ingredienteRepository.editar(ingredienteOriginal.getId(),ingrediente);
            }else{
                ingredienteRepository.adicionar(ingrediente);
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
