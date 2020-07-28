import React from 'react';
import { StyleSheet, View, Image, Text, TouchableOpacity, Button, Linking } from 'react-native';
import { ScrollView } from 'react-native-gesture-handler';

const styles = StyleSheet.create({
  outerContainer: {
    flex: 1,
    width: '100%',
    height: '100%',
    backgroundColor: '#FFF'
  },
  innerContainer: {
    width: '100%',
    height: '100%',
    flex: 1
  },
  scrollContainer: {
    flexGrow: 1,
    width: '100%',
    flexDirection: 'column',
    justifyContent: 'flex-end',
    paddingHorizontal: 16,
    paddingBottom: 5
  },
  center: {
    flexGrow: 1,
    flexDirection: 'column',
    justifyContent: 'flex-start',
    alignItems: 'flex-start',
    backgroundColor: '#FFFFFF',
  },
  scroll: {    
    paddingLeft: 20,
    paddingRight: 20,
    // paddingBottom: 25,
    paddingTop: 50,   
  },
  image: {
    alignSelf: 'flex-start',
    height: '20%',
    width:'50%',
    resizeMode: 'contain'
  },
  title: {
    fontFamily: 'IBMPlexSans-Medium',
    fontSize: 36,
    color: '#323232',
    paddingBottom: 15
  },
  subtitle: {
    fontFamily: 'IBMPlexSans-Light',
    fontSize: 24,
    color: '#323232',
    textDecorationColor: '#D0E2FF',
    textDecorationLine: 'underline',
    paddingBottom: 5,
    paddingTop: 20
  },
  content: {
    fontFamily: 'IBMPlexSans-Light',
    color: '#323232',
    marginTop: 10,
    marginBottom: 10,
    fontSize: 16
  },
  buttonGroup: {
    flex: 1,
    paddingTop: 15,
    width: 175
  },
  button: {
    backgroundColor: '#1062FE',
    color: '#FFFFFF',
    fontFamily: 'IBMPlexSans-Medium',
    fontSize: 16,
    overflow: 'hidden',
    padding: 12,
    textAlign:'center',
    marginTop: 15
  }
});

const Home = () => (
  // <View style={styles.outerContainer}>
    <ScrollView style={styles.scroll}>
      <Image
        style={styles.image}
        source={require('../images/2020-cfc-512.png')}
      />      
      <Text style={styles.title}>Shield</Text>      
      <Text style={styles.content}>
      A comprehensive ChatBot solution SHIELD to help survive  the pandemic and also potentially help in the cure. 
      </Text>
      <Text style={styles.content}>
      Shield detects depression of people staying at home, based on conversation analysis and suggest potential solutions, 
      helpline numbers and notifications to concerned party depending on the severity.
      </Text>
      <Text style={styles.content}>
      Shield calculates immunity score based on conversation analysis of eating habit, daily routine, age group etc. and 
      suggest ways of improving it through lifestyle and food changes. Immunity is the only SHIELD against COVID-19 in 
      absence of vaccine.
      </Text>
      <Text style={styles.content}>
      Shield allows voluntary registration of plasma donors to the centralized registry accessible to medical and government 
      departments. User accepting the request and confirming for donation will save atleast 5 lives.
      </Text>
      <Text>{'\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n'}</Text> 
    </ScrollView>
  // </View>
);

export default Home;
