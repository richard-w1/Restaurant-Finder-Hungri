import React from 'react';
import { Component, useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { addToken, addUser } from '../../Redux/actionCreators'
import { baseUrl } from '../../Shared/baseUrl'
import axios from 'axios'
import SearchResults from '../SearchResults/SearchResults';
import '../../style.css'
import Card from '../UI/Card'
import classes from './SearchForm.module.css'
import Restaurants from '../Restaurants/Restaurants';
// import RestaurantList from '../RestaurantList/RestaurantList';


class SearchForm extends Component {

    constructor(props) {
        super(props);
        this.displaySearch = true;
        this.state = {
            location: '',
            restaurants: ''
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

        const restaurantNames = this.state.restaurants.map((d) =>
        <li className={classes.item}>
        <div className={classes.image}>
            <img src="/images/ali-inay-y3aP9oo9Pjc-upslash.jpg" alt="title" />
        </div>
        <div className={classes.content}>
            <h3>TITLE: {d.name}</h3>
            <p>DESCRIPTION: {d.discription}</p>
            <p>HOURS</p>
            <p>BREAKFAST</p>
            <p>OPEN</p>
        </div>
        <div className={classes.action}>
            <button>
                Like
            </button>
        </div>
        </li>

        );
        var restaurant = "<ul>";
        this.state.restaurants.map((d) =>{

        restaurant += "<div>";
        restaurant += "<img src='/images/" + d.restaurantId + ".jpg' alt = 'title' height = '250px' width = 'auto'/>";
        restaurant += "<h3>" + d.name + "</h3>";
        restaurant += "<p>" + d.type + "</p>" 
        restaurant += "<p>" + d.zipCode + " " + d.city + "</p>"
        restaurant += "<p>" + d.description + "</p>";
        restaurant += "<p>" + "Hours: " + d.hoursOfOperation + "</p>"
        restaurant += "<p>" + d.open + "</p>" 
        restaurant += "<p>" + "Phone: " + d.phoneNumber + "</p>" 
        restaurant += "<p>" + "Rating: " + d.rating + "</p>" 


        restaurant += "<hr>";
        });
        restaurant += "</ul>";
        document.getElementById('results').innerHTML = restaurant
    }


    handleInputChange = (event) => {
        event.preventDefault()
        this.setState({
            [event.target.name]: event.target.value
        })
    }


    render() {
        if (this.displaySearch) {
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
                    <div id="results">

                    </div>
                </div>

            );
        }
        else {

            console.log(this.state.restaurants)

            return (
                <div>
                    <div class="input">
                        <h1>Search Restaurants</h1>


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



                        <div id = 'results'>
                            <div className={classes.restaurantCard}>
                                <Restaurants />
                            </div>
                        </div>
                        <div>
                            <Link to="/SearchForm">
                                <button renderAs="button">
                                    <span>Back to Search</span>
                                </button>
                            </Link>
                        </div>

                    </div>
                </div>
            )
        }

    }
}

export default SearchForm;