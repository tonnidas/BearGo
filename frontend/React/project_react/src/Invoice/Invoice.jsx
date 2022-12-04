import { Document, Page, Text, Image, StyleSheet, Font, PDFViewer } from "@react-pdf/renderer";
import { useState } from "react";
import AuthService from '../Service/AuthService';
import { useNavigate, useSearchParams } from "react-router-dom";
import React from 'react';
import axios from 'axios';

export default function Invoice() {

    const [post, setPost] = useState({});
    const [loaded, setLoaded] = useState(false);

    function updatePost(key, value) {
        setPost(values => ({ ...values, [key]: value }));
    }

    // console.log(document.getElementById('pid').value);

    const [searchParams] = useSearchParams();
    const postId = searchParams.get("pid");
    console.log("Post ID - " + postId);

    React.useEffect(() => {
        console.log("Post ID from use effect - " + postId);
        AuthService.setAxiosAuthHeader();
        axios.get("/api/productPosts/" + postId)
            .then(res => {
                // console.log(res.data);
                setPost(res.data);
                updatePost('id', res.data.id);
                updatePost('contractId', res.data.contract.id);
                updatePost('description', res.data.contract.description);
                updatePost('deliveryStatus', res.data.contract.deliveryStatus);
                updatePost('contractStartDate', res.data.contract.contractStartDate);
                updatePost('contractEndDate', res.data.contract.contractEndDate);
                if (res.data.contract.traveler == null) {
                    updatePost('traveler', "No traveler is selected yet.");
                } else {
                    updatePost('traveler', res.data.contract.traveler.username);
                }
                updatePost('cost', res.data.contract.cost);
                updatePost('sourceStreet', res.data.source.street);
                updatePost('sourceCity', res.data.source.city);
                updatePost('sourceState', res.data.source.state);
                updatePost('sourceZip', res.data.source.zip);
                updatePost('destinationStreet', res.data.destination.street);
                updatePost('destinationCity', res.data.destination.city);
                updatePost('destinationState', res.data.destination.state);
                updatePost('destinationZip', res.data.destination.zip);
                setLoaded(true);
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                } else {
                    alert(err.response.data.message)
                }
            });
    }, []);

    return (
        <>
            {
                (loaded == true)
                &&
                <PDFViewer className='navbar navbar-expand-lg navbar-light fixed-top'
                    style={styles.PDFViewer_Body}>
                    <Document>
                        <Page style={styles.body}>
                            <Text style={styles.title}>INVOICE</Text>
                            <Text style={styles.header} fixed>
                                ~ BearGo ~
                            </Text>

                            {/* <Text style={styles.author}>Miguel de Cervantes</Text> */}

                            <Text style={styles.text}>Invoice No: #{post.id}</Text>
                            <Text style={styles.text}>Description: {post.description}</Text>
                            <Text style={styles.text}>Delivery Status: {post.deliveryStatus}</Text>
                            <Text style={styles.text}>Start Date: {post.contractStartDate}</Text>
                            <Text style={styles.text}>End Date: {post.contractEndDate}</Text>
                            <Text style={styles.text}>Traveler: {post.traveler || "Okay"}</Text>
                            <Text style={styles.text}>Cost: {post.cost}</Text>
                            <Text style={styles.header}> - Source Location - </Text>
                            <Text style={styles.text}>Street: {post.sourceStreet}</Text>
                            <Text style={styles.text}>City: {post.sourceCity}</Text>
                            <Text style={styles.text}>State: {post.sourceState}</Text>
                            <Text style={styles.text}>Zip: {post.sourceZip}</Text>
                            <Text style={styles.header}> - Destination Location - </Text>
                            <Text style={styles.text}>Street: {post.destinationStreet}</Text>
                            <Text style={styles.text}>City: {post.destinationCity}</Text>
                            <Text style={styles.text}>State: {post.destinationState}</Text>
                            <Text style={styles.text}>Zip: {post.destinationZip}</Text>

                            <Text style={styles.pageNumber} render={({ pageNumber, totalPages }) => (
                                `${pageNumber} / ${totalPages}`
                            )} fixed />
                        </Page>
                    </Document>
                </PDFViewer>
            }
        </>
    );
}

Font.register({
    family: 'Oswald',
    src: 'https://fonts.gstatic.com/s/oswald/v13/Y_TKV6o8WovbUd3m_X9aAA.ttf'
});

const styles = StyleSheet.create({
    body: {
        paddingTop: 35,
        paddingBottom: 65,
        paddingHorizontal: 35,
    },
    title: {
        fontSize: 24,
        textAlign: 'center',
        fontFamily: 'Oswald'
    },
    author: {
        fontSize: 12,
        textAlign: 'center',
        marginBottom: 40,
    },
    subtitle: {
        fontSize: 18,
        margin: 12,
        fontFamily: 'Oswald'
    },
    text: {
        margin: 12,
        fontSize: 14,
        textAlign: 'justify',
        fontFamily: 'Times-Roman'
    },
    image: {
        marginVertical: 15,
        marginHorizontal: 100,
    },
    header: {
        fontSize: 12,
        marginBottom: 20,
        textAlign: 'center',
        color: 'grey',
    },
    pageNumber: {
        position: 'absolute',
        fontSize: 12,
        bottom: 30,
        left: 0,
        right: 0,
        textAlign: 'center',
        color: 'grey',
    },
    PDFViewer_Body: {
        height: '100%',
        position: 'absolute',
        left: 0,
        width: '100%',
        overflow: 'hidden',
    }
});

// export default Invoice
