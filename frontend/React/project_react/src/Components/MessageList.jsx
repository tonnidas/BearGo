import React from 'react';

export const MessageList = (props) => {
  return (
    <>
      <div className='body'>
        <div className='search'>
          <input placeholder='Search...' type='text' />
        </div>
        <ul>
          {
            props.data.map((chatObj, index) => {
              const msgUserId = chatObj.fromid === Number(props.id) ? chatObj.toid : chatObj.fromid;
              console.log(`list user Id: ${msgUserId}`);
              const userName = chatObj.fromid === Number(props.id) ? chatObj.toName : chatObj.fromname;
              return (<li key={index} onClick={() => props.clickHandler(msgUserId, userName)} >
                <a className='thumbnail' href='#'>
                  {userName.substring(0, 1)}
                </a>
                <div className='content'>
                  <h3>{userName}</h3>
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