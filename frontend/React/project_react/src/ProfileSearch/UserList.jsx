import React, { useState, useEffect } from 'react';
import Records from './Records';
import Pagination from './Pagination';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import urlPaths from '../urlPaths';

import { useNavigate } from "react-router-dom";

const styles = {
    center_align: {
        display: 'flex',
        justifyContent: 'center',
    }
}

export default function UserList({ name, getUserIdFromUserList }) {
    const navigate = useNavigate();
    const [data, setData] = useState([])
    const [loading, setLoading] = useState(true);

    const [currentPage, setCurrentPage] = useState(1);
    const [recordsPerPage] = useState(3);

    useEffect(() => {
        AuthService.setAxiosAuthHeader();
        // localhost:8080/api/users/findByFullname?name=Go
        axios.get("/api/users/findByFullname", {
                params: {
                    name: String(name)
                }
            })
            .then(res => {
                setData(res.data);
                setLoading(false);
                console.log(data);
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
            })
    }, [name]);

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
                            <h4 style={styles.center_align}> User's name with - {name} </h4>
                            <br />
                            <Records data={currentRecords} getUserIdFromRecords={getUserIdFromUserList}/>
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
