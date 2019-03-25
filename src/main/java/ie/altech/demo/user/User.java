package ie.altech.demo.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "User instance with name and dob")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=3, message="Name should have at least 3 characters")
    @ApiModelProperty(notes = "Name of the user must contain at least 3 character")
    private String name;

    @Past
    @ApiModelProperty(notes = "Date of Birth must be in the past")
    private Date dob;

    public User(){}

    public User(Integer id, String name, Date dob){
        super();
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }


}
