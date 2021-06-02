export interface CurrentLocation {
  latitude: number;
  longitude: number;
}

export interface GeoLocationPlugin {
  getLocation: () => Promise<CurrentLocation>;
}
