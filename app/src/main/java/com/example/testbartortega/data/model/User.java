package com.example.testbartortega.data.model;

public class User {

    String titular;
     String url;
     String email;
     String solicitud;

    public User(String titular_, String url_, String email_, String solicitud_){
        this.titular=titular_;
        this.url=url_;
        this.email=email_;
        this.solicitud=solicitud_;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        this.solicitud = solicitud;
    }

}
