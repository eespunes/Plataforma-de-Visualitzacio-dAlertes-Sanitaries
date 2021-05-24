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
    ScrollView, RefreshControl
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
                <ScrollView
                    contentContainerStyle={styles.safeArea}
                    refreshControl={
                        <RefreshControl
                            refreshing={refreshing}
                            onRefresh={onRefresh}
                        />
                    }
                >
                    <Text style={styles.header}>LLISTA D'ALERTES</Text>
                    <FlatList
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
                                        <View style={styles.semaphore}>
                                            <Text style={[styles.subheader]}>{item.lastValue}</Text>
                                        </View>
                                    </View>
                                </TouchableOpacity>
                            );
                        }}
                    />
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
        flex: 1,
        backgroundColor: '#ffffff',
        alignItems: 'center'
    },
    header: {
        fontSize: 45,
        textAlign: 'center',
        fontWeight: 'bold',
        color: '#00F8FF'
    }, flatList: {
        flex: 1,
        flexGrow: 1,
        backgroundColor: '#ffffff',

        alignItems: 'center'
    },
    list: {
        marginTop: '2.5%',
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
    semaphore: {
        borderColor: 'white',
        borderWidth: 5,
        backgroundColor: '#00ff00',
        justifyContent: 'center',
        alignItems: 'center',
        borderRadius: 5,
        flexGrow: 1,
        margin: '2.5%'
    }, insideList: {
        width: '50%',
    }

});

export default ListScene;
