package ifpr.pgua.eic.simuladorsubway.daos;

import ifpr.pgua.eic.simuladorsubway.daos.interfaces.BebidaDAO;
import ifpr.pgua.eic.simuladorsubway.db.FabricaConexoes;
import ifpr.pgua.eic.simuladorsubway.models.Bebida;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcBebidaDAO implements BebidaDAO {

    private static final String INSERT = "INSERT INTO bebidas(nome,valor) VALUES (?,?)";
    private static final String LISTA = "SELECT * FROM bebidas";
    private static final String UPDATE = "UPDATE bebidas SET nome=?, valor=? WHERE id=?";

    @Override
    public boolean inserir(Bebida bebida) throws SQLException {
        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(INSERT);

        pstmt.setString(1, bebida.getNome());
        pstmt.setDouble(2, bebida.getValor());

        int ret = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return ret == 1;
    }

    @Override
    public boolean atualizar(int id, Bebida bebida) throws SQLException {
        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(UPDATE);

        pstmt.setString(1,bebida.getNome());
        pstmt.setDouble(2,bebida.getValor());

        pstmt.setInt(3,id);

        int ret = pstmt.executeUpdate();

        return ret==1;
    }

    @Override
    public List<Bebida> lista() throws SQLException {
        ArrayList<Bebida> lista = new ArrayList<>();

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(LISTA);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            double valor = rs.getDouble("valor");

            Bebida bebida = new Bebida(id, nome, valor);

            lista.add(bebida);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return lista;
    }

    @Override
    public Bebida buscaId(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Bebida Bebida) throws SQLException {
        return false;
    }
}
