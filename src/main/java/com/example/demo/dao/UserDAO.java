package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, String> {

	@Query("SELECT u FROM User u WHERE u.emailId=:emailId AND u.password=:password")
	User findByEmailIdAndPassword(@Param("emailId") String emailId, @Param("password") String password);

}
                    