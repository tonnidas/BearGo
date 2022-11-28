import React, { useState, useEffect } from 'react';

import Sidebar from '../Components/Sidebar';
import ProfileSearchNavbar from './ProfileSearchNavbar';
import OtherUsersProfile from './OtherUsersProfile';
import UserList from './UserList';

export default function ProfileSearch() {

    const [name, setName] = useState({});
    const [otherProfileVisible, setOtherProfileVisible] = useState(false);

    const handleUserFullname = (name) => {
        console.log("ProfileSearch => Search Name: " + name);
        setName(values => ({ ...values, ['fullname']: name }))
        setOtherProfileVisible(false);
    }

    function getUserIdFromUserList(id) {
        console.log("ProfileSearch => User ID: " + id);
        setOtherProfileVisible(true);
        setName(values => ({ ...values, ['fullname']: "" }))
    }

    return (
        <div>
            <ProfileSearchNavbar handleUserFullname={handleUserFullname} />
            <Sidebar />
            <br />
            {otherProfileVisible && <OtherUsersProfile />}
            <br />
            {name.fullname && <UserList name={name.fullname} getUserIdFromUserList={getUserIdFromUserList}/>}
            <br />

            <script src="js/jquery-3.2.1.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/scripts.js"></script>
        </div>
    )

}