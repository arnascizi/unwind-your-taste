package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.Locale;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.ui.helper.AccountHelper;
import com.github.uyt.ui.view.LoggedUser;

import lombok.Getter;

public class TemplateVm implements Serializable {
    private static final long serialVersionUID = -4642314582073834590L;

    private static final Locale LOCALE_LT = new Locale("lt", "LT");
    private static final Locale LOCALE_EN = Locale.ENGLISH;

    @WireVariable(rewireOnActivate = true) private transient AccountHelper accountHelper;

    @Getter private String username;

    @Init
    public void init() {
        LoggedUser loggedUser = accountHelper.getLoggedUser();
        username = loggedUser != null ? loggedUser.getUsername() : "nothing";
    }

    @Command
    public void doSetLocaleEn() {
        Executions.getCurrent().getSession().setAttribute(Attributes.PREFERRED_LOCALE, LOCALE_EN);
        Executions.sendRedirect(null);
    }

    @Command
    public void doSetLocaleLt() {
        Executions.getCurrent().getSession().setAttribute(Attributes.PREFERRED_LOCALE, LOCALE_LT);
        Executions.sendRedirect(null);
    }

    public boolean isUserLogged() {
        return accountHelper.getLoggedUser() != null;
    }

    public String getCurrentLanguage() {
        Object localeAttribute = Executions.getCurrent().getSession().getAttribute(Attributes.PREFERRED_LOCALE);
        if (localeAttribute instanceof Locale) {
            return ((Locale) localeAttribute).getLanguage().toUpperCase();
        }
        return LOCALE_LT.getLanguage().toUpperCase();
    }
}
