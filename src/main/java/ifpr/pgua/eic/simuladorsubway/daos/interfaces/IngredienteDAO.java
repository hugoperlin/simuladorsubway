package ifpr.pgua.eic.simuladorsubway.daos.interfaces;

import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;

import java.sql.SQLException;
import java.util.List;

public interface IngredienteDAO {

    boolean inserir(Ingrediente ingrediente) throws SQLException;
    boolean atualizar(int id, Ingrediente ingrediente) throws SQLException;

    List<Ingrediente> ingredientesDoSanduiche(int id) throws SQLException;

    List<Ingrediente> lista() throws SQLException;
    Ingrediente buscaId(int id) throws SQLException;

    boolean delete(Ingrediente ingrediente) throws SQLException;

}
