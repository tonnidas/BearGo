import React, { useState, useEffect } from 'react';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';


import ReviewAndRatingPage from '../Profile/ReviewAndRatingPage';
import RoundedProfilePic from '../Profile/RoundedProfilePic';
import AllReportList from '../Profile/AllReportList';
import ReviewAsSenderAndTraveler from '../Profile/ReviewAsSenderAndTraveler';

export default function OtherUsersProfile({ userId }) {

    const navigate = useNavigate();
    const [inputs, setInputs] = useState({});
    const [currentUser, setCurrentUser] = useState({ "isAdmin": false });

    const handleReport = async (event) => {
        event.preventDefault();
        console.log("User Id: " + userId);

        const reportText = prompt("Enter your report!", "");
        if (reportText === null) {
            return;
        }

        AuthService.setAxiosAuthHeader();
        // localhost:8080/api/userComplaint/reportUser?reportTo=3&reason=So bad
        try {
            const resp = await axios.post('/api/userComplaint/reportUser?'
                + 'reportTo=' + userId + "&reason=" + reportText);
            console.log(resp.data);
            alert("Thank you for reporting, an admin will review this shortly!");
        } catch (error) {
            console.log(error);
            alert('Failed to report, reason: ' + error.response.data.message);
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

    useEffect(() => {
        AuthService.setAxiosAuthHeader();
        axios.get("/api/users/current")
            .then(res => {
                // res.data.map((key, value) => console.log(key + " " + value));
                console.log(res.data);
                setCurrentUser(res.data);
                console.log(currentUser);
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
                        <form className="user-form">
                            <RoundedProfilePic imageId={inputs.imageId} username={inputs.username} isOwnProfile={false} />
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

                            {
                                (currentUser.isAdmin == false)
                                &&
                                <>
                                    <form onClick={handleReport} className="report-user-from-other-profile">
                                        <button type="submit"
                                            className="common-btn btn-primary">Report</button>
                                    </form>
                                </>
                            }
                            {
                                (currentUser.isAdmin == true)
                                &&
                                <AllReportList userId={inputs.id} />
                            }
                            {
                                <>
                                <br />
                                    <ReviewAsSenderAndTraveler userId={inputs.id} isSender={true} />
                                </>
                            }
                            {
                                <>
                                <br />
                                    <ReviewAsSenderAndTraveler userId={inputs.id} isSender={false} />
                                </>
                            }
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