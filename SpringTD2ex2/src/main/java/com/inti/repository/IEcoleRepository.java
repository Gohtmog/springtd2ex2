package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.*;


@RestController
@RequestMapping(name = "ecole")
public interface IEcoleRepository extends JpaRepository<Ecole, Integer>{
	
	@Query(value= "select max(id) from ecole", nativeQuery = true)
	int findMaxID();


}
