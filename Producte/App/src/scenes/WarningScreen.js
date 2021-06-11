import React from "react";
import {
    Text,
    SafeAreaView,
    View, TouchableOpacity
} from "react-native";
import savedData from "../savedData";
import styles from "../Style";

function WarningScreen({navigation}) {
    return (
        <SafeAreaView style={[styles.safeAreaFullscreen]}>
            <TouchableOpacity
                style={[styles.returnButton]}
                onPress={() => savedData.return(navigation,'List')}>
                <Text style={[styles.returnButtonText]}>TORNAR</Text>
            </TouchableOpacity>
            <View style={[styles.fullscreenCard]}>
                <Text style={[styles.warningName]}>{savedData.warning.name}</Text>
                <View style={styles.separator}/>
                <Text style={[styles.warningDescription]}>{savedData.warning.description}</Text>
                <View style={styles.separator}/>
                <View
                    style={[(savedData.checkWarningColor(savedData.warning) === 0) ? styles.semaphoreGreen : (savedData.checkWarningColor(savedData.warning) === 1) ? styles.semaphoreYellow : styles.semaphoreRed]}>
                    <Text style={[styles.warningValue]}>{savedData.warning.lastValue}</Text>
                </View>
            </View>
        </SafeAreaView>
    );
};
export default WarningScreen;
