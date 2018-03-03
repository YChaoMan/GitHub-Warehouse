package com.modern;
/**
 * 2017-11-17 15:26:42
 * 该类实现的主要功能是提供学生类信息，提供了学生姓名和学号的setter/getter方法
 * 因为使用了Map所以重写了equals()方法和HashCode()方法
 * @author Administrator
 *
 */
public class StudentInformation {

    private String name;
    private String id;
    
    public StudentInformation(String id, String name) { // 构造方法进行初始化
        this.id = id;
        this.name = name;
    }
    /**
     * 提供getter/setter方法
     * @return
     */
    public String getName() {
        return name;
    }
    
    public void getName(String name) {
        this.name = name;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Map需要重写equals()方法和hashCode()方法，为了程序更加高效，不存储重复内容相同的不同对象
     */
    
    @Override
    public int hashCode() { // 返回id相同，名字也相同，那么hashode也一定相同，因为相同的字符串他们hashCode也相同
        return Integer.parseInt(this.getId()) + this.getName().hashCode();
    }
    /**
     * 重写equals方法，判断两部分，第一判断内存地址，第二判断是否属于他的实例（再比较内容）
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {  // 如果这两个对象的内存地址相同
            return true;
        } 
        if (obj instanceof StudentInformation) {    // 如果obj是StudentInformation类的实例
            StudentInformation student = (StudentInformation) obj;
            if ((student.getId() == this.getId() && student.getName().equals(this.getName()))) {
                return true;
            } 
        } else {
            return false;
        } 
        return false;
    }
    
}
