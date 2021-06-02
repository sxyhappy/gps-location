export interface GeoLocationPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
