import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Control {
    protected int upper_bound;
    protected boolean unlimited;
    protected List<Data> DataL = new ArrayList<>();
    protected int length;
    protected Scanner keyboard;

    public Control(){
        keyboard = new Scanner(System.in);
        length=0;
        upper_bound = 128;
        unlimited = false;
    }

    protected void print(int i){
        System.out.println("Name: " + DataL.get(i).getName());
        System.out.println("Phone: " + DataL.get(i).getPhone());
        System.out.println("Category: " + DataL.get(i).getCategory());
        System.out.println("E-mail: " + DataL.get(i).getEmail());
        System.out.println("Birthday: " + DataL.get(i).getBirthday());
        System.out.println();
    }

    protected int search(String s){
        if(Inspect.name_form(s)){
            for(int i=0;i<DataL.size();i++){
                if(s.equals(DataL.get(i).getName())){
                    return i;
                }
            }
        }else if(Inspect.phone_form(s)){
            for(int i=0;i<DataL.size();i++){
                if (s.equals(DataL.get(i).getPhone())){
                    return i;
                }
            }
        }
        return -1;
    }

    protected void delete(int i){
        DataL.remove(i);
        length--;
    }

    protected void modify(int i, String type, String content){
        if(type.equals("name")){
            DataL.get(i).setName(content);
        }else if(type.equals("phone")){
            DataL.get(i).setPhone(content);
        }else if(type.equals("category")){
            DataL.get(i).setCategory(content);
        }else if(type.equals("e-mail")){
            DataL.get(i).setEmail(content);
        }else if(type.equals("birthday")){
            DataL.get(i).setBirthday(content);
        }else{
            System.out.println(type + " is not type");
        }
    }

    protected void append(String name){
        if(!unlimited){
            if(length>upper_bound){
                System.out.println("data full");
                return;
            }
            DataL.add(new Data(name));
            length++;
        }else{
            DataL.add(new Data(name));
            length++;
        }
    }

    protected void save(String path) throws IOException{
        BufferedWriter bufw = new BufferedWriter(new FileWriter(path));
        for(int i=0;i<length;i++){
            bufw.write(DataL.get(i).getName() + ',' +
                       DataL.get(i).getPhone() + ',' +
                       DataL.get(i).getCategory() + ',' +
                       DataL.get(i).getEmail() + ',' +
                       DataL.get(i).getBirthday());
            if(i!=length-1)
                bufw.newLine();
        }
        bufw.flush();
        bufw.close();
    }

    protected void print_type(String type, int i){
        if(type.equals("name")){
            System.out.println("Name: " + DataL.get(i).getName());
        }else if(type.equals("phone")){
            System.out.println("Phone" + DataL.get(i).getPhone());
        }else if(type.equals("category")){
            System.out.println("Category: " + DataL.get(i).getCategory());
        }else if(type.equals("e-mail")){
            System.out.println("E-mail: " + DataL.get(i).getEmail());
        }else if(type.equals("birthday")){
            System.out.println("Birthday: " + DataL.get(i).getBirthday());
        }else{
            System.out.println(type + " is not type");
        }
    }

    protected void sort(String type, boolean trend){
        if(type.equals("name")){
            DataL.sort(new Name_Compare());
        }else if(type.equals("phone")){
            DataL.sort(new Phone_Compare());
        }else if(type.equals("category")){
            DataL.sort(new Category_Compare());
        }else if(type.equals("e-mail")){
            DataL.sort(new Email_Compare());
        }else if(type.equals("birthday")){
            DataL.sort(new Birthday_Compare());
        }else{
            System.out.println(type + " is not type");
        }

        //true increase, false decrease.
        if(!trend){
            Collections.reverse(DataL);
        }
    }
}
