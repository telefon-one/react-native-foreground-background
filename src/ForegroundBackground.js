import React, {NativeModules} from 'react-native';

export default class ReplaceDialer {
  constructor() {
    //super();
  }

  toForeground() {
    return NativeModules.ForegroundBackground.toForeground();
  }

  toBackground() {
    return NativeModules.ReplaceDialerModule.toBackground();
  }
}
