package fr.eseo.dis.android.vp.models;

public class RequestModel {

    private static String urlBase ="https://192.168.4.240/pfe/webservice.php?";
    private static String methodName = "q=";
    private static String user = "&user=";
    private static String pass = "&pass=";
    private static String tok = "&token=";


    public static String loginRequest(String username, String password){
        return urlBase+methodName+Logon.class.getSimpleName().toUpperCase()+user+username+pass+password;
    }

    public static String getAllProjectrequest(String username, String token){
        return urlBase+methodName+Liprj.class.getSimpleName().toUpperCase()+user+username+tok+token;
    }

}
