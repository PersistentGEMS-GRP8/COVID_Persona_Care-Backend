package com.covidpersona.repository;
import com.covidpersona.entity.PersonaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<PersonaUser,Long> {

    @Query("SELECT p FROM PersonaUser p WHERE LOWER(p.username) = LOWER(:username)")
    PersonaUser finddaoUserByUserName(@Param("username") String username);
}

