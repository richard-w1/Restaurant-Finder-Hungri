import React from 'react';
import { Component, useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { addToken, addUser } from '../../Redux/actionCreators'
import { baseUrl } from '../../Shared/baseUrl'
import axios from 'axios'
import '../../style.css'
import Card from '../UI/Card'
import Restaurant from '../Restaurant/Restaurant.js'

class SeeResults extends Component{

    constructor(props){
        super(props);
    }

}

export default SeeResults