import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final String COM = ">>>";

    public static void main(String[] args){
        try {
            Command cd = new Command();
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.print("Account:");
                String account = sc.nextLine();
                System.out.print("Password:");
                String password = sc.nextLine();
                if(cd.login(account, password))
                    break;
            }

            while (true) {
                System.out.print(COM);
                String command = sc.nextLine();
                if(command.equals("logout")){
                    cd.end();
                    break;
                }
                cd.process(command);
            }
        }catch (IOException e){
            System.out.println("file error");
        }
    }
}
