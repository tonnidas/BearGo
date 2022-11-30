import React, { useEffect, useState } from 'react';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import Collapsible from 'react-collapsible';
import StarRatings from 'react-star-ratings';
import { Checkmark } from 'react-checkmark'

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
    },
    center_align: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        fontFamily: 'consolas',
        fontSize: 18,
    }
}

const ReviewAndRating = ({ contractId }) => {

    const [inputs, setInputs] = useState({ 'review': "", 'rating': 0 });
    const [reviewCompletion, setReviewCompletion] = useState(null);
    const [contractLoaded, setContractLoaded] = useState(false);

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
                setReviewCompletion(true);
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

    useEffect(() => {

        if (!contractId) {
            console.log("Contract id is null.");
            return;
        }

        AuthService.setAxiosAuthHeader();
        // localhost:8080/api/contracts/getReviewCompletion/1
        axios.get("/api/contracts/getReviewCompletion/" + contractId)
            .then(res => {
                setReviewCompletion(res.data);
                setContractLoaded(true);
                console.log("Contracts data => ");
                console.log(reviewCompletion);
                console.log("Sender => " + contract.sender);
                console.log("Traveler => " + contract.traveler);
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
            })

    }, [contractId]);

    return (
        <>
            {
                contractLoaded == true
                &&
                <Collapsible trigger="Review And Rate?" triggerStyle={styles.trigger}>
                    <br />
                    <>
                        {
                            (reviewCompletion == false)
                            &&
                            <>
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
                                    <br />
                                    <button onClick={handleSubmit} className="common-btn">Submit Review</button>
                                </form>
                            </>
                        }
                        {
                            (reviewCompletion == true)
                            &&
                            <>
                                <div style={styles.center_align}>
                                    <div>
                                        <Checkmark size='xxLarge' />
                                    </div>
                                </div>
                                <br/>
                                <p style={styles.center_align}>Review Done</p>
                            </>
                        }
                    </>
                </Collapsible>
            }
        </>
    );
}

export default ReviewAndRating
