package com.vividcode.imap.app.client.web.application.map;

import com.google.gwt.maps.client.LoadApi;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.vividcode.imap.app.client.place.NameTokens;
import com.vividcode.imap.app.client.web.application.ApplicationPresenter;
import com.vividcode.imap.app.client.web.application.map.GeolocalisationPresenter.MyProxy;
import com.vividcode.imap.app.client.web.application.map.GeolocalisationPresenter.MyView;
import com.vividcode.imap.common.client.security.LoggedInGatekeeper;
import com.vividcode.imap.common.shared.constants.MapIcons;
import com.vividcode.imap.common.shared.vo.LocationVO;
import com.vividcode.imap.common.shared.vo.SpotVO;
import com.vividcode.imap.common.shared.vo.TypeSpotVO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;

public class GeolocalisationPresenter extends Presenter<MyView, MyProxy>
        implements GeolocalisationUiHandlers {
    public interface MyView extends View, HasUiHandlers<GeolocalisationUiHandlers> {
        void setPositions(SpotVO location);

        void drawMap();

        Boolean isLoaded();

        void setPositions(SpotVO spot, String icon);
    }

    @ProxyStandard
    @NameToken(NameTokens.maps)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<GeolocalisationPresenter> {
    }

    private final DispatchAsync dispatcher;

    @Inject
    public GeolocalisationPresenter(EventBus eventBus,
                                    MyView view,
                                    MyProxy proxy,
                                    DispatchAsync dispatcher) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;

        getView().setUiHandlers(this);
    }

    @Override
    public void checkLocalisations() {
        SpotVO spot1 = new SpotVO();
        spot1.setTitle("spot 1");
        spot1.setDescription("description");
        spot1.setId(1L);
        spot1.setCreated(new Date());
        spot1.setLocationOwner("simu");
        TypeSpotVO type1 = new TypeSpotVO();
        type1.setId(1L);
        type1.setTitle("Hospital");
        LocationVO location = new LocationVO();
        location.setId(1L);
        location.setLatitude(33.51792677420186);
        location.setLongitude(-7.6739501953125);
        spot1.setType(type1);
        spot1.setLocation(location);
        getView().setPositions(spot1, MapIcons.HOSPITAL);

        SpotVO spot2 = new SpotVO();
        spot2.setTitle("spot 2");
        spot2.setDescription("description");
        spot2.setId(2L);
        spot2.setCreated(new Date());
        spot2.setLocationOwner("simu");
        TypeSpotVO type2 = new TypeSpotVO();
        type2.setId(2L);
        type2.setTitle("Bitches");
        LocationVO location2 = new LocationVO();
        location2.setId(2L);
        location2.setLatitude(33.566286567920116);
        location2.setLongitude(-7.562370300292969);
        spot2.setType(type2);
        spot2.setLocation(location2);
        getView().setPositions(spot2, MapIcons.BITCHES);

        SpotVO spot3 = new SpotVO();
        spot3.setTitle("spot 3");
        spot3.setDescription("description");
        spot3.setId(1L);
        spot3.setCreated(new Date());
        spot3.setLocationOwner("simu");
        LocationVO location3 = new LocationVO();
        location3.setId(3L);
        location3.setLatitude(33.56971937037364);
        location3.setLongitude(-7.625370025634766);
        spot3.setType(type1);
        spot3.setLocation(location3);
        getView().setPositions(spot3, MapIcons.HOSPITAL);

    }

    @Override
    protected void onReveal() {
        if (getView().isLoaded()) {
            loadMapApi();
        } else {
            checkLocalisations();
        }
    }

    private void loadMapApi() {
        ArrayList<LoadApi.LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
        loadLibraries.add(LoadApi.LoadLibrary.PLACES);

        Runnable onLoad = new Runnable() {
            @Override
            public void run() {
                getView().drawMap();
            }
        };

        LoadApi.go(onLoad, loadLibraries, true);
    }
}
