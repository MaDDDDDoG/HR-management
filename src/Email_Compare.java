import java.util.Comparator;

public class Email_Compare implements Comparator<Data> {
    public int compare(Data a, Data b){
        String x = a.getEmail();
        String y = b.getEmail();
        if(x.compareToIgnoreCase(y)>0){
            return 1;
        }else{
            return -1;
        }
    }
}
