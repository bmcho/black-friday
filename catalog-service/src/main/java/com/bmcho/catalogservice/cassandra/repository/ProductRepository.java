package com.bmcho.catalogservice.cassandra.repository;

import com.bmcho.catalogservice.cassandra.entity.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CassandraRepository<Product, Long> {
}
