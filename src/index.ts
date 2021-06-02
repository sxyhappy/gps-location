import { registerPlugin } from '@capacitor/core';

import type { GeoLocationPlugin } from './definitions';

const GeoLocation = registerPlugin<GeoLocationPlugin>('GeoLocation', {});

export * from './definitions';
export { GeoLocation };
