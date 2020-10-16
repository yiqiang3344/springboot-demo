package yi.sidney.springbootdemo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Document("demo")
@Getter
@Setter
public class MongoDemo implements Serializable {
    @Id
    private Long id;

    private String name;

    private String mobile;

    private String created_time;

    @Override
    public String toString() {
        return "MongoDemo{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", mobile='" + mobile + "'" +
                ", created_time='" + created_time + "'" +
                "}";
    }

    public MongoDemo() {
        this.id = System.currentTimeMillis();
        this.created_time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(Calendar.getInstance().getTime());
    }

    public MongoDemo(String name, String mobile) {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.mobile = mobile;
        this.created_time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(Calendar.getInstance().getTime());
    }
}
