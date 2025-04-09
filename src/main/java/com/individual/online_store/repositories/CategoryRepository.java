package com.individual.online_store.repositories;

import com.individual.online_store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query(value = "select c from Category c where upper(c.name) like upper(concat('%', upper(:name), '%'))")
    Optional<Category> findByName(@Param("name") String name);
}
