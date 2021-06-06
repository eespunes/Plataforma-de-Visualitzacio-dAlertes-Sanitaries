import Constants from 'expo-constants';
import React, {useState, useEffect, useRef} from 'react';
import {Platform, BackHandler} from 'react-native';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from "@react-navigation/stack";
import {createMaterialTopTabNavigator} from "@react-navigation/material-top-tabs";

import LoginScreen from "./src/Scenes/LoginScreen";
import WarningScreen from "./src/Scenes/WarningScreen";
import ListScreen from "./src/Scenes/ListScreen";
import ProfileScreen from "./src/Scenes/ProfileScreen";
import AboutScreen from "./src/Scenes/AboutScreen";
import * as Notifications from 'expo-notifications';
import savedData from "./src/savedData";

const Stack = createStackNavigator();
const Tab = createMaterialTopTabNavigator();

Notifications.setNotificationHandler({
    handleNotification: async () => ({
        shouldShowAlert: true,
        shouldPlaySound: true,
        shouldSetBadge: false,
    }),
});

export default function App() {
    const [notification, setNotification] = useState(false);
    const notificationListener = useRef();
    const responseListener = useRef();

    useEffect(() => {
        registerForPushNotificationsAsync().then(token => savedData.token = token.replace("[", "_").replace("]", ""));

        notificationListener.current = Notifications.addNotificationReceivedListener(notification => {
            setNotification(notification);
        });

        return () => {
            Notifications.removeNotificationSubscription(notificationListener.current);
        };
    }, []);

    useEffect(() => {
        const backAction = () => {
            return true;
        };

        BackHandler.addEventListener(
            "hardwareBackPress",
            backAction
        );
    });

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
                                labelStyle: {fontWeight: 'bold', fontSize: 25},
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

async function registerForPushNotificationsAsync() {
    let token;
    if (Constants.isDevice) {
        const {status: existingStatus} = await Notifications.getPermissionsAsync();
        let finalStatus = existingStatus;
        if (existingStatus !== 'granted') {
            const {status} = await Notifications.requestPermissionsAsync();
            finalStatus = status;
        }
        if (finalStatus !== 'granted') {
            alert('Failed to get push token for push notification!');
            return;
        }
        token = (await Notifications.getExpoPushTokenAsync()).data;
    } else {
        alert('Must use physical device for Push Notifications');
    }

    if (Platform.OS === 'android') {
        await Notifications.setNotificationChannelAsync('default', {
            name: 'default',
            importance: Notifications.AndroidImportance.MAX,
            vibrationPattern: [0, 250, 250, 250],
            lightColor: '#FF231F7C',
        });
    }

    return token;
}
