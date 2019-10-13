package fr.eseo.dis.android.vpmb.models;

public class RequestModel {

    private static String urlBase ="https://192.168.4.240/pfe/webservice.php?";
    private static String methodName = "q=";
    private static String user = "&user=";
    private static String pass = "&pass=";
    private static String tok = "&token=";
    private static String proj = "&proj=";
    private static String style = "&style=";


    public static String loginRequest(String username, String password){
        return urlBase+methodName+Logon.class.getSimpleName().toUpperCase()+user+username+pass+password;
    }

    public static String getAllProjectrequest(String username, String token){
        return urlBase+methodName+Liprj.class.getSimpleName().toUpperCase()+user+username+tok+token;
    }

    public static String getSupervisorProjectrequest(String username, String token){
        return urlBase+methodName+Myprj.class.getSimpleName().toUpperCase()+user+username+tok+token;
    }

    public static String getRandomNonConfidProjetc(String username, String token){
        return urlBase+methodName+"PORTE"+user+username+tok+token;
    }

    public static String getPoster(String username, int projectId, String stylePostr, String token){
        return urlBase+methodName+"POSTR"+user+username+proj+projectId+style+stylePostr+tok+token;
    }

    public static String getMyJuriesRequest(String username, String token){
        return urlBase+methodName+Myjur.class.getSimpleName().toUpperCase()+user+username+tok+token;
    }


}
