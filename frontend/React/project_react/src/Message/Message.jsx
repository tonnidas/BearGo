import './Message.css';
import './messageListcss.css';

import axios from 'axios';
import React, { useEffect, useState } from 'react';

import MessageChatBox from '../Components/MessageChatBox';
import { MessageList } from '../Components/MessageList';
import Navbar from '../Components/Navbar';
import Sidebar from '../Components/Sidebar';
import AuthService from '../Service/AuthService';

import { useNavigate } from "react-router-dom";
import urlPaths from '../urlPaths';

import * as SockJS from 'sockjs-client';

import { Stomp } from "@stomp/stompjs";

var stompClient = null;
const Message = () => {

	const navigate = useNavigate();
	const [loading, setLoading] = useState(true);
	const [data, setData] = useState([]);
	const [userId, setUserId] = useState('');
	const [messageToId, setMessageToId] = useState('');
	const [messageToName, setMessageToName] = useState('');
	const [reloadWindow, setReloadWindow] = useState(false);

	const handleMsgChatListClick = (toId, toName) => {
		console.log('Chating with id: ', toId);
		setMessageToId(toId);
		setMessageToName(toName);
	}

	const reload = () => {
		setReloadWindow(!reloadWindow);
	}

	useEffect(() => {
		AuthService.setAxiosAuthHeader();
		axios.get("/api/users/current")
			.then(res => {
				setUserId(res.data.id);
				console.log('userId:', res.data.id);
			})
			.catch((err) => {
				console.log(err);
				if (err.response.status === 401) {
					navigate(urlPaths.login);
				}
			});
	}, []);

	useEffect(() => {
		AuthService.setAxiosAuthHeader();
		axios.get("/api/Message/msngrList")

			.then((res) => {
				setData(res.data);
				console.log('msngr list data:', res.data);
				setLoading(false);
			})
			.catch((err) => {
				console.log(err);
			});
	}, [userId, reloadWindow]);

	useEffect(() => {
		const socket = SockJS('http://localhost:8080/ws');
		console.log("My ID is ???: " + userId)
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function (frame) {
			stompClient.subscribe(
				"/topic/newmsg" +  userId,
				message => {
					
					if (message.body) {
						var dto = JSON.parse(message.body);
						//setData(dto);
						console.log("--------------");
						console.log(data)
						console.log(dto);

						var newdata = null;
						newdata = data.filter(function (obj) {
							return obj.fromid !== dto.fromid;
						});

						console.log(newdata);
						setData(newdata);

						console.log("------------");
						//setData(myArray);

						setData(values => [
							dto,
							... values
							

						]);
					}
				}
			);
		});
		console.log("Subscribed to Message");
		stompClient.activate();

	}, [userId]);

	return (
		<>
			<Navbar />

			<div className='container-fluid'>
				<div className='row'>
					<Sidebar />

					<main role='main' className='main col-md-12 ml-sm-auto col-lg-9'>
						<div className='row' style={{ position: 'relative' }}>
							<div className="col-md-8">
								<div className="main-inner">
									<div className="widget">
										<MessageChatBox id={userId} toId={messageToId} toName={messageToName} messageData={data} reload={reload} />
									</div>
								</div>
							</div>
							<div className='col-md-4'>

								<div className='chat'>

									{loading ? <p>Loading, Please Wait...</p> :
										<MessageList id={userId} data={data} clickHandler={handleMsgChatListClick} />
									}
								</div>
							</div>
						</div>
					</main>
				</div>
			</div>
		</>

	);
}

export default Message;
