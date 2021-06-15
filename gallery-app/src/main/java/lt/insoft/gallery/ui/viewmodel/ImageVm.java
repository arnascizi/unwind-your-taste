package lt.insoft.gallery.ui.viewmodel;

import java.io.Serializable;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import lombok.Getter;
import lt.insoft.gallery.ui.helper.ImageViewHelper;
import lt.insoft.gallery.ui.helper.UserViewHelper;
import lt.insoft.gallery.ui.view.ImageDetails;

public class ImageVm implements Serializable {
    private static final long serialVersionUID = -4894191210706198976L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageViewHelper imageViewHelper;

    @Getter
    private ImageDetails imageDetails;

    @Getter
    private boolean hasRoleAdmin;

    @Init
    public void init(@QueryParam("id") String id) {
        Executions.getCurrent().getParameter("id");
        imageDetails = imageViewHelper.getDetailedImageView(Long.parseLong(id));
        hasRoleAdmin = UserViewHelper.hasRole("ROLE_ADMIN");
    }

    @Command
    public void doDelete() {
        Messagebox.show(Labels.getRequiredLabel("delete.confirm") + " " + imageDetails.getName() + "?", "Question?", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, event -> {
            if (event.getName().equals("onOK")) {
                imageViewHelper.delete(imageDetails);
                Clients.alert(Labels.getRequiredLabel("delete.success"));
                Executions.sendRedirect("/gallery");
            } else {
                Clients.alert(Labels.getRequiredLabel("delete.cancel"));
            }
        });
    }
}
