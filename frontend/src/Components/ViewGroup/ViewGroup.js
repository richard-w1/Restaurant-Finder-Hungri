import React from 'react';
import { Component, useState} from 'react'
import {Link} from 'react-router-dom'
import {connect} from 'react-redux'
import {withRouter} from 'react-router-dom'
import {addToken, addUser} from '../../Redux/actionCreators'
import {baseUrl} from '../../Shared/baseUrl'
import axios from 'axios'
import classes from './ViewGroup.module.css'
import '../../style.css'

class ViewGroup extends Component {

    constructor(props) {
        super(props);
        this.displaySearch = true;
        this.handleInputChange = this.handleInputChange.bind(this);
        this.state = {
            groups: []
        }
        this.handleSearch();
    };

    handleSearch = async () => {
        this.displaySearch = false;
        var response = axios.get(baseUrl + "/find_groups/" + encodeURI(this.props.token));
        this.setState({
            groups:  (await response).data.parties
        })
    }


    handleInputChange = (event) => {
        event.preventDefault()
    }


    render(){
        return(
            <div class = 'input'>
                <h1 class = 'header'>Your Invitations</h1>
                
                <div id="group_list">
                    {
                        this.state.groups.map(
                            (ele, index)=>{
                                console.log(ele.name);
                                return <div class = 'listofgroup'>
                                <p><h3>{ele.name}</h3></p>
                                <p>{ele.location}</p>
                                <p>Voting Ends: {ele.endDate}</p>
                                <p>Invite Link: {ele.inviteLink}</p>
                                <p><Link to={{pathname:'/Group', state: ele.id}}><button renderAs='button'><span>View Invitation</span></button></Link></p>
                                <hr></hr>
                                </div>
                                
                    
                            }
                        )
                    }
                </div>
                <Link to="/CreateGroup">
                    <button renderAs="button">
                        <span>Create an Invitation</span>
                    </button>
                </Link>
            </div>
        )
    }

}

export default ViewGroup;