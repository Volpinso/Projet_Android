package fr.eseo.dis.android.vpmb.models;

public class RequestModel {

    private static String urlBase ="https://192.168.4.240/pfe/webservice.php?";
    private static String methodName = "q=";
    private static String user = "&user=";
    private static String pass = "&pass=";
    private static String jury = "&jury=";
    private static String tok = "&token=";
    private static String proj = "&proj=";
    private static String style = "&style=";
    private static String student = "&student=";
    private static String note = "&note=";


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

    public static String getMyJuriesProjetcsRequest(String username, int juryId, String token){
        return urlBase+methodName+Jyinf.class.getSimpleName().toUpperCase()+user+username+jury+juryId+tok+token;
    }

    public static String getProjectGrades(String username, int projectId, String token){
        return urlBase+methodName+Notes.class.getSimpleName().toUpperCase()+user+username+proj+projectId+tok+token;
    }

    public static String setStudentGrade(String username, int projectId, int studentId, int studentNote, String token){
        return urlBase+methodName+Newnt.class.getSimpleName().toUpperCase()+user+username+proj+projectId+
                student+studentId+note+studentNote+tok+token;
    }


}
