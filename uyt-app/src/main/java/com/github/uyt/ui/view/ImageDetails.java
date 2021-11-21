package com.github.uyt.ui.view;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDetails implements Serializable {
    private static final long serialVersionUID = -143346528840314787L;

    private Long id;
    @NotNull
    private String name;
    private String fileName;
    private String description;
    private String fileType;
    private byte[] image;
    private byte[] thumbnail;
    private List<TagView> tags;
    private UserView userView;
}
