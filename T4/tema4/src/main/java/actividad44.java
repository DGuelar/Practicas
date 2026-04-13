import java.sql.*;

public class actividad44 {

    public static void contarFilas(Connection conn) throws SQLException {

        String sql = "SELECT * FROM employees";

        Statement st = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        ResultSet rs = st.executeQuery(sql);

        if (rs.last()) {
            int total = rs.getRow();
            System.out.println("Filas: " + total);
        } else {
            System.out.println("No hay filas");
        }

        int totalFilas = rs.getRow();

        System.out.println("Número de filas: " + totalFilas);

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

            contarFilas(con);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}