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

import com.inti.model.Etudiant;
import com.inti.repository.IEtudiantRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("etudiant")
@Slf4j
public class EtudiantControler {
	
	@Autowired
	IEtudiantRepository ietr;
	
	@GetMapping("ecole")
	public List<Etudiant> getEtudiant() {
		return ietr.findAll();
	}

	@PostMapping("save")
	public boolean saveEtudiant(@RequestBody Etudiant ecole) {
		if (ecole != null) {
			ietr.save(ecole);
			return true;
		}
		return false;

	}

	@GetMapping("magasins/{id}")
	public Etudiant getEtudiant(@PathVariable int id) {
		Etudiant ec;
		try {
			ec = ietr.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			ec = null;
		}
		return ec;
	}

	@DeleteMapping("deleteEtudiant/{id}")
	public boolean deleteEtudiant(@PathVariable int id) {
		int maxID = ietr.findMaxID();
		if (id > 0 && id< maxID) {
			ietr.deleteById(id);
			log.info("la fonction a marche");
			return true;
		}
		log.info("la fonction a rate");
		return false;
	}
	@PutMapping("update/{id}")
	public Etudiant updateEtudiant(@RequestBody Etudiant nouveauEtudiant,@PathVariable int id) {
		return ietr.findById(id)
				.map(magasin -> {
					magasin.setNom(nouveauEtudiant.getNom());
					magasin.setPrenom(nouveauEtudiant.getPrenom());
					magasin.setEmail(nouveauEtudiant.getEmail());
					magasin.setTelephone(nouveauEtudiant.getTelephone());
					magasin.setAnneeEtude(nouveauEtudiant.getAnneeEtude());
					return ietr.save(magasin);
				})
				.orElseGet(() -> {
					return ietr.save(nouveauEtudiant);
				});
	}
}
