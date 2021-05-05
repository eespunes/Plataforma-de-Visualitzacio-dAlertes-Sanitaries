import {createAppContainer} from "react-navigation";
import {createStackNavigator} from "react-navigation-stack";
import LoginScene from "./src/Scenes/LoginScene";
import ListScene from "./src/Scenes/ListScene";
import WarningScene from "./src/Scenes/WarningScene";

const navigator = createStackNavigator(
    {
        Home: LoginScene,
        List: ListScene,
        Warning: WarningScene
    },
    {
        initialRouteName: "Home",
        defaultNavigationOptions: {
            title: "Plataforma de Visualització d'Alertes Sanitàries",
        },
    }
);

export default createAppContainer(navigator);
