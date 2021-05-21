import React, {useEffect} from "react";

import {
    Button,
    SafeAreaView,
    View,
    StyleSheet,
    Text, TouchableOpacity,
} from 'react-native';
import savedData from "../savedData";

function ProfileScene() {
    return (
        <SafeAreaView style={[styles.safeArea]}>
            <View style={[styles.card]}>
                <Text style={[styles.subheader]}>{savedData.user.username}</Text>
                <View style={styles.separator}/>
                <Text
                    style={[styles.subheader]}>{savedData.user.surname}, {savedData.user.name}</Text>
                <View style={styles.separator}/>
                <Text style={[styles.subheader]}>{savedData.user.role.name}</Text>
                <View style={styles.separator}/>
                <TouchableOpacity
                    style={[styles.button]}>
                    <Text style={[styles.buttonText]}>Canviar Contrasenya</Text>
                </TouchableOpacity>
                <View style={styles.separator}/>
                <TouchableOpacity
                    style={[styles.button]}>
                    <Text style={[styles.buttonText]}>Tancar Sessió</Text>
                </TouchableOpacity>
            </View>
        </SafeAreaView>
    );
}

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
        alignItems: 'center',
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
    buttonText: {
        fontSize: 30,
        color: '#00F8FF',
        marginHorizontal:'2.5%',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    input: {
        color: 'white',
        textAlign: 'center'
    },
    button: {
        backgroundColor: '#ffffff',

        borderRadius: 5
    },
    separator: {
        marginVertical: 8,
        borderColor: 'white',
        borderWidth: 2.5,
        width:'100%'
    }
});

export default ProfileScene;
