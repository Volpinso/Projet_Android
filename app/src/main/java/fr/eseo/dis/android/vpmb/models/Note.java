package fr.eseo.dis.android.vpmb.models;

public class Note {

    private int userId;
    private String forename;
    private String surname;
    private float myNote;
    private float avgNote;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public float getMyNote() {
        return myNote;
    }

    public void setMyNote(float myNote) {
        this.myNote = myNote;
    }

    public float getAvgNote() {
        return avgNote;
    }

    public void setAvgNote(float avgNote) {
        this.avgNote = avgNote;
    }

    @Override
    public String toString() {
        return "Note{" +
                "userId=" + userId +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", myNote=" + myNote +
                ", avgNote=" + avgNote +
                '}';
    }
}
