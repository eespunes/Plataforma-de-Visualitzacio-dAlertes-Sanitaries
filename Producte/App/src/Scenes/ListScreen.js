import React from "react";
import {
    Text,
    View,
    FlatList,
    SafeAreaView,
    TouchableOpacity,
    StyleSheet,
    ScrollView,
    RefreshControl
} from "react-native";
import axios from 'axios';
import {encode as btoa} from 'base-64'
import savedData from "../savedData";
import styles from "../Style";

function ListScreen({navigation}) {
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

    React.useEffect(() => {
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
            <SafeAreaView style={[styles.safeAreaTab]}>
                <Text style={styles.header}>LLISTA D'ALERTES</Text>
                <ScrollView
                    contentContainerStyle={styles.safeAreaTab}
                    scrollEnabled={false}
                    refreshControl={
                        <RefreshControl
                            refreshing={refreshing}
                            onRefresh={onRefresh}
                        />
                    }
                >
                    <View style={[styles.viewList]}>
                        <FlatList
                            data={data}
                            keyExtractor={(warning) => warning.id}
                            contentContainerStyle={[styles.flatList]}
                            renderItem={({item}) => {
                                return (
                                    <TouchableOpacity
                                        style={[styles.cardList]}
                                        onPress={() => changeToWarning(item)}>
                                        <View style={[styles.insideList]}>
                                            <Text style={[styles.warningSubheader]}>{item.name}</Text>
                                        </View>
                                        <View style={[styles.insideList]}>
                                            <View style={[(savedData.checkWarningColor(item) === 0) ? styles.semaphoreGreen : (savedData.checkWarningColor(item) === 1) ? styles.semaphoreYellow : styles.semaphoreRed]}>
                                                <Text style={[styles.noWarnings]}>{item.lastValue}</Text>
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
                <Text style={styles.noWarnings}>No s'ha trobat cap alerta!</Text>
            </SafeAreaView>
        );
    }
}

const caca = StyleSheet.create({


});

export default ListScreen;
