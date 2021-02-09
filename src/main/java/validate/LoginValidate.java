package validate;

import entity.Login;

public class LoginValidate {

    public boolean validate(Login login) {
        if ((login.getName() == null) || login.getEmail() == null
        || login.getName().equals("") || login.getEmail().indexOf("@") == -1) {
            return false;
        }
        return true;
    }

}
