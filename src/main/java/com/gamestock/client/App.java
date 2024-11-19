package com.gamestock.client;

import com.gamestock.client.controllers.ControladorLogin;
import com.gamestock.client.serveis.ServeiClient;

public class App {
    public static void main(String[] args) {
            ServeiClient.getInstance("127.0.0.1:8080/api");
            new ControladorLogin();
    }
}