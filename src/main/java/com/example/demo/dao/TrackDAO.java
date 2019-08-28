package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Track;

@Repository
public interface TrackDAO extends CrudRepository<Track, Integer> {
	@Query("SELECT r FROM Track r where r.emailId=:emailId")
    List<Track> findByEmailId(@Param("emailId") String emailId);
}
