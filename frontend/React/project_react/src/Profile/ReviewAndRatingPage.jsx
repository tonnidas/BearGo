import React from "react";

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

export default function ReviewAndRatingPage({ username }) {
    return (
        <>
            <div style={styles.top_align}>
                <label>
                    Rating As Sender
                    &nbsp; &nbsp; &nbsp; &nbsp;
                </label>
                <StarRatings
                    rating={2}
                    starRatedColor="blue"
                    numberOfStars={5}
                    starDimension='20px'
                    starSpacing='4px'
                    name='rating'
                />
            </div>

            <div style={styles.center_align}>
                <label>
                    Rating As Traveler
                    &nbsp; &nbsp; &nbsp;
                </label>
                <StarRatings
                    rating={2.4}
                    starRatedColor="red"
                    numberOfStars={5}
                    starDimension='20px'
                    starSpacing='4px'
                    name='rating'
                />
            </div>
        </>
    );
}
