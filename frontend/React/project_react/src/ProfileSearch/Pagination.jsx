import React from 'react'

const styles = {
    list_in_one_row: {
        display: 'inline-block'
    },
    center_align: {
        display: 'flex',
        justifyContent: 'center',
    }
}

const Pagination = ({ nPages, currentPage, setCurrentPage }) => {

    const pageNumbers = [...Array(nPages + 1).keys()].slice(1)

    const nextPage = () => {
        if (currentPage !== nPages) setCurrentPage(currentPage + 1)
    }
    const prevPage = () => {
        if (currentPage !== 1) setCurrentPage(currentPage - 1)
    }
    return (
        <div style={styles.center_align}>
            <nav>
                <ul className='pagination justify-content-center'>
                    <li className="page-item" style={styles.list_in_one_row}>
                        <a className="page-link"
                            onClick={prevPage}
                            href='#'>
                            &nbsp; Previous &nbsp;
                        </a>
                    </li>
                    {pageNumbers.map(pgNumber => (
                        <li key={pgNumber}
                            style={styles.list_in_one_row}
                            className={`page-item ${currentPage == pgNumber ? 'active' : ''} `} >

                            <a onClick={() => setCurrentPage(pgNumber)}
                                className='page-link'
                                href='#'>

                                &nbsp; {pgNumber} &nbsp;
                            </a>
                        </li>
                    ))}
                    <li className="page-item"
                        style={styles.list_in_one_row}>
                        <a className="page-link"
                            onClick={nextPage}
                            href='#'>
                            &nbsp; Next
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    )
}

export default Pagination
