package com.vividcode.imap.app.client.web.application.map;

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.controls.*;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.maptypes.MapTypeStyleElementType;
import com.google.gwt.maps.client.maptypes.MapTypeStyleFeatureType;
import com.google.gwt.maps.client.maptypes.MapTypeStyler;
import com.google.gwt.maps.client.overlays.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.vividcode.imap.common.shared.constants.GlobalParameters;
import com.vividcode.imap.common.shared.vo.LocationVO;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class GeolocalisationView extends ViewWithUiHandlers<GeolocalisationUiHandlers>
        implements GeolocalisationPresenter.MyView {
    public interface Binder extends UiBinder<Widget, GeolocalisationView> {
    }

    @UiField
    SimplePanel geoPanel;

    private MapWidget mapWidget;
    private Map<Long, Marker> positions = new HashMap<Long, Marker>();

    @Inject
    public GeolocalisationView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

//    @Override
//    public void adjustMapSize() {
//        mapWidget.triggerResize();
//    }

    @Override
    public void setPositions(final LocationVO location) {
        Float lat = location.getLatitude().floatValue();
        Float lng = location.getLongitude().floatValue();

        LatLng position = LatLng.newInstance(lat, lng);
        MarkerOptions options = MarkerOptions.newInstance();
        options.setPosition(position);
        options.setAnimation(Animation.DROP);
        options.setOptimized(true);

        final Marker localisation = Marker.newInstance(options);
        localisation.setMap(mapWidget);
        positions.put(location.getId(), localisation);

        localisation.addClickHandler(new ClickMapHandler() {
            public void onEvent(ClickMapEvent event) {
                drawInfoWindow(localisation);
            }
        });
    }

    private void drawInfoWindow(final Marker marker) {
        if (marker == null) {
            return;
        }

        HorizontalPanel hp = new HorizontalPanel();

        VerticalPanel vp = new VerticalPanel();

        hp.add(vp);
        InfoWindowOptions options = InfoWindowOptions.newInstance();
        options.setContent(hp);
        InfoWindow iw = InfoWindow.newInstance(options);
        iw.open(mapWidget, marker);
    }

    public Boolean isLoaded() {
        if (!positions.isEmpty()) {
            resetMarkers();
        }
        return mapWidget == null;
    }

    public void drawMap() {
        ZoomControlOptions zoomControlOptions = ZoomControlOptions.newInstance();
        zoomControlOptions.setStyle(ZoomControlStyle.SMALL);
        zoomControlOptions.setPosition(ControlPosition.TOP_LEFT);

        MapOptions mapOptions = MapOptions.newInstance();
        mapOptions.setMapTypeControl(true);
        mapOptions.setMapTypeId(MapTypeId.ROADMAP);

        MapTypeControlOptions mapTypeControlOptions = MapTypeControlOptions.newInstance();
        mapOptions.setMapTypeControlOptions(mapTypeControlOptions);
        MapTypeStyle[] mapTypeStyles = new MapTypeStyle[1];

        MapTypeStyle mapTypeStyle = MapTypeStyle.newInstance();
        mapTypeStyle.setElementType(MapTypeStyleElementType.GEOMETRY__STROKE);
        mapTypeStyle.setFeatureType(MapTypeStyleFeatureType.ADMINISTRATIVE__COUNTRY);

        MapTypeStyler[] stylers = new MapTypeStyler[1];
        MapTypeStyler styler = MapTypeStyler.newVisibilityStyler("off");
        stylers[0] = styler;
        mapTypeStyle.setStylers(stylers);
        mapTypeStyles[0] = mapTypeStyle;
        mapOptions.setMapTypeStyles(mapTypeStyles);

        mapOptions.setZoom(8);
        mapOptions.setZoomControl(true);
        mapOptions.setZoomControlOptions(zoomControlOptions);

        mapOptions.setStreetViewControl(false);
        StreetViewControlOptions streetViewControlOptions = StreetViewControlOptions.newInstance();
        streetViewControlOptions.setPosition(ControlPosition.RIGHT_BOTTOM);

        mapWidget = new MapWidget(mapOptions);
        mapWidget.setCenter(LatLng.newInstance(GlobalParameters.MOR_LAT, GlobalParameters.MOR_LNG));
        mapWidget.setSize(GlobalParameters.WIDTH_MAP, GlobalParameters.HEIGHT_MAP);
        mapWidget.getBounds();
        geoPanel.setWidget(mapWidget);

        getUiHandlers().checkLocalisations();
    }

    private void resetMarkers() {
        for (Marker marker : positions.values()) {
            marker.clear();
        }
        positions = new HashMap<Long, Marker>();
    }
}
