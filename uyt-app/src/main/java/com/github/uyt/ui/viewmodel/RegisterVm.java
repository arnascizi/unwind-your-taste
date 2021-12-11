package com.github.uyt.ui.viewmodel;

import java.io.Serializable;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import com.github.uyt.ui.helper.AccountHelper;
import com.github.uyt.ui.view.UserView;

import lombok.Getter;
import lombok.Setter;

public class RegisterVm implements Serializable {
    private static final long serialVersionUID = -4625796528885664236L;

    private static final String ERROR_EMPTY = "error.empty";
    private static final String ERR_NOT_LONGER_THAN = "error.not.longer";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final int PASSWORD_MAX_LENGTH = 8;

    @WireVariable(rewireOnActivate = true) private transient AccountHelper accountHelper;

    @Getter @Setter private UserView model = new UserView();

    @Init
    public void init() {
    }

    @Command
    @NotifyChange({"vmsgs", "model"})
    public void doRegister() {
        accountHelper.register(model);
        Clients.submitForm("register-form");
    }
}
