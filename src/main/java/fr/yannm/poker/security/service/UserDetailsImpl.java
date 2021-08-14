package fr.yannm.poker.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.yannm.poker.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * UserDetailsImpl is the class that permit to access to the User's information with :
 * <ul>
 *     <li>An id</li>
 *     <li>A username</li>
 *     <li>An email</li>
 *     <li>A password</li>
 *     <li>A wallet</li>
 * </ul>
 *
 * @author Yann
 * @version 1.0
 * @name : UserDetailsImpl
 * @created 31 /07/2021 - 14:26
 * @project poker
 * @copyright Yann
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    /**
     * The id of the user.
     */
    @Getter
    @ApiModelProperty("The id of the user.")
    private Long id;

    /**
     * The username of the user.
     */
    @Getter
    @ApiModelProperty("The username of the user.")
    private String username;

    /**
     * The email of the user.
     */
    @Getter
    @ApiModelProperty("The email of the user.")
    private String email;

    /**
     * The password of the user. Will be ignored.
     */
    @JsonIgnore
    @Getter
    @ApiModelProperty("The password of the user. Will be ignored.")
    private String password;

    /**
     * The score of the user, in other word the total amount won.
     */
    @ApiModelProperty("The score of the user, in other word the total amount won.")
    @Getter
    private int score;

    /**
     * The number of game won.
     */
    @ApiModelProperty("The number of game won.")
    @Getter
    private int win;

    /**
     * The number of game lost.
     */
    @ApiModelProperty("The number of game lost.")
    @Getter
    private int lost;

    /**
     * The ratio won/lost.
     */
    @ApiModelProperty("The ratio won/lost.")
    @Getter
    private double ratio;

    /**
     * Instantiates a new User details.
     *
     * @param id          the id
     * @param username    the username
     * @param email       the email
     * @param password    the password
     * @param win         the win
     * @param lost        the lost
     * @param ratio       the ratio
     * @param authorities the authorities
     * @since 1.0
     */
    public UserDetailsImpl(Long id,
                           String username,
                           String email,
                           String password,
                           int score,
                           int win,
                           int lost,
                           double ratio,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.score = score;
        this.win = win;
        this.lost = lost;
        this.ratio = ratio;
        this.authorities = authorities;
    }

    /**
     * Build the UserDetails.
     *
     * @param user the user
     * @return the UserDetails
     * @since 1.0
     */
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getScore(),
                user.getWin(),
                user.getLost(),
                user.getRatio(),
                authorities);
    }


    /**
     * Create the list of authorities.
     */
    @Getter
    @ApiModelProperty("Create the list of authorities.")
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserDetailsImpl user = (UserDetailsImpl) obj;
        return Objects.equals(id, user.id);
    }
}
