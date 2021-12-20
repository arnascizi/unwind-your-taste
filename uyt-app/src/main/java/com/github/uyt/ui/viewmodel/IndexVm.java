package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.ui.helper.CommonAttributesHelper;
import com.github.uyt.ui.view.CategoryView;

import lombok.Getter;

public class IndexVm implements Serializable {
    private static final long serialVersionUID = -68937799999085117L;

    @WireVariable(rewireOnActivate = true) private transient CommonAttributesHelper commonAttributesHelper;

    @Getter private List<CategoryView> categories;

    @Init
    private void init() {
        categories = commonAttributesHelper.getCocktailCategories().stream().limit(4).collect(Collectors.toList());
    }
}
