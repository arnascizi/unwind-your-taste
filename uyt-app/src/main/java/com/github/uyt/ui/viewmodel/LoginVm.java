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
import com.github.uyt.ui.view.LoginView;

import lombok.Getter;
import lombok.Setter;

public class LoginVm implements Serializable {
    private static final long serialVersionUID = 5576405403122295236L;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @WireVariable(rewireOnActivate = true) private transient AccountHelper accountHelper;

    @Getter @Setter private Map<String, String> vmsgs = new HashMap<>();
    @Getter @Setter private LoginView model = new LoginView();

    @Init
    public void init() {
    }

    @Command
    @NotifyChange({"model", "vmsgs"})
    public void doLogin() {
        if (isValid()) {
            Clients.submitForm("loginForm");
        }
    }

    private boolean isValid() {
        vmsgs.clear();

        if (StringUtils.isEmpty(model.getUsername())) {
            vmsgs.put(USERNAME, Labels.getRequiredLabel("error.empty"));
        }

        if (StringUtils.isEmpty(model.getPassword())) {
            vmsgs.put(PASSWORD, Labels.getRequiredLabel("error.empty"));
        }
        return vmsgs.isEmpty();
    }
}
