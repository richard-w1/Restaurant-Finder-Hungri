import React from 'react';
import {Link} from 'react-router-dom';
import {Component} from 'react';
import SearchForm from '../SearchForm/SearchForm';
import classes from './Home.module.css'
import '../../style.css'
// const Home = (props) => {
//     return(
//         <div>
//             You must be authorized to see this page.
//         </div>
//     )
// }

class Home extends Component {
    constructor(props){
        super(props);
    }

    render() {
        console.log(this.props.token);

        return(
            <div class = "input"  >
                    <h1 class = 'header'>Home</h1>
                    <div className={classes.logincard}>
                    

                    {/* <span><a class="button" href="/SearchForm" >Search Restaurants</a></span>
                    <span><a class="button" href="/CreateGroup" >Create an Invitation</a></span>
                    <span><a class="button" href="/ViewGroup" >See Your Existing Invitations</a></span> */}


                    <Link to="/SearchForm">
                        <button class="button2">
                            <span>Search Restaurants</span>
                        </button>
                    </Link>

                    <Link to="/CreateGroup">
                        <button class="button2">
                            <span>Create an Invitation</span>
                        </button>
                    </Link>
                    
                    <Link to="/ViewGroup">
                        <button class="button2">
                            <span>See Your Existing Invitations</span>
                        </button>
                    </Link>
                    
                </div>

            </div>
        );
    }

}
export default Home;