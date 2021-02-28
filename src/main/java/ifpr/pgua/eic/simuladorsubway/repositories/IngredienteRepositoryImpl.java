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
        ingredientes.add(ingrediente);
        return true;
    }

    public ObservableList<Ingrediente> lista(){
        return FXCollections.unmodifiableObservableList(ingredientes);
    }


}
