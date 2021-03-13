package ifpr.pgua.eic.simuladorsubway.repositories;

import ifpr.pgua.eic.simuladorsubway.daos.interfaces.BebidaDAO;
import ifpr.pgua.eic.simuladorsubway.models.Bebida;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.BebidaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class BebidaRepositoryImpl implements BebidaRepository {

    private ObservableList<Bebida> bebidas;
    private BebidaDAO bebidaDAO;

    public BebidaRepositoryImpl(BebidaDAO bebidaDAO){
        this.bebidaDAO = bebidaDAO;
        bebidas = FXCollections.observableArrayList();
    }


    @Override
    public boolean adicionar(Bebida bebida) throws SQLException {

        return bebidaDAO.inserir(new Bebida(bebidas.size(),bebida.getNome(),bebida.getValor()));

    }

    @Override
    public boolean editar(int id, Bebida bebida) throws SQLException{

        return bebidaDAO.atualizar(id,bebida);
    }

    @Override
    public ObservableList<Bebida> lista() throws SQLException{
        this.bebidas.clear();
        this.bebidas.addAll(this.bebidaDAO.lista());
        return FXCollections.unmodifiableObservableList(bebidas);
    }
}
