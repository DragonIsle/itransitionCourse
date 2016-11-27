package example.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by saul on 11/22/16.
 */
@Entity
@Table(name="achievements")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Achievement {
    @Id
    @Column
    @JsonProperty
    private String name;

    @Column(name="image_url")
    @JsonProperty
    private String imageUrl;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_achieve", joinColumns=@JoinColumn(name="achieve_name"), inverseJoinColumns=@JoinColumn(name="user_name"))
    private List<User> owners;

    public List<User> getOwners() {
        return owners;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public String getName() {

        return name;
    }

    public void setOwners(List<User> owners) {

        this.owners = owners;
    }

    public void setImageUrl(String imageUrl) {

        this.imageUrl = imageUrl;
    }

    public void setName(String name) {

        this.name = name;
    }
}
