package com.github.uyt.ui.view;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggedUser implements Serializable {
    private static final long serialVersionUID = -3641262556141226006L;

    private Long id;
    private String username;
    private String password;
    private String role;
}
