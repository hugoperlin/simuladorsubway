package ifpr.pgua.eic.simuladorsubway.repositories.interfaces;

import ifpr.pgua.eic.simuladorsubway.models.Pedido;
import ifpr.pgua.eic.simuladorsubway.models.Sanduiche;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface PedidoRepository {

    boolean adicionar(Pedido pedido) throws SQLException;
    boolean adicionar(Sanduiche sanduiche) throws SQLException;

    ObservableList<Pedido> lista() throws SQLException;



}
