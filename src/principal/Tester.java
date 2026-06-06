package principal;

public class Tester extends Usuario {
    public Tester(String nombre, String apellido, String email, String pais, String password) {
        super(nombre, apellido, email, pais, "TESTER", password);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("TESTER: " + getNombre() + " - " + getEmail());
    }
}
