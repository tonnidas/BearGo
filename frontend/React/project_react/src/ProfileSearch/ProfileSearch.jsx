import React, { useState, useEffect } from 'react';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';
import { Country, State, City } from "country-state-city";
import * as ReactDOMClient from 'react-dom/client';

import Sidebar from '../Components/Sidebar';
import ProfileSearchNavbar from './ProfileSearchNavbar';
import OtherUsersProfile from './OtherUsersProfile';

export default function ProfileSearch() {

    const handleUserFullname = (name) => {
        console.log("ProfileSearch => Search Name: " + name);
    }

    return (
        <div>
            <ProfileSearchNavbar handleUserFullname={handleUserFullname}/>
            <Sidebar />
            <br/>
            <OtherUsersProfile />

            <script src="js/jquery-3.2.1.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/scripts.js"></script>
        </div>
    )

}