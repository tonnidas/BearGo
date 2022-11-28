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
import UserList from './UserList';

export default function ProfileSearch() {

    const [name, setName] = useState({});
    const [userListVisible, setUserListVisible] = useState(false);

    const handleUserFullname = (name) => {
        console.log("ProfileSearch => Search Name: " + name);
        setName(values => ({ ...values, ['fullname']: name }))
        setUserListVisible(true);
    }

    return (
        <div>
            <ProfileSearchNavbar handleUserFullname={handleUserFullname} />
            <Sidebar />
            <br />
            {!userListVisible && <OtherUsersProfile />}
            <br />
            {userListVisible && <UserList name={name.fullname}/>}
            <br />

            <script src="js/jquery-3.2.1.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/scripts.js"></script>
        </div>
    )

}