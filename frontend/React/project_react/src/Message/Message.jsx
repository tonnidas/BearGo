import './Message.css';
import './messageListcss.css';

import axios from 'axios';
import React, { useEffect, useState } from 'react';

import MessageChatBox from '../Components/MessageChatBox';
import { MessageList } from '../Components/MessageList';
import Navbar from '../Components/Navbar';
import Sidebar from '../Components/Sidebar';
import AuthService from '../Service/AuthService';

const Message = () => {

	const [loading, setLoading] = useState(true);
	const [data, setData] = useState([]);

	useEffect(() => {
		AuthService.setAxiosAuthHeader();
		axios.get("/api/Message/3")

			.then((res) => {
				console.log(res.data);
				setData(res.data);
				setLoading(false);
			})
			.catch((err) => {
				console.log(err);
			});
	}, []);

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
										<MessageChatBox messageData={data} />
									</div>
								</div>
							</div>
							<div className='col-md-4'>

								<div className='chat'>

									{loading ? <p>Loading, Please Wait...</p> :
										<MessageList id='3' data={data} />
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
