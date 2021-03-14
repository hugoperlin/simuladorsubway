package ifpr.pgua.eic.simuladorsubway.daos.interfaces;

import ifpr.pgua.eic.simuladorsubway.models.Cliente;
import ifpr.pgua.eic.simuladorsubway.models.Pedido;

import java.sql.SQLException;
import java.util.List;

public interface PedidoDAO {

    boolean inserir(Pedido pedido) throws SQLException;

    Pedido buscaId(int id) throws SQLException;
    List<Pedido> lista() throws SQLException;

    double totalPedidos() throws SQLException;

    double totalPedidosCliente(Cliente cliente) throws SQLException;

}
