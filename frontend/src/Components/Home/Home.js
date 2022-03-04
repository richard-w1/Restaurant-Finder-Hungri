import React from 'react';
import {Link} from 'react-router-dom';
import {Component} from 'react';
import SearchForm from '../SearchForm/SearchForm';

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
        return(
            <div class = "input" >
                    <h1>Home</h1>
                            <Link to="/SearchForm">Search Restaurants</Link>
                            <Link to="/CreateGroup">Create an invite</Link>
                            <Link to="/ViewGroup">See your existing events</Link>

            </div>
        );
    }

}
export default Home;