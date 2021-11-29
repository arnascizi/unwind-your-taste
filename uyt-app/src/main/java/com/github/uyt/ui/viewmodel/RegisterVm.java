package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import com.github.uyt.ui.helper.AccountHelper;
import com.github.uyt.ui.view.UserView;

import lombok.Getter;
import lombok.Setter;

public class RegisterVm implements Serializable {
    private static final long serialVersionUID = -4625796528885664236L;

    private final static String ERROR_EMPTY = "error.empty";
    private final static String ERR_NOT_LONGER_THAN = "error.not.longer";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";
    private final static int PASSWORD_MAX_LENGTH = 8;

    @WireVariable(rewireOnActivate = true) private transient AccountHelper accountHelper;

    @Getter @Setter private UserView model = new UserView();
    @Getter private Map<String, String> vmsgs = new HashMap<>();
    @Getter private String errorNotificationMessage;

    @Init
    public void init() {
        model.setRole("ROLE_ADMIN");
    }

    @Command
    @NotifyChange("vmsgs")
    public void doRegister() {
        Clients.submitForm("register-form");
        accountHelper.register(model);
    }

    @Command
    @NotifyChange("vmsgs")
    public void doSubmit() {
        Clients.submitForm("login-form");
    }

    private boolean isRegistrationValid() {
        getVmsgs().clear();

        String errorEmpty = Labels.getLabel(ERROR_EMPTY);
        if (StringUtils.isBlank(model.getUsername())) {
            vmsgs.put(EMAIL, errorEmpty);
            errorNotificationMessage = Labels.getRequiredLabel("error.login.data.incorrect");
        }

        if (StringUtils.isBlank(model.getPassword())) {
            vmsgs.put(PASSWORD, errorEmpty);
        } else if (model.getPassword().length() < PASSWORD_MAX_LENGTH) {
            vmsgs.put(PASSWORD, Labels.getRequiredLabel(ERR_NOT_LONGER_THAN, new Integer[] {PASSWORD_MAX_LENGTH}));
        }

        return getVmsgs().isEmpty();
    }

}
