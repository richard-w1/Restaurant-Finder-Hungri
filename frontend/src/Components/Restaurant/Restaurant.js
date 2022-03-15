import React, { Component } from 'react';
import { Redirect, Link } from 'react-router-dom';
import { addToken, deleteUser } from '../../Redux/actionCreators';
import { baseUrl } from '../../Shared/baseUrl'
import axios from 'axios'

class Restaurant extends Component {

    constructor(props) {
        super(props);
        this.state = 
            {
                restaurant: this.props.restaurant
            }
        ;

    }

    like = async () => {

        const data = {

                restaurantId: this.props.restaurant.restaurantId,
                userVote: 1

        }
        console.log(data)
        var votes = axios.post(baseUrl + "/party/" + this.props.restaurant.groupId + "/vote", data);
        console.log(votes);
        this.setnewvote((await votes).data.totalVote);

    }
    setnewvote(newTotal){
        var restaurant = this.state.restaurant;
        restaurant.totalVote = newTotal;
        this.setState(
            {
                restaurant: restaurant
            }
        )
    }

    dislike = async () => {

        const data = {

                restaurantId: this.props.restaurant.restaurantId,
                userVote: -1
        }
            var votes = axios.post(baseUrl + "/party/" + this.props.restaurant.groupId + "/vote", data);
            console.log(votes);
    }

    render(){
        console.log(this.props);
        var d = this.state.restaurant;
        var rating = "";
        for (var i = 0; i < d.rating; i++) {
           rating += "<img src='/star.png' alt = 'rating' height = '20px' width = '20px'>";
        };
        var image = "/images/" + d.restaurantId + ".jpg";
        return(
            <div class = 'listofrest'>
            <img class = 'restimage' src={image} alt = 'rest image'/>

            <div>
            <ul>
            <h3>{d.name}</h3>

            <p>
            {d.rating >= 1 ? <img src='/star.png' alt = 'rating' height = '20px' width = '20px'></img>: ""}
            {d.rating >= 2 ? <img src='/star.png' alt = 'rating' height = '20px' width = '20px'></img>: ""}
            {d.rating >= 3 ? <img src='/star.png' alt = 'rating' height = '20px' width = '20px'></img>: ""}
            {d.rating >= 4 ? <img src='/star.png' alt = 'rating' height = '20px' width = '20px'></img>: ""}
            {d.rating >= 5 ? <img src='/star.png' alt = 'rating' height = '20px' width = '20px'></img>: ""}
            
            </p>
            <p><b>{d.type}</b></p>
            <p>{d.city}, {d.zipCode}</p>
            <p>{d.description}</p>
            <p><b>{d.open ? "Open" : "Closed"}</b></p> 
            <p><b>Hours:</b>{d.hoursOfOperation}</p>
            <p><b>Phone:</b>{d.phoneNumber}</p>
            <p>{d.vote != null ? "Votes: " + d.vote : ""}</p>
            </ul>
            </div>
            <div>
                <p><button type="submit" onClick={this.like}>Like</button></p>
                
                <p><button type="submit" onClick={this.dislike}>Dislike</button></p>
            </div>
            </div>
        )
    }


}
export default Restaurant;