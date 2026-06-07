VC1 Funcionalidades, descripciones y datos 

Autenticación
- Login: Iniciar sesión como administrador (Email y Contraseña)
- Reiniciar contraseña: Recuperar cuenta (Email, Contraseña y Repetir contraseña)
- Logout: Cerrar sesión (Sí o Cancelar)

Gestión de cuentas
- Crear cuenta administrador: Dar alta de cuenta con Nombre, Apellido, Email, Contraseña, Repetir contraseña y País de nacimiento
- Crear cuenta tester: Dar alta de cuenta con Nombre, Apellido, Email, País de nacimiento, Contraseña por defecto y Perfil (Tester Junior, Tester Senior, Tester Líder)

Gestión de usuarios
- Ver usuarios: Ver usuarios registrados (Nombre, Apellido, Email, País, Perfil (Tester Junior/Senior/Líder o Administrador)
- (Derivado del anterior) Eliminar usuarios: Eliminar usuarios registrados en el sistema (Aceptar o Cancelar)
- Ver perfil de usuario: Ver detalles del perfil (Nombre, Apellido, Email, País, Perfil)
- (Derivado del anterior) Modificar datos de perfil de usuario: Cambiar detalles del perfil (Nombre, Apellido, Email, País, Perfil)


VC3 – Modelado con POO

Clases principales
- Usuario  
  Clase base con atributos privados, getters/setters y constructor.  
  Método `mostrarInfo()` para imprimir datos básicos.

- Admin  
  Subclase que hereda de `Usuario`.  
  Sobreescribe `mostrarInfo()` para mostrar rol de administrador.

- Tester  
  Subclase que hereda de `Usuario`.  
  Sobreescribe `mostrarInfo()` para mostrar rol de tester.

- SistemaUsuarios  
  Contiene un arreglo de usuarios (`Usuario[]` o `ArrayList<Usuario>`).  
  Métodos: `loginUsuario()`, `registrarUsuario()`, `mostrarMenu()`.  
  Precarga usuarios de prueba.

- Main  
  Clase principal que arranca el sistema y muestra el menú.

---

Diagrama UML

mermaid

classDiagram
    class Usuario {
        -nombre : String
        -apellido : String
        -email : String
        -pais : String
        -perfil : String
        -password : String
        +Usuario(nombre, apellido, email, pais, perfil, password)
        +getEmail() : String
        +getPassword() : String
        +getNombre() : String
        +getPerfil() : String
        +setPassword(password : String) : void
        +mostrarInfo() : void
    }

    class Admin {
        +Admin(nombre, apellido, email, pais, password)
        +mostrarInfo() : void
    }

    class Tester {
        +Tester(nombre, apellido, email, pais, password)
        +mostrarInfo() : void
    }

    class SistemaUsuarios {
        -usuarios : ArrayList<Usuario>
        -scanner : Scanner
        +SistemaUsuarios()
        +mostrarMenu() : void
        -loginUsuario() : void
        -registrarUsuario() : void
    }

    Usuario <|-- Admin
    Usuario <|-- Tester
    SistemaUsuarios --> Usuario
