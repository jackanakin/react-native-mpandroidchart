/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * Generated with the TypeScript template
 * https://github.com/react-native-community/react-native-template-typescript
 *
 * @format
 */

import React from 'react';
import {
  requireNativeComponent,
  StyleSheet,
  View,
} from 'react-native';

const GraphViewManager = requireNativeComponent(`GraphViewManager`);

const App = () => {

  return (
    <View style={styles.container}>
      <GraphViewManager style={styles.view} />
    </View>
  );
};

var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  view: {
    height: 50 + "%",
    width: 100 + "%",
    alignSelf: 'center',
  },
});

export default App;
