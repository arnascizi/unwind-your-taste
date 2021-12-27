package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import com.github.uyt.ui.helper.AccountHelper;
import com.github.uyt.ui.helper.CommonAttributesHelper;
import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.utility.SecurityFunctions;
import com.github.uyt.ui.view.CategoryView;
import com.github.uyt.ui.view.ComplexityView;
import com.github.uyt.ui.view.CompositionView;
import com.github.uyt.ui.view.ProductView;
import com.github.uyt.ui.view.RecipeView;

import lombok.Getter;
import lombok.Setter;

public class CocktailEntryVm implements Serializable {
    private static final long serialVersionUID = -1813920837374820512L;

    @WireVariable(rewireOnActivate = true) private transient AccountHelper accountHelper;
    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;
    @WireVariable(rewireOnActivate = true) private transient CommonAttributesHelper commonAttributesHelper;

    @Getter @Setter private List<CompositionView> products = new ArrayList<>();
    @Getter @Setter private RecipeView model = new RecipeView();
    @Getter @Setter private ProductView productModel = new ProductView();
    @Getter @Setter private CompositionView ingredientModel = new CompositionView();
    @Getter @Setter private String productId;
    @Getter private List<CategoryView> categories = new ArrayList<>();
    @Getter private List<ProductView> ingredients = new ArrayList<>();
    @Getter private List<ComplexityView> complexities = new ArrayList<>();

    @Init
    public void init() {
        categories = commonAttributesHelper.getAllDetailedCategories();
        ingredients = commonAttributesHelper.getAllIngredients();
        complexities = commonAttributesHelper.getAllComplexities();
    }

    @Command
    @NotifyChange("model")
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
    @NotifyChange("model")
    public void doSubmit() {
        if (isValid()) {
            RecipeView testView = new RecipeView();
            testView.builder()
                    .title("recipeView.getTitle()")
                    .guideline("recipeView.getGuideline()")
                    .serving("recipeView.getServing()")
                    .uploadTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .image(model.getImage())
                    .build();
            recipeHelper.saveRecipe(testView, SecurityFunctions.getLoggedUser());
        }
    }

    @Command
    @NotifyChange({"ingredientModel", "products", "amount"})
    public void doAddProduct() {

        recipeHelper.saveRecipe(RecipeView.builder()
                .title("recipeView.getTitle()")
                .guideline("recipeView.getGuideline()")
                .serving("recipeView.getServing()")
                .uploadTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .image(model.getImage())
                .categoryView(categories.get(0))
                .complexity(complexities.get(0))
                .build(), SecurityFunctions.getLoggedUser());

        // ingredientModel.setProductView(productModel);
        // products.add(ingredientModel);
        // System.out.println(productModel.getName());
        // productModel = new ProductView();
        // ingredientModel = new CompositionView();
    }

    private boolean isValid() {
        return false;
    }
}
