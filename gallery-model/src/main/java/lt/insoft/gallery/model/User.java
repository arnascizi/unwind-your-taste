package lt.insoft.gallery.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "PK_USER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "USER_USERNAME")
    private String username;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_EMAIL")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE")
    private Role role;

    @Column(name = "USER_CREATED_AT")
    private Date created_at;

    @OneToMany
    private List<Image> images;
}
