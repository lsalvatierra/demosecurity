package com.example.demosecurity.repository;

import com.example.demosecurity.model.bd.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>
{
    Rol findByNomrol(String nomrol);
}