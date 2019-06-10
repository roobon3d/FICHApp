package edu.cftic.fichapp;

public class Fichaje {
    private int id_fichaje;
    private int id_empleado;
    private String horaEntrada;
    private String horaSalida;
    private String mensaje;

    public Fichaje(){

    }

    public Fichaje(int id_fichaje, int id_empleado, String horaEntrada, String horaSalida, String mensaje) {
        this.id_fichaje = id_fichaje;
        this.id_empleado = id_empleado;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.mensaje = mensaje;
    }

    public int getId_fichaje() {
        return id_fichaje;
    }

    public void setId_fichaje(int id_fichaje) {
        this.id_fichaje = id_fichaje;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
