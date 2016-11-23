package example.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by saul on 11/14/16.
 */
@Entity
@Table(name="chapters")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Chapter {
    @Id
    @Column
    @JsonProperty
    private int id;

    @Column
    @JsonProperty
    private int number;

    @Column(name = "file_ref")
    @JsonProperty
    private String fileRef;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creative_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Creative creative;

    public void setCreative(Creative creative) {
        this.creative = creative;
    }

    public Creative getCreative() {

        return creative;
    }

    public void setFileRef(String fileRef) {
        this.fileRef = fileRef;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getNumber() {

        return number;
    }

    public int getId() {

        return id;
    }

    public String getFileRef() {

        return fileRef;
    }
}
