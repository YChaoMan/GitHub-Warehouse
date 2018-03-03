package com.modern;
/**
 * 2017-11-17 15:26:42
 * ����ʵ�ֵ���Ҫ�������ṩѧ������Ϣ���ṩ��ѧ��������ѧ�ŵ�setter/getter����
 * ��Ϊʹ����Map������д��equals()������HashCode()����
 * @author Administrator
 *
 */
public class StudentInformation {

    private String name;
    private String id;
    
    public StudentInformation(String id, String name) { // ���췽�����г�ʼ��
        this.id = id;
        this.name = name;
    }
    /**
     * �ṩgetter/setter����
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
     * Map��Ҫ��дequals()������hashCode()������Ϊ�˳�����Ӹ�Ч�����洢�ظ�������ͬ�Ĳ�ͬ����
     */
    
    @Override
    public int hashCode() { // ����id��ͬ������Ҳ��ͬ����ôhashodeҲһ����ͬ����Ϊ��ͬ���ַ�������hashCodeҲ��ͬ
        return Integer.parseInt(this.getId()) + this.getName().hashCode();
    }
    /**
     * ��дequals�������ж������֣���һ�ж��ڴ��ַ���ڶ��ж��Ƿ���������ʵ�����ٱȽ����ݣ�
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {  // ���������������ڴ��ַ��ͬ
            return true;
        } 
        if (obj instanceof StudentInformation) {    // ���obj��StudentInformation���ʵ��
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
