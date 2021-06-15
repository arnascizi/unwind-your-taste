package lt.insoft.gallery.ui.viewmodel;

import java.io.Serializable;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import lombok.Getter;
import lombok.Setter;
import lt.insoft.gallery.ui.helper.UserViewHelper;
import lt.insoft.gallery.ui.view.UserView;

public class LoginVm implements Serializable {
    private static final long serialVersionUID = -4058088173205073058L;

    @WireVariable(rewireOnActivate = true)
    private transient UserViewHelper userViewHelper;

    @Getter
    @Setter
    private UserView userView;

    @Init
    public void init() {
    }

    @Command
    public void doSubmit() {
        Clients.submitForm("loginform");
    }
}