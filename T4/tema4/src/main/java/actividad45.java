import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class actividad45 {
    public static void mostrarClientesPorDni(Connection conn, String[] dnis) throws SQLException {

        String sql = "SELECT * FROM CLIENTES WHERE DNI=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (String dniBuscado : dnis) {

                pstmt.setString(1, dniBuscado);

                try (ResultSet rs = pstmt.executeQuery()) {

                    if (rs.next()) {
                        String dni = rs.getString("DNI");
                        String apellidos = rs.getString("APELLIDOS");
                        String cp = rs.getString("CP");

                        System.out.println("Cliente encontrado:");
                        System.out.println("DNI: " + dni);
                        System.out.println("APELLIDOS: " + apellidos);
                        System.out.println("CP: " + cp);
                        System.out.println("---------------------------");
                    } else {
                        System.out.println("No existe ningún cliente con DNI: " + dniBuscado);
                        System.out.println("---------------------------");
                    }
                }
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

            String[] dnis = {
                    "78901234X",
                    "89012345E",
                    "56789012B",
                    "00000000Z"
            };

            mostrarClientesPorDni(con, dnis);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}