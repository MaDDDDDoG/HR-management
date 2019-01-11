import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {
        String filePath = new File("").getAbsolutePath();
        System.out.print(filePath);
        BufferedReader bt = new BufferedReader(new FileReader(filePath + "\\src\\account.csv"));
        String x;
        while ((x = bt.readLine()) != null) {
            System.out.println(x);
        }
    }
}
