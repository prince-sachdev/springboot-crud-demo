package com.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>{

}
