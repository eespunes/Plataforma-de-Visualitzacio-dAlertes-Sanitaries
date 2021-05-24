import React from "react";

import {NavigationContainer} from "@react-navigation/native";
import {createStackNavigator} from "@react-navigation/stack";
import {createMaterialTopTabNavigator} from "@react-navigation/material-top-tabs";

import LoginScene from "./src/Scenes/LoginScene";
import WarningScene from "./src/Scenes/WarningScene";
import ListScene from "./src/Scenes/ListScene";
import ProfileScene from "./src/Scenes/ProfileScene";
import AboutScene from "./src/Scenes/AboutScene";
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
                <Stack.Screen name='Login' component={LoginScene}/>
                <Stack.Screen name='Warning' component={WarningScene}/>
                <Stack.Screen name='About' component={AboutScene}/>
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
