package ttp.lombok.bean;

import lombok.Data;

@Data
public class User {
    private String name;
    private int age;

    /**
     * 已经存在了 set 方法，不会被 lombok 覆盖
     */
    public void setAge(int age) {
        this.age = 10;
    }
}
