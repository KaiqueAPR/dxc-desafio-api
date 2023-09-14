package com.kaiqueapr.dxcdesafioapi.repositories;

import com.kaiqueapr.dxcdesafioapi.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {
    Optional<ClienteModel> findByNmEmail(String nmEmail);
}
