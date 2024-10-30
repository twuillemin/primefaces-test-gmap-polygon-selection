package org.primefaces.test;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.jboss.logging.Logger;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.*;

import java.io.Serializable;

@Named
@ViewScoped
public class TestView implements Serializable {

    final static private Logger logger = Logger.getLogger(TestView.class);

    private MapModel<Long> polygons;
    private MapModel<Long> makers;
    private MapModel<Long> polygonsAndMarkers;

    private String lastIdSelected;

    @PostConstruct
    public void init() {
        polygons = new DefaultMapModel<>();
        polygons.addOverlay(makePolygon1(1));
        polygons.addOverlay(makePolygon2(2));

        makers = new DefaultMapModel<>();
        makers.addOverlay(makeMarker1(3));
        makers.addOverlay(makeMarker2(4));

        polygonsAndMarkers = new DefaultMapModel<>();
        polygonsAndMarkers.addOverlay(makeMarker1(5));
        polygonsAndMarkers.addOverlay(makePolygon1(6));
        polygonsAndMarkers.addOverlay(makePolygon2(7));
        polygonsAndMarkers.addOverlay(makeMarker2(8));
    }

    public MapModel<Long> getModelPolygons() {
        return polygons;
    }

    public MapModel<Long> getModelMarkers() {
        return makers;
    }

    public MapModel<Long> getModelPolygonsAndMarkers() {
        return polygonsAndMarkers;
    }

    public String getLastIdSelected() {
        return lastIdSelected;
    }

    public void onOverlaySelect(OverlaySelectEvent<Long> event) {
        final Long id;

        if (event.getOverlay() instanceof Polygon<?>) {
            final Polygon<Long> polygon = (Polygon<Long>) event.getOverlay();
            id = polygon.getData();
            lastIdSelected = "Polygon #" + (id != null ? id.toString() : "null");
        } else if (event.getOverlay() instanceof Marker<?>) {
            final Marker<Long> marker = (Marker<Long>) event.getOverlay();
            id = marker.getData();
            lastIdSelected = "Marker #" + (id != null ? id.toString() : "null");
        } else {
            id = null;
        }

        logger.debug("getIdFromOverlay = " + (id != null ? id : "null"));
    }

    private Polygon<Long> makePolygon1(final long id) {
        final Polygon<Long> polygon = new Polygon<>();
        polygon.setData(id);
        polygon.setStrokeWeight(3);
        polygon.setStrokeColor("#FF0000");
        polygon.setStrokeOpacity(0.7);
        polygon.setFillColor("#CCCCCC");
        polygon.setFillOpacity(0.1);
        polygon.getPaths().add(new LatLng(41.997114884587276, 21.438009932209390));
        polygon.getPaths().add(new LatLng(42.000184549965255, 21.438009932209390));
        polygon.getPaths().add(new LatLng(42.000184549965255, 21.442119198588493));
        polygon.getPaths().add(new LatLng(41.997114884587276, 21.442119198588493));

        return polygon;
    }

    private Polygon<Long> makePolygon2(final long id) {
        final Polygon<Long> polygon = new Polygon<>();
        polygon.setData(id);
        polygon.setStrokeWeight(3);
        polygon.setStrokeColor("#FF0000");
        polygon.setStrokeOpacity(0.7);
        polygon.setFillColor("#CCCCCC");
        polygon.setFillOpacity(0.1);
        polygon.getPaths().add(new LatLng(41.997553461580680, 21.444640318139285));
        polygon.getPaths().add(new LatLng(42.000009069148405, 21.444640318139285));
        polygon.getPaths().add(new LatLng(42.000009069148405, 21.448374013294600));
        polygon.getPaths().add(new LatLng(41.997553461580680, 21.448374013294600));

        return polygon;
    }

    private Marker<Long> makeMarker1(final long id) {
        return new Marker<>(
                new LatLng(41.99882115264601, 21.440413258024208),
                "Marker1",
                id,
                null);
    }

    private Marker<Long> makeMarker2(final long id) {
        return new Marker<>(
                new LatLng(41.99869360656399, 21.446786081584676),
                "Marker2",
                id,
                null);
    }

}
