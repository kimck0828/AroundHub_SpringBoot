package com.kimck0828.aroundhub.aroundhub_springboot.data.repository;

import com.kimck0828.aroundhub.aroundhub_springboot.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}
