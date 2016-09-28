package orange.com.androidtool.Info;

/**
 * Created by Orange on 2016/9/20.
 */
public class Msg {

    private String name;
    private String password;
    private int id;

    public Msg(String name, String password, int id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
