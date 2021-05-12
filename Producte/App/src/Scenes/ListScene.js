// import React from "react";
import React, {useState, useEffect} from "react";
import {Text, View, FlatList, SafeAreaView, TouchableOpacity, StyleSheet, TextInput, Button} from "react-native";
import axios from 'axios';
// import iid from '@react-native-firebase/iid';

// const state = {
//     data: []
// }
// const Item = ({item, onPress}) => (
//     <TouchableOpacity onPress={onPress} style={[styles.item]}>
//         <Text style={[styles.title]}>{item.name}</Text>
//         <Text style={[styles.title]}>{item.lastValue}</Text>
//     </TouchableOpacity>
// );

const ListScene = (props) => {
    let [data, setData] = React.useState('')

    const warningListData = async () => {
        axios
            .get('https://tfg-informatica.herokuapp.com/api/warning/all')
            .then(function (response) {
                setData(response.data);
            })
            .catch(function (error) {
                alert(error.message);
            })
    };

    useEffect(() => {
        warningListData();
    }, [])

    if (data.length !== 0) {
        return (
            <SafeAreaView>
                <Text style={styles.header}>LLISTA D'ALERTES</Text>
                <FlatList
                    data={data}
                    keyExtractor={(warning) => warning.id}
                    renderItem={({item}) => {
                        return (
                            <TouchableOpacity
                                style={[styles.item]}
                                onPress={() => props.navigation.navigate("Warning", {
                                    item: item,
                                })}
                            >
                                <Text style={[styles.title]}>{item.name}</Text>
                                <Text style={[styles.title]}>{item.lastValue}</Text>
                            </TouchableOpacity>
                        );
                    }}
                />
            </SafeAreaView>
        );
    } else {
        return (
            <SafeAreaView>
                <Text style={styles.header}>LLISTA D'ALERTES</Text>
                <Text style={styles.title}>No s'ha trobat cap alerta!</Text>
            </SafeAreaView>
        );
    }
}
// ;

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
        color: 'black'
    },
    header: {
        fontSize: 45
    }
});

export default ListScene;
