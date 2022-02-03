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
import { GraphView } from './src/GraphViewManager';


const App = () => {

  const sampleData = [{ x: 0, y: 31 }, { x: 1, y: 15 }, { x: 2, y: 60 }, { x: 3, y: 50 }, { x: 4, y: 51 },
  { x: 5, y: 100 }, { x: 6, y: 15 }, { x: 7, y: 44 }, { x: 8, y: 50 }, { x: 9, y: 51 }]
  const sampleLabels = ["14:00", "14:01", "14:02", "14:03", "14:04", "14:05",
    "14:06", "14:07", "14:08"]

  return (
    <View style={styles.container}>
      <GraphView assembleData={{
        labels: sampleLabels, data: sampleData,
        visibleX: 8
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
