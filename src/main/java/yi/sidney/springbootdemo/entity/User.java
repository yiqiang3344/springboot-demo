package yi.sidney.springbootdemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
@Table(name = "user")
@Getter
@Setter
@Log4j2
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "mobile", nullable = false, length = 16)
    private String mobile;

    @Column(name = "created_time", nullable = false)
    private String created_time;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", mobile='" + mobile + "'" +
                ", created_time='" + created_time + "'" +
                "}";
    }

    public User() {
    }

    public User(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
        this.created_time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(Calendar.getInstance().getTime());
        log.info("yjqtest:" + this.created_time);
    }
}
