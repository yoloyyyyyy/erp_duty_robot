/**
 * Created by yolo on 2019/8/7.
 */
public class Duty {
    private String user ;
    private String phone;
    private String date ;

    public Duty() {
    }

    public Duty(String user, String phone) {
        this.user = user;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Duty{" +
                "user='" + user + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
