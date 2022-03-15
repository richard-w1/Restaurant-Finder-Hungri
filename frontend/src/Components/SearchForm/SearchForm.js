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
import './SearchForm.css'
import Restaurant from '../Restaurant/Restaurant.js'

class SearchForm extends Component {

    constructor(props) {
        super(props);
        this.displaySearch = true;
        this.state = {
            location: '',
            restaurants: []
        }
        this.handleInputChange = this.handleInputChange.bind(this);
    };

    handleSearch = async () => {
        this.displaySearch = false;
        const data = { location: this.state.location }
        console.log(data);
        var response = axios.get(baseUrl + "/find_restaurants/" + data.location)
        console.log(response);
        this.setState({
            location: this.state.location,
            restaurants: (await response).data.restaurants
        })

        var restaurant = "<ul>";
        this.state.restaurants.map((d) =>{

        restaurant += "<div class = 'listofrest'>";
        restaurant += "<img class = 'restimage' src='/images/" + d.restaurantId + ".jpg' alt = 'title'/>";

        restaurant += "<div>";
        restaurant += "<ul>";
        restaurant += "<h3>" + d.name + "</h3>";

        restaurant += "<p>"
        for (var i = 0; i < d.rating; i++) {
            restaurant += "<img src='/star.png' alt = 'rating' height = '20px' width = '20px'>"
        }
        restaurant += "</p>";
        restaurant += "<p><b>" + d.type + "</b></p>";
        restaurant += "<p>" + d.city + " " + d.zipCode + "</p>";
        restaurant += "<p>" + d.description + "</p>";
        var isOpen = '';
        if(d.open) {
            isOpen = "Open"
        } else {
            isOpen = 'Closed'
        };
        restaurant += "<p><b>" + isOpen + "</b></p>" 
        restaurant += "<p>" + "<b>Hours:</b> " + d.hoursOfOperation + "</p>";
        restaurant += "<p>" + "<b>Phone:</b> " + d.phoneNumber + "</p>";
        restaurant += "</ul>";
        restaurant += "</div>";
        
        restaurant += "</div>";

        restaurant += "<hr>";
        });
        restaurant += "</ul>";
        //document.getElementById('results').innerHTML = restaurant
    }


    handleInputChange = (event) => {
        event.preventDefault()
        this.setState({
            location: event.target.value,
            restaurants: this.state.restaurants
        })
    }


    render() {
        

            return (
                <div class="input">
                    <h1 class='header' >Search Restaurants</h1>
                    <label class="sr-only">Enter City or Zip Code</label>
                    <input
                        type='text'
                        id="location"
                        name="location"
                        class="form-control"
                        v-model="user.username"
                        placeholder='Enter City or Zip Code'
                        onChange={this.handleInputChange}
                        required
                    />
                    <button type='submit' onClick={this.handleSearch}>Search</button>
                        {
                        console.log("# of Res: " + this.state.restaurants)}
                    {
                        this.state.restaurants.map(
                            (d, index)=>{
                                return <Restaurant restaurant = {d}/>
                            }                        
                        )
                    }
                </div>

            );

    }
}

export default SearchForm;