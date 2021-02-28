package ifpr.pgua.eic.simuladorsubway.repositories.interfaces;

import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import javafx.collections.ObservableList;

public interface IngredienteRepository {

    public boolean adicionar(Ingrediente ingrediente);
    public ObservableList<Ingrediente> lista();
}
