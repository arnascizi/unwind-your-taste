package com.github.uyt.ui.view;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedUser implements Serializable {
    private static final long serialVersionUID = -1385105897781345648L;

    private String username;
    private String role;
}