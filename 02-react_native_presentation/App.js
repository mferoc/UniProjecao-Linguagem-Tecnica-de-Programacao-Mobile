/*
 **  Tela simples que exibe um card e um alert baseado no OS que está
 **  executando o app
 **  Feito para o workshop de introdução ao react-native ministrado por mim
 */
import React from "react";
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  Alert,
  Platform,
} from "react-native";
export default class Hello extends React.Component {
  clickMe() {
    Alert.alert(
      Platform.select({
        ios: "Welcome to iOS!",
        android: "Welcome to Android!",
      })
    );
  }
  render() {
    return (
      <View style={styles.container}>
        <TouchableOpacity onPress={this.clickMe.bind(this)}>
          <View style={styles.box}>
            <Text>Hello {this.props.platforms}. Please click me.</Text>
          </View>
        </TouchableOpacity>
      </View>
    );
  }
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    position: "absolute",
    bottom: 0,
    left: 0,
    right: 0,
  },
  box: {
    borderColor: "red",
    backgroundColor: "#fff",
    borderWidth: 1,
    padding: 10,
    width: 100,
    height: 100,
  },
});
