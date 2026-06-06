package principal;

public class Usuario {
    private String nombre;
    private String apellido;
    private String email;
    private String pais;
    private String perfil;
    private String password;

    public Usuario(String nombre, String apellido, String email, String pais, String perfil, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.pais = pais;
        this.perfil = perfil;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getNombre() { return nombre; }
    public String getPerfil() { return perfil; }

    public void setPassword(String password) { this.password = password; }

    public void mostrarInfo() {
        System.out.println(nombre + " " + apellido + " - " + email + " (" + pais + ") Perfil: " + perfil);
    }
}
