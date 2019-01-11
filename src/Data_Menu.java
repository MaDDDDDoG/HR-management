import java.io.*;


public class Data_Menu extends Control{
    private String DATA_PATH = new File("").getAbsolutePath() + "/src/data.csv";

    public Data_Menu() throws IOException {
        super();
        BufferedReader br = new BufferedReader(new FileReader(DATA_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] temp = line.split(",");
            DataL.add(new Data(temp[0], temp[1], temp[2], temp[3], temp[4]));
            length++;
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
        }else if(command.indexOf("print")==0){
            String operand = command.substring(6);
            print_(operand);
        }else if(command.indexOf("sort")==0){
            String operand = command.substring(5);
            sort_(operand);
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

    private void print_(String s){
        print_type(s);
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

    public void save_data(){
        try {
            save(DATA_PATH);
            System.out.println("save complete");
        }catch (IOException e){
            System.out.println("file error");
        }
    }
}
