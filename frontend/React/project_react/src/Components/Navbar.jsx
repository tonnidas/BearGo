import React, { useState, useEffect } from 'react';

import Notification from '../Components/Notification';

import admin from '../images/admin.jpg';
import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";

export default function Navbar() {

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
                        <li className='nav-item dropdown search'>
                            <a
                                className='input-search dropdown-toggle'
                                href='#'
                                id='navbarDropdown'
                                role='button'
                                data-toggle='dropdown'
                                aria-haspopup='true'
                                aria-expanded='false'
                            ></a>
                            <div
                                className='search-area dropdown-menu'
                                aria-labelledby='navbarDropdown'
                            >
                                <form action='#'>
                                    <div className='row'>
                                        <div className='col-md-6'>
                                            <div className='form-group'>
                                                <label>Start Date</label>
                                                <input
                                                    className='form-control'
                                                    type='date'
                                                    name=''
                                                    value=''
                                                />
                                            </div>
                                        </div>
                                        <div className='col-md-6'>
                                            <div className='form-group'>
                                                <label>End Date</label>
                                                <input
                                                    className='form-control'
                                                    type='date'
                                                    name=''
                                                    value=''
                                                />
                                            </div>
                                        </div>
                                    </div>
                                    <div className='form-group'>
                                        <label>Source Location</label>
                                        <div className='select-style'>
                                            <select name='#'>
                                                <option value=''>New York</option>
                                                <option value=''>Los Angeles</option>
                                                <option value=''>Chicago</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div className='form-group'>
                                        <label>Destination Location</label>
                                        <div className='select-style'>
                                            <select name='#'>
                                                <option value=''>New York</option>
                                                <option value=''>Los Angeles</option>
                                                <option value=''>Chicago</option>
                                            </select>
                                        </div>
                                    </div>
                                    <button className='common-btn'>Search</button>
                                </form>
                            </div>
                        </li>

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
                                <img src={admin} alt='' />
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
