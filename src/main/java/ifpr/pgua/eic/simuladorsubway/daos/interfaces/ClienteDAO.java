package ifpr.pgua.eic.simuladorsubway.daos.interfaces;

import ifpr.pgua.eic.simuladorsubway.models.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {

    boolean inserir(Cliente cliente) throws SQLException;
    boolean atualizar(int id, Cliente cliente) throws SQLException;

    List<Cliente> lista() throws SQLException;
    Cliente buscaId(int id) throws SQLException;
    Cliente buscaClienteDoPedido(int idPedido) throws SQLException;

    boolean delete(Cliente cliente) throws SQLException;

}
