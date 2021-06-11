import React from "react";

import {
    SafeAreaView,
    View,
    Text,
    TouchableOpacity,
    Modal,
    TextInput
} from 'react-native'
import savedData from "../savedData"
import axios from "axios"
import styles from "../Style"

function ProfileScreen({navigation}) {
    let [password, setPassword] = React.useState('')
    const [modalVisible, setModalVisible] = React.useState(false)

    const logout = async () => {
        axios
            .get(savedData.URL + 'logout/'+savedData.user.username+'/'+savedData.token)
            .then(function (response) {
                console.log(response.data)
                if (response.data !== '') {
                    alert('Sessió tancada')
                    savedData.user = null
                    savedData.loggedIn=false
                    navigation.navigate("Login")
                } else {
                    alert('No s\'ha pogut tancar la sessió')
                }
            })
            .catch(function (error) {
                alert('S\'ha produit un error al servidor.')
            })
    }
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
    }
    return (
        <SafeAreaView style={[styles.safeAreaTab]}>
            <Modal
                animationType="slide"
                transparent={true}
                visible={modalVisible}
                onRequestClose={() => {
                    setModalVisible(false)
                }}
            >
                <View style={styles.changePassword}>
                    <View style={styles.cardCP}>
                        <TouchableOpacity
                            style={[styles.closeButton]}
                            onPress={() => setModalVisible(false)}
                        >
                            <Text style={styles.subheader}>X</Text>
                        </TouchableOpacity>
                        <Text style={styles.buttonText}>Introdueix la nova contrasenya</Text>

                        <TextInput
                            style={styles.alternativeInputEnd}
                            onChangeText={value => setPassword(value)}
                            value={password}
                            placeholder="Introdueix la nova contrasenya..."
                            secureTextEntry={true}
                            maxLength={32}
                        />

                        <TouchableOpacity
                            style={[styles.passwordButton]}
                            onPress={() => changePassword()}
                        >
                            <Text style={styles.subheader}>Canviar</Text>
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
                style={[styles.loginButton]}
                onPress={() => navigation.navigate('About')}>
                <Text style={[styles.aboutButtonText]}>Sobre l'aplicació</Text>
            </TouchableOpacity>
        </SafeAreaView>
    )
}
export default ProfileScreen
