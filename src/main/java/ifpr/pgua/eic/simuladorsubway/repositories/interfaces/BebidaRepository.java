package ifpr.pgua.eic.simuladorsubway.repositories.interfaces;

import ifpr.pgua.eic.simuladorsubway.models.Bebida;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface BebidaRepository {

    boolean adicionar(Bebida bebida) throws SQLException;
    boolean editar(int id, Bebida bebida) throws SQLException;

    ObservableList<Bebida> lista() throws SQLException;


}
