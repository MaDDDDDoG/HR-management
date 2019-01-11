import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Command extends Control{
    private String DATA_PATH = new File("").getAbsolutePath() + "/src/data.csv";
    private String ACCOUNT_PATH = new File("").getAbsolutePath() + "/src/account.csv";
    private Map<String, String> account_map = new HashMap<>();
    private String account;
    private String password;

    public Command() throws IOException {
        super();
        BufferedReader br = new BufferedReader(new FileReader(DATA_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] temp = line.split(",");
            DataL.add(new Data(temp[0], temp[1], temp[2], temp[3], temp[4]));
            length++;
        }

        BufferedReader bt = new BufferedReader(new FileReader(ACCOUNT_PATH));
        while ((line = bt.readLine()) != null) {
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
        if(command.indexOf("search")==0){
            String operand = command.substring(7);
            search_(operand);
        }else if(command.indexOf("delete")==0){
            String operand = command.substring(7);
            delete_(operand);
        }else if(command.indexOf("modify")==0){
            String operand = command.substring(7);
            modify_(operand);
        }else if(command.indexOf("append")==0){
            String operand = command.substring(7);
            append_(operand);
        }else if(command.equals("save")){
            save_(DATA_PATH);
        }else if(command.indexOf("print")==0){
            String operand = command.substring(6);
            print_(operand);
        }else if(command.indexOf("upper")==0){
            String operand = command.substring(6);
            change_upper(operand);
        }else if(command.indexOf("sort")==0){
            String operand = command.substring(5);
            sort_(operand);
        }else if(command.equals("change password")){
            change_password();
        }else if(command.equals("new account")){
            new_account();
        }else{
            System.out.println("command not found");
        }
    }

    private void search_(String s){
        if(s.equals("all")) {
            for (int i = 0; i < DataL.size(); i++) {
                print(i);
            }
        }else if(search(s)>=0){
            print(search(s));
        }else{
            System.out.println(s + "not found");
        }
    }

    private void delete_(String s){
        if(s.equals("all")) {
            for (int i = 0; i < DataL.size(); i++) {
                delete(i);
            }
            System.out.println("delete complete");
        }else if(search(s)>=0){
            delete(search(s));
            System.out.println("delete complete");
        }else{
            System.out.println(s + "not found");
        }
    }

    private void modify_(String s){
        try {
            String x = s.substring(0, s.indexOf("$"));
            int index = search(x);
            if(index>=0){
                String[] m = s.substring(s.indexOf("$")+1).split(",");
                for(int i=0;i<m.length;i+=2){
                    modify(index, m[i], m[i+1]);
                }
            }else{
                System.out.println(x + "not found");
            }
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("error command form");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("error command form");
        }
    }

    private void append_(String s){
        try {
            String x = s.substring(0, s.indexOf("$"));
            if(!x.equals("")&&Inspect.name_form(x)){
                append(x);
            }else{
                Inspect.error_message(x, "Name");
                return ;
            }
            String[] a = s.substring(s.indexOf("$")+1).split(",");
            for(int i=0;i<a.length;i+=2){
                modify(length-1, a[i], a[i+1]);
            }
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("error command form");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("error command form");
        }
    }

    private void save_(String s){
        try {
            save(s);
            System.out.println("save complete");
        }catch (IOException e){
            System.out.println("file error");
        }
    }

    private void print_(String s){
        print_type(s);
    }

    private void change_upper(String s){
        try{
            int u = Integer.parseInt(s);
            upper_bound = u;
        }catch (NumberFormatException e){
            System.out.println(s + " must integer");
        }
    }

    private void sort_(String s){
        try {
            boolean trend = true;  //true increase, false decrease.
            String[] o = s.split(" ");
            if(o[0].equals("i")){
                trend = true;
            }else if(o[0].equals("d")){
                trend = false;
            }else{
                System.out.println("unknown trend");
            }
            sort(o[1], trend);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("error command form");
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
