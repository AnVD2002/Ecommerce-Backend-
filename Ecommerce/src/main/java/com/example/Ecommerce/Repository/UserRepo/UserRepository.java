package com.example.Ecommerce.Repository.UserRepo;

import com.example.Ecommerce.Entity.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select* from user where tentk = :name", nativeQuery = true)
    public Optional<User> FindByName(@Param("name") String name);
    @Query(value = "select email from user", nativeQuery = true)
    public Page<String> FindByEmailPage(Pageable pageable);
    @Query(value = "select phapdanh from user", nativeQuery = true)
    public Page<String> FindByNamePage(Pageable pageable);
    @Query(value = "select gioitinh from user", nativeQuery = true)
    public Page<String> FindByGenderPage(Pageable pageable);
    @Query(value = "select* from user where email = :email", nativeQuery = true)
    public Optional<User> FindByEmail(@Param("email") String email);



}
