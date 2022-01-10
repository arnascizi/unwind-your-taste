package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import com.github.uyt.enums.PageLocationEnum;
import com.github.uyt.ui.helper.AccountHelper;
import com.github.uyt.ui.view.UserView;

import lombok.Getter;
import lombok.Setter;

public class RegisterVm implements Serializable {
    private static final long serialVersionUID = -4625796528885664236L;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String PASS_REPEAT = "passRepeat";
    private static final String EMAIL = "email";

    @WireVariable(rewireOnActivate = true) private transient AccountHelper accountHelper;

    @Getter @Setter private Map<String, String> vmsgs = new HashMap<>();
    @Getter @Setter private UserView model = new UserView();

    @Init
    public void init() {
    }

    @Command
    @NotifyChange({"vmsgs", "model"})
    public void doRegister() {
        if (isValid()) {
            accountHelper.register(model);
            Clients.submitForm("registerForm");
            Executions.sendRedirect(PageLocationEnum.LOGIN.getUrl());
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

        if (StringUtils.isEmpty(model.getPassRepeat())) {
            vmsgs.put(PASS_REPEAT, Labels.getRequiredLabel("error.empty"));
        }

        if (StringUtils.isEmpty(model.getEmail())) {
            vmsgs.put(EMAIL, Labels.getRequiredLabel("error.empty"));
        }

        return vmsgs.isEmpty();
    }
}
