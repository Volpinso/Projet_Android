package fr.eseo.dis.android.vp.models;

public class RequestModel {

    private static String urlBase ="https://192.168.4.240/pfe/webservice.php?";
    private static String methodName = "q=";
    private static String user = "&user=";
    private static String pass = "&pass=";

    public static String loginRequest(String username, String password){
        return urlBase+methodName+Logon.class.getSimpleName().toUpperCase()+user+username+pass+password;
    }

}
