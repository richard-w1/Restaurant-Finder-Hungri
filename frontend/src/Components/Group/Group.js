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
import './Group.css'
class Group extends Component{

    constructor(props){
        super(props);
        console.log("Group: ");
        console.log(this.props.location.invite.name);
        this.group = this.props.location.invite;
        this.state = {
            party: [],
            restaurants:  []
        };
        console.log(this.state);
        this.loadparty();
        this.loadrestaurants();
        //this.loadvotes();
        console.log(this.state);

    }

    loadparty = async () => {
        var response = axios.get(baseUrl + "/party/" + this.group.id);
        console.log(response);
        this.setState({
            party: (await response).data.party,
            restaurants:  this.state.restaurants,
            votes:  this.state.votes
        })
    }
    loadrestaurants = async () => {
        var restaurant = axios.get(baseUrl + "/party/" + this.group.id + "/restaurants");
        console.log(restaurant);
        this.setState({
            party: this.state.party,
            restaurants:  (await restaurant).data.restaurants,
            votes:  this.state.votes
        });
        //this.matchvotes(await restaurant, this.state.votes);
    }
    loadvotes = async () => {
        var votes = axios.get(baseUrl + "/party/" + this.group.id + "/view-finalists");
        console.log(votes);
        this.setState({
            party: this.state.party,
            restaurants: this.state.restaurants,
            votes:  (await votes).data.restaurantGroup
        });
        this.matchvotes(this.state.restaurants, await votes);
    }
    matchvotes(restaurant, votes){
        var res = this.state.restaurants;
        var votes = this.state.votes;
        for (var i = 0; i < res.length; i++){
            res[i].groupId = this.group.id;
            for (var j = 0; j < votes.length; j++){
                if(res[i].restaurantid == votes[j].restaurantid && res[i].restaurantid != null &&  res[i].restaurantid != null){
                    console.log("log");
                    console.log(res[i].restaurantid);
                    console.log(votes[j].restaurantid);
                    console.log(votes[j].totalVotes);
                    res[i].vote = votes[j].totalVotes;
                    break;
                }
            }
        }
        this.setState({
            party: this.state.party,
            restaurants: res,
            votes:  this.state.votes
        });
        console.log(this.state);
    }
    render() {
        return(
            <div class = "input">
                <h3>{this.group.name}</h3>
                    {
                    this.state.restaurants != null ?
                        this.state.restaurants.map(
                            (d, index)=>{
                                d.groupId = this.group.id;
                                return <Restaurant restaurant = {d}/>
                            }                        
                        ) : ""

                    }
            </div>
        )
    }

}

export default Group;