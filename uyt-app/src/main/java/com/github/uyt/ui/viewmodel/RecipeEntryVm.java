package com.github.uyt.ui.viewmodel;

import java.io.Serializable;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import com.github.uyt.ui.helper.AccountHelper;
import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.view.RecipeView;

import lombok.Getter;
import lombok.Setter;

public class RecipeEntryVm implements Serializable {
    private static final long serialVersionUID= -1813920837374820512L;

    @WireVariable(rewireOnActivate = true) private transient AccountHelper accountHelper;
    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;

    @Getter @Setter private RecipeView model = new RecipeView();

    @Init
    public void init() {

    }

    @Command
    public void doUploadImage(@BindingParam("image") Media media) {
        if (!media.getContentType().startsWith("image/")) {
            Messagebox.show(Labels.getRequiredLabel("error.wrong.file"), "error.warning", Messagebox.OK, Messagebox.ERROR);
        }
        try {
            model.setImage(media.getByteData());
        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    @Command
    public void doSave() {
        if (isValid()) {
            recipeHelper.saveRecipe(model, accountHelper.getLoggedUser());
        }
    }

    private boolean isValid() {
        return false;
    }
}
