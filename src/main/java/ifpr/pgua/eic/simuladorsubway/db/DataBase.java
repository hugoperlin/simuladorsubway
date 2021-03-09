package ifpr.pgua.eic.simuladorsubway.db;

import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataBase {


    public void criarBanco(){

        try{
            Connection conn = FabricaConexoes.getConnection();


            Statement stm = conn.createStatement();


            String sql = "CREATE TABLE ingredientes(id integer primary key autoincrement, " +
                    "                               nome varchar(255) not null," +
                    "                               descricao varchar(255) not null," +
                    "                               valor double not null );";

            stm.execute(sql);

            System.out.println("Tabela criada...");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void insereIngrediente(Ingrediente ingrediente){


        try {

            Connection conn = FabricaConexoes.getConnection();

            String sql = "INSERT INTO ingredientes(nome,descricao,valor) VALUES (?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,ingrediente.getNome());
            pstmt.setString(2,ingrediente.getDescricao());
            pstmt.setDouble(3,ingrediente.getValor());

            int ret = pstmt.executeUpdate();

            System.out.println("Executou..."+ret);


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public List<Ingrediente> listaIngredientes(){
        ArrayList<Ingrediente> lista = new ArrayList<>();

        try{

            Connection conn = FabricaConexoes.getConnection();

            String sql = "SELECT * FROM ingredientes";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){

                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");

                Ingrediente ingrediente = new Ingrediente(id,nome,descricao,valor);

                lista.add(ingrediente);
            }

            rs.close();
            pstmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return lista;
    }

    public Ingrediente buscaPorId(int id){

        Ingrediente ingrediente = null;

        try{
            Connection conn = FabricaConexoes.getConnection();

            String sql = "SELECT id as pk, nome, descricao, valor FROM ingredientes WHERE id=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,id);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int pk = rs.getInt("pk");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");

                ingrediente = new Ingrediente(pk,nome,descricao,valor);

            }


            rs.close();
            pstmt.close();
            conn.close();


        }catch (SQLException e){
            e.printStackTrace();
        }

        return ingrediente;


    }


}
