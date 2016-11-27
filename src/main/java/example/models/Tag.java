package example.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by saul on 11/22/16.
 */
@Entity
@Table(name = "tags")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Tag {
    @Id
    @Column
    @JsonProperty
    private String name;

    public void setCreatives(List<Creative> creatives) {
        this.creatives = creatives;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public List<Creative> getCreatives() {

        return creatives;
    }

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="creative_tag", joinColumns=@JoinColumn(name="tag_name"), inverseJoinColumns=@JoinColumn(name="creative_id"))
    private List<Creative> creatives;
}
