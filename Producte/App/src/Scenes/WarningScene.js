import React from "react";
import {Text, SafeAreaView, View, StyleSheet} from "react-native";
import savedData from "../savedData";

function WarningScene() {
    let warning = savedData.warning;
    return (
        <SafeAreaView style={[styles.safeArea]}>
            <View style={[styles.card]}>
                <Text style={[styles.subheader]}>{warning.name}</Text>
                <View style={styles.separator}/>
                <Text style={[styles.text]}>{warning.description}</Text>
                <View style={styles.separator}/>
                <View style={styles.semaphore}>
                    <Text style={[styles.subheader]}>{warning.lastValue}</Text>
                </View>
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
        textAlign: 'justify'
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
    }

});

export default WarningScene;
