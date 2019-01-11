import java.util.Comparator;

public class Category_Compare implements Comparator<Data> {
    public int compare(Data a, Data b){
        String x = a.getCategory();
        String y = b.getCategory();
        if(x.compareToIgnoreCase(y)>0){
            return 1;
        }else{
            return -1;
        }
    }
}