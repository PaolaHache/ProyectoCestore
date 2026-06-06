package principal;

public class Admin extends Usuario {
    public Admin(String nombre, String apellido, String email, String pais, String password) {
        super(nombre, apellido, email, pais, "ADMIN", password);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("ADMIN: " + getNombre() + " - " + getEmail());
    }
}
