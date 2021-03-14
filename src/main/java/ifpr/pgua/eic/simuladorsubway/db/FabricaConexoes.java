package ifpr.pgua.eic.simuladorsubway.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FabricaConexoes {

    private static final int MAX_CONNECTIONS = 5;
    //jdbc:mysql://infoguaifpr.com.br:3136
    private static String URL;

    private static String USERNAME;
    private static String PASSWORD;

    private Connection[] connections;

    private static FabricaConexoes instance = new FabricaConexoes();

    private FabricaConexoes(){

        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("/config/config.properties");

        try{
            properties.load(inputStream);

            URL = properties.getProperty("DB_URL");
            USERNAME = properties.getProperty("DB_USERNAME");
            PASSWORD = properties.getProperty("DB_PASSWORD");

        }catch (IOException e){
            e.printStackTrace();
        }


        connections = new Connection[MAX_CONNECTIONS];

    }

    public static Connection getConnection() throws SQLException {

        for(int i=0;i<MAX_CONNECTIONS;i++){
            if(instance.connections[i] == null || instance.connections[i].isClosed()){
                instance.connections[i] = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                return instance.connections[i];
            }
        }

        throw new SQLException("Limite de conexões atingido!!");

    }



}
