package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TrackDAO;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Track;

@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	private TrackDAO dao;

	@Override
	public void addTrack(Track track) {
		dao.save(track);
	}

	@Override
	public Optional<Track> getTracksById(int id) {
		return dao.findById(id);
	}

//	@Override
//	public void deleteTrack(Track track) {
//		dao.delete(track);
//	}

	@Override
	public void update(Track track) {
		dao.save(track);
	}

	@Override
	public List<Track> getAllTracks() {
		return (List<Track>) dao.findAll();
	}

	@Override
	public void deleteTrack(int id) {
		dao.deleteById(id);
	}

	@Override
	public List<Track> findByEmailId(String emailId) {
		 List<Track> userTrack = null;
		    try {
		        
		    	userTrack=(List<Track>) dao.findByEmailId(emailId);
		             for(Track r:userTrack) {
		                
		             }
		            if (userTrack == null) {
		                 throw new UserNotFoundException("User Not Found");
		            } }catch (Exception e) { System.out.println(e);
		        
		    
		    }
		    return userTrack;

}
}