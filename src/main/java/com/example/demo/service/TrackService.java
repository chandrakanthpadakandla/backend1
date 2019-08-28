package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Track;

public interface TrackService {

	public Optional<Track> getTracksById(int id);

	public void addTrack(Track track);


	public void update(Track track);

	public List<Track> getAllTracks();

	public void deleteTrack(int id);

	public List<Track> findByEmailId(String emailId);

}
