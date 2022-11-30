import React, { useEffect, useState } from 'react';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import Collapsible from 'react-collapsible';
import StarRatings from 'react-star-ratings';

const styles = {
    trigger: {
        backgroundColor: 'white',
        border: '2px solid black',
        borderTopLeftRadius: '5px',
        borderTopRightRadius: '5px',
        borderBottomLeftRadius: '5px',
        borderBottomRightRadius: '5px',
        fontFamily: 'consolas',
        fontSize: 18,
        padding: '5px 15px 5px 15px',
        cursor: 'pointer',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    }
}

const ReviewAndRating = ({ contractId }) => {

    const [inputs, setInputs] = useState({ 'review': "", 'rating': 0 });

    const handleReview = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        console.log(name + " " + value);
        setInputs(values => ({ ...values, [name]: value }))
    }

    function changeRating(newRating) {
        setInputs(values => ({ ...values, ['rating']: newRating }))
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log("Handle Submit clicked");
        if (!contractId) {
            console.log("Contract id is null.");
            return;
        }

        AuthService.setAxiosAuthHeader();
        // localhost:8080/api/reviewAndRating/reviewAndRate?contractId=1&rating=4&review=Good communication
        axios.post("/api/reviewAndRating/reviewAndRate?contractId=" + contractId +
            "&rating=" + inputs.rating +
            "&review=" + inputs.review)
            .then(res => {
                setInputs(res.data);
                console.log("Review Rating = " + inputs);
                alert("Thank you for the review.");
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
                alert("Failed - " + err.response.data.message);
            })
    }

    // useEffect(() => {

    //     if (!contractId) {
    //         console.log("Contract id is null.");
    //         return;
    //     }

    //     AuthService.setAxiosAuthHeader();
    //     // localhost:8080/api/reviewAndRating/reviewAndRate?contractId=1&rating=4&review=Good communication
    //     axios.post("/api/reviewAndRating/reviewAndRate?contractId=" + contractId + "&rating=4&review=Good communication")
    //         .then(res => {
    //             setRating(res.data);
    //             console.log("Rating As Sender = " + rating.ratingAsSender);
    //             console.log("Rating As Traveler = " + rating.ratingAsTraveler);
    //         })
    //         .catch((err) => {
    //             console.log(err);
    //             if (err.response.status === 401) {
    //                 navigate(urlPaths.login);
    //             }
    //         })

    // }, [contractId]);

    return (
        <>
            <Collapsible trigger="Review And Rate?" triggerStyle={styles.trigger}>
                <br />
                <form className="review-rating-from-product-post-form">
                    <div>
                        <label>Write your review</label>
                        <textarea required className='form-control'
                            rows='3'
                            name='review'
                            value={inputs.review}
                            onChange={handleReview}>
                        </textarea>
                    </div>
                    <div>
                        <label>Select a rating</label> &nbsp; &nbsp;
                        <StarRatings
                            rating={inputs.rating}
                            starRatedColor="blue"
                            numberOfStars={5}
                            starDimension='20px'
                            changeRating={changeRating}
                            starSpacing='4px'
                            name='rating'
                        />
                    </div>
                    <br/>
                    <button onClick={handleSubmit} className="common-btn">Submit Review</button>
                </form>
            </Collapsible>
        </>
    );
}

export default ReviewAndRating
