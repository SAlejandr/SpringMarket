package com.example.demo.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Rol;
import com.example.demo.pojos.Usuario;
@Repository
public interface RolRepository extends JpaRepository<Rol, Byte> {

}
