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
                console.log("Data:" + JSON.stringify(response.data))
                setData(JSON.stringify(response.data));
                alert(JSON.stringify(response.data))
            })
            .catch(function (error) {
                alert(error.message);
            })

        // iid().get().then(function (response) {
        //     alert('Current Instance ID: ' + response);
        // })
    };

    useEffect(() => {
        warningListData();
    }, [])

    // const renderItem = ({item}) => {
    //     return (
    //         <Item
    //             item={item}
    //             props={props}
    //
    //             onPress={() => props.navigation.navigate("Warning", {
    //                 item: item,
    //             })}
    //         />
    //     );
    // };

    // return (data.map((data, i) => {
    //     return
    //     (<TouchableOpacity key={i}>
    //         <View>
    //             <Text>
    //                 {data.name}
    //             </Text>
    //         </View>
    //     </TouchableOpacity>)
    // }))
// else
// {
    return (
        <SafeAreaView>
            <Text style={styles.header}>CARREGANT...</Text>
        </SafeAreaView>
    );
}
// }
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
    text: {
        fontSize: 45
    }
});

export default ListScene;
