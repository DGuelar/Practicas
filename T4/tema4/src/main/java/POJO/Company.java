package POJO;

public class Company {
    private String cif;
    private String nombre;
    private String sector;

    public Company(String cif, String nombre, String sector) {
        this.cif = cif;
        this.nombre = nombre;
        this.sector = sector;
    }

    public String getCif() {
        return cif;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSector() {
        return sector;
    }

    @Override
    public String toString() {
        return "Company{" +
                "cif='" + cif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sector='" + sector + '\'' +
                '}';
    }
}
