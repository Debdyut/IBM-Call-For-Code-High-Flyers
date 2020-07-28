import React from 'react';
import { Header } from '@react-navigation/stack';
import HTML from 'react-native-render-html';
import { StyleSheet, ToastAndroid, KeyboardAvoidingView, ScrollView, View, Text, TextInput, Button, TouchableOpacity, Linking } from 'react-native';

import { session, message } from '../lib/utils';

const styles = StyleSheet.create({
  outerContainer: {
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
  messageContainer: {
    flexDirection: 'column',
    marginTop: 10,
    alignItems: 'stretch',
    justifyContent: 'flex-start'
  },
  waText: {
    fontFamily: 'IBMPlexSans-Medium',
    backgroundColor: '#D0E2FF',
    padding: 10,
    alignSelf: 'flex-start',
    maxWidth: '85%'
  },
  myText: {
    fontFamily: 'IBMPlexSans-Medium',
    backgroundColor: '#F1F0EE',
    padding: 10,
    alignSelf: 'flex-end',
    maxWidth: '80%'
  },
  inputContainer: {
    backgroundColor: '#F1F0EE',
    width: '100%',
    flexDirection: 'row',
    alignItems: 'center',
    padding: 16
  },
  textInput: {
    fontFamily: 'IBMPlexSans-Medium',
    flex: 1,
    backgroundColor: '#fff',
    padding: 16,
    elevation: 2,
    paddingRight: 70,
    marginBottom: 25
  },
  submitButton: {
    fontFamily: 'IBMPlexSans-Medium',
    position: 'absolute',
    right: 24,
    bottom: 47
  },
  anchorLink: {
    fontFamily: 'IBMPlexSans-Medium',
    color: '#1062FE',
    padding: 2.5
  },
  chatText: {
    fontFamily: 'IBMPlexSans-Medium'
  },
  optionButton: {
    marginTop: 5,
    marginBottom: 5,
    paddingLeft: 2,
    paddingRight: 2,    
  }
});

const Chat = function ({ navigation }) {
  const [input, setInput] = React.useState('');
  const [sessionId, setSessionId] = React.useState('');
  const [messages, setMessages] = React.useState([]);

  React.useEffect(() => {
    addMessages([{ 
      title: "Hi, please select how I can help you.",
      options: [
        {
          label: "Check mental health",
          value: {
            input: {
              text: "Check mental health"
            }
          }
        },
        {
          label: "Improve Immunity",
          value: {
            input: {
              text: "Improve Immunity"
            }
          }
        },
        {
          label: "Donate plasma",
          value: {
            input: {
              text: "Donate plasma"
            }
          }
        }
      ],
      response_type: "option" }], false);
  }, [])

  const MapLink = (props) => {
    let locationText = ''
    const loc = (typeof props.location === 'string') ? props.location.split(',') : props.location;
    if (loc.length !== 2 || isNaN(loc[0]) || isNaN(loc[1])) {
      locationText = loc;
    } else {
      locationText = 'this location';
    }

    return (
      <TouchableOpacity onPress={() => { navigation.navigate('Map', { item: props }); }}>
        <Text style={styles.chatText}>  {props.quantity} at <Text style={styles.anchorLink}>{locationText}</Text> </Text>
      </TouchableOpacity>
    )
  };

  const MailLink = (props) => {
    return (
      <TouchableOpacity onPress={() => { Linking.openURL(`mailto:${props.contact}?subject=${props.name}`) }}>
        <Text style={styles.chatText}>  {props.quantity} from <Text style={styles.anchorLink}>{props.contact}</Text> </Text>
      </TouchableOpacity>
    )
  };

  const Resource = (props) => {
    if (props.location) {
      return <MapLink {...props} />
    } else {
      return <MailLink {...props} />
    }
  };
  
  const Message = (props) => {
    const style = props.fromInput ? styles.myText : styles.waText;    
    console.log(props)
    if(!props.text) 
      return null;
    else return (
      <View style={styles.messageContainer}>
        <View style={style}>
          <HTML  style={styles.chatText} html={props.text} />
          { props.description ? <Text style={styles.chatText}>{props.description}</Text> : null}
          { props.options.map((option, i) => {            
            return <View key={option.label} style = {styles.optionButton}>
                <Button
                title={option.label}
                key={option.label}
                onPress={() => sendMessage(option.label)}                
              />            
            </View>
          })}
          { props.resources.map((resource, i) => {
            resource.key = `sup-${(new Date()).getTime()}-${i}`;
            return <Resource {...resource} />
          })}
        </View>
      </View>
    );
  }; 

  

  const getSession = () => {
    return session()
      .then(sid => {
        setSessionId(sid);
        return sid;
      });
  };

  const handleMessageResponse = (response) => {
    if (!response.ok) {
      throw new Error(response.statusText || response.message || response.status);
    } else {
      return response.json().then(response => {
        console.log(JSON.stringify(response))
        addMessages(response.generic, false, response.resources);
      })
    }
  }

  const sendMessage = (msg) => {

    setInput(msg)    

    const payload = {
      text: input.trim() || msg,
      sessionid: sessionId
    };

    addMessages([{ text: input || msg }], true);

    setInput('');
    msg = null

    message(payload)
      .then(handleMessageResponse)
      .catch(e => {
        getSession()
          .then((sid) => {
            return message({
              text: payload.text,
              sessionid: sid
            });
          })
          .then(handleMessageResponse)
          .catch(err => {
            console.log(err)
            addMessages([{
              text: 'ERROR: Please try again. If the problem persists contact an administrator.'
            }]);
          });
      });
  };

  const addMessages = (msgs, fromInput, resources) => {
    const date = (new Date()).getTime();
    const result = msgs.map((r, i) => {
      
      return {        
        text: r.text || r.title,
        description: r.description,
        fromInput: fromInput,
        resources: resources || [],
        options: r.options || []                
      }
    });

    setMessages(msgs => [
      ...msgs,
      ...result
    ]);
  };

  React.useEffect(() => {
    navigation.addListener('focus', () => {
      getSession();
    });
  }, []);

  let scroll = null;
  const autoScroll = () => {        
    scroll.scrollToEnd( { animated: false } )
  }

  return (
    <View style={styles.outerContainer}>
      <KeyboardAvoidingView      
        style={styles.innerContainer}
        behavior='height'
        keyboardVerticalOffset={Platform.select({
          ios: 78,
          android: Header.HEIGHT + 20
        })} >
        <ScrollView ref={(input) => { scroll = input; }} contentContainerStyle={styles.scrollContainer} onContentSizeChange={ autoScroll } >
          {messages.map((msg, i) => {
            msg.key = `msg-${(new Date()).getTime()}-${i}`;
            return <Message {...msg} />
          })}
        </ScrollView>
        <View style={styles.inputContainer}>
          <TextInput
            style={styles.textInput}
            value={input}
            onChangeText={setInput}
            onSubmitEditing={input ? sendMessage : () => ToastAndroid.show("Cannot send empty message!", ToastAndroid.SHORT)}
            returnKeyType='send'
            enablesReturnKeyAutomatically={true}
            placeholder='Ask a question...'
            blurOnSubmit={false}
          />
          <View style={styles.submitButton}>
            {input !== '' && <Button title='Send' onPress={input ? sendMessage : () => ToastAndroid.show("Cannot send empty message!", ToastAndroid.SHORT)} />}
          </View>
        </View>
      </KeyboardAvoidingView>
    </View>
  );
};

export default Chat;
