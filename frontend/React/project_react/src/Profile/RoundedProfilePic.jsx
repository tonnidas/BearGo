import React, { useState } from "react";
import ReactRoundedImage from "react-rounded-image";
import MyPhoto from "./profile_pic.jpg";
import axios from 'axios';
import AuthService from '../Service/AuthService';

const styles = {
    profile_pic: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        height: '20vh',
        margin: '25px 0px 0px 0px',
        cursor: 'pointer'
    },
    profile_pic_name: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        height: '5vh',
        margin: '25px 0px 0px 0px'
    },
    profile_pic_name_style: {
        fontFamily: 'consolas',
        fontSize: 30,
        margin: '0px 0px 0px 0px'
    },
    div_background: {
        // backgroundColor: 'blue'
    },
    center_align: {
        display: 'flex',
        justifyContent: 'center'
    },
    left_align: {
        display: 'flex',
        justifyContent: 'left'
    },
    right_align: {
        display: 'flex',
        justifyContent: 'right'
    }
}


export default function RoundedProfilePic({ imageChange, imageId, username, isOwnProfile }) {

    const [inputs, setInputs] = React.useState({ ['imageUploadFile']: MyPhoto });

    const handleUpload = async (event) => {
        const name = event.target.name;
        if (name === 'imageFile' && event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            const value = URL.createObjectURL(file);
            setInputs(values => ({ ...values, [name]: value, ['imageUploadFile']: file }));
        }
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (!inputs.imageUploadFile) {
            alert('Please select an image');
            return;
        }
        AuthService.setAxiosAuthHeader();
        try {
            // first upload the image and get image id
            let formData = new FormData();
            formData.append('file', inputs.imageUploadFile);
            const respImageUpload = await axios.post('/api/images/upload', formData);
            console.log('image id:' + respImageUpload.data.id);

            // localhost:8080/api/users/updateProfileImage/3
            const resp = await axios.post('/api/users/updateProfileImage/' + respImageUpload.data.id);
            console.log(resp.data);
            if(imageChange) {
                imageChange(respImageUpload.data.id);
            }
            alert('Profile image updated');
        } catch (error) {
            console.log(error);
            alert('Failed to update profile image, reason: ' + error.response.data.message);
        }
    }

    function getUploadImageOption(isOwnProfile) {
        if (isOwnProfile == false) {
            return (<></>);
        }
        return (
            <>
                <br />
                <div className='row'>
                    <div className='col-md-6' style={styles.right_align}>
                        <div className='post' style={styles.center_align}>
                            <label className='upload-btn'>
                                Select
                                <input type='file' single name='imageFile' onChange={handleUpload} />
                            </label>
                        </div>
                    </div>
                    <div className='col-md-6' style={styles.left_align}>
                        <div className='post' style={styles.center_align}>
                            <label className='upload-btn'>
                                Upload
                                <input type='file' single name='imageFile' onClick={handleSubmit} />
                            </label>
                        </div>
                    </div>
                </div>
            </>
        );
    }

    return (
        <>
            <div style={styles.div_background}>
                <div style={styles.profile_pic}>
                    <ReactRoundedImage
                        image={inputs.imageFile || (imageId && ("/api/images/download/" + imageId)) || MyPhoto}
                        roundedColor="#000000"
                        imageWidth="150"
                        imageHeight="150"
                        roundedSize="10"
                        hoverColor="#007bff" />
                </div>

                {getUploadImageOption(isOwnProfile)}

                <div style={styles.profile_pic_name}>
                    <span style={styles.profile_pic_name_style}>{username}</span>
                </div>
            </div>
        </>
    );
}
