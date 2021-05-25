import React from "react";
import {
    Text,
    SafeAreaView,
    View
} from "react-native";
import savedData from "../savedData";
import styles from "../Style";

function WarningScreen() {
    let warning = savedData.warning;
    return (
        <SafeAreaView style={[styles.safeAreaFullscreen]}>
            <View style={[styles.fullscreenCard]}>
                <Text style={[styles.warningName]}>{warning.name}</Text>
                <View style={styles.separator}/>
                <Text style={[styles.warningDescription]}>{warning.description}</Text>
                <View style={styles.separator}/>
                <View
                    style={[(savedData.checkWarningColor(warning) === 0) ? styles.semaphoreGreen : (savedData.checkWarningColor(warning) === 1) ? styles.semaphoreYellow : styles.semaphoreRed]}>
                    <Text style={[styles.warningValue]}>{warning.lastValue}</Text>
                </View>
            </View>
        </SafeAreaView>
    );
};
export default WarningScreen;
