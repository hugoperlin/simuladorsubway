package ifpr.pgua.eic.simuladorsubway.daos;

import ifpr.pgua.eic.simuladorsubway.daos.interfaces.PedidoDAO;
import ifpr.pgua.eic.simuladorsubway.db.FabricaConexoes;
import ifpr.pgua.eic.simuladorsubway.models.Pedido;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class JDBCPedidoDAO implements PedidoDAO {

    private static String INSERE = "INSERT INTO pedidos(data,valor,id_sanduiche,id_cliente) VALUES (?,?,?,?)";
    private static String INSEREBEBIDAPEDIDO = "INSERT INTO pedido_bebida(id_pedido,id_bebida,valor) VALUES (?,?,?)";
    private static String BUSCAID = "SELECT * FROM pedidos WHERE id=?";

    @Override
    public boolean inserir(Pedido pedido) throws SQLException {

        Connection conn = FabricaConexoes.getConnection();

        //inserindo pedido
        PreparedStatement pstmt = conn.prepareStatement(INSERE);

        pstmt.setTimestamp(1, Timestamp.valueOf(pedido.getData()));
        pstmt.setDouble(2,pedido.getValorTotal());
        pstmt.setInt(3,pedido.getSanduiche().getId());
        pstmt.setInt(4,pedido.getCliente().getId());

        int ret = pstmt.executeUpdate();

        //recuperando o id do pedido
        ResultSet rs = pstmt.getGeneratedKeys();
        int idPedido = rs.getInt(1);
        rs.close();
        pstmt.close();

        pstmt = conn.prepareStatement(INSEREBEBIDAPEDIDO);

        pstmt.setInt(1,idPedido);
        pstmt.setInt(2,pedido.getBebida().getId());
        pstmt.setDouble(3,pedido.getBebida().getValor());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return ret == 1;
    }

    @Override
    public Pedido buscaId(int id) throws SQLException {

        Pedido pedido = null;

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(BUSCAID);

        pstmt.setInt(1,id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            int pk = rs.getInt("id");
            LocalDateTime data = rs.getTimestamp("data").toLocalDateTime();
            double valor = rs.getDouble("valor");

            pedido = new Pedido(pk,null,null,null,data,valor);

        }

        rs.close();
        pstmt.close();
        conn.close();

        return pedido;
    }

    @Override
    public List<Pedido> lista() throws SQLException {
        return null;
    }
}
