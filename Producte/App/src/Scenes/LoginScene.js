import React from "react";
import {Text, SafeAreaView, Button,TextInput, StyleSheet} from "react-native";
import apiController from "../api/apiController";

const LoginScene = (props) => {

    return (
        <SafeAreaView>
            <Text style={styles.header}>INICIA SESSIÓ</Text>
            <Text style={styles.subheader}>Nom d'usuari</Text>
            <TextInput
                // style={styles.input}
                // onChangeText={onChangeNumber}
                // value={number}
                placeholder="Introdueix el nom d'usuari..."
            />
            <Text style={styles.subheader}>Contrasenya</Text>
            <TextInput
                // style={styles.input}
                // onChangeText={onChangeNumber}
                // value={number}
                placeholder="Introdueix la contrasenya..."
                secureTextEntry={true}
            />
            <Button
                onPress={()=>props.navigation.navigate("List")}
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
