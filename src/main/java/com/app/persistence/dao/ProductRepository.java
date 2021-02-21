package com.app.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.persistence.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
