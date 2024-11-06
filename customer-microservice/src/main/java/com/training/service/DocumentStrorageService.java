package com.training.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentStrorageService {

	 public void init();

	  public void save(MultipartFile file, String fileName);

	  public Resource load(String filename);

}
