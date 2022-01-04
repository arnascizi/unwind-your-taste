package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.enums.PageLocationEnum;
import com.github.uyt.model.CommonConstants;
import com.github.uyt.ui.helper.AccountHelper;
import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.utility.SecurityFunctions;
import com.github.uyt.ui.view.LoggedUser;
import com.github.uyt.ui.view.SearchView;

import lombok.Getter;
import lombok.Setter;

public class TemplateVm implements Serializable {
    private static final long serialVersionUID = -4642314582073834590L;

    @WireVariable(rewireOnActivate = true) private transient AccountHelper accountHelper;
    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;

    @Getter @Setter private SearchView search = new SearchView();
    @Getter private String username;

    @Init
    public void init() {
        LoggedUser loggedUser = SecurityFunctions.getLoggedUser();
        username = loggedUser != null ? loggedUser.getUsername() : StringUtils.EMPTY;
    }

    @Command
    public void doSetLocaleEn() {
        Executions.getCurrent().getSession().setAttribute(Attributes.PREFERRED_LOCALE, CommonConstants.LOCALE_EN);
        Executions.sendRedirect(null);
    }

    @Command
    public void doSetLocaleLt() {
        Executions.getCurrent().getSession().setAttribute(Attributes.PREFERRED_LOCALE, CommonConstants.LOCALE_LT);
        Executions.sendRedirect(null);
    }

    @Command
    public void doSearch() {
        Executions.sendRedirect( PageLocationEnum.COCKTAIL.getUrl() + "?searchValue=" + search.getSearchValue());
    }

    public boolean isFullPage() {
        return false;
    }

    public boolean isUserLogged() {
        return SecurityFunctions.isUserLogged();
    }

    public String getCurrentLanguage() {
        Object localeAttribute = Executions.getCurrent().getSession().getAttribute(Attributes.PREFERRED_LOCALE);
        if (localeAttribute instanceof Locale) {
            return ((Locale) localeAttribute).getLanguage().toUpperCase();
        }
        return CommonConstants.LOCALE_LT.getLanguage().toUpperCase();
    }
}
