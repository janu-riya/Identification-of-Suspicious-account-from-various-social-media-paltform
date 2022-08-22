package com.manthan.ti.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manthan.ti.bo.ITiBo;
import com.manthan.ti.entity.Data;
import com.manthan.ti.model.AnalyzeModal;
import com.manthan.ti.model.Page2Model;

@RestController
@RequestMapping("/ti")
public class TiController {
	
	@Autowired
	ITiBo iTiBo;

	@GetMapping("/search")
	public Page2Model start(@RequestParam(name = "searchTerm") String searchTerm) {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String searchKey = searchTerm.replaceAll("@gmail.com", "");
		return iTiBo.getWebsitesSocials(searchKey);
	}
	
	@RequestMapping(value = "/start")
	public ResponseEntity<?> test() {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok("Searching for Profiles");
	}
	
	@GetMapping("/analyze")
	public List<AnalyzeModal> analyze(@RequestParam(name = "searchTermId") Long searchTermId) {
		return iTiBo.analyze(searchTermId);
	}
}
