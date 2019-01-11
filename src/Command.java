import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Command{
    private String ACCOUNT_PATH = new File("").getAbsolutePath() + "/src/account.csv";
    private Map<String, String> account_map = new HashMap<>();
    private String account;
    private String password;
    private Data_Menu Data_M;

    public Command() throws IOException {
        Data_M = new Data_Menu();
        BufferedReader br = new BufferedReader(new FileReader(ACCOUNT_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            if(line.equals("")){continue;}
            String[] temp = line.split(",");
            account_map.put(temp[0], temp[1]);
        }
    }

    public boolean login(String a, String p) {
        if(account_map.containsKey(a)&&account_map.get(a).equals(p)){
            account = a;
            password = p;
            return true;
        }else{
            System.out.println("error password");
            return false;
        }
    }

    public void end(){
        try {
            BufferedWriter bufw = new BufferedWriter(new FileWriter(ACCOUNT_PATH));
            for (String key:account_map.keySet()) {
                bufw.write(key + "," + account_map.get(key));
                bufw.newLine();
            }
            bufw.flush();
            bufw.close();
        }catch (IOException e){
            System.out.println("file error");
        }
    }

    public void process(String command){
        if(command.equals("save")){
            save();
        }else if(command.indexOf("upper")==0){
            String operand = command.substring(6);
            change_upper(operand);
        }else if(command.equals("change password")){
            change_password();
        }else if(command.equals("new account")){
            new_account();
        }else{
            System.out.println("command not found");
        }
    }

    private void save(){
        Data_M.save_data();
    }

    private void change_upper(String s){
        try{
            if(s.equals("unlimited")){
                Data_M.setUnlimited(true);
            }else {
                int u = Integer.parseInt(s);
                if(u< Data_M.getLength()){
                    System.out.println(s + " can not lower than current data size");
                }
            }
        }catch (NumberFormatException e){
            System.out.println(s + " must integer or unlimited");
        }
    }

    private void change_password(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("old password:");
            String old = sc.nextLine();
            if(old.equals("exit")){return;}
            if(old.equals(password)){
                break;
            }else{
                System.out.println("error password");
            }
        }
        System.out.print("new password:");
        String new_p = sc.nextLine();
        password = new_p;
        account_map.put(account, new_p);
    }

    private void new_account(){
        Scanner sc = new Scanner(System.in);
        System.out.print("New Account:");
        String new_a = sc.nextLine();
        System.out.print("New Password:");
        String new_p = sc.nextLine();
        account_map.put(new_a, new_p);
    }
}
