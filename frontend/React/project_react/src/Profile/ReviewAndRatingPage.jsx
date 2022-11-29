import React, { useState, useEffect } from "react";

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import urlPaths from '../urlPaths';
import StarRatings from 'react-star-ratings';

import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    fontSize: '15px',
    fontFamily: 'consolas',
    color: theme.palette.text.primary,
}));

const styles = {
    center_align: {
        display: 'flex',
        justifyContent: 'center',
    },
    top_align: {
        margin: '25px 0px 0px 0px',
        display: 'flex',
        justifyContent: 'center',
    },
    left_align: {
        margin: '25px 0px 0px 0px',
        display: 'flex',
        justifyContent: 'left',
    },
    box_background: {
        backgroundColor: 'white',
        border: '1px solid black',
        borderTopLeftRadius: '5px',
        borderTopRightRadius: '5px',
        borderBottomLeftRadius: '5px',
        borderBottomRightRadius: '5px'
    }
}

export default function ReviewAndRatingPage({ userId }) {

    const navigate = useNavigate();
    const [rating, setRating] = useState({
        'userId': userId,
        'ratingAsSender': 0,
        'ratingAsTraveler': 0
    });
    const [contract, setContract] = useState({
        'userId': userId,
        'totalContractAsSender': 0,
        'totalDeliveredAsSender': 0,
        'totalUnsuccessfulAsSender': 0,
        'totalContractAsTraveler': 0,
        'totalDeliveredAsTraveler': 0,
        'totalUnsuccessfulAsTraveler': 0
    });

    useEffect(() => {

        if (!userId) {
            console.log("User id is null.");
            return;
        }

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


    useEffect(() => {

        if (!userId) {
            console.log("User id is null.");
            return;
        }

        console.log("ReviewRating User ID: " + userId);
        AuthService.setAxiosAuthHeader();

        // localhost:8080/api/users/getContractFrequency?userId=3
        axios.get("/api/users/getContractFrequency", {
            params: {
                userId: Number(userId)
            }
        })
            .then(res => {
                setContract(res.data);
                console.log("User id " + contract.userId);
                console.log("Sender " + contract.totalContractAsSender);
                console.log("Traveler " + contract.totalContractAsTraveler);
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

            <br />

            <Box sx={{ flexGrow: 1 }} style={styles.box_background}>
                <Grid container spacing={1}>
                    <Grid item xs={6} md={12}>
                        <Item>
                            <label style={styles.center_align}>As sender</label>
                            Number of contracts: {contract.totalContractAsSender}
                            <Grid container spacing={1}>
                                <Grid item xs={6} md={6}>
                                    <Item>Delivered: {contract.totalDeliveredAsSender}</Item>
                                </Grid>
                                <Grid item xs={6} md={6}>
                                    <Item>Unsuccessful: {contract.totalUnsuccessfulAsSender}</Item>
                                </Grid>
                            </Grid>
                        </Item>
                    </Grid>
                </Grid>
            </Box>

            <br />

            <Box sx={{ flexGrow: 1 }} style={styles.box_background}>
                <Grid container spacing={1}>
                    <Grid item xs={6} md={12}>
                        <Item>
                            <label style={styles.center_align}>As traveler</label>
                            Number of contracts: {contract.totalContractAsTraveler}
                            <Grid container spacing={1}>
                                <Grid item xs={6} md={6}>
                                    <Item>Delivered: {contract.totalDeliveredAsTraveler}</Item>
                                </Grid>
                                <Grid item xs={6} md={6}>
                                    <Item>Unsuccessful: {contract.totalUnsuccessfulAsTraveler}</Item>
                                </Grid>
                            </Grid>
                        </Item>
                    </Grid>
                </Grid>
            </Box>
        </>
    );
}
