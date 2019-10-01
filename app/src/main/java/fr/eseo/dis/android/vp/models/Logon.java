package fr.eseo.dis.android.vp.models;

public class Logon {

    private String result;
    private String api;
    private String token;
    private String error;

    public Logon(String result, String api, String token,String error){
        this.api = api;
        this.result = result;
        this.token = token;
        this.error = error;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Logon{" +
                "result='" + result + '\'' +
                ", api='" + api + '\'' +
                ", statut='" + token + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
