import React, {useState, useEffect} from "react";
import {
    Text,
    View,
    FlatList,
    SafeAreaView,
    TouchableOpacity,
    StyleSheet,
    TextInput,
    Button,
    ScrollView, RefreshControl, Platform
} from "react-native";
import axios from 'axios';
import {encode as btoa} from 'base-64'
import savedData from "../savedData";

function ListScene({navigation}) {
    let [data, setData] = React.useState('')
    const [refreshing, setRefreshing] = React.useState(false);

    const warningListData = async () => {
        axios
            .get(savedData.URL + 'warning/all/' + savedData.user.role.name + '/' + savedData.user.role.healthcareInstitution.id + '/' + savedData.user.role.healthcareInstitution.country.id)
            .then(function (response) {
                const dataArray = Object.values(response.data);
                for (let i = 0; i < dataArray.length; i++) {
                    const warning = dataArray[i];
                    console.log(warning.role.healthcareInstitution.url + warning.uri)
                    // try {
                    //     axios
                    //         .get(warning.role.healthcareInstitution.url + warning.uri, {
                    //             headers: {
                    //                 'Accept': 'application/json',
                    //                 'User-Agent': 'axios/0.21.1',
                    //                 'Authorization': 'Basic ' + btoa(warning.role.healthcareInstitution.username + ':' + warning.role.healthcareInstitution.password)
                    //             }
                    //         })
                    //         .then(function (response) {
                    //             console.log(warning.name)
                    //             console.log(response.data)
                    //             warning.lastValue = response.data.value
                    //         })
                    // } catch (error) {
                    //     console.log(error.response.data)
                    // }
                }
                setData(response.data);
            })
            .catch(function (error) {
                alert('S\'ha produit un error al servidor.')
            })
    };

    useEffect(() => {
        const unsubscribe = navigation.addListener('focus', () => {
            onRefresh();
        });
        return unsubscribe;
    }, [])

    function changeToWarning(item) {
        savedData.warning = item;
        navigation.navigate("Warning")
    }

    const onRefresh = React.useCallback(() => {
        setRefreshing(true)
        warningListData().then(() => setRefreshing(false))
    }, []);


    if (data.length !== 0) {
        return (
            <SafeAreaView style={[styles.safeArea]}>
                <Text style={styles.header}>LLISTA D'ALERTES</Text>
                <ScrollView
                    contentContainerStyle={styles.safeArea}
                    scrollEnabled={false}
                    refreshControl={
                        <RefreshControl
                            refreshing={refreshing}
                            onRefresh={onRefresh}
                        />
                    }
                >
                    <View style={[styles.test]}>
                        <FlatList
                            // nestedScrollEnabled
                            data={data}
                            keyExtractor={(warning) => warning.id}
                            contentContainerStyle={[styles.flatList]}
                            renderItem={({item}) => {
                                return (
                                    <TouchableOpacity
                                        style={[styles.list]}
                                        onPress={() => changeToWarning(item)}>
                                        <View style={[styles.insideList]}>
                                            <Text style={[styles.subheader]}>{item.name}</Text>
                                        </View>
                                        <View style={[styles.insideList]}>
                                            <View style={[(savedData.checkWarningColor(item) === 0) ? styles.semaphoreGreen : (savedData.checkWarningColor(item) === 1) ? styles.semaphoreYellow : styles.semaphoreRed]}>
                                                <Text style={[styles.subheader]}>{item.lastValue}</Text>
                                            </View>
                                        </View>
                                    </TouchableOpacity>
                                );
                            }}
                        />
                    </View>
                </ScrollView>
            </SafeAreaView>
        );
    } else {
        return (
            <SafeAreaView>
                <Text style={styles.header}>LLISTA D'ALERTES</Text>
                <Text style={styles.nothing}>No s'ha trobat cap alerta!</Text>
            </SafeAreaView>
        );
    }
}

const styles = StyleSheet.create({
    safeArea: {
        flexGrow: 1,
        backgroundColor: '#ffffff',
        alignItems: 'center'
    },
    test: {
        maxHeight: "92.5%",
        width: '100%'
    },
    header: {
        fontSize: 45,
        textAlign: 'center',
        fontWeight: 'bold',
        color: '#00F8FF'
    },
    flatList: {
        flexGrow: 0,
        backgroundColor: '#ffffff',

        alignItems: 'center'
    },
    list: {
        marginTop: '2.5%',
        marginBottom: '2.5%',
        width: '97.5%',
        height: 175,
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
        margin: '2.5%',
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    nothing: {
        fontSize: 20,
        margin: '2.5%',
        color: 'black',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    semaphoreGreen: {
        borderColor: 'white',
        borderWidth: 5,
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
        margin: '2.5%'
    },
    insideList: {
        width: '50%',
    }

});

export default ListScene;
