import React, { useState, useEffect } from 'react';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';


import ReviewAndRatingPage from '../Profile/ReviewAndRatingPage';
import RoundedProfilePic from '../Profile/RoundedProfilePic';

export default function OtherUsersProfile({ userId }) {

    const navigate = useNavigate();
    const [inputs, setInputs] = useState({});

    const handleSubmit = async (event) => {
        event.preventDefault();
        console.log("Username: " + inputs.username);

        try {
            const resp = await axios.post('/api/users/updateProfile', {
                username: inputs.username,
                fullname: inputs.fullname,
                password: inputs.password,
                phoneNumber: inputs.phoneNumber,
                state: inputs.state,
                city: inputs.city,
                street: inputs.street,
                zip: inputs.zip
            });
            console.log(resp.data);
            alert('Profile Updated!');
        } catch (error) {
            console.log(error);
            alert('Profile update failed! Please try again');
        }
    }

    useEffect(() => {
        AuthService.setAxiosAuthHeader();
        axios.get("/api/users/getUserById", {
            params: {
                id: Number(userId)
            }
        })
            .then(res => {
                // res.data.map((key, value) => console.log(key + " " + value));
                // console.log(res.data);
                setInputs(res.data);
                console.log(inputs);
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
            });
    }, [userId]);

    return (
        <div>
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-md-5">
                        <form className="user-form" onSubmit={handleSubmit}>
                            <RoundedProfilePic username={inputs.username} />
                            <ReviewAndRatingPage userId={inputs.id} />
                            <div className="text-center">
                                <img src={logo_white} alt="" />
                            </div>
                            <div className="form-group">
                                <label>Name</label>
                                <div className="form-control">
                                    {inputs.fullname || "-"}
                                </div>
                            </div>
                            <div className="form-group">
                                <label>Phone</label>
                                <div className="form-control">
                                    {inputs.phoneNumber || "-"}
                                </div>
                            </div>

                            <div className="form-group">
                                <label>State</label>
                                <div className="form-control">
                                    {inputs.state || "-"}
                                </div>
                            </div>

                            <div className="form-group">
                                <label>City</label>
                                <div className="form-control">
                                    {inputs.city || "-"}
                                </div>
                            </div>

                            <div className="form-group">
                                <label>Street</label>
                                <div className="form-control">
                                    {inputs.street || "-"}
                                </div>
                            </div>

                            <div className="form-group">
                                <label>Zip</label>

                                <div className="form-control">
                                    {inputs.zip || "-"}
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <script src="js/jquery-3.2.1.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/scripts.js"></script>
        </div>
    )
}