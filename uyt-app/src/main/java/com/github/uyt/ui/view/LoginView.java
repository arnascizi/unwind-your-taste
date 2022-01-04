package com.github.uyt.ui.view;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginView implements Serializable {
    private static final long serialVersionUID = -2345329776774513577L;

    private String username;
    private String password;
}
