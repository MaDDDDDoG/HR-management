import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Command extends Data_Menu{
    private String ACCOUNT_PATH = new File("").getAbsolutePath() + "/src/account.csv";
    private Map<String, String> account_map = new HashMap<>();
    private String account;
    private String password;

    public Command() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ACCOUNT_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            if(line.equals("")){continue;}
            String[] temp = line.split(",");
            account_map.put(temp[0], temp[1]);
        }
    }

    public void process(String command){
        if(command.equals("data")){
            System.out.println("---------------------");
            System.out.println("| Data Control Menu |");
            System.out.println("---------------------");
            while(true){
                System.out.print("---");
                String s = keyboard.nextLine();
                if(data_process(s)){
                    break;
                }
            }
        }else if(command.equals("save")){
            save_(DATA_PATH);
        }else if(command.indexOf("upper")==0){
            String operand = command.substring(6);
            change_upper(operand);
        }else if(command.equals("change password")){
            change_password();
        }else if(command.equals("new account")){
            new_account();
        }else if(command.indexOf("?")==0){
            String operand = command.substring(1);
            System.out.println(explain(operand));
        }else if(command.indexOf("export")==0){
            String operand = command.substring(7);
            export(operand);
        }else if(command.equals("time")){
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            System.out.println(ft.format(dNow));
        }else{
            System.out.println("command not found");
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

    private void save_(String path){
        save_data(path);
    }

    private void change_upper(String s){
        try{
            if(s.equals("unlimited")){
                unlimited = true;
            }else {
                int u = Integer.parseInt(s);
                if(u<length){
                    System.out.println(s + " can not lower than current data size");
                }else{
                    upper_bound = u;
                    unlimited = false;
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

    private String explain(String s){
        if(s.equals("upper")){
            return "upper A, A=integer which can't lower than current data size or \"unlimited\"";
        }else if(s.equals("change password")){
            return "change current password";
        }else if(s.equals("new account")){
            return "append a new account";
        }else if(s.equals("save")){
            return "save data";
        }else if(s.equals("logout")) {
            return "exit system";
        }else if(s.equals("time")){
            return "now time";
        }else if(s.equals("export")){
            return "export A, A=target path, export data copy.";
        }else {
            return s + " command not found";
        }
    }

    private void export(String path){
        File f = new File(path, "export.csv");
        try {
            if(!f.exists())
                f.createNewFile();
            save_data(path+"\\export.csv");
        }catch (IOException e){
            System.out.println("1file error");
        }
    }
}
