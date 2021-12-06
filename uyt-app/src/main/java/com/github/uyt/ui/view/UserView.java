package com.github.uyt.ui.view;

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
public class UserView {
    private static final long serialVersionUID = -3641262556141226006L;

    private String username;
    private String password;
}
