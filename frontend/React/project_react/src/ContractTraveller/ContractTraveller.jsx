

import React, { useEffect, useState } from 'react';
import Navbar from '../Components/Navbar';
import Sidebar from '../Components/Sidebar';
import image_man from '../images/man_avatar1.jpg';
import parcel_1 from '../images/parcel-1.jpg';
import axios from 'axios';
import AuthService from '../Service/AuthService';
import Moment from 'react-moment';

import urlPaths from '../urlPaths';

import ReviewAndRating from '../ReviewAndRating/ReviewAndRating';
import ProfileImage from '../Service/Helper';

export default function ContractTraveller() {
    const [posts, setPosts] = useState([]);
    const [people, setPeople] = useState([]);

    const handleChange = (event, param) => {
        const value = event.target.value;
        console.log(value);
        AuthService.setAxiosAuthHeader();
        const resp = axios.post("api/contracts/updateStatus/" + param + "/" + value)
        console.log(resp.data);
        alert('Status Updated!');
    }

    const handleChangePeople = (event, param) => {
        const value = event.target.value;
        AuthService.setAxiosAuthHeader();
        const resp = axios.post("api/contracts/confirmContract/" + param + "/" + value)
        console.log("id " + param);
        alert('Traveller selected!');
    }

    function onload() {
        AuthService.setAxiosAuthHeader();
        axios.get("/api/productPosts/getProductPostByCriteria/traveler/NONE")

            .then((res) => {
                console.log(res.data);
                setPosts(res.data);
            })
            .catch((err) => {
                console.log(err);

            });
    }
    useEffect(() => {
        let ignore = false;

        if (!ignore) onload()
        return () => { ignore = true; }
    }, []);

    const handleClick = (e, status) => {
        if (status == "NONE") {
            AuthService.setAxiosAuthHeader();
            axios.get("/api/productPosts/getProductPostByCriteria/traveler/NONE")

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
            axios.get("/api/productPosts/getProductPostByCriteria/traveler/IN_TRANSIT")

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
            axios.get("/api/productPosts/getProductPostByCriteria/traveler/DELIVERED")

                .then((res) => {
                    console.log(res.data);
                    setPosts(res.data);
                })
                .catch((err) => {
                    console.log(err);

                });
        }

    };

    const searchPosts = (searchData) => {
        console.log("front page ");
        console.log(searchData);
        setPosts(searchData);
    }

    function reviewRatingButton(contractId, deliveryStatus) {
        if (deliveryStatus == "DELIVERED") {
            return (
                <>
                    <br />
                    <ReviewAndRating contractId={contractId} />
                </>
            );
        }
        return (
            <></>
        );
    }

    const handleInvoice = (event, post) => {
        console.log("handleInvoice called");
        event.preventDefault();
        window.open(urlPaths.invoice + "?pid=" + post.id);
    }

    function createInvoiceButton(post) {
        return (
            <>
                <br />
                <br />
                <a>
                    <button className='common-btn' onClick={async (event) => handleInvoice(event, post)}>Create Invoice</button>
                </a>
            </>
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
                        </div>
                        <div className='row' style={{ position: 'relative' }}>
                            <div className='col-md-8'>
                                <div className='main-inner'>
                                    {
                                        posts.map(post =>
                                            <div className='widget'>

                                                <div className='widget-head'>
                                                    <a href='#' className='user-avatar'>
                                                        <div className='mask'>
                                                            <img className='mask-img' src={ProfileImage(post.contract.sender)} alt='' />
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
                                                        <form action='#' className="user-form">
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

                                                            <div className='col-md-6'>
                                                                <div className='form-group'>
                                                                    <label>Cost</label>

                                                                    <p>
                                                                        {post.contract.cost}
                                                                    </p>

                                                                </div>
                                                            </div>
                                                            <div className='form-group'>
                                                                <label>Update Status</label>
                                                                <div className='select-style'>
                                                                    <select name='#' onChange={e => handleChange(e, post.contract.id)}>
                                                                        <option value='PICKED_UP' selected={post.contract.deliveryStatus === 'PICKED_UP' ? true : false}> PICKED_UP</option>
                                                                        <option value='IN_TRANSIT' selected={post.contract.deliveryStatus === 'IN_TRANSIT' ? true : false}>IN-TRANSIT</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <br />

                                                            {/* <button className='common-btn'>Update Status</button> */}
                                                            {
                                                                createInvoiceButton(post)
                                                            }
                                                            <br />
                                                            {
                                                                reviewRatingButton(post.contract.id, post.contract.deliveryStatus)
                                                            }
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        )}
                                </div>
                            </div>
                        </div>
                    </main>
                </div>
            </div>
        </>
    );
}
