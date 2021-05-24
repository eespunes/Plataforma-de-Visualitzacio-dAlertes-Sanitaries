import React from "react";
import {Text, SafeAreaView, View, StyleSheet, Image, Platform} from "react-native";
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
                <View
                    style={[(savedData.checkWarningColor(warning) === 0) ? styles.semaphoreGreen : (savedData.checkWarningColor(warning) === 1) ? styles.semaphoreYellow : styles.semaphoreRed]}>
                    <Text style={[styles.subheader]}>{warning.lastValue}</Text>
                </View>
            </View>
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    safeArea: {
        paddingTop: '7.5%',
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
        width: '100%'
    }, semaphoreGreen: {
        borderColor: 'white',
        borderWidth: 5,
        width: '95%',
        backgroundColor: '#00ff00',
        justifyContent: 'center',
        alignItems: 'center',
        borderRadius: 5,
        flexGrow: 1,
        margin: '2.5%'
    },
    semaphoreYellow: {
        borderColor: 'white',
        borderWidth: 5,
        width: '95%',
        backgroundColor: '#ffff00',
        justifyContent: 'center',
        alignItems: 'center',
        borderRadius: 5,
        flexGrow: 1,
        margin: '2.5%'
    },
    semaphoreRed: {
        borderColor: 'white',
        borderWidth: 5,
        backgroundColor: '#ff0000',
        justifyContent: 'center',
        alignItems: 'center',
        borderRadius: 5,
        flexGrow: 1,
        width: '95%',
        margin: '2.5%'
    },

});

export default WarningScene;
