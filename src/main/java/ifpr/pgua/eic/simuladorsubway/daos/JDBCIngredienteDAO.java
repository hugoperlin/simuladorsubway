package ifpr.pgua.eic.simuladorsubway.daos;

import ifpr.pgua.eic.simuladorsubway.daos.interfaces.IngredienteDAO;
import ifpr.pgua.eic.simuladorsubway.db.FabricaConexoes;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCIngredienteDAO implements IngredienteDAO {

    private static final String INSERT = "INSERT INTO ingredientes(nome,descricao,valor) VALUES (?,?,?)";
    private static final String LISTA = "SELECT * FROM ingredientes";
    private static final String UPDATE = "UPDATE ingredientes SET nome=?, descricao=?, valor=? WHERE id=?";

    @Override
    public boolean inserir(Ingrediente ingrediente) throws SQLException {

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(INSERT);

        pstmt.setString(1, ingrediente.getNome());
        pstmt.setString(2, ingrediente.getDescricao());
        pstmt.setDouble(3, ingrediente.getValor());

        int ret = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return ret == 1;
    }

    @Override
    public boolean atualizar(int id, Ingrediente ingrediente) throws SQLException {

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(UPDATE);

        pstmt.setString(1,ingrediente.getNome());
        pstmt.setString(2,ingrediente.getDescricao());
        pstmt.setDouble(3,ingrediente.getValor());

        pstmt.setInt(4,id);

        int ret = pstmt.executeUpdate();

        return ret==1;
    }

    @Override
    public List<Ingrediente> lista() throws SQLException {
        ArrayList<Ingrediente> lista = new ArrayList<>();

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(LISTA);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String descricao = rs.getString("descricao");
            double valor = rs.getDouble("valor");

            Ingrediente ingrediente = new Ingrediente(id, nome, descricao, valor);

            lista.add(ingrediente);
        }

        rs.close();
        pstmt.close();
        conn.close();


        return lista;
    }

    @Override
    public Ingrediente buscaId(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Ingrediente ingrediente) throws SQLException {
        return false;
    }
}
