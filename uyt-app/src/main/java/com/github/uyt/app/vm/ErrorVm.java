package com.github.uyt.app.vm;

import java.io.Serializable;

import org.zkoss.bind.annotation.Init;

public class ErrorVm implements Serializable {
    private static final long serialVersionUID = 6367417906175763775L;

    private String errorMsg;

    @Init
    public void init() {
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
