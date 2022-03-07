import React from 'react';
import { Component, useState} from 'react'
import {Link} from 'react-router-dom'
import {connect} from 'react-redux'
import {withRouter} from 'react-router-dom'
import {addToken, addUser} from '../../Redux/actionCreators'
import {baseUrl} from '../../Shared/baseUrl'
import axios from 'axios'

class ViewGroup extends Component {

    constructor(props) {
        super(props);
        this.displaySearch = true;
        //this.handleSearch();
        this.handleInputChange = this.handleInputChange.bind(this);
        this.state = {
            groups: []
        }
    };

    handleSearch = async () => {
        this.displaySearch = false;
        // const data = {userId : this.state.userId}
        console.log(this.props.token);
        var response = axios.get(baseUrl + "/find_groups/" + encodeURI(this.props.token));
        console.log((await response).data.parties);
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
            //groupInfo = this.state.groups.map((d) => 
            //<li key={d.name}>{d.name}</li>
            //);
            groupInfo = "<ul>";
            for (var i = 0; i < this.state.groups.length; i++){
                groupInfo += "<li>" + this.state.groups[i].name + "</li>"
            }
            groupInfo += "</ul>";
        }
        console.log(groupInfo);
        return groupInfo;
    }

    render(){
        this.handleSearch();
        console.log(this.state);
        const groupInfo = this.generateList();
        return(
            <div>
                <h1>Your invitations</h1>
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
    //TODO: user needs to see list of groups they are the host of and access them

}

export default ViewGroup;