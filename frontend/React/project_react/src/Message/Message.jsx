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
import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';
const Message = () => {

	const navigate = useNavigate();
	const [loading, setLoading] = useState(true);
	const [data, setData] = useState([]);
	const [userId, setUserId] = useState('');

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
				setLoading(false);
			})
			.catch((err) => {
				console.log(err);
			});
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
										<MessageChatBox id={userId} toId={'3'} messageData={data} />
									</div>
								</div>
							</div>
							<div className='col-md-4'>

								<div className='chat'>

									{loading ? <p>Loading, Please Wait...</p> :
										<MessageList id={userId} data={data} />
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
