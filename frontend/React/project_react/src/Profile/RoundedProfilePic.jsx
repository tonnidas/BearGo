import React from "react";
import ReactRoundedImage from "react-rounded-image";
import MyPhoto from "./profile_pic.jpg";

const styles = {
    profile_pic: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'left',
        height: '20vh',
        margin: '0px 0px 0px 20px'
    },
    profile_pic_name: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'left',
        height: '10vh',
        margin: '0px 0px 0px 15px'
    },
    profile_pic_name_style: {
        fontFamily: 'consolas',
        fontSize: 30,
        margin: '0px 0px 0px 10px'
    }
}

export default function ImageAvatars() {
    return (
    <>  
        <div style={styles.profile_pic}>
            <ReactRoundedImage
                image={MyPhoto}
                roundedColor="#321124"
                imageWidth="150"
                imageHeight="150"
                roundedSize="10"
                hoverColor="#DD1144" />
        </div>
        <div style={styles.profile_pic_name}>
            <span style={styles.profile_pic_name_style}>swapnilsaha.me</span>
        </div>
        <div style={styles.profile_pic_name}
            className='form-group'>
            <label className='upload-btn'>
            {' '}
            <i className='icon-upload'></i> Upload Image
            <input type='file' multiple />
            </label>
        </div>
    </>
  );
}
