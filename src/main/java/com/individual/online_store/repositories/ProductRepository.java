package com.individual.online_store.repositories;

import com.individual.online_store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select p from Product p where upper(p.title) like upper(concat('%', upper(:title), '%'))" +
            " and p.active is true")
    List<Product> findByTitle (@Param("title") String title);

    @Query(value = "select p from Product p where p.category.name = :name")
    List<Product> findByCategory(String category);

    @Query(value = "select p from Product p where p.author.authorId = :authorId")
    List<Product> findByAuthor (Long authorId);

    List<Product> findAllByActiveIsTrue();
}
