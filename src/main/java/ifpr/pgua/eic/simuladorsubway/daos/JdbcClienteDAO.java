package ifpr.pgua.eic.simuladorsubway.daos;

import ifpr.pgua.eic.simuladorsubway.daos.interfaces.ClienteDAO;
import ifpr.pgua.eic.simuladorsubway.db.FabricaConexoes;
import ifpr.pgua.eic.simuladorsubway.models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcClienteDAO implements ClienteDAO {

    private static final String INSERT = "INSERT INTO clientes(nome,telefone,email) VALUES (?,?,?)";
    private static final String LISTA = "SELECT * FROM clientes";
    private static final String UPDATE = "UPDATE clientes SET nome=?, telefone=?, email=? WHERE id=?";
    private static final String BUSCAID = "SELECT * FROM clientes WHERE id=?";
    private static final String CLIENTEPEDIDO = "SELECT id_cliente FROM pedidos WHERE id=?";

    @Override
    public boolean inserir(Cliente cliente) throws SQLException {
        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(INSERT);

        pstmt.setString(1, cliente.getNome());
        pstmt.setString(2, cliente.getTelefone());
        pstmt.setString(3, cliente.getEmail());

        int ret = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return ret == 1;
    }

    @Override
    public boolean atualizar(int id, Cliente cliente) throws SQLException {
        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(UPDATE);

        pstmt.setString(1, cliente.getNome());
        pstmt.setString(2, cliente.getTelefone());
        pstmt.setString(3, cliente.getEmail());

        pstmt.setInt(4,id);

        int ret = pstmt.executeUpdate();

        return ret==1;
    }

    @Override
    public List<Cliente> lista() throws SQLException {
        ArrayList<Cliente> lista = new ArrayList<>();

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(LISTA);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String telefone = rs.getString("telefone");
            String email = rs.getString("email");

            Cliente cliente = new Cliente(id, nome, email,telefone);

            lista.add(cliente);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return lista;
    }

    @Override
    public Cliente buscaId(int id) throws SQLException {
        Cliente cliente=null;

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(BUSCAID);

        pstmt.setInt(1,id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            int pk = rs.getInt("id");
            String nome = rs.getString("nome");
            String telefone = rs.getString("telefone");
            String email = rs.getString("email");

            cliente = new Cliente(id, nome, email,telefone);

        }

        rs.close();
        pstmt.close();
        conn.close();

        return cliente;
    }

    @Override
    public Cliente buscaClienteDoPedido(int idPedido) throws SQLException {
        Cliente cliente = null;

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(CLIENTEPEDIDO);
        pstmt.setInt(1,idPedido);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            int idCliente = rs.getInt("id_cliente");

            cliente = buscaId(idCliente);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return cliente;
    }

    @Override
    public boolean delete(Cliente Cliente) throws SQLException {
        return false;
    }
}
