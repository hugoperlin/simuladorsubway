package ifpr.pgua.eic.simuladorsubway.daos.interfaces;

import ifpr.pgua.eic.simuladorsubway.models.Sanduiche;

import java.sql.SQLException;

public interface SanduicheDAO {

    boolean inserir(Sanduiche sanduiche) throws SQLException;
    Sanduiche buscaId(int id) throws SQLException;

    Sanduiche buscaSanduicheDoPedido(int idPedido) throws SQLException;
}
