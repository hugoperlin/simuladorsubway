package ifpr.pgua.eic.simuladorsubway;

import ifpr.pgua.eic.simuladorsubway.db.DataBase;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;

import java.util.List;

public class MainDB {


    public static void main(String[] args) {

        DataBase db = new DataBase();


         db.criarBanco();

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
