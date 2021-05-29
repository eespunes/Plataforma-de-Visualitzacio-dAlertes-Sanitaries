export default class savedData {
    static user;
    static warning;
    // static URL = "https://tfg-informatica.herokuapp.com/api/";
    static URL = "http://localhost:8080/api/";
    static token;

    static checkWarningColor = function (warning) {
        if (warning.greenValue < warning.yellowValue) {
            if (warning.lastValue <= warning.greenValue)
                return 0;
            else if (warning.lastValue <= warning.redValue)
                return 1;
            else {
                return 2;
            }
        } else {
            if (warning.lastValue >= warning.greenValue)
                return 0;
            else if (warning.lastValue >= warning.redValue)
                return 1;
            else {
                return 2;
            }
        }
    }
}
