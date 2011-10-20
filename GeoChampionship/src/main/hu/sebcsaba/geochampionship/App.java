package hu.sebcsaba.geochampionship;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class App {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, ConfigException {
		Properties config = getConfig(args);
		Connection conn = openConnection(config);
		ChampionshipWriter w1 = new EtapChampionshipWriter(conn,config);
		w1.run();
		ChampionshipWriter w2 = new SzlalomChampionshipWriter(conn,config);
		w2.run();
		conn.close();
	}

	private static Connection openConnection(Properties config) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://"+config.getProperty("database.host")+"/"+config.getProperty("database.name");
		Connection conn = DriverManager.getConnection(url, config.getProperty("database.username"), config.getProperty("database.password"));
		return conn;
	}

	private static Properties getConfig(String[] args) throws IOException {
		File configFile = new File( (args.length>0) ? args[0] : "config.properties" );
		Properties config = new Properties();
		config.load(new FileInputStream(configFile));
		return config;
	}
	

}
