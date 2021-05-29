import React from "react";
import {
    Text,
    SafeAreaView,
    View,
    TextInput,
    TouchableOpacity
} from "react-native";
import axios from "axios";
import savedData from "../savedData";
import Spinner from 'react-native-loading-spinner-overlay';
import styles from "../Style";

function LoginScreen({navigation}) {
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [loading, setLoading] = React.useState(false);


    const login = async () => {
        setLoading(true);
        console.log(savedData.URL + 'login/' + username + '/' + password + '/'+savedData.token)
        axios
            .get(savedData.URL + 'login/' + username + '/' + password + '/00')
            .then(function (response) {
                // iid().get().then(function (response) {
                //     alert('Current Instance ID: ' + response);
                // })
                setLoading(false);
                if (response.data !== '') {
                    savedData.user = response.data
                    navigation.navigate("List")
                } else {
                    alert('El nom d\'usuari o la contrasenya no són correctes.')
                }
            })
            .catch(function (error) {
                setLoading(false);
                console.log(error)
                alert('S\'ha produit un error al servidor')
            })
    };

    return (
        <SafeAreaView style={styles.safeArea}>
            <Spinner
                visible={loading}
                overlayColor={'rgba(0, 248, 255, 15)'}
                textContent={'Iniciant sessió...'}
                textStyle={styles.subheader}
                animation={'slide'}
            />
            <Text style={styles.header}>INICIA SESSIÓ</Text>
            <View style={styles.card}>
                <Text style={styles.subheader}>Nom d'usuari</Text>
                <TextInput
                    style={styles.input}
                    onChangeText={value => setUsername(value)}
                    value={username}
                    placeholder="Introdueix el nom d'usuari..."
                    maxLength={64}
                />
                <Text style={styles.subheader}>Contrasenya</Text>
                <TextInput
                    style={styles.inputEnd}
                    onChangeText={value => setPassword(value)}
                    value={password}
                    placeholder="Introdueix la contrasenya..."
                    secureTextEntry={true}
                    maxLength={32}
                />
            </View>
            <TouchableOpacity
                style={[styles.loginButton]}
                onPress={() => login()}>
                <Text style={[styles.subheader]}>Inicia Sessió</Text>
            </TouchableOpacity>
        </SafeAreaView>
    );
};

export default LoginScreen;
