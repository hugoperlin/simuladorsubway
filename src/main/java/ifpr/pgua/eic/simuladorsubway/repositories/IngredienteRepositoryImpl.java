package ifpr.pgua.eic.simuladorsubway.repositories;

import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.IngredienteRepository;

import java.util.ArrayList;

public class IngredienteRepositoryImpl implements IngredienteRepository {

    private ArrayList<Ingrediente> ingredientes;


    public IngredienteRepositoryImpl(){

        ingredientes = new ArrayList<>();
    }

    public boolean adicionar(Ingrediente ingrediente){
        ingredientes.add(ingrediente);
        return true;
    }

}
