package validate;

import entity.Admin;

public class LoginValidate {

    public boolean validate(Object obj) {
        if (obj instanceof Admin) {
            Admin admin = (Admin) obj;
            if ((admin.getUsername() == null) || admin.getPassword() == null
                    || admin.getUsername().equals("")) {
                return false;
            }
        }
        return true;
    }

}
