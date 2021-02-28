package ifpr.pgua.eic.simuladorsubway.repositories;

import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.IngredienteRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class IngredienteRepositoryImpl implements IngredienteRepository {

    private ObservableList<Ingrediente> ingredientes;


    public IngredienteRepositoryImpl(){

        ingredientes = FXCollections.observableArrayList();
    }

    public boolean adicionar(Ingrediente ingrediente){
        ingredientes.add(new Ingrediente(ingredientes.size(),ingrediente.getNome(),ingrediente.getDescricao(), ingrediente.getValor()));
        return true;
    }

    public boolean editar(int id, Ingrediente ingrediente){

        for(Ingrediente i:ingredientes){
            if(i.getId() == id){
                i.setNome(ingrediente.getNome());
                i.setDescricao(ingrediente.getDescricao());
                i.setValor(ingrediente.getValor());

                return true;
            }
        }

        return false;
    }

    public ObservableList<Ingrediente> lista(){
        return FXCollections.unmodifiableObservableList(ingredientes);
    }


}
