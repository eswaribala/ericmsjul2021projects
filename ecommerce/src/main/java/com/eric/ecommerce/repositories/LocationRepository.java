package com.eric.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eric.ecommerce.models.Location;

public interface LocationRepository extends JpaRepository<Location,Long>{

}
