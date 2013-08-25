package com.vividcode.imap.app.client.web.application.admin.locations;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.vividcode.imap.app.client.place.NameTokens;
import com.vividcode.imap.app.client.web.application.admin.SettingsPresenter;

import javax.inject.Inject;

public class LocationsPresenter extends Presenter<LocationsPresenter.MyView, LocationsPresenter.MyProxy>
        implements LocationsUiHandlers {
    public interface MyView extends View, HasUiHandlers<LocationsUiHandlers> {
    }

    @ProxyStandard
    @NameToken(NameTokens.locations)
    public interface MyProxy extends ProxyPlace<LocationsPresenter> {
    }

    @Inject
    LocationsPresenter(EventBus eventBus,
                       MyView view,
                       MyProxy proxy) {
        super(eventBus, view, proxy, SettingsPresenter.TYPE_SetSettingContent);

        getView().setUiHandlers(this);
    }
}
