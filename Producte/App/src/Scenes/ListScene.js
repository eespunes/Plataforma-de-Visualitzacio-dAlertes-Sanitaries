// import React from "react";
import React, { useState } from "react";
import {Text,View,FlatList, SafeAreaView, TouchableOpacity, StyleSheet} from "react-native";

const DATA = [
    {
        id: 'bd7acbea-c1b1-46c2-aed5-3ad53abb28ba',
        title: 'Warning 1 - Name',
        description: 'Hola Pepsicola',
        value: 'Warning 1 - Value'
    },
    {
        id: '3ac68afc-c605-48d3-a4f8-fbd91aa97f63',
        title: 'Warning 2 - Name',
        description: 'Hola Pepsicola',
        value: 'Warning 2 - Value'
    },
    {
        id: '58694a0f-3da1-471f-bd96-145571e29d72',
        title: 'Warning 3 - Name',
        description: 'Hola Pepsicola',
        value: 'Warning 3 - Value'
    },
    {
        id: 'bd7acbea-c1x1-46c2-aed5-3ad53abb28ba',
        title: 'Warning 4 - Name',
        description: 'Hola Pepsicola',
        value: 'Warning 4 - Value'
    },
    {
        id: '3ac68afc-c6v5-48d3-a4f8-fbd91aa97f63',
        title: 'Warning 5 - Name',
        description: 'Hola Pepsicola',
        value: 'Warning 5 - Value'
    },
    {
        id: '58694a0f-3ca1-471f-bd96-145571e29d72',
        title: 'Warning 6 - Name',
        description: 'Hola Pepsicola',
        value: 'Warning 6 - Value'
    },
];

const Item = ({ item, onPress}) => (
    <TouchableOpacity onPress={onPress} style={[styles.item]}>
        <Text style={[styles.title]}>{item.title}</Text>
        <Text style={[styles.title]}>{item.value}</Text>
    </TouchableOpacity>
);

const ListScene = (props) => {
    const renderItem = ({ item }) => {

        return (
            <Item
                item={item}
                props={props}

                onPress={() => props.navigation.navigate("Warning", {
                    item: item, })}
            />
        );
    };

    return (
        <SafeAreaView style={styles.container}>
            <Text style={styles.text}>LLISTA D'ALERTES</Text>
            <FlatList
                data={DATA}
                renderItem={renderItem}
                keyExtractor={(item) => item.id}
            />
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    item: {
        backgroundColor: '#ffffff',
        padding: 20,
        marginVertical: 8,
        marginHorizontal: 16,
    },
    title: {
        fontSize: 32,
        color:'black'
    },
    text: {
        fontSize: 45
    }
});

export default ListScene;
