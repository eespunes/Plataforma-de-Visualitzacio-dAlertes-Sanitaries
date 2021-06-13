export default class savedData {
    static user;
    static warning;
    static URL = "https://tfg-informatica.herokuapp.com/api/";
    static token;
    static loggedIn;
    static fromNotification;

    static checkWarningColor = function (warning) {
        if (warning === undefined) return 0

        if (warning.greenValue < warning.yellowValue) {
            if (warning.lastValue <= warning.greenValue) {
                return 0
            }
            else if (warning.lastValue <= warning.redValue) {
                return 1
            }
            else {
                return 2
            }
        } else {
            if (warning.lastValue >= warning.greenValue) {
                return 0
            }
            else if (warning.lastValue >= warning.redValue) {
                return 1
            }
            else {
                return 2
            }
        }
    }

    static return(navigation, profile) {
        if (this.loggedIn)
            navigation.navigate(profile)
        else
            navigation.navigate('Login')
    }
}
