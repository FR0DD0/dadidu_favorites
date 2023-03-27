package com.makiia.crosscutting.persistence.repository;

import com.makiia.crosscutting.persistence.entity.EntyRecmaesfavorito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EntyRecmaesfavoritoRepository extends JpaRepository<EntyRecmaesfavorito,String>
{
    String FILTER_USERS_QUERY = "select c from EntyRecmaesfavorito c where UPPER(c.recNroregRefv) like concat('%',upper(?1),'%')";
    @Query(value = FILTER_USERS_QUERY)
    Page<EntyRecmaesfavorito> findNameFavorite(String filter , Pageable pageable);

    Optional<EntyRecmaesfavorito> findById(String id);

}
