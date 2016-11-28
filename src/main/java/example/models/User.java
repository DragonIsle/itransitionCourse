package example.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import example.enums.AuthType;
import example.enums.Role;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class User {

    @Id
    @Column
    @JsonProperty
    private String login;

    @Column
    @JsonProperty
    private String password;

    @Column(name = "auth_type")
    @JsonProperty
    private String type;

    @Column
    @JsonProperty
    private String role;

    @Column(name="avatar_id")
    @JsonProperty
    private String avatarId;

    @Column
    @JsonProperty
    private String name;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Creative> creatives;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_achieve", joinColumns=@JoinColumn(name="user_name"), inverseJoinColumns=@JoinColumn(name="achieve_name"))
    private List<Achievement> achievements;

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<Achievement> getAchievements() {

        return achievements;
    }

    public void setCreatives(Set<Creative> creatives) {
        this.creatives = creatives;
    }

    public Set<Creative> getCreatives() {
        return creatives;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatarId(String avatarId) {

        this.avatarId = avatarId;
    }

    public void setRole(String role) {

        this.role = role;
    }

    public void setType(String type) {

        this.type = type;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public String getName() {

        return name;
    }

    public String getAvatarId() {

        return avatarId;
    }

    public String getRole() {

        return role;
    }

    public String getType() {

        return type;
    }

    public String getPassword() {

        return password;
    }

    public String getLogin() {
        return login;

    }
}

