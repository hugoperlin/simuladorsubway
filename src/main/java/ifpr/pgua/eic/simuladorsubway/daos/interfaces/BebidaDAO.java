package ifpr.pgua.eic.simuladorsubway.daos.interfaces;

import ifpr.pgua.eic.simuladorsubway.models.Bebida;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;

import java.sql.SQLException;
import java.util.List;

public interface BebidaDAO {

    boolean inserir(Bebida bebida) throws SQLException;
    boolean atualizar(int id, Bebida bebida) throws SQLException;

    List<Bebida> lista() throws SQLException;
    Bebida buscaId(int id) throws SQLException;
    public Bebida buscaBebidaDoPedido(int idPedido) throws SQLException;

    boolean delete(Bebida Bebida) throws SQLException;

}
