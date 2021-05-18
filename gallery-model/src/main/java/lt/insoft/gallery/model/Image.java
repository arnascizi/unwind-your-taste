package lt.insoft.gallery.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "IMAGE")
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @Column(name = "PK_IMAGE_ID")
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "IMAGE_NAME")
    private String name;

    @Column(name = "IMAGE_DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "IMAGE_CREATED_AT")
    private Date createdAt;

    @NotNull
    @Column(name = "IMAGE_UPDATED_AT")
    private Date updatedAt;

    @NotNull
    @Column(name = "IMAGE_FILE")
    private Byte[] file;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FK_AUTHOR_ID")
    private User author;
}
