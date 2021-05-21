import React from "react";

import {NavigationContainer} from "@react-navigation/native";
import {createStackNavigator} from "@react-navigation/stack";
import {createMaterialTopTabNavigator} from "@react-navigation/material-top-tabs";

import LoginScene from "./src/Scenes/LoginScene";
import WarningScene from "./src/Scenes/WarningScene";
import ListScene from "./src/Scenes/ListScene";
import ProfileScene from "./src/Scenes/ProfileScene";

const Stack = createStackNavigator();
const Tab = createMaterialTopTabNavigator();


const App = () => {
    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName='Login'>
                <Stack.Screen name='Login' component={LoginScene}/>
                <Stack.Screen name='Warning' component={WarningScene}/>
                <Stack.Screen name='List' options={{title: ''}}>
                    {() => (
                        <Tab.Navigator initialRouteName='List'>
                            <Tab.Screen name='List' component={ListScene}/>
                            <Tab.Screen name='Profile' component={ProfileScene}/>
                        </Tab.Navigator>
                    )}
                </Stack.Screen>
            </Stack.Navigator>
        </NavigationContainer>
    );
}
export default App;
