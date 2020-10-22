package app.user.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserList implements java.io.Serializable {
    private static final long serialVersionUID = 6529685098267757690L;

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