import React from "react";
import {Text, SafeAreaView, View, StyleSheet, Image} from "react-native";

function AboutScene() {
    return (
        <SafeAreaView style={[styles.safeArea]}>
            <View style={[styles.card]}>
                <Text style={[styles.subheader]}>TREBALL DE FINAL DE GRAU</Text>
                <View style={styles.separator}/>
                <Text style={[styles.text]}>CURS 2020/2021</Text>
                <View style={styles.separator}/>
                <Text style={[styles.text]}>AUTOR: ERIK ESPUÑES JUBERÓ</Text>
                <View style={styles.separator}/>
                <Text style={[styles.text]}>TUTOR: EUGENI FERNANDEZ GONZALEZ</Text>
                <View style={styles.separator}/>
                <Text style={[styles.text]}>GRAU EN ENGINYERIA INFORMÀTICA DE GESTIÓ I SISTEMES D’INFORMACIÓ</Text>
                <View style={styles.separator}/>
                <Image style={styles.image} resizeMode="contain" source={require('./tcm.png')} />
            </View>
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    safeArea: {
        flex: 1,
        backgroundColor: '#ffffff',
        justifyContent: 'center',

        alignItems: 'center'
    },
    header: {
        fontSize: 45,
        textAlign: 'center',
        fontWeight: 'bold',
        color: '#00F8FF'
    },
    card: {
        width: '95%',
        height: '95%',
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
        fontSize: 40,
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    text: {
        width: '95%',
        fontSize: 20,
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    input: {
        color: 'white',
        textAlign: 'center'
    },
    button: {
        backgroundColor: '#00aeef',
        borderColor: 'red',
        borderWidth: 5,
        borderRadius: 15
    },
    separator: {
        marginVertical: 8,
        borderColor: 'white',
        borderWidth: 2.5,
        width:'100%'
    }, semaphore: {
        borderColor: 'white',
        borderWidth: 5,
        backgroundColor: '#00ff00',
        width: '95%',
        justifyContent: 'center',
        alignItems: 'center',
        borderRadius: 5,
        flexGrow: 1,
        marginBottom:'2.5%'
    },image:{
        width:'95%'
    }

});

export default AboutScene;
