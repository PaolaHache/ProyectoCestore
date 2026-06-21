package principal;

public abstract class Usuario {
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

    // Getters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
    public String getPais() { return pais; }
    public String getPerfil() { return perfil; }
    public String getPassword() { return password; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEmail(String email) { this.email = email; }
    public void setPais(String pais) { this.pais = pais; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
    public void setPassword(String password) { this.password = password; }

    // Métodos abstractos
    public abstract void mostrarInfo();
    public abstract void accionEspecial();
}
