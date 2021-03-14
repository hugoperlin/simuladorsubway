package ifpr.pgua.eic.simuladorsubway;

import ifpr.pgua.eic.simuladorsubway.daos.*;
import ifpr.pgua.eic.simuladorsubway.daos.interfaces.*;
import ifpr.pgua.eic.simuladorsubway.db.DataBase;
import ifpr.pgua.eic.simuladorsubway.models.*;

import java.sql.SQLException;
import java.util.List;

public class MainDB {


    public static void main(String[] args) throws SQLException {

        ClienteDAO clienteDAO = new JdbcClienteDAO();
        PedidoDAO pedidoDAO = new JDBCPedidoDAO();

        Cliente cliente = clienteDAO.buscaId(2);

        System.out.println(pedidoDAO.totalPedidosCliente(cliente));
        System.out.println(pedidoDAO.totalPedidos());

        /*
        Sanduiche sanduiche = sanduicheDAO.buscaId(3);
        Bebida bebida = bebidaDAO.buscaId(1);
        Cliente cliente = clienteDAO.buscaId(2);

        Pedido pedido = new Pedido(sanduiche,bebida,cliente);

        pedidoDAO.inserir(pedido);
        */



        /*



        List<Ingrediente> ingredientes = ingredienteDAO.lista();

        Sanduiche sanduiche = new Sanduiche();

        sanduiche.adiciona(ingredientes.get(0));
        sanduiche.adiciona(ingredientes.get(1));
        sanduiche.adiciona(ingredientes.get(2));

        sanduicheDAO.inserir(sanduiche);
*/


        //DataBase db = new DataBase();


         //db.criarBanco();

//        db.insereIngrediente(new Ingrediente("Tomate","Um tomate",1.0));
//        db.insereIngrediente(new Ingrediente("Alface","Uma alface",2.0));
//
//        List<Ingrediente> lista = db.listaIngredientes();
//
//        for(Ingrediente i:lista){
//            System.out.println(i.toString());
//        }
//
//        System.out.println(db.buscaPorId(1));




    }


}
