package fr.yannm.poker.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Role is the entity that represents a role with :
 * <ul>
 *     <li>An id</li>
 *     <li>A name</li>
 * </ul>
 * @author Yann
 * @version 1.0
 * @name : Role
 * @created 30/07/2021 - 14:55
 * @project poker
 * @copyright Yann
 **/
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role {

    /**
     * "The id of the role."
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @ApiModelProperty("The id of the role.")
    private Long id;

    /**
     * The name of the role.
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @Getter
    @Setter
    @ApiModelProperty("The name of the role.")
    private ERole name;


    /**
     * Instantiates a new Role.
     *
     * @param name the name
     */
    public Role(ERole name) {
        this.name = name;
    }
}
