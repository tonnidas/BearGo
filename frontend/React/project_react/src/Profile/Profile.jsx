import React from 'react';
import Navbar from '../Components/Navbar';

import RoundedProfilePic from './RoundedProfilePic';

const styles = {
    input_field: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'left',
        margin: "0px 0px 0px 10px", // top, right, bottom, left
    }
}

export default function Profile() {
  return (
    <>
    <div>
        <RoundedProfilePic/>
        <div style={styles.input_field} 
            className='row'>
            <div className='col-md-4'>
                <div>
                <label>Name:</label>
                <input
                    className='form-control'
                    type='text'
                    name='name'
                    maxLength={30} />
                </div>
            </div>
        </div>
        
    </div>
    </>
  );
}