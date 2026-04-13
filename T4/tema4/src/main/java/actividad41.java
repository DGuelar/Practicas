import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class actividad41 {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hr_database",
                    "root",
                    "password"
            );

            Statement st = con.createStatement();

            st.executeUpdate(
                    "DELETE FROM CLIENTES WHERE DNI NOT IN ('78901234X', '89012345E', '56789012B')"
            );

            st.executeUpdate(
                    "UPDATE CLIENTES SET APELLIDOS='NADALES', CP='44126' WHERE DNI='78901234X'"
            );

            st.executeUpdate(
                    "UPDATE CLIENTES SET APELLIDOS='ROJAS', CP=NULL WHERE DNI='89012345E'"
            );

            st.executeUpdate(
                    "UPDATE CLIENTES SET APELLIDOS='SAMPER', CP='29730' WHERE DNI='56789012B'"
            );

            System.out.println("Tabla CLIENTES actualizada correctamente.");

            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
