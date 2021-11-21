package com.github.uyt.ui.view;

import java.io.Serializable;

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
public class ImageThumbnail implements Serializable {
    private static final long serialVersionUID = 803931525124255098L;

    private Long id;
    private String name;
    private byte[] thumbnail;
}
