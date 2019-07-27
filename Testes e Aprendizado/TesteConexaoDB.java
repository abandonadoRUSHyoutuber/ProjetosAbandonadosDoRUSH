import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteConexaoDB {

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			Statement st = con.createStatement();
			//st.execute("CREATE TABLE teste( id int primary key); ");
			System.out.println("Conexão efetuada com sucesso!");
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
