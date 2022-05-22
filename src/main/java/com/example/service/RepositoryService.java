package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.music.MusicRepository;

@Service
public class RepositoryService {

	@Autowired
	private MusicRepository repository;

	public MusicRepository getMusicRepository() {
		return repository;
	}
}
