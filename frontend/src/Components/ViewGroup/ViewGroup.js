import React from 'react';
import { Component, useState} from 'react'
import {Link} from 'react-router-dom'
import {connect} from 'react-redux'
import {withRouter} from 'react-router-dom'
import {addToken, addUser} from '../../Redux/actionCreators'
import {baseUrl} from '../../Shared/baseUrl'
import axios from 'axios'

class SearchForm extends Component {
    constructor(props) {
        super(props);
    }
    render(){
        return(
            <div>
                <h1>Your invitations</h1>
            </div>
        )
    }
    //TODO: user needs to see list of groups they are the host of and access them

}

export default SearchForm;