import React from 'react';

export const MessageReceiveComponent = (props) => {
  return (
    <>
    <div className="message message-received">
            <div className="avatar">
              <a className='thumbnail' href='#'>
                {props.name.substring(0,1)}
              </a>
              <div className="user-status status-active">&nbsp;</div>
            </div>
            <ul>
              <li>
                {props.msg}
              </li>
            </ul>
          </div>
    </>
  );}