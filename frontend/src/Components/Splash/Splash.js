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

            <div id = "body">
                
                    <h2 id="preheading">Welcome to</h2>
                    <h1 id = "heading">Hungri.</h1>
                    <h5 id="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</h5>
                    <h3 id="strapline">Eat the way that <span>you</span> want.</h3>
                    <h3 id="strapline">Eat the way that <span>they</span> want.</h3>
                    <h3 id="strapline">Eat the way that <span>everyone</span> wants.</h3>
                    <p></p>
                    <div>
                    <p></p>
                    
                    <span><a class="button" href="/Login" >Login</a></span>
                    <span> &nbsp; &nbsp; </span>
                    <span><a class="button" href="/Register" >Register</a></span>
                    </div>

                
            </div>

        )

    }

}

export default Splash;