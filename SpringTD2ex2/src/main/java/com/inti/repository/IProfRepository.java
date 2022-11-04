package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Prof;

@RestController
@RequestMapping(name = "Prof")
public interface IProfRepository extends JpaRepository<Prof, Integer>{

	@Query(value= "select max(id) from Prof", nativeQuery = true)
	int findMaxID();

}
