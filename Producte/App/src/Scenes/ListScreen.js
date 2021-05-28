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
    const [warningsUpdated, setWarningsUpdated] = React.useState(0);

    const warningListData = async () => {
        axios
            .get(savedData.URL + 'warning/all/' + savedData.user.role.name + '/' + savedData.user.role.healthcareInstitution.id + '/' + savedData.user.role.healthcareInstitution.country.id)
            .then(async function (response) {
                setData(response.data);
                // setRefreshing(true)
                // setWarningsUpdated(0)
                const dataArray = Object.values(response.data);
                for (let i = 0; i < dataArray.length; i++) {
                    const warning = dataArray[i];
                    console.log(warning.role.healthcareInstitution.url + warning.uri)
                    try {
                        let response = await fetch(warning.role.healthcareInstitution.url + warning.uri, {
                            method: 'GET',
                            headers: {
                                'Accept': '*/*',
                                'User-Agent': 'tfg/0.1.0',
                                'Content-Type': 'application/json',
                                'Connection': 'keep-alive',
                                'Authorization': 'Basic ' + btoa(warning.role.healthcareInstitution.username + ':' + warning.role.healthcareInstitution.password)
                            }
                        });
                        let json = await response.json();
                        warning.lastValue = json.value
                        console.log(json.value + '-' + warning.lastValue)
                        // dataArray[i] = warning;
                        // setWarningsUpdated(warningsUpdated + 1)
                        // if(warningsUpdated===dataArray.length)
                        // setRefreshing(false)
                    } catch (error) {
                        console.log(error.response.data)
                    }
                }
                setData(dataArray);
                console.log("finished")
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
                        {data.map((item, index) =>
                            <TouchableOpacity
                                style={[styles.cardList]}
                                onPress={() => changeToWarning(item)}
                                key={item.id}>
                                <View style={[styles.insideList]}>
                                    <Text style={[styles.warningSubheader]}>{item.name}</Text>
                                </View>
                                <View style={[styles.insideList]}>
                                    <View
                                        style={[(savedData.checkWarningColor(item) === 0) ? styles.semaphoreGreen : (savedData.checkWarningColor(item) === 1) ? styles.semaphoreYellow : styles.semaphoreRed]}>
                                        <Text style={[styles.noWarnings]}>{item.lastValue}</Text>
                                    </View>
                                </View>
                            </TouchableOpacity>
                        )}
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

const caca = StyleSheet.create({});

export default ListScreen;
