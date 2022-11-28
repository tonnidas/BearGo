import React, { useState, useEffect } from 'react';
import Records from './Records';
import Pagination from './Pagination';

const styles = {
    center_align: {
        display: 'flex',
        justifyContent: 'center',
    }
}

export default function UserList({name}) {
    let users = [{ 'id': 1, 'first_name': 'A1', 'last_name': 'S1', 'city': 'Dhaka' },
    { 'id': 2, 'first_name': 'A2', 'last_name': 'S2', 'city': 'Dhaka' },
    { 'id': 3, 'first_name': 'A3', 'last_name': 'S3', 'city': 'Dhaka' },
    { 'id': 4, 'first_name': 'A4', 'last_name': 'S4', 'city': 'Dhaka' },
    { 'id': 5, 'first_name': 'A5', 'last_name': 'S5', 'city': 'Dhaka' },
    { 'id': 6, 'first_name': 'A6', 'last_name': 'S6', 'city': 'Dhaka' },
    { 'id': 7, 'first_name': 'A7', 'last_name': 'S7', 'city': 'Dhaka' },
    { 'id': 8, 'first_name': 'A8', 'last_name': 'S8', 'city': 'Dhaka' },
    { 'id': 9, 'first_name': 'A9', 'last_name': 'S9', 'city': 'Dhaka' },
    { 'id': 10, 'first_name': 'A10', 'last_name': 'S10', 'city': 'Dhaka' },
    { 'id': 11, 'first_name': 'A11', 'last_name': 'S11', 'city': 'Dhaka' },
    { 'id': 12, 'first_name': 'A12', 'last_name': 'S12', 'city': 'Dhaka' },
    { 'id': 13, 'first_name': 'A13', 'last_name': 'S13', 'city': 'Dhaka' },
    { 'id': 1, 'first_name': 'A1', 'last_name': 'S1', 'city': 'Dhaka' },
    { 'id': 2, 'first_name': 'A2', 'last_name': 'S2', 'city': 'Dhaka' },
    { 'id': 3, 'first_name': 'A3', 'last_name': 'S3', 'city': 'Dhaka' },
    { 'id': 4, 'first_name': 'A4', 'last_name': 'S4', 'city': 'Dhaka' },
    { 'id': 5, 'first_name': 'A5', 'last_name': 'S5', 'city': 'Dhaka' },
    { 'id': 6, 'first_name': 'A6', 'last_name': 'S6', 'city': 'Dhaka' },
    { 'id': 7, 'first_name': 'A7', 'last_name': 'S7', 'city': 'Dhaka' },
    { 'id': 8, 'first_name': 'A8', 'last_name': 'S8', 'city': 'Dhaka' },
    { 'id': 9, 'first_name': 'A9', 'last_name': 'S9', 'city': 'Dhaka' },
    { 'id': 10, 'first_name': 'A10', 'last_name': 'S10', 'city': 'Dhaka' },
    { 'id': 11, 'first_name': 'A11', 'last_name': 'S11', 'city': 'Dhaka' },
    { 'id': 12, 'first_name': 'A12', 'last_name': 'S12', 'city': 'Dhaka' },
    { 'id': 13, 'first_name': 'A13', 'last_name': 'S13', 'city': 'Dhaka' },
    { 'id': 1, 'first_name': 'A1', 'last_name': 'S1', 'city': 'Dhaka' },
    { 'id': 2, 'first_name': 'A2', 'last_name': 'S2', 'city': 'Dhaka' },
    { 'id': 3, 'first_name': 'A3', 'last_name': 'S3', 'city': 'Dhaka' },
    { 'id': 4, 'first_name': 'A4', 'last_name': 'S4', 'city': 'Dhaka' },
    { 'id': 5, 'first_name': 'A5', 'last_name': 'S5', 'city': 'Dhaka' },
    { 'id': 6, 'first_name': 'A6', 'last_name': 'S6', 'city': 'Dhaka' },
    { 'id': 7, 'first_name': 'A7', 'last_name': 'S7', 'city': 'Dhaka' },
    { 'id': 8, 'first_name': 'A8', 'last_name': 'S8', 'city': 'Dhaka' },
    { 'id': 9, 'first_name': 'A9', 'last_name': 'S9', 'city': 'Dhaka' },
    { 'id': 10, 'first_name': 'A10', 'last_name': 'S10', 'city': 'Dhaka' },
    { 'id': 11, 'first_name': 'A11', 'last_name': 'S11', 'city': 'Dhaka' },
    { 'id': 12, 'first_name': 'A12', 'last_name': 'S12', 'city': 'Dhaka' },
    { 'id': 13, 'first_name': 'A13', 'last_name': 'S13', 'city': 'Dhaka' },
    { 'id': 1, 'first_name': 'A1', 'last_name': 'S1', 'city': 'Dhaka' },
    { 'id': 2, 'first_name': 'A2', 'last_name': 'S2', 'city': 'Dhaka' },
    { 'id': 3, 'first_name': 'A3', 'last_name': 'S3', 'city': 'Dhaka' },
    { 'id': 4, 'first_name': 'A4', 'last_name': 'S4', 'city': 'Dhaka' },
    { 'id': 5, 'first_name': 'A5', 'last_name': 'S5', 'city': 'Dhaka' },
    { 'id': 6, 'first_name': 'A6', 'last_name': 'S6', 'city': 'Dhaka' },
    { 'id': 7, 'first_name': 'A7', 'last_name': 'S7', 'city': 'Dhaka' },
    { 'id': 8, 'first_name': 'A8', 'last_name': 'S8', 'city': 'Dhaka' },
    { 'id': 9, 'first_name': 'A9', 'last_name': 'S9', 'city': 'Dhaka' },
    { 'id': 10, 'first_name': 'A10', 'last_name': 'S10', 'city': 'Dhaka' },
    { 'id': 11, 'first_name': 'A11', 'last_name': 'S11', 'city': 'Dhaka' },
    { 'id': 12, 'first_name': 'A12', 'last_name': 'S12', 'city': 'Dhaka' },
    { 'id': 13, 'first_name': 'A13', 'last_name': 'S13', 'city': 'Dhaka' },
    { 'id': 1, 'first_name': 'A1', 'last_name': 'S1', 'city': 'Dhaka' },
    { 'id': 2, 'first_name': 'A2', 'last_name': 'S2', 'city': 'Dhaka' },
    { 'id': 3, 'first_name': 'A3', 'last_name': 'S3', 'city': 'Dhaka' },
    { 'id': 4, 'first_name': 'A4', 'last_name': 'S4', 'city': 'Dhaka' },
    { 'id': 5, 'first_name': 'A5', 'last_name': 'S5', 'city': 'Dhaka' },
    { 'id': 6, 'first_name': 'A6', 'last_name': 'S6', 'city': 'Dhaka' },
    { 'id': 7, 'first_name': 'A7', 'last_name': 'S7', 'city': 'Dhaka' },
    { 'id': 8, 'first_name': 'A8', 'last_name': 'S8', 'city': 'Dhaka' },
    { 'id': 9, 'first_name': 'A9', 'last_name': 'S9', 'city': 'Dhaka' },
    { 'id': 10, 'first_name': 'A10', 'last_name': 'S10', 'city': 'Dhaka' },
    { 'id': 11, 'first_name': 'A11', 'last_name': 'S11', 'city': 'Dhaka' },
    { 'id': 12, 'first_name': 'A12', 'last_name': 'S12', 'city': 'Dhaka' },
    { 'id': 13, 'first_name': 'A13', 'last_name': 'S13', 'city': 'Dhaka' },
    { 'id': 1, 'first_name': 'A1', 'last_name': 'S1', 'city': 'Dhaka' },
    { 'id': 2, 'first_name': 'A2', 'last_name': 'S2', 'city': 'Dhaka' },
    { 'id': 3, 'first_name': 'A3', 'last_name': 'S3', 'city': 'Dhaka' },
    { 'id': 4, 'first_name': 'A4', 'last_name': 'S4', 'city': 'Dhaka' },
    { 'id': 5, 'first_name': 'A5', 'last_name': 'S5', 'city': 'Dhaka' },
    { 'id': 6, 'first_name': 'A6', 'last_name': 'S6', 'city': 'Dhaka' },
    { 'id': 7, 'first_name': 'A7', 'last_name': 'S7', 'city': 'Dhaka' },
    { 'id': 8, 'first_name': 'A8', 'last_name': 'S8', 'city': 'Dhaka' },
    { 'id': 9, 'first_name': 'A9', 'last_name': 'S9', 'city': 'Dhaka' },
    { 'id': 10, 'first_name': 'A10', 'last_name': 'S10', 'city': 'Dhaka' },
    { 'id': 11, 'first_name': 'A11', 'last_name': 'S11', 'city': 'Dhaka' },
    { 'id': 12, 'first_name': 'A12', 'last_name': 'S12', 'city': 'Dhaka' },
    { 'id': 13, 'first_name': 'A13', 'last_name': 'S13', 'city': 'Dhaka' },
    ];

    const [data, setData] = useState([])
    const [loading, setLoading] = useState(true);

    const [currentPage, setCurrentPage] = useState(1);
    const [recordsPerPage] = useState(3);

    useEffect(() => {
        // axios.get('MOCK_DATA.json')
        //     .then(res => {
        //             setData(res.data);
        //             setLoading(false);
        //         })
        //         .catch(() => {
        //             alert('There was an error while retrieving the data')
        //         })
        setData(users);
        setLoading(false);
    }, [])

    const indexOfLastRecord = currentPage * recordsPerPage;
    const indexOfFirstRecord = indexOfLastRecord - recordsPerPage;
    const currentRecords = data.slice(indexOfFirstRecord, indexOfLastRecord);
    const nPages = Math.ceil(data.length / recordsPerPage)

    return (
        <div>
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-md-5">
                        <div className='container mt-5'>
                            <h4 style={styles.center_align}> All users with fullname - {name} </h4>
                            <br />
                            <Records data={currentRecords} />
                            <Pagination
                                nPages={nPages}
                                currentPage={currentPage}
                                setCurrentPage={setCurrentPage}
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
