package yi.sidneyi.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import yi.sidneyi.springbootdemo.dao.UserDao;
import yi.sidneyi.springbootdemo.entity.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findById(int id) {
        return userDao.findUserById(id);
    }

    public List<User> findByName(String name) {
        return userDao.findUserByName(name);
    }

    public User findByMobile(String mobile) {
        return userDao.findUserByMobile(mobile);
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }

    public Page<User> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return (Page<User>) userDao.findAll(pageable);
    }
}
