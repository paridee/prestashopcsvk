package csvGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.h2.tools.DeleteDbFiles;

import csvGenerator.model.Product;

public class TestMain {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//H2 test
		
		String gs	=	"/home/paride/Scaricati/SG_LISTINO_CSV.CSV";
		String ledlux	=	"/home/paride/a2zworldsrl_sellrapido_list1.csv";
		String byz	=	"/home/paride/byzancia.csv";
		
		if(args.length>2){
        	gs		=	args[0];
        	ledlux	=	args[1];
        	byz		=	args[2];
        }
        
        try {
            // delete the H2 database named 'test' in the user home directory
            DeleteDbFiles.execute("~", "test", true);
            insertWithStatement();
            DeleteDbFiles.execute("~", "test", true);
            insertWithPreparedStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        SupplierProductsLoader load;
        ArrayList<Product> pr	=	new ArrayList<Product> ();
       // load	=	new LindiLoader();
       // pr.addAll(load.loadProducts());
        load	=	new GriffatiLoader();
        pr.addAll(load.loadProducts());
        load	=	new BigBuyLoader();
        pr.addAll(load.loadProducts());
        load	=	new GSLoader(gs);
        pr.addAll(load.loadProducts());
        load	=	new Dropshipping4youLoader();
        pr.addAll(load.loadProducts());
        load	=	new LedLuxLoader(ledlux);
        pr.addAll(load.loadProducts());
        load	=	new ByzanciaLoader(byz);
        pr.addAll(load.loadProducts());
        ProductRepository rep	=	new ProductRepository();
        rep.addProducts(pr);
        List<String> csv	=	rep.getCsvWithAllFields('|',10,1000);
        int count	=	0;
        
        String mailMessage	=	"Report del giorno "+(new Date()).toString()+"\nCategorie sconosciute:\n";
        
        for(String s:csv){
        	if(count<10){
        		System.out.println("ROW "+s);	
        	}
        	count++;
        }
        System.out.println("unknown categories");
        for(String s:Utils.getUnkn()){
        	System.out.println(s);
        	mailMessage=mailMessage+s+"\n";
        }
        System.out.println("Categories:");
        for(String s:rep.getCategores()){
        	System.out.println(s);
        }
		System.out.println("Going to create string for file creation");
		FileWriter 		fw	=	null;
		BufferedWriter 	bw	=	null;
		try {

			fw 	= 	new FileWriter("output.csv");
			bw 	= 	new BufferedWriter(fw);
			int counter	=	0;
			for(String s:csv){
				if(counter%100==99){
					System.out.println("Scritte "+counter+" rows");
				}
				//if(counter<500){
					bw.write(s+"\n");	
				//}	
				counter++;
			}
			mailMessage	=	mailMessage+"\nProcessati "+counter+" prodotti";
			System.out.println("Done");
			MailSender sender	=	new MailSender();
			sender.sendEmail("paride.casulli@gmail.com,simass81@gmail.com,alep981@gmail.com", "[Print Solutions Roma] Report Generazione Csv", mailMessage);
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
    // H2 SQL Prepared Statement Example
    private static void insertWithPreparedStatement() throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;
        PreparedStatement insertPreparedStatement = null;
        PreparedStatement selectPreparedStatement = null;

        String CreateQuery = "CREATE TABLE PERSON(id int primary key, name varchar(255))";
        String InsertQuery = "INSERT INTO PERSON" + "(id, name) values" + "(?,?)";
        String SelectQuery = "select * from PERSON";
        try {
            connection.setAutoCommit(false);
           
            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
           
            insertPreparedStatement = connection.prepareStatement(InsertQuery);
            insertPreparedStatement.setInt(1, 1);
            insertPreparedStatement.setString(2, "Jose");
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
           
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            System.out.println("H2 Database inserted through PreparedStatement");
            while (rs.next()) {
                System.out.println("Id "+rs.getInt("id")+" Name "+rs.getString("name"));
            }
            selectPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    // H2 SQL Statement Example
    private static void insertWithStatement() throws SQLException {
        Connection connection = getDBConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE PERSON(id int primary key, name varchar(255))");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(1, 'Anju')");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(2, 'Sonia')");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(3, 'Asha')");

            ResultSet rs = stmt.executeQuery("select * from PERSON");
            System.out.println("H2 Database inserted through Statement");
            while (rs.next()) {
                System.out.println("Id "+rs.getInt("id")+" Name "+rs.getString("name"));
            }
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

}
