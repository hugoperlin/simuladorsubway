package ifpr.pgua.eic.simuladorsubway.daos;

import ifpr.pgua.eic.simuladorsubway.daos.interfaces.SanduicheDAO;
import ifpr.pgua.eic.simuladorsubway.db.FabricaConexoes;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.models.Sanduiche;

import java.sql.*;

public class JDBCSanduicheDAO implements SanduicheDAO {

    private static String INSERE = "INSERT INTO sanduiches(valor) values(?)";
    private static String INSERE_INGREDIENTE = "INSERT INTO sanduiche_ingrediente(id_sanduiche,id_ingrediente,valor) values(?,?,?)";
    private static String BUSCAID = "SELECT * FROM sanduiches where id=?";
    private static String SANDUICHEPEDIDO = "SELECT id_sanduiche FROM pedidos WHERE id=?";

    @Override
    public boolean inserir(Sanduiche sanduiche) throws SQLException {

        Connection conn = FabricaConexoes.getConnection();


        //inserindo o sanduiche
        PreparedStatement pstmt = conn.prepareStatement(INSERE,Statement.RETURN_GENERATED_KEYS);

        pstmt.setDouble(1,sanduiche.getValorTotal());

        pstmt.executeUpdate();

        //pegando o id do sanduiche criad
        ResultSet rsId = pstmt.getGeneratedKeys();
        rsId.next();
        int id = rsId.getInt(1);
        rsId.close();
        pstmt.close();


        sanduiche.setId(id);


        pstmt = conn.prepareStatement(INSERE_INGREDIENTE);

        //inserindo os ingredientes do sanduiche na tabela de relacionamento
        for(Ingrediente i:sanduiche.getIngredientes()){

            pstmt.setInt(1,id);
            pstmt.setInt(2,i.getId());
            pstmt.setDouble(3,i.getValor());

            pstmt.executeUpdate();
        }

        pstmt.close();
        conn.close();

        return false;
    }



    @Override
    public Sanduiche buscaId(int id) throws SQLException {

        Sanduiche sanduiche = null;

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(BUSCAID);

        pstmt.setInt(1,id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            int pk = rs.getInt("id");
            double valor = rs.getDouble("valor");

            sanduiche = new Sanduiche(pk,valor);

        }

        rs.close();
        pstmt.close();
        conn.close();

        return sanduiche;
    }

    @Override
    public Sanduiche buscaSanduicheDoPedido(int idPedido) throws SQLException {
        Sanduiche sanduiche = null;

        Connection conn = FabricaConexoes.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(SANDUICHEPEDIDO);
        pstmt.setInt(1,idPedido);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            int idSanduiche = rs.getInt("id_sanduiche");

            sanduiche = buscaId(idSanduiche);
        }

        rs.close();
        pstmt.close();
        conn.close();


        return sanduiche;
    }
}
