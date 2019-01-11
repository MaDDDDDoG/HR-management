import java.util.Comparator;

public class Phone_Compare implements Comparator<Data> {
    public int compare(Data a, Data b){
        String x = a.getPhone();
        String y = b.getPhone();
        if(x.compareToIgnoreCase(y)>0){
            return 1;
        }else{
            return -1;
        }
    }
}
