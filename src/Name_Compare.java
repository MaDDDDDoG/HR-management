import java.util.Comparator;

public class Name_Compare implements Comparator<Data> {
    public int compare(Data a, Data b){
        String x = a.getName();
        String y = b.getName();
        if(x.compareToIgnoreCase(y)>0){
            return 1;
        }else{
            return -1;
        }
    }
}
