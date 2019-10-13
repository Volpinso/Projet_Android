package fr.eseo.dis.android.vpmb.models;

import java.util.Arrays;

public class Juries {

    private int idJury;
    private String date;
    private Info info;

    public int getIdJury() {
        return idJury;
    }

    public void setIdJury(int idJury) {
        this.idJury = idJury;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Juries{" +
                "idJury=" + idJury +
                ", date='" + date + '\'' +
                ", info=" + info +
                '}';
    }
}
