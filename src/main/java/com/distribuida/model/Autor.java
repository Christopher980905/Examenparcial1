package com.distribuida.model;

public class Autor {
    private int idAutor;
    private String nombreAutor;
    private String apellidoAutor;
    private String paisAutor;
    private String direccionAutor;
    private String telefonoAutor;
    private String emailAutor;

    public Autor() {}

    public Autor(int idAutor, String nombreAutor, String apellidoAutor, String paisAutor, String direccionAutor, String telefonoAutor, String emailAutor) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.paisAutor = paisAutor;
        this.direccionAutor = direccionAutor;
        this.telefonoAutor = telefonoAutor;
        this.emailAutor = emailAutor;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getApellidoAutor() {
        return apellidoAutor;
    }

    public void setApellidoAutor(String apellidoAutor) {
        this.apellidoAutor = apellidoAutor;
    }

    public String getPaisAutor() {
        return paisAutor;
    }

    public void setPaisAutor(String paisAutor) {
        this.paisAutor = paisAutor;
    }

    public String getDireccionAutor() {
        return direccionAutor;
    }

    public void setDireccionAutor(String direccionAutor) {
        this.direccionAutor = direccionAutor;
    }

    public String getTelefonoAutor() {
        return telefonoAutor;
    }

    public void setTelefonoAutor(String telefonoAutor) {
        this.telefonoAutor = telefonoAutor;
    }

    public String getEmailAutor() {
        return emailAutor;
    }

    public void setEmailAutor(String emailAutor) {
        this.emailAutor = emailAutor;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "idAutor=" + idAutor +
                ", nombreAutor='" + nombreAutor + '\'' +
                ", apellidoAutor='" + apellidoAutor + '\'' +
                ", paisAutor='" + paisAutor + '\'' +
                ", direccionAutor='" + direccionAutor + '\'' +
                ", telefonoAutor='" + telefonoAutor + '\'' +
                ", emailAutor='" + emailAutor + '\'' +
                '}';
    }
}
