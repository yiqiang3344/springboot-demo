package yi.sidneyi.springbootdemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "mobile", nullable = false, length = 16)
    private String mobile;

    @Column(name = "created_time", nullable = false)
    private Timestamp created_time;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", mobile='" + mobile + "'" +
                ", created_time='" + created_time + "'" +
                "}";
    }

    public User() {
    }
}
