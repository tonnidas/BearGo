import React, { useState, useEffect } from 'react';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';
import { Country, State, City } from "country-state-city";
import * as ReactDOMClient from 'react-dom/client';

import RoundedProfilePic from './RoundedProfilePic';

const styles = {
    input_field: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'left',
        margin: "0px 0px 15px 10px", // bottom, right, top, left
    }
}

export default function Profile() {

    // const navigate = useNavigate();
    // const [states, setStates] = useState([])

    // const [inputs, setInputs] = React.useState({});

    // const handleCountryChange = (event) => {
    //     const name = event.target.name;

    // }

    // useEffect(() => {
    //     // AuthService.setAxiosAuthHeader();
    //     axios.get("/api/state/findAllState")
    //         .then((res) => {
    //             // console.log(res.data);
    //             setStates(res.data);
    //         })
    //         .catch((err) => {
    //             console.log(err);
    //             if (err.response.status === 401) {
    //                 navigate(urlPaths.login);
    //             }
    //         });
    // }, [])

    // // console.log(City.getCitiesOfState("US", "TX"));

    // return (
    //     <>
    //         <div>
    //             <RoundedProfilePic />
    //             <div style={styles.input_field}
    //                 className='row'>
    //                 <div
    //                     className='col-md-4'>
    //                     <div>
    //                         <label>Name:</label>
    //                         <input
    //                             className='form-control'
    //                             type='text'
    //                             name='name'
    //                             maxLength={30} />
    //                     </div>
    //                 </div>
    //             </div>

    //             <div style={styles.input_field}
    //                 className='row'>
    //                 <div
    //                     className='col-md-4'>
    //                     <div>
    //                         <label>State:</label>
    //                         <div className='select-style'>
    //                             <select name='destCountry' value={inputs.destCountry} onChange={handleStateChange}>
    //                                 {
    //                                     State.getStatesOfCountry("US").map((option) => (<option>{option.name}</option>))
    //                                 }
    //                             </select>
    //                         </div>
    //                     </div>
    //                 </div>
    //             </div>

    //             <div style={styles.input_field}
    //                 className='row'>
    //                 <div
    //                     className='col-md-4'>
    //                     <div>
    //                         <label>City:</label>
    //                         <div className='select-style'>
    //                             <select name='destCountry' value={inputs.destCountry} onChange={handleCountryChange}>
    //                                 {
    //                                     City.getCitiesOfState("US", "TX").map((option) => (<option>{option.name}</option>))
    //                                 }
    //                             </select>
    //                         </div>
    //                     </div>
    //                 </div>
    //             </div>

    //         </div>
    //     </>
    // );

    const navigate = useNavigate();
    const [inputs, setInputs] = useState({});

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
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
                phoneNumber: inputs.phoneNumber
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
        axios.get("/api/users/current")
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
    }, []);

    return (
        <div>
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-md-5">
                        <form className="user-form" onSubmit={handleSubmit}>
                            <RoundedProfilePic username={inputs.username} />
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
                                    value={inputs.phoneNumber || ""}
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