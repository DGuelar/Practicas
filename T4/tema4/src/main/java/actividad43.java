import java.sql.*;

public class actividad43 {

    public static void imprimirNombresReverso(Connection conn) throws SQLException {

        String sql = "SELECT CONCAT(first_name, ' ', last_name) AS name FROM employees";

        Statement st = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        ResultSet rs = st.executeQuery(sql);

        rs.last();

        while (rs.getRow() > 0) {

            String nombre = rs.getString("name");
            System.out.println(nombre);

            rs.previous();
        }

        rs.close();
        st.close();
    }

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hr_database",
                    "root",
                    "password"
            );

            imprimirNombresReverso(con);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}