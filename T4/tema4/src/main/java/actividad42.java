import java.sql.*;

public class actividad42 {
    public static void imprimirRegistros2(Connection conn) throws SQLException {

        String sql = "SELECT * FROM CLIENTES";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                String dni = rs.getString("DNI");
                String apellidos = rs.getString("APELLIDOS");

                int cp = rs.getInt("CP");

                System.out.println(dni + " - " + apellidos + " - " + cp);
            }
        }
    }

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hr_database",
                    "root",
                    "password"
            );

            imprimirRegistros2(con);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
