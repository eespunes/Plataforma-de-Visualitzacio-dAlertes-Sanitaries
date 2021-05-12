import React from "react";
import {Text, SafeAreaView, Button, TextInput, StyleSheet} from "react-native";
import apiController from "../api/apiController";
import iid from '@react-native-firebase/iid';
import axios from "axios";

const LoginScene = (props) => {
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');

    const login = async () => {
        axios
            .get('https://tfg-informatica.herokuapp.com/api/login/' + username + '/' + password + '/1234')
            .then(function (response) {
                // iid().get().then(function (response) {
                //     alert('Current Instance ID: ' + response);
                // })
                if (JSON.stringify(response.data) === 'true') {
                    props.navigation.navigate("List", {
                        username: username,
                    })
                } else {
                    alert('El nom d\'usuari o la contrasenya no són correctes.')
                }
            })
            .catch(function (error) {
                alert(error.message);
            })
    };

    return (
        <SafeAreaView>
            <Text style={styles.header}>INICIA SESSIÓ</Text>
            <Text style={styles.subheader}>Nom d'usuari</Text>
            <TextInput
                // style={styles.input}
                onChangeText={value => setUsername(value)}
                value={username}
                placeholder="Introdueix el nom d'usuari..."
            />
            <Text style={styles.subheader}>Contrasenya</Text>
            <TextInput
                // style={styles.input}
                onChangeText={value => setPassword(value)}
                value={password}
                placeholder="Introdueix la contrasenya..."
                secureTextEntry={true}
            />
            <Button
                onPress={() => login()}
                title="Login"></Button>
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    header: {
        fontSize: 45
    },
    subheader: {
        fontSize: 20
    }
});

export default LoginScene;
