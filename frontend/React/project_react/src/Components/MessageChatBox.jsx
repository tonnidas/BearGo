import axios from 'axios';
import React, { useEffect, useState } from 'react';

import { MessageReceiveComponent } from '../Components/MessageReceiveComponent';
import { MessageSentComponent } from '../Components/MessageSentComponent';
import AuthService from '../Service/AuthService';

import * as SockJS from 'sockjs-client';

import { Stomp } from "@stomp/stompjs";

var stompClient = null;

export default function MessageChatBox(props) {

  const [loading, setLoading] = useState(true);
  const [data, setData] = useState([]);
  const [msg, setMsg] = useState('');
  const [reloadWindow, setReloadWindow] = useState(false);

  const handleChange = (event) => {
    const value = event.target.value;
    setMsg(value);
    console.log(`Msg: ${msg}`);
  }

  const sendMsg = async () => {
    if (!msg) {
      alert('No msg to send!');
      return;
    }
    const data = {
      msg,
    }
    console.log(data);

    try {
      AuthService.setAxiosAuthHeader();
      const response = await axios.post(`/api/Message/${props.toId}`, data);
      props.reload();
      setReloadWindow(!reloadWindow);
    } catch (error) {
      console.error(`Failed to send msg. Error: ${error.message}`);
      alert('Msg not sent!');
    }
  }

  useEffect(() => {
    console.log(`Showing chat for ID: ${props.toId}`);
    AuthService.setAxiosAuthHeader();
    axios.get(`/api/Message/${props.toId}`)
      .then((res) => {
        console.log('Chat box messages', res.data);
        setData(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.log(err);
      });
    //}, [props.toId]);
    }, [props.toId, reloadWindow]);
    
    useEffect(() => {
        const socket = SockJS('http://localhost:8080/ws');
        console.log("My ID is ???: "  + props.toId)
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe(
                "/topic/frommsg" + props.toId,
                message => {

                    if (message.body) {
                        var dto = JSON.parse(message.body);
                        //setData(dto);
                        console.log("--------------");
                        console.log(msg);
                        console.log(data)
                        console.log(dto);
                        

                        console.log("------------");
                        //setData(myArray);
                        //setMsg(dto.msg);
                        console.log(msg);
                        /*
                        setMsg(values => [
                            ...values,
                            dto



                        ]);
                        */
                        setData(values => [
                            ...values,
                            dto
                            


                        ]);
                        
                        //setReloadWindow(!reloadWindow);
                    }
                }
            );
        });
        console.log("Subscribed to Message");
        stompClient.activate();
    }, [props.toId]);
    

  return (
    <>
      <div className="chatapp-conversation">
        {loading && <p></p>}
        {!loading && (<><div className="chatapp-header">
          <div className="back">
            <svg className="icon">
              <use href="#icon-back"></use>
            </svg>
          </div>
          <div className="avatar">
            <a className='thumbnail' href='#'>
              {props.toName.substring(0, 1)}
            </a>
            <div className="user-status status-active">&nbsp;</div>
          </div>
          <div className="user-details">
            <h4>{props.toName}</h4>
          </div>
        </div>
          <div className="chatapp-conversation-inner">
            {
              data.map(chatObj => {
                const sentMsg = chatObj.fromUser === Number(props.id);
                const component = sentMsg ?
                  (<MessageSentComponent msg={chatObj.msg} />) : (<MessageReceiveComponent name='NA' msg={chatObj.msg} />)
                return component;
              })
            }
          </div>

          <div className="chatapp-footer" style={{ margin: '4px' }}>
            <textarea onChange={handleChange}
              style={{ margin: '16px' }}
              name="chatText" rows="1" className="form-control" placeholder="Write a message"></textarea>
            {/* <div className="send-button"> */}
            <button className="btn btn-send" onClick={event => sendMsg()}>
              <use href="#icon-send"></use></button>
            {/* </div> */}
          </div> </>)
        }
      </div>
    </>
  );
}