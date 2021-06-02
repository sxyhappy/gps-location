import { registerPlugin } from '@capacitor/core';

import type { GeoLocationPlugin } from './definitions';

const GeoLocation = registerPlugin<GeoLocationPlugin>('GeoLocation', {
  web: () => import('./web').then(m => new m.GeoLocationWeb()),
});

export * from './definitions';
export { GeoLocation };
