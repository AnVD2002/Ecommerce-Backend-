package com.example.Ecommerce.Repository.UserRepo;

import com.example.Ecommerce.Entity.User.Decentralization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface DecentralizationRepository extends JpaRepository<Decentralization, Integer> {
    @Query(value = "SELECT * FROM phanquyen WHERE tenquyenhan = :name", nativeQuery = true)
    Optional<Decentralization> findByAuthorityName(@Param("name") String name);
}
