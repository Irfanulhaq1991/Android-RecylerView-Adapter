import React from 'react'
import PropTypes from 'prop-types'


const Content = ({descp}) => {
    return (
      <p>{descp}</p>
    )
}

Content.propTypes = {
    descp:PropTypes.string
}
Content.defaultProp = {
    descp :""
}

export default Content
