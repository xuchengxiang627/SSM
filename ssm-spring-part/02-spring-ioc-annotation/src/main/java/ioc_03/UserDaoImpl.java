package ioc_03;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public void show() {
        System.out.println("UserDaoImpl.show()");
    }
}
