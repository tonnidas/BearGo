import { Document, Page, Text, Image, StyleSheet, Font, PDFViewer } from "@react-pdf/renderer";
import { useEffect } from "react";

const Invoice = () => {

    useEffect(() => {
        console.log(window["myVar"]);
    }, []);

    return (
        <>
            <PDFViewer className='navbar navbar-expand-lg navbar-light fixed-top'
                style={styles.PDFViewer_Body}>
                <Document>
                    <Page style={styles.body}>
                        <Text style={styles.title}>INVOICE</Text>
                        <Text style={styles.header} fixed>
                            ~ BearGo ~
                        </Text>


                        {/* <Text style={styles.author}>Miguel de Cervantes</Text> */}

                        <Text style={styles.text}>Invoice No: #{window["post"].contract.id}</Text>
                        <Text style={styles.text}>Description: {window["post"].contract.description}</Text>
                        <Text style={styles.text}>Delivery Status: {window["post"].contract.deliveryStatus}</Text>
                        <Text style={styles.text}>Start Date: {window["post"].contract.contractStartDate}</Text>
                        <Text style={styles.text}>End Date: {window["post"].contract.contractEndDate}</Text>
                        <Text style={styles.text}>Traveler: {window["post"].contract.traveler || "Nobody is selected yet."}</Text>
                        <Text style={styles.text}>Cost: {window["cost"]}</Text>
                        <Text style={styles.header}> - Source Location - </Text>
                        <Text style={styles.text}>Street: {window["post"].source.street}</Text>
                        <Text style={styles.text}>City: {window["post"].source.city}</Text>
                        <Text style={styles.text}>State: {window["post"].source.state}</Text>
                        <Text style={styles.text}>Zip: {window["post"].source.zip}</Text>
                        <Text style={styles.header}> - Destination Location - </Text>
                        <Text style={styles.text}>Street: {window["post"].destination.street}</Text>
                        <Text style={styles.text}>City: {window["post"].destination.city}</Text>
                        <Text style={styles.text}>State: {window["post"].destination.state}</Text>
                        <Text style={styles.text}>Zip: {window["post"].destination.zip}</Text>


                        <Text style={styles.pageNumber} render={({ pageNumber, totalPages }) => (
                            `${pageNumber} / ${totalPages}`
                        )} fixed />
                    </Page>
                </Document>
            </PDFViewer>
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

export default Invoice
