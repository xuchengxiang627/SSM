package ioc_03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Override
    public void show() {
        userDao.show();
        System.out.println("UserServiceImpl.show()");

    }
}
