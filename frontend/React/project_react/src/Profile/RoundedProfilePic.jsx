import React from "react";
import ReactRoundedImage from "react-rounded-image";
import MyPhoto from "./profile_pic.jpg";

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
    }
}

export default function RoundedProfilePic({ username, isOwnProfile }) {
    return (
        <>
            <div style={styles.div_background}>
                <div style={styles.profile_pic}>
                    <ReactRoundedImage
                        image={MyPhoto}
                        roundedColor="#321124"
                        imageWidth="150"
                        imageHeight="150"
                        roundedSize="10"
                        hoverColor="#DD1144" />
                </div>

                {getUploadImageOption(isOwnProfile)}

                <div style={styles.profile_pic_name}>
                    <span style={styles.profile_pic_name_style}>{username}</span>
                </div>
            </div>
        </>
    );
}

function getUploadImageOption(isOwnProfile) {
    if (isOwnProfile == false) {
        return (<></>);
    }
    return (
        <>
            <br />
            <div className='post' style={styles.center_align}>
                <label className='upload-btn'>
                    <i className='icon-upload'></i> Update Image
                    <input type='file' single name='imageFile' />
                </label>
            </div>
        </>
    );
}