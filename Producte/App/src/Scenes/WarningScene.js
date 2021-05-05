import React from "react";
import {Text, View, StyleSheet} from "react-native";

const WarningScene = (props) => {
    let item = props.navigation.state.params.item;
    return (
        <View>
            <Text style={styles.header}>Warning Screen</Text>
            <Text style={[styles.text]}>{item.title}</Text>
            <Text style={[styles.text]}>{item.description}</Text>
            <Text style={[styles.text]}>{item.value}</Text>
        </View>
    );
};

const styles = StyleSheet.create({
    header: {
        fontSize: 45
    },
    text: {
        fontSize: 30
    }
});

export default WarningScene;
