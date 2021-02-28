package ifpr.pgua.eic.simuladorsubway.gui.controls;

import ifpr.pgua.eic.simuladorsubway.Main;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.IngredienteRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AdicionarIngrediente {


    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfValor;


    private IngredienteRepository ingredienteRepository;


    public AdicionarIngrediente(IngredienteRepository ingredienteRepository){
        this.ingredienteRepository = ingredienteRepository;
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


        ingredienteRepository.adicionar(ingrediente);

        Main.mudaCena(Main.PRINCIPAL,(aClass)-> new Principal(ingredienteRepository));


    }

    @FXML
    private void cancelar(){
        Main.mudaCena(Main.PRINCIPAL,(aClass)-> new Principal(ingredienteRepository));
    }


}
