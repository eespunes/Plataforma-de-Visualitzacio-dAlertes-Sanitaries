import React from "react";
import {
    Text,
    SafeAreaView,
    View,
    Image
} from "react-native";
import styles from "../Style";

function AboutScreen() {
    return (
        <SafeAreaView style={[styles.safeAreaFullscreen]}>
            <View style={[styles.fullscreenCard]}>
                <Text style={[styles.aboutSubheader]}>TREBALL DE FINAL DE GRAU</Text>
                <View style={styles.separator}/>
                <Text style={[styles.aboutText]}>CURS 2020/2021</Text>
                <View style={styles.separator}/>
                <Text style={[styles.aboutText]}>AUTOR: ERIK ESPUÑES JUBERÓ</Text>
                <View style={styles.separator}/>
                <Text style={[styles.aboutText]}>TUTOR: EUGENI FERNANDEZ GONZALEZ</Text>
                <View style={styles.separator}/>
                <Text style={[styles.aboutText]}>GRAU EN ENGINYERIA INFORMÀTICA DE GESTIÓ I SISTEMES D’INFORMACIÓ</Text>
                <View style={styles.separator}/>
                <Image style={styles.image} source={require('../../assets/images/tcm.png')} />
            </View>
        </SafeAreaView>
    );
};
export default AboutScreen;
