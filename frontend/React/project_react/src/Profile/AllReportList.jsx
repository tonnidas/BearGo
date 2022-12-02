import React, { useEffect, useState } from 'react';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import Collapsible from 'react-collapsible';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Moment from 'react-moment';

const styles = {
    trigger: {
        backgroundColor: 'red',
        border: '2px solid red',
        borderTopLeftRadius: '9px',
        borderTopRightRadius: '9px',
        borderBottomLeftRadius: '9px',
        borderBottomRightRadius: '9px',
        // fontFamily: 'consolas',
        fontSize: 16,
        color: 'white',
        padding: '11px 15px 11px 15px',
        cursor: 'pointer',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    center_align: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        fontFamily: 'consolas',
        fontSize: 14,
    },
    list_style_sx: {
        width: '100%',
        maxWidth: 360,
        bgcolor: 'background.paper'
    }
}

const AllReportList = ({ userId }) => {

    const [userComplaints, setUserComplaints] = useState([]);
    const [userComplaintsLoaded, setUserComplaintsLoaded] = useState(false);
    const [currentLoggedInUser, setCurrentLoggedInUser] = useState({ 'isAdmin': false });
    const [user, setUser] = useState({});
    const [banText, setBanText] = useState({ 'text': "" });

    const handleBanUser = async (event) => {
        event.preventDefault();
        // localhost:8080/api/users/banOrUnbanUser/{userId}/{isBanned}
        AuthService.setAxiosAuthHeader();
        console.log(user);
        axios.post(("/api/users/banOrUnbanUser/" + user.id + "/" + user.enabled))
            .then(res => {
                // res.data.map((key, value) => console.log(key + " " + value));
                console.log(res.data);
                setUser(res.data);
                if (res.data.enabled) {
                    setBanText({ 'text': "Ban User" });
                } else {
                    setBanText({ 'text': "Unban User" });
                }
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
            });
    }

    function getCurrentLoggedInUser() {
        AuthService.setAxiosAuthHeader();
        axios.get("/api/users/current")
            .then(res => {
                // res.data.map((key, value) => console.log(key + " " + value));
                console.log(res.data);
                setCurrentLoggedInUser(res.data);
                console.log(currentLoggedInUser);
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
            });
    }

    function getUser(id) {
        // localhost:8080/api/users/getUserById?id=3
        AuthService.setAxiosAuthHeader();
        axios.get("/api/users/getUserById?id=" + id)
            .then(res => {
                // res.data.map((key, value) => console.log(key + " " + value));
                console.log(res.data);
                setUser(res.data);
                if (res.data.enabled) {
                    setBanText({ 'text': "Ban User" });
                } else {
                    setBanText({ 'text': "Unban User" });
                }
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
            });
    }

    function getAllComplaints() {
        if (!userId) {
            console.log("AllReportList => User id is null.");
            return;
        }

        AuthService.setAxiosAuthHeader();
        // localhost:8080/api/userComplaint/getAllComplaintByUserId/3
        axios.get("/api/userComplaint/getAllComplaintByUserId/" + userId)
            .then(res => {
                setUserComplaints(res.data);
                setUserComplaintsLoaded(true);
                console.log("User complaints data => " + userId);
                console.log(userComplaints);
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
            })
    }

    useEffect(() => {
        getCurrentLoggedInUser();
        getUser(userId);
        setUserComplaintsLoaded(false);
        getAllComplaints();
    }, [userId]);

    return (
        <>
            {
                userComplaintsLoaded == true
                &&
                <Collapsible trigger="View All Report" triggerStyle={styles.trigger}>
                    <br />
                    <>
                        {
                            (userComplaints.length == 0)
                            &&
                            <>
                                <h6 style={styles.center_align}>Great, you don't have any reports yet.</h6>
                            </>
                        }
                        {
                            (userComplaints.length != 0)
                            &&
                            <>
                                {
                                    getComplaintList(userComplaints)
                                }
                            </>
                        }
                    </>
                </Collapsible>
            }
            {
                currentLoggedInUser.isAdmin
                &&
                (banText.text != "")
                &&
                <>
                    <br />
                    <form className="other-user-ban-button btn-primary">
                        <button onClick={handleBanUser} className="common-btn">{banText.text}</button>
                    </form>
                </>
            }
        </>
    );

    function getSecondaryItem(reportedBy, reportDate) {
        return (
            <p>
                {"By - " + reportedBy + " on "}
                <span>
                    <Moment format="LLL">{reportDate}</Moment>
                </span>
            </p>
        );
    }

    function getComplaintList(userComplaints) {
        return (
            <List sx={styles.list_style_sx}>
                {
                    userComplaints.map(item => (
                        <ListItem>
                            <ListItemText
                                primary={item.reason}
                                secondary={getSecondaryItem(item.complainedByUserName, item.complainDate)} />
                        </ListItem>)
                    )
                }
            </List>
        );
    }
}

export default AllReportList
