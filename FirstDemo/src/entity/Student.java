package entity;

import java.util.Date;

public class Student {

    private Integer id; //  ѧ�����
    private String name;    // ѧ������
    private Integer sex;    // �Ա�
    private Date birthday;  // ��������
    private String mobile;  // �ֻ���
    private Integer clbumId;    // �༶���

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
