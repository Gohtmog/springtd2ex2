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

import com.inti.model.Ecole;
import com.inti.repository.IEcoleRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("ecole")
@Slf4j
public class EcoleControler {

	
	@Autowired
	IEcoleRepository ier;
	
	
	@GetMapping("ecole")
	public List<Ecole> getEcole() {
		return ier.findAll();
	}

	@PostMapping("save")
	public boolean saveEcole(@RequestBody Ecole ecole) {
		if (ecole != null) {
			ier.save(ecole);
			return true;
		}
		return false;

	}

	@GetMapping("magasins/{id}")
	public Ecole getEcole(@PathVariable int id) {
		Ecole ec;
		try {
			ec = ier.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			ec = null;
		}
		return ec;
	}

	@DeleteMapping("deleteEcole/{id}")
	public boolean deleteEcole(@PathVariable int id) {
		int maxID = ier.findMaxID();
		if (id > 0 && id< maxID) {
			ier.deleteById(id);
			log.info("la fonction a marche");
			return true;
		}
		log.info("la fonction a rate");
		return false;
	}
	@PutMapping("update/{id}")
	public Ecole updateEcole(@RequestBody Ecole nouveauEcole,@PathVariable int id) {
		return ier.findById(id)
				.map(magasin -> {
					magasin.setNom(nouveauEcole.getNom());
					magasin.setAdresse(nouveauEcole.getAdresse());
					magasin.setCp(nouveauEcole.getCp());
					magasin.setVille(nouveauEcole.getVille());
					return ier.save(magasin);
				})
				.orElseGet(() -> {
					return ier.save(nouveauEcole);
				});
	}
	
	
	
}
