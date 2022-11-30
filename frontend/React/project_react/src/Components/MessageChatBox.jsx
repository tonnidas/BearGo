import axios from 'axios';
import React, { useEffect, useState } from 'react';

import { MessageReceiveComponent } from '../Components/MessageReceiveComponent';
import { MessageSentComponent } from '../Components/MessageSentComponent';
import image_woman from '../images/women_avatar1.jpg';
import AuthService from '../Service/AuthService';

export default function MessageChatBox(props) {

  const [loading, setLoading] = useState(true);
  const [data, setData] = useState([]);

  useEffect(() => {
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
  }, []);


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
            <img src={image_woman} alt="avatar" />
            <div className="user-status status-active">&nbsp;</div>
          </div>
          <div className="user-details">
            <h4>Nusa Penida</h4>
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

          <div className="chatapp-footer">
            <form action="" method="GET">
              <textarea name="" rows="1" className="form-control" placeholder="Write a message"></textarea>
              <div className="send-button">
                <button className="btn btn-send"><svg className="icon">
                  <use href="#icon-send"></use>
                </svg></button>
              </div>
            </form>
          </div> </>)
        }
      </div>
    </>
  );
}