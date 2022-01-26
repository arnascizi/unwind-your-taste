package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import com.github.uyt.enums.PageLocationEnum;
import com.github.uyt.ui.helper.AccountHelper;
import com.github.uyt.ui.helper.CommonAttributesHelper;
import com.github.uyt.ui.helper.RecipeHelper;
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

    @Getter @Setter private Map<String, String> vmsgs = new HashMap<>();
    @Getter @Setter private List<CompositionView> products = new ArrayList<>();
    @Getter @Setter private CompositionView ingredientModel = new CompositionView();
    @Getter @Setter private ComplexityView complexity;
    @Getter @Setter private CategoryView category;
    @Getter @Setter private ProductView productModel = new ProductView();
    @Getter @Setter private RecipeView model = new RecipeView();
    @Getter private List<ComplexityView> complexities = new ArrayList<>();
    @Getter private List<CategoryView> categories = new ArrayList<>();
    @Getter private List<ProductView> ingredients = new ArrayList<>();

    @Init
    public void init() {
        categories = commonAttributesHelper.getCocktailCategories();
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
    @NotifyChange({"model", "complexities", "categories", "ingredients"})
    public void doSubmit() {
        if (isValid()) {
            model.setProducts(products);
            model.setCategoryView(category);
            model.setComplexity(complexity);
            recipeHelper.saveRecipe(model);
            Executions.sendRedirect(PageLocationEnum.COCKTAILS.getUrl());
        }
    }

    @Command
    @NotifyChange({"ingredientModel", "products", "amount", "productModel"})
    public void doAddProduct() {
        ingredientModel.setProductView(productModel);
        products.add(ingredientModel);
        productModel = new ProductView();
        ingredientModel = new CompositionView();
    }

    public String getIngredientValue(CompositionView compositionView) {
        return compositionView.getProductView().getName() + StringUtils.SPACE + compositionView.getAmount() + StringUtils.SPACE + compositionView.getProductView().getMeasurement();
    }

    @Command
    @NotifyChange({"model", "products"})
    public void doResolveCategory() {
        products.stream().forEach(System.out::println);
    }

    @Command
    public void doCancel() {
        Executions.sendRedirect(PageLocationEnum.COCKTAILS.getUrl());
    }

    private boolean isValid() {
        vmsgs.clear();

        if (category == null) {
            vmsgs.put("category", Labels.getRequiredLabel("error.empty"));
        }

        if (model.getCategoryView() == null) {
            vmsgs.put("category", Labels.getRequiredLabel("error.empty"));
        }

        if (model.getGuideline() == null) {
            vmsgs.put("category", Labels.getRequiredLabel("error.empty"));
        }

        if (StringUtils.isEmpty(model.getTitle())) {
            vmsgs.put("title", Labels.getRequiredLabel("error.empty"));
        }

        if (model.getImage() == null) {
            vmsgs.put("category", Labels.getRequiredLabel("error.empty"));
        }

        if (model.getUploader() == null) {
            vmsgs.put("category", Labels.getRequiredLabel("error.empty"));
        }

        if (model.getServing() == null) {
            vmsgs.put("category", Labels.getRequiredLabel("error.empty"));
        }

        return vmsgs.isEmpty();
    }
}
