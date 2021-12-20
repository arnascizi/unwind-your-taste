package com.github.uyt.ui.components;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.HtmlMacroComponent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import com.github.uyt.ui.helper.ReviewHelper;
import com.github.uyt.ui.view.ReviewView;

import lombok.Getter;
import lombok.Setter;

public class ReviewComponent extends HtmlMacroComponent {
    private static final long serialVersionUID = -8541924142904287852L;

    @WireVariable(rewireOnActivate = true) private transient ReviewHelper reviewHelper;

    @Getter @Setter private String reviewId;

    @Getter @Setter private ReviewView model;

    @Init
    private void init() throws Exception {
        model = reviewHelper.getSingleReview(Long.parseLong(reviewId));
    }

    @Command
    @NotifyChange({"model"})
    public void doDelete(String id) {
        Messagebox.show(Labels.getRequiredLabel("cocktail.delete.confirm"), "Question?", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, event -> {
            if (event.getName().equals("onOK")) {
                reviewHelper.deleteReview(Long.parseLong(id));
                Clients.alert(Labels.getRequiredLabel("cocktail.delete.success"));
            } else {
                Clients.alert(Labels.getRequiredLabel("cocktail.delete.cancel"));
            }
        });
    }
}
