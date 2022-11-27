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
            <div className='row' style={styles.top_align}>
                <div className='col-md-6'>
                    <label style={styles.center_align}>
                        Rating As Sender
                    </label>
                    <div style={styles.center_align}>
                        <StarRatings
                            rating={2}
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
                            rating={2.4}
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
