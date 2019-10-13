package fr.eseo.dis.android.vpmb.models;

public class Members {

    private String forename;
    private String surname;

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Members{" +
                "forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
