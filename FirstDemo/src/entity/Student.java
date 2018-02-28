package entity;

import java.util.Date;

public class Student {

    private Integer id; //  学生编号
    private String name;    // 学生姓名
    private Integer sex;    // 性别
    private Date birthday;  // 出生日期
    private String mobile;  // 手机号
    private Integer clbumId;    // 班级编号

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getClbumId() {
        return clbumId;
    }

    public void setClbumId(Integer clbumId) {
        this.clbumId = clbumId;
    }

}
