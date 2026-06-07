package principal;

public class Tester extends Usuario {

    private String areaAsignada;

    public Tester(String nombre, String apellido, String email, String pais, String password) {
        super(nombre, apellido, email, pais, "TESTER", password);
        this.areaAsignada = "General";
    }

    public String getAreaAsignada() { return areaAsignada; }
    public void setAreaAsignada(String areaAsignada) { this.areaAsignada = areaAsignada; }

    @Override
    public void mostrarInfo() {
        System.out.println("TESTER: " + getNombre() + " - " + getEmail() + " | Área: " + areaAsignada);
    }
}