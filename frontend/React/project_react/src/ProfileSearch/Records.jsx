import React from 'react'
import ReactRoundedImage from "react-rounded-image";
import MyPhoto from "../Profile/profile_pic.jpg";

const styles = {
    profile_pic: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'left',
        height: '20vh',
        margin: '0px 0px 0px 15px',
    },
    profile_pic_name_style: {
        fontFamily: 'consolas',
        fontSize: 18,
        margin: '0px 0px 0px 0px'
    },
    div_background: {
        backgroundColor: 'white',
        border: '2px solid black',
        borderTopLeftRadius: '20px',
        borderTopRightRadius: '20px',
        borderBottomLeftRadius: '20px',
        borderBottomRightRadius: '20px'
    },
    button: {
        backgroundColor: 'black',
        color: 'white',
        fontSize: '15px',
        padding: '10px 40px',
        borderRadius: '5px',
        margin: '10px 0px',
        cursor: 'pointer'
    },
    button_align: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    }
}


const Records = ({ data, getUserIdFromRecords }) => {

    function getUserId(id) {
        console.log("Records => User ID: " + id);
        getUserIdFromRecords(id);
    }

    return (
        <>
            {data.map(item => (
                <>
                    <div style={styles.div_background} key={item.id}>
                        <div style={styles.profile_pic}>
                            <ReactRoundedImage
                                image={MyPhoto}
                                roundedColor="#321124"
                                imageWidth="70"
                                imageHeight="70"
                                roundedSize="10"
                                hoverColor="#DD1144" />
                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                            <span style={styles.profile_pic_name_style}>{item.fullname}</span>
                        </div>
                        <div style={styles.button_align}>
                            <button style={styles.button} onClick={() => getUserId(item.id)}>
                                View
                            </button>
                        </div>
                    </div>
                    <br />
                </>
            ))}
        </>
    )
}

export default Records
