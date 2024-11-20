package com.gamestock.client.models;

/**
 * Classe que representa la resposta d'una operació de login, indicant si l'usuari està 
 * autenticat i si és administrador.
 */
public class RespostaLogin {
    private boolean autenticat;
    private boolean administrador;

    /**
     * Obté si l'usuari ha estat autenticat correctament.
     *
     * @return true si l'usuari està autenticat, false en cas contrari.
     */
    public boolean isAutenticat() {
        return autenticat;
    }

    /**
     * Assigna l'estat d'autenticació de l'usuari.
     *
     * @param autenticat true si l'usuari està autenticat, false en cas contrari.
     */
    public void setAutenticat(boolean autenticat) {
        this.autenticat = autenticat;
    }

    /**
     * Obté si l'usuari té privilegis d'administrador.
     *
     * @return true si l'usuari és administrador, false en cas contrari.
     */
    public boolean isAdministrador() {
        return administrador;
    }

    /**
     * Assigna l'estat d'administrador a l'usuari.
     *
     * @param administrador true si l'usuari és administrador, false en cas contrari.
     */
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
}