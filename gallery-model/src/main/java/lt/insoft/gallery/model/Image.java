package lt.insoft.gallery.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "IMAGE")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Image {

    @Id
    @SequenceGenerator(name = "image_seq", sequenceName = "IMAGE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private LocalDateTime date;

    @Column
    private byte[] file;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "IMAGE_TAG", joinColumns = @JoinColumn(name = "IMAGE_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tags;

    public Image(Long id, String name, LocalDateTime date, String description, byte[] file) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.file = file;
    }
}
