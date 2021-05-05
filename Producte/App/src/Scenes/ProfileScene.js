import React from "react";
import { Text, View, StyleSheet } from "react-native";

const ProfileScene = () => {
  return (
      <View>
        <Text style={styles.text}>Profile Screen</Text>
      </View>
  );
};

const styles = StyleSheet.create({
  text: {
    fontSize: 30
  }
});

export default ProfileScene;
