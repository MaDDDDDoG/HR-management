public class Inspect {
    private static final int[] MONTH_DAY = {29, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static boolean all_number(String s) {
        if(s.equals("")){return true;}
        for(int i=0;i<s.length();i++) {
            if(!('0'<=s.charAt(i)&&s.charAt(i)<='9')){
                return false;
            }
        }
        return true;
    }

    public static boolean phone_form(String s){
        if(s.equals("")){return true;}
        if(s.length()==7&&all_number(s)) {
            return true;
        }else if(s.length()==11&&
                s.charAt(0)=='('&&s.charAt(3)==')'&&
                all_number(s.substring(1, 3))&&
                all_number(s.substring(4))){
            return true;
        }else if(s.length()==12&&
                s.charAt(0)=='('&&s.charAt(4)==')'&&
                all_number(s.substring(1, 4))&&
                all_number(s.substring(5))){
            return true;
        }
        return false;
    }

    public static boolean name_form(String s) {
        for(int i=0;i<s.length();i++) {
            if(!('a'<=s.charAt(i)&&s.charAt(i)<='z'||'A'<=s.charAt(i)&&s.charAt(i)<='Z'||s.charAt(i)==' ')){
                return false;
            }
        }
        return true;
    }

    public static boolean email_form(String s){
        if(s.equals("")){return true;}
        if(s.contains("@")){
            for(int i=0;i<s.indexOf("@");i++){
                if(!(s.charAt(i)=='.'||
                     s.charAt(i)=='_'||
                     'a'<=s.charAt(i)&&s.charAt(i)<='z'||
                     'A'<=s.charAt(i)&&s.charAt(i)<='Z'||
                     '0'<=s.charAt(i)&&s.charAt(i)<='9')){
                    return false;
                }
            }
            for(int i=s.indexOf("@")+1;i<s.length();i++){
                if(!(s.charAt(i)=='.'||
                     s.charAt(i)=='-'||
                     'a'<=s.charAt(i)&&s.charAt(i)<='z'||
                     'A'<=s.charAt(i)&&s.charAt(i)<='Z'||
                     '0'<=s.charAt(i)&&s.charAt(i)<='9')){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public static boolean date_form(String s){
        if(s.equals("")){return true;}
        if(s.length()==4){
            try {
                int month = Integer.parseInt(s.substring(0, 2));
                int day = Integer.parseInt(s.substring(2, 4));
                return 1<=month && month<=12 && 1<=day && day<=MONTH_DAY[month];
            }catch (NumberFormatException e){
                return false;
            }
        }else{
            return false;
        }
    }

    public static void error_message(String s, String type){
        System.out.println(s + " is invalid " + type + " form");
    }
}
