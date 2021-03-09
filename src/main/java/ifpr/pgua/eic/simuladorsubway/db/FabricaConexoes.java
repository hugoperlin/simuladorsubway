package ifpr.pgua.eic.simuladorsubway.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexoes {

    private static final int MAX_CONNECTIONS = 5;
    private static final String URL = "jdbc:sqlite:sanduiche.sqlite";

    private Connection[] connections;

    private static FabricaConexoes instance = new FabricaConexoes();

    private FabricaConexoes(){

        connections = new Connection[MAX_CONNECTIONS];

    }

    public static Connection getConnection() throws SQLException {

        for(int i=0;i<MAX_CONNECTIONS;i++){
            if(instance.connections[i] == null || instance.connections[i].isClosed()){
                instance.connections[i] = DriverManager.getConnection(URL);
                return instance.connections[i];
            }
        }

        throw new SQLException("Limite de conexões atingido!!");

    }


}
