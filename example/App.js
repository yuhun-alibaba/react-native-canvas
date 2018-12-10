import React from "react";
import { View, Text } from "react-native";
import { createStackNavigator, createAppContainer } from "react-navigation";

import F2 from "./src/f2";
// import Echarts from "./src/echarts";
// import Simple from "./src/simple";
// import PanAnimation from "./src/pan-animation";
// import Animation from "./src/animation";

class HomeScreen extends React.Component {
  static navigationOptions = {
    title: "Home"
  };

  render() {
    return <F2 />;
  }
}

class DetailsScreen extends React.Component {
  render() {
    return (
      <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
        <Text>Details Screen</Text>
      </View>
    );
  }
}

const AppNavigator = createStackNavigator(
  {
    Home: HomeScreen,
    Details: DetailsScreen
  },
  {
    initialRouteName: "Home",
    defaultNavigationOptions: {
      headerStyle: {
        backgroundColor: "#fff"
      },
      headerTintColor: "#000",
      headerTitleStyle: {
        fontWeight: "bold"
      }
    }
  }
);

export default createAppContainer(AppNavigator);
