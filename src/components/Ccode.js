import React from 'react'
import PropTypes from 'prop-types'

const Ccode = ({sample}) => {
    return (
        <pre>
        <code >
            {sample}
        </code>
        </pre>
    )
}

Ccode.propTypes = {
    sample:PropTypes.string
}

Ccode.defaultsProps = {
    sample:''
}

export default Ccode
 