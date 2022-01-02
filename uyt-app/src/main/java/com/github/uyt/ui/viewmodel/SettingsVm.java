package com.github.uyt.ui.viewmodel;

import java.io.Serializable;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import com.github.uyt.ui.helper.AccountHelper;
import com.github.uyt.ui.utility.SecurityFunctions;
import com.github.uyt.ui.view.UserView;

import lombok.Getter;
import lombok.Setter;

public class SettingsVm implements Serializable {
    private static final long serialVersionUID = 2858262057053641027L;

    @WireVariable(rewireOnActivate = true) private transient AccountHelper accountHelper;

    @Getter @Setter private UserView model;

    @Init
    public void init() {
        model = accountHelper.getUserByName(SecurityFunctions.getLoggedUser().getUsername());
    }

    @Command
    public void doUpdate() {
    //TODO
    }

    @Command
    public void doDelete() {
        Messagebox.show(Labels.getRequiredLabel("settings.delete.confirm"), "Question?", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, event -> {
            if (event.getName().equals(Events.ON_OK)) {
                accountHelper.getUserByName(model.getUsername());
                Clients.showNotification(Labels.getRequiredLabel("settings.delete.success"));
                Executions.sendRedirect("/logout");
            } else {
                Clients.showNotification(Labels.getRequiredLabel("settings.delete.cancel"));
            }
        });
    }
}
