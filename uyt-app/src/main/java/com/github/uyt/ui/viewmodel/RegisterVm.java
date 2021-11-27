package com.github.uyt.ui.viewmodel;

import java.io.Serializable;

import org.zkoss.bind.annotation.Init;

import lombok.Getter;

public class RegisterVm implements Serializable {
    private static final long serialVersionUID = -4625796528885664236L;

    @Getter private String message;

    @Init
    public void init() {
        message = "Yay!";
    }
}
