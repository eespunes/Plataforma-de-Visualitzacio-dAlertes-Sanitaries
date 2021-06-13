import React from "react";
import {StyleSheet} from "react-native";

const styles = StyleSheet.create({
    // VIEWS
    safeArea: {
        paddingTop: '7.5%',
        flex: 1,
        backgroundColor: '#ffffff',
        alignItems: 'center'
    },
    safeAreaTab: {
        flex: 1,
        backgroundColor: '#ffffff',
        alignItems: 'center'
    },
    flatList: {
        flexGrow: 0,
        backgroundColor: '#ffffff',
        alignItems: 'center'
    },
    viewList: {
        backgroundColor: '#ffffff',
        maxHeight: "100%",
        width: '100%',
        alignItems: 'center'
    },
    changePassword: {
        flex: 1,
        backgroundColor: '#00F8FF',
        alignItems: 'center'
    },
    safeAreaFullscreen: {
        paddingTop: '7.5%',
        flex: 1,
        backgroundColor: '#ffffff',
        alignItems: "center"
    },
    // CARDS
    card: {
        marginTop: '2.5%',
        width: '95%',
        justifyContent: 'center',
        textAlign: 'center',
        backgroundColor: '#00F8FF',
        borderRadius: 10,
        shadowColor: '#000',
        shadowOffset: {width: 0, height: 1},
        shadowOpacity: 0.8,
        shadowRadius: 2,
        elevation: 5
    },
    cardList: {
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
    insideList: {
        width: '50%',
    },
    cardCP: {
        marginTop: '10%',
        width: '95%',
        textAlign: 'center',
        alignItems: 'center',
        backgroundColor: 'white',
        borderRadius: 10,
        shadowColor: '#000',
        shadowOffset: {width: 0, height: 1},
        shadowOpacity: 0.8,
        shadowRadius: 2,
        elevation: 5
    },
    fullscreenCard: {
        width: '95%',
        height: '90%',
        textAlign: 'center',
        alignItems: 'center',
        backgroundColor: '#00F8FF',
        borderRadius: 10,
        shadowColor: '#000',
        shadowOffset: {width: 0, height: 1},
        shadowOpacity: 0.8,
        shadowRadius: 2,
        elevation: 5
    },
    // TEXTS
    header: {
        fontSize: 45,
        textAlign: 'center',
        fontWeight: 'bold',
        color: '#00F8FF'
    },
    subheader: {
        fontSize: 30,
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    warningSubheader: {
        fontSize: 25,
        margin: '2.5%',
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    noWarnings: {
        fontSize: 30,
        margin: '2.5%',
        color: 'black',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    warningName: {
        fontSize: 45,
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    warningValue: {
        fontSize: 50,
        margin: '2.5%',
        color: 'black',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    warningDescription: {
        width: '95%',
        fontSize: 20,
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'justify'
    },
    buttonText: {
        fontSize: 30,
        color: '#00F8FF',
        marginHorizontal: '2.5%',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    aboutButtonText: {
        color: 'white',
        fontSize: 30,
        fontWeight: 'bold',
        textAlign: 'center'
    },
    aboutSubheader: {
        fontSize: 50,
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    aboutText: {
        width: '95%',
        fontSize: 30,
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'center'
    },
    returnButtonText: {
        color: 'white',
        fontSize: 30,
        fontWeight: 'bold',
        textAlign: 'center',
        margin: '.5%',
    },
    // INPUTS
    input: {
        color: 'white',
        alignSelf: 'center',
        textAlign: 'center',
        borderColor: 'white',
        borderWidth: 2.5,
        fontSize: 20,
        width: '90%',
        borderRadius: 10
    },
    inputEnd: {
        color: 'white',
        alignSelf: 'center',
        textAlign: 'center',
        borderColor: 'white',
        borderWidth: 2.5,
        fontSize: 20,
        marginBottom: '2.5%',
        width: '90%',
        borderRadius: 10
    },
    alternativeInputEnd: {
        color: '#00F8FF',
        alignSelf: 'center',
        textAlign: 'center',
        borderColor: '#00F8FF',
        borderWidth: 2.5,
        fontSize: 20,
        marginBottom: '2.5%',
        width: '90%',
        borderRadius: 10
    },
    // BUTTONS
    button: {
        backgroundColor: '#ffffff',
        width: '95%',
        alignSelf: 'center',
        borderRadius: 10
    },
    buttonEnd: {
        backgroundColor: '#ffffff',
        borderRadius: 10,
        width: '95%',
        alignSelf: 'center',
        marginBottom: '2.5%'
    },
    returnButton: {
        backgroundColor: '#00F8FF',
        borderRadius: 10,
        alignSelf: 'flex-start',
        margin: '2.5%'
    },
    loginButton: {
        width: '95%',
        marginTop: '2.5%',
        backgroundColor: '#00F8FF',
        borderRadius: 10
    },
    passwordButton: {
        width: '95%',
        marginBottom: '2.5%',
        backgroundColor: '#00F8FF',
        borderRadius: 10
    },
    closeButton: {
        backgroundColor: 'red',
        borderRadius: 10,
        width: '10%',
        alignSelf: 'flex-end',
        margin: '2.5%',
    },
    // SEMAPHORE
    semaphoreGreen: {
        borderColor: 'white',
        borderWidth: 5,
        backgroundColor: '#00ff00',
        justifyContent: 'center',
        width: '95%',
        alignItems: 'center',
        borderRadius: 10,
        flexGrow: 1,
        margin: '2.5%'
    },
    semaphoreYellow: {
        borderColor: 'white',
        borderWidth: 5,
        backgroundColor: 'yellow',
        justifyContent: 'center',
        alignItems: 'center',
        width: '95%',
        borderRadius: 10,
        flexGrow: 1,
        margin: '2.5%'
    },
    semaphoreRed: {
        borderColor: 'white',
        borderWidth: 5,
        backgroundColor: 'red',
        justifyContent: 'center',
        width: '95%',
        alignItems: 'center',
        borderRadius: 10,
        flexGrow: 1,
        margin: '2.5%'
    },
    // EXTRA
    image: {
        width: '95%',
        height: '15%',
        resizeMode: 'contain',
        alignSelf: 'center',
        justifyContent:'center',
    },
    separator: {
        marginVertical: 8,
        borderColor: 'white',
        borderWidth: 2.5,
        width: '100%'
    }
});

export default styles;
