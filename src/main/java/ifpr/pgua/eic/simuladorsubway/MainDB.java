package ifpr.pgua.eic.simuladorsubway;

import ifpr.pgua.eic.simuladorsubway.daos.JDBCIngredienteDAO;
import ifpr.pgua.eic.simuladorsubway.daos.JDBCSanduicheDAO;
import ifpr.pgua.eic.simuladorsubway.daos.interfaces.IngredienteDAO;
import ifpr.pgua.eic.simuladorsubway.daos.interfaces.SanduicheDAO;
import ifpr.pgua.eic.simuladorsubway.db.DataBase;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.models.Sanduiche;

import java.sql.SQLException;
import java.util.List;

public class MainDB {


    public static void main(String[] args) throws SQLException {

        SanduicheDAO sanduicheDAO = new JDBCSanduicheDAO();
        IngredienteDAO ingredienteDAO = new JDBCIngredienteDAO();

        Sanduiche sanduiche = sanduicheDAO.buscaId(3);
        sanduiche.adicionaTodos(ingredienteDAO.ingredientesDoSanduiche(sanduiche.getId()));


        System.out.println(sanduiche.toString());


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
