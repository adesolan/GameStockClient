package com.gamestock.client.models;

public class RespostaLogin {
    private boolean autenticat;
    private boolean administrador;

    // Getters i setters
    public boolean isAutenticat() {
        return autenticat;
    }

    public void setAutenticat(boolean autenticat) {
        this.autenticat = autenticat;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
}