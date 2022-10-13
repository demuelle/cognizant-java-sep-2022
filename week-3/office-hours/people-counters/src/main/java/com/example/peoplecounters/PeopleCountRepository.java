package com.example.peoplecounters;

import com.example.peoplecounters.model.PeopleCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface PeopleCountRepository extends JpaRepository<PeopleCount, Integer> {
}
