import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class actividad48 {

    public static void mostrarFila(ResultSet rs) throws Exception {
        ResultSetMetaData meta = rs.getMetaData();
        int numColumnas = meta.getColumnCount();

        System.out.println("Fila " + rs.getRow());

        for (int i = 1; i <= numColumnas; i++) {
            String nombreColumna = meta.getColumnName(i);
            String valor = rs.getString(i);
            System.out.println(nombreColumna + ": " + valor);
        }

        System.out.println("-----------------------------");
    }

    public static void navegarTabla(Connection conn, String nombreTabla) throws Exception {
        String sql = "SELECT * FROM " + nombreTabla;

        try (
                Statement st = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
                ResultSet rs = st.executeQuery(sql);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
        ) {

            if (!rs.first()) {
                System.out.println("La tabla no contiene filas.");
                return;
            }

            mostrarFila(rs);

            while (true) {
                System.out.print("Introduce comando (k=siguiente, d=anterior, número=ir a fila, .=salir): ");
                String comando = br.readLine();

                if (comando.equals(".")) {
                    System.out.println("Fin del programa.");
                    break;
                } else if (comando.equals("k")) {
                    if (rs.next()) {
                        mostrarFila(rs);
                    } else {
                        System.out.println("No se puede avanzar. Ya estás en la última fila.");
                        rs.last();
                    }
                } else if (comando.equals("d")) {
                    if (rs.previous()) {
                        mostrarFila(rs);
                    } else {
                        System.out.println("No se puede retroceder. Ya estás en la primera fila.");
                        rs.first();
                    }
                } else {
                    try {
                        int fila = Integer.parseInt(comando);

                        if (fila <= 0) {
                            System.out.println("Debes introducir un número de fila mayor que 0.");
                        } else if (rs.absolute(fila)) {
                            mostrarFila(rs);
                        } else {
                            System.out.println("La fila " + fila + " no existe.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Comando no válido.");
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

            navegarTabla(con, "employees");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}