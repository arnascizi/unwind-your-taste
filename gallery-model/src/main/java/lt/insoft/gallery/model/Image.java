package lt.insoft.gallery.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "IMAGE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @Id
    @SequenceGenerator(name = "image_seq", sequenceName = "IMAGE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column(name = "FILE_NAME")
    @NotNull
    private String fileName;

    @Column
    private String description;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column
    @NotNull
    private byte[] image;

    @Column
    @NotNull
    private byte[] thumbnail;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "IMAGE_TAG", joinColumns = @JoinColumn(name = "IMAGE_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private List<Tag> tags;
}
