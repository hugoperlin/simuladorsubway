package ifpr.pgua.eic.simuladorsubway.daos;

import ifpr.pgua.eic.simuladorsubway.daos.interfaces.IngredienteDAO;
import ifpr.pgua.eic.simuladorsubway.db.FabricaConexoes;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.models.Sanduiche;

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
    private static final String LISTAINGREDIENTESSANDUICHE = "SELECT id_ingrediente,valor FROM sanduiche_ingrediente WHERE id_sanduiche=?";
    private static final String BUSCAID = "SELECT * FROM ingredientes WHERE id=?";

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
    public List<Ingrediente> ingredientesDoSanduiche(int idSanduiche) throws SQLException {

        List<Ingrediente> ingredientes = new ArrayList<>();

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(LISTAINGREDIENTESSANDUICHE);

        pstmt.setInt(1,idSanduiche);

        ResultSet rs = pstmt.executeQuery();


        while(rs.next()){
            int idIngrediente = rs.getInt("id_ingrediente");
            double valor = rs.getDouble("valor");

            Ingrediente ingrediente = buscaId(idIngrediente);
            ingrediente.setValor(valor);

            ingredientes.add(ingrediente);

        }

        rs.close();
        pstmt.close();
        conn.close();

        return ingredientes;
    }

    @Override
    public Ingrediente buscaId(int id) throws SQLException {
        Ingrediente ingrediente = null;

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(BUSCAID);

        pstmt.setInt(1,id);

        ResultSet rs = pstmt.executeQuery();


        while(rs.next()){
            int pk = rs.getInt("id");
            String nome = rs.getString("nome");
            String descricao = rs.getString("descricao");
            double valor = rs.getDouble("valor");

            ingrediente = new Ingrediente(pk,nome,descricao,valor);

        }

        rs.close();
        pstmt.close();
        conn.close();

        return ingrediente;
    }

    @Override
    public boolean delete(Ingrediente ingrediente) throws SQLException {
        return false;
    }
}
