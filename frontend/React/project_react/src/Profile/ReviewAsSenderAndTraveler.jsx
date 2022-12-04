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

const ReviewAsSenderAndTraveler = ({ userId, isSender }) => {

    const [userReviews, setUserReviews] = useState([]);
    const [userReviewsLoaded, setUserReviewsLoaded] = useState(false);
    var path = "";

    function getAllUserReviews() {
        if (!userId) {
            console.log("AllUserReviews => User id is null.");
            return;
        }

        AuthService.setAxiosAuthHeader();
        // localhost:8080/api/users/getReviewRatingByUserIdAsSender/3
        // localhost:8080/api/users/getReviewRatingByUserIdAsTraveler/3

        axios.get("/api/users/" + path + "/" + userId)
            .then(res => {
                setUserReviews(res.data);
                setUserReviewsLoaded(true);
                console.log("User reviews data => " + userId);
                console.log(userReviews);
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
            })
    }

    useEffect(() => {
        if (isSender == true) {
            path = "getReviewRatingByUserIdAsSender";
        } else {
            path = "getReviewRatingByUserIdAsTraveler";
        }
        setUserReviewsLoaded(false);
        getAllUserReviews();
    }, [userId]);

    return (
        <>
            {
                userReviewsLoaded == true
                &&
                getCollapsible()
            }
        </>
    );

    function getCollapsible() {
        if (isSender == true) {
            return (
                <Collapsible trigger="Review as Sender" triggerStyle={styles.trigger}>
                    {
                        getCollapsibleElement()
                    }
                </Collapsible>
            );
        } else {
            return (
                <Collapsible trigger="Review as Traveler" triggerStyle={styles.trigger}>
                    {
                        getCollapsibleElement()
                    }
                </Collapsible>
            );
        }
    }

    function getCollapsibleElement() {
        return (
            <>
                <br />
                <>
                    {
                        (userReviews.length == 0)
                        &&
                        <>
                            <h6 style={styles.center_align}>No reviews found.</h6>
                        </>
                    }
                    {
                        (userReviews.length != 0)
                        &&
                        <>
                            {
                                getUserReviews(userReviews)
                            }
                        </>
                    }
                </>
            </>
        );
    }

    function getSecondaryItem(reviewedBy, rating, reviewDate) {
        return (
            <p>
                {"By - " + reviewedBy + ", Rating - " + rating + "* on "}
                <span>
                    <Moment format="LLL">{reviewDate}</Moment>
                </span>
            </p>
        );
    }

    function getUserReviews(userReviews) {
        return (
            <List sx={styles.list_style_sx}>
                {
                    userReviews.map(item => (
                        <ListItem>
                            <ListItemText
                                primary={item.review}
                                secondary={getSecondaryItem(item.reviewer, item.rating, item.reviewDateTime)} />
                        </ListItem>)
                    )
                }
            </List>
        );
    }
}

export default ReviewAsSenderAndTraveler
