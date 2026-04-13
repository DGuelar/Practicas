import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Types;

public class actividad47 {
    public static void borrarFuncionSiExiste(Connection conn) throws Exception {
        String sql = "DROP FUNCTION IF EXISTS obtener_apellidos_cliente";

        try (Statement st = conn.createStatement()) {
            st.execute(sql);
            System.out.println("Función anterior eliminada si existía.");
        }
    }

    public static void crearFuncion(Connection conn) throws Exception {
        String sql =
                "CREATE FUNCTION obtener_apellidos_cliente(dni_busqueda CHAR(9)) " +
                        "RETURNS VARCHAR(32) " +
                        "DETERMINISTIC " +
                        "BEGIN " +
                        "    DECLARE apellidos_cliente VARCHAR(32); " +
                        "    SELECT APELLIDOS INTO apellidos_cliente " +
                        "    FROM CLIENTES " +
                        "    WHERE DNI = dni_busqueda; " +
                        "    RETURN apellidos_cliente; " +
                        "END";

        try (Statement st = conn.createStatement()) {
            st.execute(sql);
            System.out.println("Función creada correctamente.");
        }
    }
    public static String obtenerApellidosCliente(Connection conn, String dni) throws Exception {
        String sql = "{ ? = call obtener_apellidos_cliente(?) }";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2, dni);
            stmt.execute();
            return stmt.getString(1);
        }
    }

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hr_database",
                    "root",
                    "password"
            );

            borrarFuncionSiExiste(con);
            crearFuncion(con);

            String dni = "78901234X";
            String apellidos = obtenerApellidosCliente(con, dni);

            System.out.println("DNI consultado: " + dni);
            System.out.println("Apellidos devueltos por la función: " + apellidos);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}