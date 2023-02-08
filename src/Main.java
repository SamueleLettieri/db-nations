import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/ls_dbNations";
		String user = "root";
		String password = "root";
		
		
		
		try(Connection con = DriverManager.getConnection(url, user, password)) {
			
			String sql = "SELECT c.NAME, c.COUNTRY_ID, r.NAME, c2.NAME" 
							+" FROM COUNTRIES C "
							+" INNER JOIN REGIONS R "
							+" ON c.REGION_ID = r.REGION_ID "
							+" INNER JOIN CONTINENTS C2 "
							+" ON r.CONTINENT_ID = c2.CONTINENT_ID "
							+" ORDER BY r.NAME  DESC ";
			
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				
				try(ResultSet rs = ps.executeQuery(sql)) {
					
					while (rs.next()) {
						
						String countriName = rs.getString(1);
						int id = rs.getInt(2);
						String regionName = rs.getString(3);
						String continentName = rs.getString(4);
						
						System.out.println(countriName + " - " + id + " - " + regionName + " - " + continentName);
					}
					
				}
				
			} 
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
