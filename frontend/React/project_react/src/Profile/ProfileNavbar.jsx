import React, { useState, useEffect } from 'react';
import ReactRoundedImage from "react-rounded-image";
import Notification from '../Components/Notification';

import admin from '../images/admin.jpg';
import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";

export default function ProfileNavbar({ imageId }) {

    const navigate = useNavigate();
    const [inputs, setInputs] = useState({});

    useEffect(() => {
        AuthService.setAxiosAuthHeader();
        axios.get("/api/users/current")
            .then(res => {
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

    useEffect(() => {
        setInputs(values => ({ ...values, ['imageId']: imageId }));
    }, [imageId]);

    return (
        <>
            <nav className='navbar navbar-expand-lg navbar-light fixed-top'>
                <div className='col-md-3 sidebar-top'>
                    <a className='navbar-brand' href='/'>
                        <img src={logo_white} alt='' />
                    </a>
                </div>
                <span className='sidebar-toggler'>
                    <i className='icon-more'></i>
                </span>
                <button
                    className='navbar-toggler'
                    type='button'
                    data-toggle='collapse'
                    data-target='#navbar'
                    aria-expanded='false'
                    aria-label='Toggle navigation'
                >
                    <i className='icon-bold'></i>
                </button>

                <div className='collapse navbar-collapse' id='navbar'>
                    <ul className='navbar-nav'>

                        <li className='nav-item dropdown ml-auto'>
                            <Notification />
                        </li>
                        <li className='nav-item dropdown'>
                            <a
                                className='nav-link dropdown-toggle'
                                href='#'
                                id='navbarDropdown'
                                role='button'
                                data-toggle='dropdown'
                                aria-haspopup='true'
                                aria-expanded='false'
                            >
                                <ReactRoundedImage
                                    image={(inputs.imageId && ("/api/images/download/" + inputs.imageId)) || admin}
                                    roundedColor="#000000"
                                    imageWidth="40"
                                    imageHeight="40"
                                    roundedSize="2"
                                    hoverColor="#007bff" />
                            </a>
                            <div className='dropdown-menu' aria-labelledby='navbarDropdown'>
                                <h3>{inputs.fullname}</h3>
                                <a className='dropdown-item' href={urlPaths.profile}>
                                    Profile
                                </a>
                                <a className='dropdown-item' href={urlPaths.login}>
                                    Logout
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </>
    );
}
