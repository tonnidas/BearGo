import React, { useState, useEffect } from "react";

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import urlPaths from '../urlPaths';
import StarRatings from 'react-star-ratings';

const styles = {
    center_align: {
        display: 'flex',
        justifyContent: 'center',
    },
    top_align: {
        margin: '25px 0px 0px 0px',
        display: 'flex',
        justifyContent: 'center',
    }
}

export default function ReviewAndRatingPage({ userId }) {

    const navigate = useNavigate();
    const [rating, setRating] = useState({'ratingAsSender': 0, 'ratingAsTraveler': 0});

    useEffect(() => {
        console.log("ReviewRating User ID: " + userId);
        AuthService.setAxiosAuthHeader();
        // localhost:8080/api/users/getRatingByUserId?userId=3
        axios.get("/api/users/getRatingByUserId", {
            params: {
                userId: Number(userId)
            }
        })
        .then(res => {
            setRating(res.data);
            console.log("Rating As Sender = " + rating.ratingAsSender);
            console.log("Rating As Traveler = " + rating.ratingAsTraveler);
        })
        .catch((err) => {
            console.log(err);
            if (err.response.status === 401) {
                navigate(urlPaths.login);
            }
        })
        
    }, [userId]);

    return (
        <>
            <div className='row' style={styles.top_align}>
                <div className='col-md-6'>
                    <label style={styles.center_align}>
                        Rating As Sender
                    </label>
                    <div style={styles.center_align}>
                        <StarRatings
                            rating={rating.ratingAsSender || 0.0}
                            starRatedColor="blue"
                            numberOfStars={5}
                            starDimension='20px'
                            starSpacing='4px'
                            name='rating'
                        />
                    </div>

                </div>
                <div className='col-md-6'>
                    <label style={styles.center_align}>
                        Rating As Traveler
                    </label>
                    <div style={styles.center_align}>
                        <StarRatings
                            rating={rating.ratingAsTraveler || 0.0}
                            starRatedColor="red"
                            numberOfStars={5}
                            starDimension='20px'
                            starSpacing='4px'
                            name='rating'
                        />
                    </div>
                </div>
            </div>
        </>
    );
}
