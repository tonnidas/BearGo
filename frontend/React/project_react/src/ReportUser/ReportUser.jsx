

import React, { useEffect, useState } from 'react';

import Navbar from '../Components/Navbar';
import Sidebar from '../Components/Sidebar';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import urlPaths from '../urlPaths';


export default function ReportUser() {
    const navigate = useNavigate();
    const [posts, setPosts] = useState([]);

    const handleResolve = async (verdict,complaintid) => {
        if (window.confirm("Are you sure to mark this as " + verdict + "?") == false) {
          return
        }
    
        AuthService.setAxiosAuthHeader();
    
        try {
          const resp = await axios.post('/api/userComplaint/resolveComplaint/' + complaintid);
          console.log(resp.data);
          alert("Thank you for resolving the issue!");
        } catch (error) {
          console.log(error);
          alert('Failed to reolve the issue, reason: ' + error.response.data.message);
        }
      }

    useEffect(() => {
        AuthService.setAxiosAuthHeader();

        axios.get("/api/userComplaint/allUserComplaints")
            .then((res) => {
                console.log(res.data);
                setPosts(res.data);
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
            });
    }, []);

    const handleIgnore = async (event, complaintid) => {
        event.preventDefault();
        console.log("ignored");
        handleResolve("ignored", complaintid);
    }

    const handleBlock = async (event) => {
        event.preventDefault();
        console.log("block");
        // handleResolve("blocked");
    }

    return (
        <div>
            <title>User Complaints</title>

            <Navbar />

            <div className='container-fluid'>
                <div className='row'>
                    <Sidebar />
                    {posts.map(post =>
                        <div className="container">
                            <div className="row justify-content-center">
                                <div className="col-md-5">
                                    <form className="user-form">
                                        <div className="form-group">
                                            <label>Name</label>
                                            <p>
                                                {post.complainedUser.fullname}
                                            </p>
                                        </div>
                                        <div className="form-group">
                                            <label>Phone</label>
                                            <p>
                                                {post.complainedUser.phoneNumber}
                                            </p>
                                        </div>
                                        <div className="form-group">
                                            <label>Reason</label>
                                            <p>
                                                {post.reason}
                                            </p>
                                        </div>

                                        <div className='widget-footer'>
                                            <div className='post-action'>
                                                <ul>
                                                    <li>
                                                        <a role="button" href='#' onClick={ e => handleIgnore(e, post.id)}>
                                                            <i className='icon-check'></i>Ignore
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a role="button" href='#' onClick={handleBlock}>
                                                            <i className='icon-alert'></i>Block
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    )}
                </div>

            </div>

            <script src='js/jquery-3.2.1.min.js'></script>
            <script src='js/bootstrap.min.js'></script>
            <script src='js/scripts.js'></script>
        </div>
    );
}
