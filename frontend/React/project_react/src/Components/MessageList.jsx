

import React, { useEffect, useState } from 'react';

import AuthService from '../Service/AuthService';
import urlPaths from '../urlPaths';
import axios from 'axios';
import { Input,Card } from 'antd';


export var MessageList = (props) => {

    var [users, setUsers] = useState([]);
    var [usermatch, setusermatch] = useState([]);
    useEffect(() => {
        AuthService.setAxiosAuthHeader();
        axios.get("api/Message/all/users")
            .then(res => {
                //setUserId(res.data.id);
                console.log('userId:', res.data.id);
                setUsers(res.data);
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
            });
    }, []);

    console.log(users);
    const searchUsers = (text) => {

        if (!text) {
            setusermatch([]);
        }
        else {
            let matches = users.filter((userdata) => {
                const regex = new RegExp(`${text}`, 'gi');
                return userdata.fullname.match(regex);
            });
            setusermatch(matches);
            console.log("matched data");
            console.log(matches);
        }
    };

    var newfunc =(item)=> {
        console.log("My Data--------");
        console.log(item);
        console.log(props);
        props.clickHandler(item.id, item.fullname);

    }
    
  return (
    <>
          <div className='body'>
              <div className='search'>
                  <input style={{ width: "40%", marginTop: "5px"} } placeholder='Search...' type='text' onChange={(e) => searchUsers(e.target.value)} />
              </div>
              {/*{usermatch && usermatch.map((item, index) => (*/}
              {/*    <div key={index} style={{ marginLeft: "5%", marginTop:"5px" }}>*/}

              {/*        <a href="/">{item.fullname}</a>*/}
              {/*        */}{/*<Card title={item.fullname}/>*/}
              {/*        */}{/*Id:{item.id}*/}
              {/*        */}{/*</Card>*/}
              {/*    </div>*/}
              {/*))}*/}

              {usermatch && usermatch.map((item, index) => (
                  <div key={index} style={{ marginLeft: "5%", marginTop: "5px" }}>
                      
                      <a onClick={(e) => newfunc(item)} >{item.fullname}</a>
                      {/*<Card title={item.fullname}/>*/}
                      {/*Id:{item.id}*/}
                      {/*</Card>*/}
                  </div>
              ))}
              
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