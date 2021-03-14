package ifpr.pgua.eic.simuladorsubway;

import ifpr.pgua.eic.simuladorsubway.daos.*;
import ifpr.pgua.eic.simuladorsubway.daos.interfaces.*;
import ifpr.pgua.eic.simuladorsubway.db.DataBase;
import ifpr.pgua.eic.simuladorsubway.models.*;

import java.sql.SQLException;
import java.util.List;

public class MainDB {


    public static void main(String[] args) throws SQLException {

        SanduicheDAO sanduicheDAO = new JDBCSanduicheDAO();
        IngredienteDAO ingredienteDAO = new JDBCIngredienteDAO();
        BebidaDAO bebidaDAO = new JdbcBebidaDAO();
        ClienteDAO clienteDAO = new JdbcClienteDAO();
        PedidoDAO pedidoDAO = new JDBCPedidoDAO();

        /*
        Sanduiche sanduiche = sanduicheDAO.buscaId(3);
        Bebida bebida = bebidaDAO.buscaId(1);
        Cliente cliente = clienteDAO.buscaId(2);

        Pedido pedido = new Pedido(sanduiche,bebida,cliente);

        pedidoDAO.inserir(pedido);
        */

        Pedido rec = pedidoDAO.buscaId(1);
        rec.setBebida(bebidaDAO.buscaBebidaDoPedido(rec.getId()));
        rec.setCliente(clienteDAO.buscaClienteDoPedido(rec.getId()));
        rec.setSanduiche(sanduicheDAO.buscaSanduicheDoPedido(rec.getId()));

        System.out.println(rec);


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
