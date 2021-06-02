import { WebPlugin } from '@capacitor/core';

import type { GeoLocationPlugin } from './definitions';

export class GeoLocationWeb extends WebPlugin implements GeoLocationPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
