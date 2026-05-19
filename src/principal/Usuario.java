package principal;

public class Usuario {
    String nombre;
    String apellido;
    String email;
    String pais;
    String perfil;
    String password;

    public Usuario(String nombre, String apellido, String email, String pais, String perfil, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.pais = pais;
        this.perfil = perfil;
        this.password = password;
    }
}
