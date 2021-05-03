import { createAppContainer } from "react-navigation";
import { createStackNavigator } from "react-navigation-stack";
import LoginScene from "./src/Scenes/LoginScene";
import ListScene from "./src/Scenes/ListScene";

const navigator = createStackNavigator(
  {
    Home: LoginScene,
    List: ListScene
  },
  {
    initialRouteName: "List",
    defaultNavigationOptions: {
      title: "Plataforma de Visualització d'Alertes Sanitàries",
    },
  }
);

export default createAppContainer(navigator);
