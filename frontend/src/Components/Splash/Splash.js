import React from 'react';
import { Component, useState} from 'react'
import {Link} from 'react-router-dom'
import {connect} from 'react-redux'
import {withRouter} from 'react-router-dom'
import {addToken, addUser} from '../../Redux/actionCreators'
import {baseUrl} from '../../Shared/baseUrl'
import axios from 'axios'
import './Splash.css'

class Splash extends Component {
    constructor(props){
        super(props);
    }

    render() {
        return (
            <div>
                <body>
                    <h2 id="preheading">Welcome to</h2>
                    <h1 id = "heading">Hungri.</h1>
                    <h3 id="strapline">Eat the way you want.</h3>
                    <h3 id="strapline">Eat the way they want.</h3>
                    <h3 id="strapline">Eat the way everyone wants.</h3>

                    <span><a class="button" href="/Login" >Login</a></span>
                    
                    <span><a class="button" href="/Register" >Register</a></span>
                </body>
            </div>
        )

    }

}

export default Splash;