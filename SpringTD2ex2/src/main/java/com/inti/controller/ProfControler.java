package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Prof;
import com.inti.repository.IProfRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("prof")
@Slf4j
public class ProfControler {
	
	@Autowired
	IProfRepository ipr;
	
	
	@GetMapping("prof")
	public List<Prof> getprof() {
		return ipr.findAll();
	}

	@PostMapping("save")
	public boolean saveprof(@RequestBody Prof prof) {
		if (prof != null) {
			ipr.save(prof);
			return true;
		}
		return false;

	}

	@GetMapping("profs/{id}")
	public Prof getprof(@PathVariable int id) {
		Prof p;
		try {
			p = ipr.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			p = null;
		}
		return p;
	}

	@DeleteMapping("deleteprof/{id}")
	public boolean deleteprof(@PathVariable int id) {
		int maxID = ipr.findMaxID();
		if (id > 0 && id< maxID) {
			ipr.deleteById(id);
			log.info("la fonction a marche");
			return true;
		}
		log.info("la fonction a rate");
		return false;
	}
	@PutMapping("update/{id}")
	public Prof updateprof(@RequestBody Prof nouveauProf,@PathVariable int id) {
		return ipr.findById(id)
				.map(prof -> {
					prof.setNom(nouveauProf.getNom());
					prof.setPrenom(nouveauProf.getPrenom());
					prof.setSalaire(nouveauProf.getSalaire());
					return ipr.save(prof);
				})
				.orElseGet(() -> {
					return ipr.save(nouveauProf);
				});
	}

}
