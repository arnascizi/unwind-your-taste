package com.github.uyt.ui.view;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedUser implements Serializable {
    private static final long serialVersionUID = -1385105897781345648L;

    private Long id;
    private String username;
    private String role;
}
