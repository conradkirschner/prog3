package app.user.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserList implements java.io.Serializable {
    private List<User> userList;
    //

    public UserList() {
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    //

    @Override
    public String toString() {
        return userList + "";
    }

}