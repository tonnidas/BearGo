import React, { useEffect, useState } from 'react';

export const MessageList = (props) => {
  return (
    <>    <div className='body'>

      <div className='search'>
        <input placeholder='Search...' type='text' />
      </div>
      <ul>

        {
          props.data.map(chatObj => {
            const userId = chatObj.fromUser === Number(props.id) ? chatObj.toUser : chatObj.fromUser;
            console.log(userId, Number(props.id));
            return (<li>
              <a className='thumbnail' href='#'>
                NR
              </a>
              <div className='content'>
                <h3>{userId}</h3>
                <span className='preview'><a href='#'>{chatObj.msg ? chatObj.msg.substring(0, 10) + '...' : ''}</a></span>
              </div>
            </li>
            );
          })
        }
      </ul>
    </div>
    </>
  )
}