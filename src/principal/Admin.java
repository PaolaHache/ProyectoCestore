package principal;

public class Admin extends Usuario {

    private String nivelAcceso;

    public Admin(String nombre, String apellido, String email, String pais, String password) {
        super(nombre, apellido, email, pais, "ADMIN", password);
        this.nivelAcceso = "TOTAL";
    }

    public String getNivelAcceso() { return nivelAcceso; }
    public void setNivelAcceso(String nivelAcceso) { this.nivelAcceso = nivelAcceso; }

    @Override
    public void mostrarInfo() {
        System.out.println("ADMIN: " + getNombre() + " - " + getEmail() + " | Acceso: " + nivelAcceso);
    }

    @Override
    public void accionEspecial() {
        System.out.println("Gestionando usuarios y configuraciones...");
    }
}
