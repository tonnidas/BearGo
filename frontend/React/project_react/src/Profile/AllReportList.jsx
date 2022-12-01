import React, { useEffect, useState } from 'react';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import Collapsible from 'react-collapsible';

const styles = {
    trigger: {
        backgroundColor: 'red',
        border: '2px solid black',
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
    list_style: {
        fontSize: '14px',
        marginLeft: '10p',
        listStyleType: 'circle',
    }
}

const AllReportList = ({ userId }) => {

    const [userComplaints, setUserComplaints] = useState([]);
    const [userComplaintsLoaded, setUserComplaintsLoaded] = useState(false);

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
                                <h6 style={styles.center_align}>Great, you don't have any complaints yet.</h6>
                            </>
                        }
                        {
                            (userComplaints.length != 0)
                            &&
                            <>
                                <ul style={styles.list_style}>
                                    {
                                        userComplaints.map(item => (
                                            <>
                                                <li style={{display: 'list-item'}}>{item.reason}</li>
                                            </>
                                        ))
                                    }
                                </ul>
                            </>
                        }
                    </>
                </Collapsible>
            }
        </>
    );
}

export default AllReportList
