import java.io.*;


public class Data_Menu extends Control{
    protected String DATA_PATH = new File("").getAbsolutePath() + "/src/data.csv";

    public Data_Menu() throws IOException {
        super();
        BufferedReader br = new BufferedReader(new FileReader(DATA_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] temp = line.split(",", 5);
            try {
                DataL.add(new Data(temp[0], temp[1], temp[2], temp[3], temp[4]));
                length++;
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println(line + "lack data");
            }
        }
    }

    public boolean data_process(String command){
        if(command.indexOf("search")==0){
            String operand = command.substring(7);
            search_data(operand);
        }else if(command.indexOf("delete")==0){
            String operand = command.substring(7);
            delete_data(operand);
        }else if(command.indexOf("modify")==0){
            String operand = command.substring(7);
            modify_data(operand);
        }else if(command.indexOf("append")==0){
            String operand = command.substring(7);
            append_data(operand);
        }else if(command.indexOf("sort")==0){
            String operand = command.substring(5);
            sort_data(operand);
        }else if(command.equals("exit")){
            return true;
        }else if(command.indexOf("?")==0){
            String operand = command.substring(1);
            System.out.println(explain(operand));
        }else{
            System.out.println("command not found");
        }
        return false;
    }

    private void search_data(String s){
        int a = s.indexOf("$");
        String key = s;
        String type_l = "";
        if(a>=0){
            key = s.substring(0, a);
            type_l = s.substring(a+1);
        }

        if(key.equals("all")) {
            for (int i = 1; i <= DataL.size(); i++) {
                if(a<0){
                    print(i-1);
                }else{
                    String[] type = type_l.split(",");
                    for(int j=0;j<type.length;j++){
                        print_type(type[j], i-1);
                    }
                    System.out.println();
                }

                if(i%5==0){
                    String x = keyboard.nextLine();
                }
            }
        }else if(search(key)>=0){
            int i = search(key);
            if(a<0){
                print(i);
            }else{
                String[] type = type_l.split(",");
                for(int j=0;j<type.length;j++){
                    print_type(type[j], i);
                }
                System.out.println();
            }
        }else{
            System.out.println(key + "not found");
        }
    }

    private void delete_data(String s){
        if(s.equals("all")) {
            for (int i = 0; i < DataL.size(); i++) {
                delete(i);
            }
            System.out.println("delete complete");
        }else if(search(s)>=0){
            delete(search(s));
            System.out.println("delete complete");
        }else{
            System.out.println(s + " not found");
        }
    }

    private void modify_data(String s){
        try {
            String x = s.substring(0, s.indexOf("$"));
            int index = search(x);
            if(index>=0){
                String[] m = s.substring(s.indexOf("$")+1).split(",");
                for(int i=0;i<m.length;i+=2){
                    modify(index, m[i], m[i+1]);
                }
            }else{
                System.out.println(x + " not found");
            }
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("error command form");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("error command form");
        }
    }

    private void append_data(String s){
        if(Inspect.name_form(s)){
            append(s);
            System.out.print("Phone:");
            modify(length-1, "phone", keyboard.nextLine());
            System.out.print("Category: ");
            modify(length-1, "category", keyboard.nextLine());
            System.out.print("E-mail:");
            modify(length-1, "e-mail", keyboard.nextLine());
            System.out.print("Birthday: ");
            modify(length-1, "birthday", keyboard.nextLine());
        }else{
            Inspect.error_message(s, "Name");
        }
    }

    private void sort_data(String s){
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

    public void save_data(String path){
        try {
            save(path);
            System.out.println("save complete");
        }catch (IOException e){
            System.out.println("file error");
        }
    }

    private String explain(String s){
        if(s.equals("search")){
            return "search key,$a,b,...  ,key=name type or phone type or \"all\"\na,b,...=the types which print";
        }else if(s.equals("delete")){
            return "delete key, key=name type or \"all\"";
        }else if(s.equals("modify")){
            return "modify A$B,C,D,E...., A=name type, B=type, C=modify data, D=type, E=modify type";
        }else if(s.equals("sort")){
            return "sort A B, A=trend i(increase) or d(decrease), B=sort by type";
        }else if(s.equals("exit")){
            return "exit data menu";
        }else{
            return s + " command not found";
        }
    }
}
