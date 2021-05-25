import React from 'react'
import PropTypes from 'prop-types'


function Heading({title}) {

    return (
        <div style ={{marginBottom:'10px',marginTop:"20px",fontSize:'18px', fontWeight:"bold"}}>
            <p>{title}</p>      
        </div>
    )
}


Heading.propTypes = {
    title:PropTypes.string
}
Heading.defaultsProps = {
    title:""
}


export default Heading

