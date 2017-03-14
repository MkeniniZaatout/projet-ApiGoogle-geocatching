package fr.unice.iut.shapes.resources;

public class GeoPoint {

    private double lng;
    private double lat;

    // On a besoin d'un constructeur par defaut et des getter/setter pour la serialisation automatique en json.
    public GeoPoint() {
    }

    public GeoPoint(double latitude, double longitude) {
        this.lat = latitude;
        this.lng = longitude;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

}
