import React from "react";
import {Text, SafeAreaView, View, Button, TextInput, StyleSheet, TouchableOpacity} from "react-native";
import axios from "axios";
import savedData from "../savedData";

function LoginScene({navigation}) {
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');

    const login = async () => {
        console.log('Holaaa')
        axios
            .get(savedData.URL + 'login/' + username + '/' + password + '/000')
            .then(function (response) {
                // iid().get().then(function (response) {
                //     alert('Current Instance ID: ' + response);
                // })
                if (response.data !== '') {
                    savedData.user = response.data
                    navigation.navigate("List")
                } else {
                    alert('El nom d\'usuari o la contrasenya no són correctes.')
                }
            })
            .catch(function (error) {
                alert(error.message)
                // alert('S\'ha produit un error al servidor.')
            })
    };

    return (
        <SafeAreaView style={styles.safeArea}>
            <Text style={styles.header}>INICIA SESSIÓ</Text>
            <View style={styles.card}>
                <Text style={styles.subheader}>Nom d'usuari</Text>
                <TextInput
                    style={styles.input}
                    onChangeText={value => setUsername(value)}
                    value={username}
                    placeholder="Introdueix el nom d'usuari..."
                />
                <Text style={styles.subheader}>Contrasenya</Text>
                <TextInput
                    style={styles.input}
                    onChangeText={value => setPassword(value)}
                    value={password}
                    placeholder="Introdueix la contrasenya..."
                    secureTextEntry={true}
                />
            </View>
            <TouchableOpacity
                style={[styles.button]}
                onPress={() => login()}>
                <Text style={[styles.subheader]}>Inicia Sessió</Text>
            </TouchableOpacity>
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    safeArea: {
        flex: 1,
        backgroundColor: '#ffffff',
        alignItems: 'center'
    },
    header: {
        fontSize: 45,
        textAlign: 'center',
        fontWeight: 'bold',
        color: '#00F8FF'
    },
    card: {
        marginTop:'2.5%',
        width: '95%',
        textAlign: 'center',
        backgroundColor: '#00F8FF',
        borderRadius: 10,
        shadowColor: '#000',
        shadowOffset: {width: 0, height: 1},
        shadowOpacity: 0.8,
        shadowRadius: 2,
        elevation: 5
    },
    subheader: {
        fontSize: 30,
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    input: {
        color: 'white',
        fontSize: 20,
        textAlign: 'center'
    },
    button: {
        width:'95%',
        marginTop:'2.5%',
        backgroundColor: '#00F8FF',
        borderRadius: 5
    },
});

export default LoginScene;
