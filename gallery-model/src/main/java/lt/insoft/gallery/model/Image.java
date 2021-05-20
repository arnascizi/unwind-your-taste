package lt.insoft.gallery.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "IMAGE")
@Getter
@Setter
@NoArgsConstructor
public class Image {

    @Id
    @SequenceGenerator(name = "image_seq", sequenceName = "IMAGE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDate date;

    @Column
    private String quality;

    @Column
    private String description;

    @Column
    private byte[] file;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "IMAGE_TAG", joinColumns = @JoinColumn(name = "IMAGE_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tags;

    public Image(String name, String quality, String description, byte[] file) {
        this.name = name;
        this.quality = quality;
        this.description = description;
        this.file = file;
    }
}
