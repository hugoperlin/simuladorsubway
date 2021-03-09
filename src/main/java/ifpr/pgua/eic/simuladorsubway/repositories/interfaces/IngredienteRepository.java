package ifpr.pgua.eic.simuladorsubway.repositories.interfaces;

import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IngredienteRepository {

    boolean adicionar(Ingrediente ingrediente) throws SQLException;
    boolean editar(int id, Ingrediente ingredienteAntigo) throws SQLException;
    ObservableList<Ingrediente> lista() throws SQLException;
}
