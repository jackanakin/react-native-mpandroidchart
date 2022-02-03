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
  Button,
  StyleSheet,
  View,
} from 'react-native';
import { GraphViewManager } from './src/GraphViewManager';


const App = () => {

  return (
    <View style={styles.container}>
      <GraphViewManager assembleData={{
        label: "Label a",
        data: [{ key: 1643835776, value: 123 }, { key: 1643835777, value: 124 }, { key: 1643835778, value: 125 }]
      }} style={styles.view} />
      <Button title='BUTTON' />
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
