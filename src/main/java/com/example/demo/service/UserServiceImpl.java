package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.UserDAO;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;

	@Override
	public void addRegister(User user) {
		dao.save(user);
	}


	@Override
    public User findByEmailIdAndPassword(String emailId, String password) throws UserNotFoundException {
        User user = dao.findByEmailIdAndPassword(emailId, password);
        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        }
        return user;
    }
	
//	@Override
//    public User updatepassword(User user, String id) {
//        User user1 = dao.findById(id).get();
//        user1.setEmailId(user1.getEmailId());
//        user1.setPassword(user.getPassword());
//        User updated = dao.save(user);
//        return updated;
//}

//
//	@Override
//	public User updatepassword(String id, User user) {
//		 User user1 = dao.findById(id).get();
//       user1.setEmailId(user1.getEmailId());
//	        user1.setPassword(user.getPassword());
//	        User updated = dao.save(user);
//	        return updated;
//	}
//

	@Override
	public User updatePassword(User user, String id) {
		 User user1 = dao.findById(id).get();
//       user1.setEmailId(user1.getEmailId());
	        user1.setPassword(user.getPassword());
	        User updated = dao.save(user);
	        return updated;
	}

}


