package com.vividcode.imap.app.client.web.application.admin.locations;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import javax.inject.Inject;

public class LocationsView extends ViewWithUiHandlers<LocationsUiHandlers>
        implements LocationsPresenter.MyView {
    public interface Binder extends UiBinder<Widget, LocationsView> {
    }

    @Inject
    LocationsView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
