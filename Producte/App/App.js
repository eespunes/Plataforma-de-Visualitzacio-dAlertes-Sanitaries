import React from "react";

import {NavigationContainer} from "@react-navigation/native";
import {createStackNavigator} from "@react-navigation/stack";
import {createMaterialTopTabNavigator} from "@react-navigation/material-top-tabs";

import LoginScreen from "./src/Scenes/LoginScreen";
import WarningScreen from "./src/Scenes/WarningScreen";
import ListScreen from "./src/Scenes/ListScreen";
import ProfileScreen from "./src/Scenes/ProfileScreen";
import AboutScreen from "./src/Scenes/AboutScreen";
import {Platform} from "react-native";

const Stack = createStackNavigator();
const Tab = createMaterialTopTabNavigator();


const App = () => {
    return (
        <NavigationContainer>
            <Stack.Navigator
                initialRouteName='Login'
                screenOptions={{
                    headerShown: false
                }}
            >
                <Stack.Screen name='Login' component={LoginScreen}/>
                <Stack.Screen name='Warning' component={WarningScreen}/>
                <Stack.Screen name='About' component={AboutScreen}/>
                <Stack.Screen name='List'>
                    {() => (
                        <Tab.Navigator
                            initialRouteName='List'
                            tabBarOptions={{
                                style: {
                                    paddingTop: '7.5%',
                                    backgroundColor: '#00F8FF'
                                },
                                inactiveTintColor: 'white',
                                activeTintColor: '#00F8FF',
                                labelStyle: { fontWeight: 'bold' },
                                indicatorStyle: {
                                    height: '60%',
                                    backgroundColor: 'white',
                                }

                            }}>
                            <Tab.Screen name='List' component={ListScreen}/>
                            <Tab.Screen name='Profile' component={ProfileScreen}/>
                        </Tab.Navigator>
                    )}
                </Stack.Screen>
            </Stack.Navigator>
        </NavigationContainer>
    );
}
export default App;
