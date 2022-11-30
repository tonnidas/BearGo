import React from 'react';

export const MessageSentComponent = (props) => {
  console.log(props.name);
  return (
    <>
      <div className="message message-sent">
        <ul>
          <li>
            {props.msg}
          </li>
        </ul>
      </div>
    </>
  );
}