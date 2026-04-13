import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import POJO.Company;

public class actividad46 {

    public static void crearTablaCompanies(Connection conn) throws SQLException {

        String sql = """
                CREATE TABLE IF NOT EXISTS COMPANIES (
                    CIF CHAR(9) PRIMARY KEY,
                    NOMBRE VARCHAR(100) NOT NULL,
                    SECTOR VARCHAR(100) NOT NULL
                )
                """;

        try (Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Tabla COMPANIES creada correctamente o ya existente.");
        }
    }

    public static void insertarCompaniesBatch(Connection conn, List<Company> companies) throws SQLException {

        String sql = "INSERT INTO COMPANIES (CIF, NOMBRE, SECTOR) VALUES (?, ?, ?)";

        boolean autoCommitOriginal = conn.getAutoCommit();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            for (Company company : companies) {
                pstmt.setString(1, company.getCif());
                pstmt.setString(2, company.getNombre());
                pstmt.setString(3, company.getSector());
                pstmt.addBatch();
            }

            int[] resultados = pstmt.executeBatch();

            conn.commit();
            System.out.println("Transacción confirmada correctamente.");
            System.out.println("Número de inserciones ejecutadas: " + resultados.length);

        } catch (SQLException e) {
            System.out.println("Se ha producido un error. Se realizará rollback.");

            try {
                conn.rollback();
                System.out.println("Rollback realizado correctamente.");
            } catch (SQLException ex) {
                System.out.println("Error al hacer rollback.");
                ex.printStackTrace();
            }

            throw e;

        } finally {
            conn.setAutoCommit(autoCommitOriginal);
        }
    }

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hr_database",
                    "root",
                    "password"
            );

            crearTablaCompanies(con);

            List<Company> companies = Arrays.asList(
                    new Company("12345678A", "Tech Solutions", "Tecnologia"),
                    new Company("23456789B", "Green Energy", "Energia"),
                    new Company("34567890C", "Food Market", "Alimentacion")
            );

            insertarCompaniesBatch(con, companies);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}