import java.util.Comparator;

public class Birthday_Compare implements Comparator<Data> {
    public int compare(Data a, Data b){
        String x = a.getBirthday();
        String y = b.getBirthday();
        if(x.compareToIgnoreCase(y)>0){
            return 1;
        }else{
            return -1;
        }
    }
}