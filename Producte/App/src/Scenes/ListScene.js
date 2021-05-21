import React, {useState, useEffect} from "react";
import {Text, View, FlatList, SafeAreaView, TouchableOpacity, StyleSheet, TextInput, Button} from "react-native";
import axios from 'axios';
import savedData from "../savedData";

function ListScene({navigation}) {
    let [data, setData] = React.useState('')

    const warningListData = async () => {
        axios
            .get(savedData.URL + 'warning/all/' + savedData.user.role.name + '/' + savedData.user.role.healthcareInstitution.id + '/' + savedData.user.role.healthcareInstitution.country.id)
            // .get('https://tfg-informatica.herokuapp.com/api/warning/all/WEB-ADMIN/0/ESP')
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

    function changeToWarning(item) {
        savedData.warning = item;
        navigation.navigate("Warning")
    }

    if (data.length !== 0) {
        return (
            <SafeAreaView style={[styles.safeArea]}>
                <Text style={styles.header}>LLISTA D'ALERTES</Text>
                <FlatList
                    data={data}
                    keyExtractor={(warning) => warning.id}
                    renderItem={({item}) => {
                        return (
                            <TouchableOpacity
                                style={[styles.list]}
                                onPress={() => changeToWarning(item)}>
                                <View style={[styles.insideList]}>
                                    <Text style={[styles.subheader]}>{item.name}</Text>
                                </View>
                                <View style={[styles.insideList]}>
                                    <View style={styles.semaphore}>
                                        <Text style={[styles.subheader]}>{item.lastValue}</Text>
                                    </View>
                                </View>
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
    list: {
        marginTop:'2.5%',
        width: '95%',
        // height: '60%',
        flexDirection: "row",
        textAlign: 'center',
        alignItems: 'center',
        backgroundColor: '#00F8FF',
        borderRadius: 10,
        shadowColor: '#000',
        shadowOffset: {width: 0, height: 1},
        shadowOpacity: 0.8,
        shadowRadius: 2,
        elevation: 5,
    },
    subheader: {
        fontSize: 20,
        margin:'2.5%',
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    semaphore: {
        borderColor: 'white',
        borderWidth: 5,
        backgroundColor: '#00ff00',
        justifyContent: 'center',
        alignItems: 'center',
        borderRadius: 5,
        flexGrow: 1,
        margin:'2.5%'
    }, insideList: {
        width:'50%',
    }

});

export default ListScene;
