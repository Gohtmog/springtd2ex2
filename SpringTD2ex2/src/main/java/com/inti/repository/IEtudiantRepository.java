package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Etudiant;

@RestController
@RequestMapping(name = "Etudiant_Td3Ex2")
public interface IEtudiantRepository extends JpaRepository<Etudiant, Integer>{

	@Query(value= "select max(id) from Etudiant_Td3Ex2", nativeQuery = true)
	int findMaxID();

}
