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
    };

    handleSearch = async () => {
        this.displaySearch = false;
        var response = axios.get(baseUrl + "/find_groups/" + encodeURI(this.props.token));
        this.state = {
            groups: (await response).data.parties
        }
        document.getElementById("group_list").innerHTML = this.generateList()
        console.log(this.state);
    }


    handleInputChange = (event) => {
        event.preventDefault()
        this.setState({
            [event.target.name]: event.target.value
        })
    }
    generateList(){
        var groupInfo = "No invitations found, invite some friends!";
        if (this.state.groups.length > 0){
            groupInfo = "<ul>";
            for (var i = 0; i < this.state.groups.length; i++){
                groupInfo += "<div class = 'listofgroup'>";
                groupInfo += "<p><h3>" + this.state.groups[i].name + "</h3></p>"
                groupInfo += "<p>" + this.state.groups[i].location + "</p>"
                groupInfo += "<p>Voting Ends: " + this.state.groups[i].endDate + "</p>"
                groupInfo += "<p>Invite Link: " + this.state.groups[i].inviteLink + "</p>"
                groupInfo += "<p><Link to='/Group/" + this.state.groups[i].id + "'><button renderAs='button'><span>View Invitation</span></button></Link><p>"
                groupInfo += "</div>";
                groupInfo += "<hr>"
            }
            groupInfo += "</ul>";
        }
        return groupInfo;
    }

    render(){
        this.handleSearch();
        const groupInfo = this.generateList();
        return(
            <div class = 'input'>
                <h1 class = 'header'>Your Invitations</h1>
                <div id="group_list">
                {groupInfo} 
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