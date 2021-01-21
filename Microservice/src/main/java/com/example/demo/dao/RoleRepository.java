package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Role;
import com.example.demo.enumeration.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(RoleName roleAdmin);

}
