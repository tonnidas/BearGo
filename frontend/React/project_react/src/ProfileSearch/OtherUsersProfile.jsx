import React, { useState, useEffect } from 'react';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';
import { Country, State, City } from "country-state-city";
import * as ReactDOMClient from 'react-dom/client';


import ReviewAndRatingPage from '../Profile/ReviewAndRatingPage';
import RoundedProfilePic from '../Profile/RoundedProfilePic';

export default function OtherUsersProfile({ userId }) {

    const navigate = useNavigate();
    const [inputs, setInputs] = useState({});

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        console.log(name + " " + value);
        setInputs(values => ({ ...values, [name]: value }))
    }

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

    // console.log(State.getStatesOfCountry("US"));
    // console.log(City.getCitiesOfState("US", "TX"));

    return (
        <div>
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-md-5">
                        <form className="user-form" onSubmit={handleSubmit}>
                            <RoundedProfilePic username={inputs.username} />
                            <ReviewAndRatingPage username={"review"}/>
                            <div className="text-center">
                                <img src={logo_white} alt="" />
                            </div>
                            <div className="form-group">
                                <label>Name</label>
                                <input type="text"
                                    className="form-control"
                                    placeholder="Jhon Doe"
                                    name='fullname'
                                    value={inputs.fullname || ""}
                                    onChange={handleChange} />
                            </div>
                            <div className="form-group">
                                <label>Phone</label>
                                <input type="text"
                                    className="form-control"
                                    placeholder="(XXX)-XXX-XXXX"
                                    name='phoneNumber'
                                    pattern='^(\([0-9]{3}\)|[0-9]{3}-)[0-9]{3}-[0-9]{4}$'
                                    value={inputs.phoneNumber || ""}
                                    onChange={handleChange} />
                            </div>

                            <div className="form-group">
                                <label>State</label>
                                <div className='select-style'>
                                    <select name='state' 
                                        value={inputs.state || ""} 
                                        onChange={handleChange}>
                                        {
                                            State.getStatesOfCountry("US").map((option) => (<option value={option.isoCode}>{option.name}</option>))
                                        }
                                    </select>
                                </div>
                            </div>

                            <div className="form-group">
                                <label>City</label>
                                <div className='select-style'>
                                    <select name='city' value={inputs.city || ""} onChange={handleChange}>
                                        {
                                            City.getCitiesOfState("US", inputs.state).map((option) => (<option value={option.isoCode}>{option.name}</option>))
                                        }
                                    </select>
                                </div>
                            </div>

                            <div className="form-group">
                                <label>Street</label>
                                <input type="text"
                                    className="form-control"
                                    placeholder="Street, Apt, Building"
                                    name='street'
                                    value={inputs.street || ""}
                                    onChange={handleChange} />
                            </div>

                            <div className="form-group">
                                <label>Zip</label>
                                <input type="text"
                                    pattern="(^\d{5}$)|(^\d{9}$)|(^\d{5}-\d{4}$)"
                                    className="form-control"
                                    placeholder="XXXXX"
                                    name='zip'
                                    value={inputs.zip || ""}
                                    onChange={handleChange} />
                            </div>
                               
                            <div className="form-group">
                                <label>Password</label>
                                <input type="password"
                                    className="form-control"
                                    placeholder="12345"
                                    name='password'
                                    value={inputs.password || ""}
                                    onChange={handleChange} />
                            </div>
                            <div className="form-group">
                                <label>Confirm Password</label>
                                <input type="password"
                                    className="form-control"
                                    placeholder="12345"
                                    name='confirmPassword'
                                    value={inputs.confirmPassword || ""}
                                    onChange={handleChange} />
                            </div>

                            <button type="submit"
                                className="common-btn btn-primary">Update</button>
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