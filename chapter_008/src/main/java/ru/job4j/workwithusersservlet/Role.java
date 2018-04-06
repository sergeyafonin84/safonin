package ru.job4j.workwithusersservlet;

public class Role {

    String rolename;
    String userlogin;

    public Role(String rolename, String userlogin) {
        this.rolename = rolename;
        this.userlogin = userlogin;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        if (rolename != null ? !rolename.equals(role.rolename) : role.rolename != null) {
            return false;
        }
        return userlogin != null ? userlogin.equals(role.userlogin) : role.userlogin == null;
    }

    @Override
    public int hashCode() {
        int result = rolename != null ? rolename.hashCode() : 0;
        result = 31 * result + (userlogin != null ? userlogin.hashCode() : 0);
        return result;
    }

    public String getRolename() {

        return rolename;
    }

    public String getUserlogin() {
        return userlogin;
    }
}