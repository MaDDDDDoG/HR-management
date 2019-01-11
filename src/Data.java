public class Data {
    private String name;
    private String phone;
    private String category;
    private String email;
    private String birthday;

    public Data(String n, String p, String c, String e, String b){
        setName(n);
        setPhone(p);
        setCategory(c);
        setEmail(e);
        setBirthday(b);
    }

    public Data(String n){
        setName(n);
        setPhone("");
        setCategory("");
        setEmail("");
        setBirthday("");
    }

    public void setName(String s){
        if(Inspect.name_form(s)) {
            name = s;
        }else{
            Inspect.error_message(s, "Name");
        }
    }

    public void setPhone(String s){
        if(Inspect.phone_form(s)){
            phone = s;
        } else{
            Inspect.error_message(s, "Phone");
        }
    }

    public void setCategory(String s){
        category = s;
    }

    public void setEmail(String s){
        if(Inspect.email_form(s)){
            email = s;
        }else{
            Inspect.error_message(s, "E-mail");
        }
    }

    public void setBirthday(String s){
        if(Inspect.date_form(s)){
            birthday = s;
        }else{
            Inspect.error_message(s, "Birthday");
        }
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public String getCategory(){
        return category;
    }

    public String getEmail(){
        return email;
    }

    public String getBirthday(){
        return birthday;
    }
}
