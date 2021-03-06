package example.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by saul on 11/11/16.
 */
@Entity
@Table(name="creatives")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Creative {
    @Id
    @Column
    @JsonProperty
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "login", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User author;

    @Column
    @JsonProperty
    private String name;

    @Column
    @JsonProperty
    private double rating;

    @Column(name="rate_count")
    @JsonProperty
    private int rateCount;

    @Column(name = "author_name")
    @JsonProperty
    private String authorName;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "creative", fetch = FetchType.EAGER)
    private List<Chapter> chapters;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="creative_tag", joinColumns=@JoinColumn(name="creative_id"), inverseJoinColumns=@JoinColumn(name="tag_name"))
    private List<Tag> tags;

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }

    public int getRateCount() {

        return rateCount;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {

        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }
    public void setAuthorName(String author_name) {
        this.authorName = author_name;
    }

    public String getAuthorName() {

        return authorName;
    }

    public int getId() {

        return id;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setChapters(List<Chapter> chapters) {

        this.chapters = chapters;
    }

    public void setAuthor(User author) {

        this.author = author;
    }

    public double getRating() {

        return rating;
    }

    public List<Chapter> getChapters() {

        return chapters;
    }

    public User getAuthor() {

        return author;
    }
}
