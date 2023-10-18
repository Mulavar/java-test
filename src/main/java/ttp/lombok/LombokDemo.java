package ttp.lombok;

import ttp.lombok.bean.User;

public class LombokDemo {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(100);
        System.out.println(user);
    }
}
