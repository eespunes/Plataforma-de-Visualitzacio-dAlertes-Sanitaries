import React, {useEffect} from "react";

import {
    SafeAreaView,
    View,
    StyleSheet,
    Text, TouchableOpacity, Modal, TextInput, Platform,
} from 'react-native';
import savedData from "../savedData";
import axios from "axios";

function ProfileScene({navigation}) {
    let [password, setPassword] = React.useState('')
    const [modalVisible, setModalVisible] = React.useState(false);

    const logout = async () => {
        axios
            .get(savedData.URL + 'logout/000')
            .then(function (response) {
                console.log(response.data)
                if (response.data !== '') {
                    alert('Sessió tancada')
                    navigation.navigate("Login")
                } else {
                    alert('No s\'ha pogut tancar la sessió')
                }
            })
            .catch(function (error) {
                alert(error.message)
                alert('S\'ha produit un error al servidor.')
            })
    };
    const changePassword = async () => {
        if (password.length >= 3)
            axios
                .get(savedData.URL + 'employee/change/' + savedData.user.username + '/' + password)
                .then(function (response) {
                    console.log(response.data)
                    if (response.data === '') {
                        alert('No s\'ha pogut canviar la contrasenya')
                    }
                })
                .catch(function (error) {
                    alert('S\'ha produit un error al servidor')
                })
        else
            alert('La nova contrasenya ha de tenir entre 3 i 32 caracters')
        setModalVisible(false)
    };
    return (
        <SafeAreaView style={[styles.safeArea]}>
            <Modal
                animationType="slide"
                transparent={true}
                visible={modalVisible}
                onRequestClose={() => {
                    setModalVisible(false);
                }}
            >
                <View style={styles.changePassword}>
                    <View style={styles.cardCP}>
                        <Text style={styles.subheader}>Introdueix la nova contrasenya</Text>

                        <TextInput
                            style={styles.input}
                            onChangeText={value => setPassword(value)}
                            value={password}
                            placeholder="Introdueix la nova contrasenya..."
                            secureTextEntry={true}
                            maxLength={32}
                        />

                        <TouchableOpacity
                            style={[styles.buttonEnd]}
                            onPress={() => changePassword()}
                        >
                            <Text style={styles.buttonText}>Canviar</Text>
                        </TouchableOpacity>
                    </View>
                </View>
            </Modal>
            <View style={[styles.card]}>
                <Text style={[styles.subheader]}>{savedData.user.username}</Text>
                <View style={styles.separator}/>
                <Text
                    style={[styles.subheader]}>{savedData.user.surname}, {savedData.user.name}</Text>
                <View style={styles.separator}/>
                <Text style={[styles.subheader]}>{savedData.user.role.name}</Text>
                <View style={styles.separator}/>
                <TouchableOpacity
                    style={[styles.button]}
                    onPress={() => setModalVisible(true)}>
                    <Text style={[styles.buttonText]}>Canviar Contrasenya</Text>
                </TouchableOpacity>
                <View style={styles.separator}/>
                <TouchableOpacity
                    style={[styles.buttonEnd]}
                    onPress={() => logout()}>
                    <Text style={[styles.buttonText]}>Tancar Sessió</Text>
                </TouchableOpacity>
            </View>
            <TouchableOpacity
                style={[styles.button]}
                onPress={() => navigation.navigate('About')}>
                <Text style={[styles.buttonText]}>Crédits</Text>
            </TouchableOpacity>
        </SafeAreaView>
    );
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
    },
    card: {

        marginTop: '2.5%',
        width: '95%',
        textAlign: 'center',
        alignItems: 'center',
        backgroundColor: '#00F8FF',
        borderRadius: 10,
        shadowColor: '#000',
        shadowOffset: {width: 0, height: 1},
        shadowOpacity: 0.8,
        shadowRadius: 2,
        elevation: 5
    }, cardCP: {
        borderColor: 'white',
        borderWidth: 5,
        marginTop: '2.5%',
        width: '95%',
        textAlign: 'center',
        alignItems: 'center',
        backgroundColor: '#00F8FF',
        borderRadius: 10,
        shadowColor: '#000',
        shadowOffset: {width: 0, height: 1},
        shadowOpacity: 0.8,
        shadowRadius: 2,
        elevation: 5
    }, changePassword: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        marginTop: 22
    },
    subheader: {
        fontSize: 30,
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    buttonText: {
        fontSize: 30,
        color: '#00F8FF',
        marginHorizontal: '2.5%',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    input: {
        color: 'white',
        textAlign: 'center',
        borderColor: 'white',
        borderWidth: 2.5,
        marginBottom: '2.5%',
        borderRadius: 5
    },
    button: {
        backgroundColor: '#ffffff',

        borderRadius: 5
    },
    buttonEnd: {
        backgroundColor: '#ffffff',
        borderRadius: 5,
        marginBottom: '2.5%'
    },
    separator: {
        marginVertical: 8,
        borderColor: 'white',
        borderWidth: 2.5,
        width: '100%'
    }
});

export default ProfileScene;
