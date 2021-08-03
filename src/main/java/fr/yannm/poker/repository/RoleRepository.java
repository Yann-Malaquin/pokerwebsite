package fr.yannm.poker.repository;

import fr.yannm.poker.model.ERole;
import fr.yannm.poker.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * RoleRepository is the interface where to find the differents query for the entity Role.
 *
 * @author Yann
 * @version 1.0
 * @name : RoleRepository
 * @created 30/07/2021 - 15:07
 * @project poker
 * @copyright Yann
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Find a role by its name
     *
     * @param name
     * @return an optional role
     * @since 1.0
     */
    Optional<Role> findByName(ERole name);
}
