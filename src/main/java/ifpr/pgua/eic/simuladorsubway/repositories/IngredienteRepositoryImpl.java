package ifpr.pgua.eic.simuladorsubway.repositories;

import ifpr.pgua.eic.simuladorsubway.daos.interfaces.IngredienteDAO;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.IngredienteRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class IngredienteRepositoryImpl implements IngredienteRepository {

    private ObservableList<Ingrediente> ingredientes;
    private IngredienteDAO ingredienteDAO;

    public IngredienteRepositoryImpl(IngredienteDAO ingredienteDAO){

        ingredientes = FXCollections.observableArrayList();
        this.ingredienteDAO = ingredienteDAO;

    }

    public boolean adicionar(Ingrediente ingrediente) throws SQLException {

        return ingredienteDAO.inserir(ingrediente);


    }

    public boolean editar(int id, Ingrediente ingrediente) throws SQLException{

        return ingredienteDAO.atualizar(id,ingrediente);
    }

    public ObservableList<Ingrediente> lista() throws SQLException{

        ingredientes.clear();
        ingredientes.addAll(ingredienteDAO.lista());

        return FXCollections.unmodifiableObservableList(ingredientes);
    }


}
