package co.analisys.gimnasio.dto;

public class MiembroRegistroMessage {
    private String nombre;
    private String email;

    public MiembroRegistroMessage() {}

    public MiembroRegistroMessage(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MiembroRegistroMessage{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
