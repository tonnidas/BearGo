import './ContactSender.css';

import React, { useEffect, useState } from 'react';
import Navbar from '../Components/Navbar';
import Sidebar from '../Components/Sidebar';
import image_man from '../images/man_avatar1.jpg';
import parcel_1 from '../images/parcel-1.jpg';
import axios from 'axios';
import AuthService from '../Service/AuthService';
import Moment from 'react-moment';
import Collapsible from 'react-collapsible';

import ReviewAndRating from '../ReviewAndRating/ReviewAndRating';

export default function ContactSender() {
    const [posts, setPosts] = useState([]);
    const [inputs, setInputs] = useState('');

    const handleChange = (event, param) => {
        const value = event.target.value;
        const name = event.target.name;
        //setPosts(values => ({ ...values, [name]: value }))
        AuthService.setAxiosAuthHeader();
        const resp = axios.post("api/contracts/updateStatus/" + param + "/" + value)
        console.log(resp.data);
        alert('Status Updated!');
    }

    const handleChangePeople = (event, param) => {
        const value = event.target.value;
        const name = event.target.name;
        AuthService.setAxiosAuthHeader();
        const resp = axios.post("api/contracts/confirmContract/" + param + "/" + value)
        console.log("id " + param);
        alert('Traveller selected!');
    }

    const handleChangeCost = (event) => {
        const value = event.target.value;
        setInputs(value);
    }

    const handleButton = (event, param) => {
        const value = event.target.value;

        console.log("cost " + inputs);
        AuthService.setAxiosAuthHeader();
        const resp = axios.post("api/contracts/addCost/" + param + "/" + inputs)

        //alert('Traveller selected!');
    }

    const handleClick = (e, status) => {
        if (status == "NONE") {
            AuthService.setAxiosAuthHeader();
            axios.get("/api/productPosts/getProductPostByCriteria/sender/NONE")

                .then((res) => {
                    console.log(res.data);
                    setPosts(res.data);
                })
                .catch((err) => {
                    console.log(err);

                });
        }
        else if (status == "IN_TRANSIT") {
            AuthService.setAxiosAuthHeader();
            axios.get("/api/productPosts/getProductPostByCriteria/sender/IN_TRANSIT")

                .then((res) => {
                    console.log(res.data);
                    setPosts(res.data);
                })
                .catch((err) => {
                    console.log(err);

                });
        }
        else if (status == "DELIVERED") {
            AuthService.setAxiosAuthHeader();
            axios.get("/api/productPosts/getProductPostByCriteria/sender/DELIVERED")

                .then((res) => {
                    console.log(res.data);
                    setPosts(res.data);
                })
                .catch((err) => {
                    console.log(err);

                });
        }
    };


    function reviewRatingButton(contractId, deliveryStatus) {
        if (deliveryStatus == "DELIVERED") {
            return <ReviewAndRating contractId={contractId} />;
        }
        return (
            <></>
        );
    }

    const searchPosts = (searchData) => {
        console.log("front page ");
        console.log(searchData);
        setPosts(searchData);
    }

    function showPost(post) {
        return (
            <div className='widget'>

                <div className='widget-head'>
                    <a href='#' className='user-avatar'>
                        <div className='mask'>
                            <img className='mask-img' src={image_man} alt='' />
                            <svg>
                                <use href='#icon-mask'></use>
                            </svg>
                        </div>
                        <div className='user-avatar-name'>
                            <h4>{post.contract.sender.username}</h4>
                            <span><Moment format="LLL">{post.createdAt}</Moment></span>
                        </div>
                    </a>
                </div>
                <div className='widget-inner'>
                    <div className='post post-contact'>
                        <form className="user-form">
                            <div className='form-group'>
                                <label>Description</label>

                                <p>
                                    {post.contract.description}
                                </p>

                            </div>
                            <div className='form-group'>
                                <label>Delivery Status</label>
                                <p>
                                    {post.contract.deliveryStatus}
                                </p>

                            </div>

                            <div className='row'>
                                <div className='col-md-6'>
                                    <div className='form-group'>
                                        <label>Start Date</label>
                                        {posts.map(post =>
                                            <p>
                                                {post.contract.contractStartDate}
                                            </p>
                                        )}
                                    </div>
                                </div>
                                <div className='col-md-6'>
                                    <div className='form-group'>
                                        <label>End Date</label>

                                        <p>
                                            {post.contract.contractEndDate}
                                        </p>

                                    </div>
                                </div>
                                <div className='col-md-6'>
                                    <label>Source Location</label>
                                    <br />
                                    <label>Street: </label>
                                    <p>{post.source.street}</p>
                                    <br />
                                    <label>City: </label>
                                    <p>{post.source.city}</p>
                                    <br />
                                    <label>Zip: </label>
                                    <p>{post.source.zip}</p>
                                    <br />
                                    <label>State: </label>
                                    <p>{post.source.state}</p>
                                </div>

                                <div className='col-md-6'>
                                    <label>Destination Location</label>
                                    <br />
                                    <label>Street: </label>
                                    <p>{post.destination.street}</p>
                                    <br />
                                    <label>City: </label>
                                    <p>{post.destination.city}</p>
                                    <br />
                                    <label>Zip: </label>
                                    <p>{post.destination.zip}</p>
                                    <br />
                                    <label>State: </label>
                                    <p>{post.destination.state}</p>
                                </div>
                            </div>

                            <div className='form-group'>

                            </div>
                            <br />
                            <div className="form-group">
                                <label>Interested Travelers</label>
                                <div className='select-style'>
                                    <select name='interestedPeoples'
                                        // value={post.contract.traveler.username || ""} 
                                        disabled={post.contract?.traveler?.id}
                                        onChange={event => handleChangePeople(event, post.id)}>
                                        <option value='None'>Select Traveler</option>
                                        {
                                            post.interestedPeoples.map(p =>
                                                <option value={p.id} selected={post.contract.traveler?.id === p.id ? true : false}>{p.username}</option>
                                            )
                                        }
                                    </select>
                                </div>
                            </div>
                            <div className='form-group'>
                                <label>Update Status</label>
                                <div className='select-style'>
                                    <select name='deliveryStatus'
                                        onChange={e => handleChange(e, post.contract.id)}>
                                        <option value='SEARCHING_TRAVELER' selected={post.contract.deliveryStatus === 'SEARCHING_TRAVELER' ? true : false}>SEARCHING_TRAVELER</option>
                                        <option value='PICKED_UP' selected={post.contract.deliveryStatus === 'PICKED_UP' ? true : false}> PICKED_UP</option>
                                        <option value='DELIVERED' selected={post.contract.deliveryStatus === 'DELIVERED' ? true : false}>DELIVERED</option>
                                    </select>
                                </div>
                            </div>
                            <div className="form-group">
                                <label>Cost</label>
                                <input className="form-control" placeholder="0.0" name='cost'
                                    onChange={event => handleChangeCost(event)} />
                            </div>

                            <button className='common-btn' onClick={event => handleButton(event, post.contract.id)}>Add Cost</button>

                            <br />
                            {/* <button className='common-btn'>Update Status</button> */}
                            {
                                reviewRatingButton(post.contract.id, post.contract.deliveryStatus)
                            }
                        </form>
                    </div>
                </div>
            </div>
        );
    }

    return (
        <>
            <Navbar searchpost={searchPosts} />

            <div className='container-fluid'>
                <div className='row'>
                    <Sidebar />

                    <main role='main' className='main col-md-12 ml-sm-auto col-lg-9'>
                        <div className='topnav'>
                            <button className='common-btn global' onClick={e => handleClick(e, "NONE")}>
                                <i className='icon'></i> <span>My Contracts</span>
                            </button>

                            <button className='common-btn global' onClick={e => handleClick(e, "IN_TRANSIT")}>
                                <i className='icon'></i> <span>In-transit</span>
                            </button>

                            <button className='common-btn global' onClick={e => handleClick(e, "DELIVERED")}>
                                <i className='icon'></i> <span>Completed</span>
                            </button>

                            {/* <a class="active" href="#posts" >My Posts</a>
        <a href="#interested">Interested Travellers</a>
        <a href="#completed">Completed</a>
        <a href="#intransit">In-Transit</a> */}
                        </div>
                        <div className='row' style={{ position: 'relative' }}>
                            <div className='col-md-8'>
                                <div className='main-inner'>
                                    {
                                        posts.map(post => showPost(post))
                                    }
                                </div>
                            </div>
                            <div className='col-md-4'>
                                <div className='twitter-feed'>
                                    <blockquote
                                        className='twitter-tweet'
                                        style={{ marginBottom: '10px' }}
                                    >
                                        <p lang='en' dir='ltr'>
                                            I think it&#39;s entirely appropriate that the courier
                                            delivery pin to receive my parcel from
                                            <a href='https://twitter.com/CounterLove?ref_src=twsrc%5Etfw'>
                                                @CounterLove
                                            </a>
                                            was 911 because these limited edition Hertzoggie cookies
                                            are criminally delicious!
                                            <a href='https://t.co/1z52eA0Oor'>
                                                pic.twitter.com/1z52eA0Oor
                                            </a>
                                        </p>
                                        &mdash; Nicole Engelbrecht (@the_pod_one)
                                        <a href='https://twitter.com/the_pod_one/status/1572841559812411392?ref_src=twsrc%5Etfw'>
                                            September 22, 2022
                                        </a>
                                    </blockquote>
                                    <blockquote
                                        className='twitter-tweet'
                                        style={{ marginBottom: '10px' }}
                                    >
                                        <p lang='en' dir='ltr'>
                                            I think it&#39;s entirely appropriate that the courier
                                            delivery pin to receive my parcel from
                                            <a href='https://twitter.com/CounterLove?ref_src=twsrc%5Etfw'>
                                                @CounterLove
                                            </a>
                                            was 911 because these limited edition Hertzoggie cookies
                                            are criminally delicious!
                                            <a href='https://t.co/1z52eA0Oor'>
                                                pic.twitter.com/1z52eA0Oor
                                            </a>
                                        </p>
                                        &mdash; Nicole Engelbrecht (@the_pod_one)
                                        <a href='https://twitter.com/the_pod_one/status/1572841559812411392?ref_src=twsrc%5Etfw'>
                                            September 22, 2022
                                        </a>
                                    </blockquote>
                                    <script
                                        async
                                        src='https://platform.twitter.com/widgets.js'
                                        charSet='utf-8'
                                    ></script>
                                </div>
                            </div>
                        </div>
                    </main>
                </div>
            </div>
        </>
    );
}
