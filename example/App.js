import React from "react";
import {
  ScrollView,
  View,
  Text,
  TouchableWithoutFeedback,
  StyleSheet
} from "react-native";
import { createStackNavigator, createAppContainer } from "react-navigation";

import F2 from "./src/f2";
import F2Kline from "./src/f2/k";
import Echarts from "./src/echarts";
import Simple from "./src/simple";
import PanAnimation from "./src/pan-animation";
import Animation from "./src/animation";
import EchartsKline from "./src/echarts/k";
// import TestChart from "./src/echarts/bar";

const routes = [
  {
    name: "f2 图表框架示例",
    route: "F2"
  },
  {
    name: "echarts 图表框架示例",
    route: "Echarts"
  },
  {
    name: "echarts 复杂 k 线示例",
    route: "EchartsKline"
  },
  {
    name: "简单的 Canvas 绘图示例",
    route: "Simple"
  },
  {
    name: "Canvas 动画示例",
    route: "Animation"
  },
  {
    name: "Canvas 手势识别示例",
    route: "PanAnimation"
  }
];

const createScreen = (name, Comp) => {
  return class Screen extends React.Component {
    static navigationOptions = {
      title: name
    };

    render() {
      return <Comp />;
    }
  };
};

const styles = StyleSheet.create({
  block: {
    backgroundColor: "#ffffff",
    borderBottomColor: "#eeeeee",
    borderBottomWidth: 1,
    height: 40,
    paddingHorizontal: 12,
    justifyContent: "center"
  }
});

class TestScreen extends React.Component {
  static navigationOptions = {
    title: "test"
  };

  render() {
    return <TestChart />;
  }
}

class HomeScreen extends React.Component {
  static navigationOptions = {
    title: "React Native Canvas"
  };

  onPress = route => {
    this.props.navigation.navigate(route);
  };

  render() {
    return (
      <ScrollView style={{ flex: 1, backgroundColor: "#f4f4f4" }}>
        <View style={{ marginBottom: 10 }}>
          <View style={{ backgroundColor: "#ffffff", padding: 12 }}>
            <Text style={{ fontSize: 14 }}>
              <Text style={{ fontWeight: "bold" }}>React Native Canvas</Text>:
              Canvas API in React Native. Using Native Components
            </Text>
          </View>
          <F2Kline height={250} style={{ marginTop: 10 }} />
        </View>

        {routes.map(route => (
          <TouchableWithoutFeedback
            key={route.route}
            onPress={() => this.onPress(route.route)}
          >
            <View style={[styles.block]}>
              <Text style={{ fontSize: 14 }}>{route.name}</Text>
            </View>
          </TouchableWithoutFeedback>
        ))}
      </ScrollView>
    );
  }
}

const Router = {
  Home: HomeScreen,
  // Home: TestScreen,
  F2: createScreen("f2", F2),
  Echarts: createScreen("echarts", Echarts),
  Simple: createScreen("simple", Simple),
  Animation: createScreen("animation", Animation),
  PanAnimation: createScreen("pan-animation", PanAnimation),
  EchartsKline: createScreen("echarts-k-line", EchartsKline)
};

const AppNavigator = createStackNavigator(Router, {
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
});

export default createAppContainer(AppNavigator);
